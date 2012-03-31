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
		$grid = array();
		$count_nb = 0;
		$count_gr=0;

		foreach($this->fields as $single_field){
			if($x+$range>=$single_field->x && $x-$range<=$single_field->x && $y+$range>=$single_field->y && $y-$range<=$single_field->y){
				$grid[$count_gr++]=$single_field;
			}
		}
		foreach($grid as $single_field){
			$movment_points=$range;
			$movment_points-= abs($x-$single_field->x);
			$movment_points-= abs($y-$single_field->y);
			if($movment_points>=0) $neighbours[$count_nb++]=$single_field;
			
		}
			

		return $neighbours;		
		
	}
}