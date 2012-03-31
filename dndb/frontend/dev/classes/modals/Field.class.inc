<?php
class Field {
	
	var $x, $y;
	var $blocking;
	var $item; //hero
	
	function __construct($x, $y, $blocking = null, $item = null){
		$this->x = $x;
		$this->y = $y;
		$this->blocking = $blocking;
		$this->item = $item;
	}
	
}