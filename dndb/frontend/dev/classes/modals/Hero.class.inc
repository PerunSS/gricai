<?php
class Hero{
	var $hp, $ac, $ab;
	var $costitution, $strength, $dexterity, $inteligence, $charisma, $wisdon;
	var $weapon, $armor, $magic_item;
	var $name, $level, $money;
	var $field;
	var $points;
	var $remaining_points;

	function __construct($hp, $ac, $ab, $characteristics, $items, $name, $field){
		$this->hp = $hp;
		$this->ac = $ac;
		$this->ab = $ab;
		$this->name = $name;
		$this->field = $field;
		$this->costitution = $characteristics['constitution'];
		$this->strength = $characteristics['strength'];
		$this->dexterity = $characteristics['dexterity'];
		$this->inteligence = $characteristics['inteligence'];
		$this->charisma = $characteristics['charisma'];
		$this->wisdon = $characteristics['wisdon'];
		$this->weapon = $items['weapon'];
		$this->armor = $items['armor'];
		$this->magic_item = $items['magic_item'];
	}

	function displayFieldsAvaliable($map){
		$fields = $map->getFieldsAvaliable($this->field,$this->points);
		foreach($fields as $field){
			if($field->hero != null){
				if(abs($field->x + $field->y - $this->field->x - $this->field->x)<=$this->weapon->range){
					$field->color = 3;
				}else{
					$field->color = 0;
				}
			}elseif($field->obstacle != null){
				$field->color = 0;
			}elseif(abs($field->x + $field->y - $this->field->x - $this->field->x)<=$this->points/2) {
				$field->color = 2;
			} else {
				if($this->remaining_points < $this->points ){
					$field->color = 0;
				}else{
					$field->color = 1;
				}
			}
		}
	}
	
	function doAction($field){
		if($field->hero!=null){
			$this->attack($field->hero);
		}else{
			$this->move($field);
		}
	}
	
	function move($field){
		$this->points -=abs($field->x + $field->y - $this->field->x - $this->field->x);
		$this->field = $field;
		$this->field->hero = $this;
	}
	
	function attack($hero){
		
	}
}