<?php
class DBManager {
	private $properties;
	private $host;
	private $user;
	private $pass;
	private $database;
	private $conn;


	function __construct(){
		$properties = parse_ini_file("db.ini");
		$this->host = $properties['host'];
		$this->user = $properties['user'];;
		$this->pass = $properties['pass'];;
		$this->database = $properties['database'];;
	}

	function conn(){
		$driver = "mysql:host=".$this->host.";dbname=".$this->database;
		$options = array(
		PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8',
		PDO::ATTR_PERSISTENT => true
		);
		try{
			$this->conn=new PDO( $driver, $this->user, $this->pass, $options);
			$this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		}catch (Exception $e){
			throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		}
	}
	function callProcedure($procedure, $params){
		$query = "call ".$procedure . "(";
		if (!empty($params)){
			for($i = 0; $i<sizeof($params); $i++){
				$query = $query."?";
				if($i!=sizeof($params)-1)
				$query = $query.",";
			}
		}
		$query .= ");";
		try{
			$this->conn();
			if ($this->conn!=null){
				$stmt = $this->conn->prepare($query);
				foreach ($params as $key=>$param){
					if (empty($param) && $param!=0){
						$stmt->bindValue($key+1, null, PDO::PARAM_NULL);
					}
					else {
						$stmt->bindValue($key+1, $param);
					}
				}
				$stmt->execute();
				$this->close();
			}
			else throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		}
		catch (SecurityExceptions $e){
			print_r($e->getJSON());
		}
		catch (Exception $e){
		}
		$array = $stmt->fetchAll();
		return $array;
	}

	function close(){
		$this->conn=null;
	}
}
?>