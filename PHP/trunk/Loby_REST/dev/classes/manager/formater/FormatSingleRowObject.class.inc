<?php
class FormatSingleRowObject
		implements Formater {

	private $class_name;

	public function __construct($class_name) {
		$this->class_name = $class_name;
	}
	/**
	 * Format PDOStatement object into Modal object. 
	 * If there is no rows, it will returns Error Modal object.
	 * @param PDOStatement $data
	 * @return Modal $result
	 */

	public function format($data) {
		if ($data->rowCount() == 1) {
			$data->setFetchMode(PDO::FETCH_CLASS | PDO::FETCH_PROPS_LATE,
							$this->class_name);
			$object = $data->fetch();
			if (!is_null($object->error))
				return Error::get_error($object->error);
			return $object;
		} elseif ($data->rowCount() == 0)
			return Error::get_error(Error::CODE_FORMAT_SINGLE_ROW_OBJECT_EMPTY);
		else
			return Error::get_error(Error::CODE_FORMAT_SINGLE_ROW_OBJECT_MULTY);
	}
}
