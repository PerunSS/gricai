<?php
class Text{
	private $text;
	private $index;
	private $category;
	private $tags;

	/**
	 * Constructor of class Text
	 * arguments should be TEXT, INDEX, CATEGORY, ARRAY OF TAGS
	 */
	function __construct(){
		$args = func_get_args();
		$size = sizeof($args);
		if($size >= 4){
			$tags = $args[3];
		}
		if($size >= 3){
			$category = $args[2];
		}
		if($size >= 2){
			$index = $args[1];
		}
		if($size >= 1){
			$text = $args[0];
		}
	}

	function toJson(){
		$data = array('text' => $text, 'index' => $index, 'category' => $category, 'tags' => $tags);
		return json_encode($data);
	}
}