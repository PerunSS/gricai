<?php
class Weapon {
	
	var $d;
	var $range;
	var $cost;
	
	function __construct($d, $range, $cost){
		$this->range = $range;
		$this->d = $d;
		$this->cost = $cost;
	}
	
}