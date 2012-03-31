<?php
class Map{

	var $fields;

	function __construct($fields = array()){
		$this->fields = $fields;
	}

	/**
	 *
	 * @param $field
	 */
	function getFieldsAvaliable($field,$range){
		$x = $field->x;
		$y = $field->y;
		$neighbours = array();
		$i = 0;
		foreach($this->fields as $single_field){
			if(abs($x + $y - $single_field->x - $single_field->y)<= $range){
				$neighbours[$i++] = $single_field;
			}
		}
		return $neighbours;		
		
	}
}