<?php
/**
 * Util class for managing database connection
 * 
 * @todo abstrat class for DB management and subclasses for particular DBMS
 * @author Author <author@domain.com>
 * @version 1.0
 */
class Manager {

	/**
	 * Database connection handler
	 * @var PDO
	 */

	private $db = NULL;
	/**
	 * String values necessary to establish DB connection
	 * host, database, username, password
	 * @var array
	 */
	private $connection_params = array();

	public function __construct() {
		$this->connection_params = ManagerIni::get_connection_params();
	}

	/**
	 * Open DB connection
	 * @throws PDOException If connection fails
	 */

	private function connect() {
		$connection_string = 'mysql:dbname='
				. $this->connection_params['database'] . ';host='
				. $this->connection_params['host'];
		$options = array(
				PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8',
				PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
		);
		try {
			$this->db = new PDO($connection_string,
					$this->connection_params['username'],
					$this->connection_params['password'], $options);
		} catch (PDOException $e) {
			throw new PDOException(
					'Database connection error: ' . $e->getMessage(),
					$e->getCode());
		}
	}

	/**
	 * Close DB connection
	 */

	private function close() {
		$this->db = NULL;
	}

	/**
	 * Function that calls stored procedure, execute it, and return result.
	 * @param string $name Name of the procedure
	 * @param array $data Optional arguments if required by the procedure
	 * @return PDOStatement $result Procedure result set
	 * @throws Exception If procedure fails to execute
	 */

	public function execute_procedure($name, $data) {
		$statement = NULL;
		$query = "call " . $name . "(";
		if (is_array($data)) {
			for ($i = 0; $i < sizeof($data); $i++) {
				$query = $query . "?";
				if ($i != sizeof($data) - 1)
					$query = $query . ",";
			}
		}
		$query .= ");";
		try {
			$this->connect();
			if ($this->db != NULL) {
				$statement = $this->db
						->prepare($query);
				foreach ($data as $key => $value) {
					if (empty($value) && $value != 0)
						$statement->bindValue($key + 1, null, PDO::PARAM_NULL);
					else
						$statement->bindValue($key + 1, $value);
				}
				$statement->execute();
			}
			$this->close();
		} catch (Exception $e) {
			throw new Exception($e->getMessage(), $e->getCode());
		}
		return $statement;
	}

	/**
	 * Function that calls stored function, execute it, and return result.
	 * @param string $name Name of the procedure
	 * @param array $data Optional arguments if required by the procedure
	 * @return Object $result Result of query
	 * @throws Exception If procedure fails to execute
	 */

	public function execute_function($name, $data) {
		$statement = NULL;
		$result = false;
		$query = "select " . $name . "(";
		if (is_array($data)) {
			for ($i = 0; $i < sizeof($data); $i++) {
				$query = $query . "?";
				if ($i != sizeof($data) - 1)
					$query = $query . ",";
			}
		}
		$query .= ") as status;";
		try {
			$this->connect();
			if ($this->db != NULL) {
				$statement = $this->db
						->prepare($query);
				foreach ($data as $key => $value) {
					if (empty($value) && $value != 0)
						$statement->bindValue($key + 1, null, PDO::PARAM_NULL);
					else
						$statement->bindValue($key + 1, $value);
				}
				$statement->execute();
				$array = $statement->fetchAll();
				$result = $array[0]["status"];
			}
			$this->close();
		} catch (Exception $e) {
			throw new Exception($e->getMessage(), $e->getCode());
		}
		return $result;
	}
}
