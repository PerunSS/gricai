<?php
/**
 *
 * Class used to comunicate with database
 * @author Sale
 *
 */
class DBManager {
	private $properties;
	private $host;
	private $user;
	private $pass;
	private $database;
	private $conn;

	/**
	 *
	 * Loads all preferences used to comunicate with database
	 */
	function __construct(){
		$this->properties = parse_ini_file("zajebavancija.ini");

		$this->host = $this->properties['host'];
		$this->user = $this->properties['user'];
		$this->pass = $this->properties['pass'];
		$this->database = $this->properties['database'];
        $this->conn();
	}

	/**
	 *
	 * Opens connection to database
	 */
	function conn(){
		$driver = "mysql:host=".$this->host.";dbname=".$this->database;
		$options = array(
		PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8',
                PDO::ATTR_PERSISTENT => true
		);
		//Log::logInfo("connecting to ".$driver);
		try{
			$this->conn = new PDO ( $driver, $this->user, $this->pass, $options);
			$this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			if(!$this->conn){
				echo "cannot connect";
				//Log::logFatal("cannot connect to ".$this->host);
			}
		}catch (PDOException $e){
			echo 'Failed to connect to database: '.$e->getMessage();
                        error_log('Failed to connect to database: '.$e->getMessage());
			//Log::logException($e->getLine()." ".$e->getMessage());
		}
	}

	/**
	 * Executes procedure in database, and returns result
	 * @param string $procedure this must be valid procedure name
	 * @param array $params this must be either array of parameters for procedure or emty string ('') if procedure has no params
	 * @return returns an array containing all of the remaining rows in the result set. The array represents each row as either an array of column values or an object with properties corresponding to each column name.
	 */
	function callProcedure($procedure, $params){
		$this->conn();

		$query = "call ".$procedure . "(";
		if (!empty($params))
		for($i = 0; $i<sizeof($params); $i++){
			$query = $query."?";
			if($i!=sizeof($params)-1)
				$query = $query.",";
		}
		$query .= ");";
		//Log::logInfo("executing query: ".$query);
		try{
			$stmt = $this->conn->prepare($query);
			if($params != ''){
				$stmt->execute($params);
			}else{
				$stmt->execute();
			}
		}catch (Exception $e){
			//Log::logException($e->getLine()." ".$e->getMessage());
		}
		if($stmt->errorCode()!='00000'){
			$err = $stmt->errorInfo();
			//Log::logErr("error: ".$err[2].", code: ".$stmt->errorCode());
		}else {
			//Log::logInfo("executed query");
		}
		return $stmt->fetchAll();
	}

	/**
	 *
	 * Closes connection to database
	 */
	function close(){
		$this->conn=null;
		//Log::logInfo("connection closed");
	}
}
?>