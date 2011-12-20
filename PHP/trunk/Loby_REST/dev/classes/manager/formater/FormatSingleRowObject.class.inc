<?php
class FormatSingleRowObject
		implements Formater {

	private $class_name;

	public function __construct($class_name) {
		$this->class_name = $class_name;
	}
	/**
	 * Format PDOStatement object into Modal object
	 * @param PDOStatement $data
	 * @return Modal $result
	 */
	public function format($data){
		$data->setFetchMode(PDO::FETCH_CLASS|PDO::FETCH_PROPS_LATE, $this->class_name); 
		return $data->fetch();
	}
}
