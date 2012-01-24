<?php
class Formaters {
	const MESSAGE_FORMAT_SINGLE_ROW_OBJECT_EMPTY = 'single_row_is_empty';
	const MESSAGE_FORMAT_SINGLE_ROW_OBJECT_MULTY = 'single_row_is_multy_row';
/**
	 * Format PDOStatement object into object of desired class or into Error model.
	 * @param array $class_name
	 * @param array $data
	 * @param array $object
	 */
	final static function format_single_row_into_object($class_name, $data,
			&$object) {
		if ($data->rowCount() == 1) {
			$data->setFetchMode(PDO::FETCH_CLASS | PDO::FETCH_PROPS_LATE,
							$class_name);
			$object = $data->fetch();
			if (!is_null($object->error))
				$object = Error::get_error($object->error);
		} elseif ($data->rowCount() == 0)
			$object = Error::get_error(Formaters::MESSAGE_FORMAT_SINGLE_ROW_OBJECT_EMPTY);
		else
			$object = Error::get_error(Formaters::MESSAGE_FORMAT_SINGLE_ROW_OBJECT_MULTY);
	}
}
