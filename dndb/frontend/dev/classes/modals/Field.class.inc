<?php
class Field {
	
	var $x, $y;
	var $obstacle;
	var $hero;
	
	function __construct($x, $y, $obstacle = null, $hero = null){
		$this->x = $x;
		$this->y = $y;
		$this->obstacle = $obstacle;
		$this->hero = $hero;
	}
	
}