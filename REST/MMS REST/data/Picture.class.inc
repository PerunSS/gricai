<?php
class Picture{
	private $path;
	private $description;
	private $name;
	private $index;
	private $category;

	/**
	 * Constructor of class Picture
	 * arguments should be PATH, DESCRITPION, NAME, INDEX, CATEGORY
	 */
	function __construct(){
		$args = func_get_args();
		$size = sizeof($args);
		if($size == 5){
			$category = $args[4];
		}
		if($size >= 4){
			$index = $args[3];
		}
		if($size >= 3){
			$name = $args[2];
		}
		if($size >= 2){
			$desription = $args[1];
		}
		if($size >= 1){
			$path = $args[0];
		}
	}

	function toJson(){
		$data = array('path' => $path, 'description' => $description, 'name' => $name, 'index' => $index, 'category' => $category);
		return json_encode($data);
	}
}