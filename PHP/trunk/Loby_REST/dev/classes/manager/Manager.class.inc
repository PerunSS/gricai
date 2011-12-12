<?php
class Manager {

	/**
	 * @var PDO
	 */

	private $db = NULL;
	private $connection_params = array();

	public function __construct() {
		$this->connection_params = ManagerIni::get_connection_params();
	}

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

	private function close() {
		$this->db = NULL;
	}

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
					if (empty($value) && $value != 0) {
						$statement->bindValue($key + 1, null, PDO::PARAM_NULL);
					} else {
						$statement->bindValue($key + 1, $value);
					}
				}
				$statement->execute();
			}
			$this->close();
		} catch (Exception $e) {
			throw new Exception($e->getMessage(), $e->getCode());
		}
		return $statement;
	}
}
