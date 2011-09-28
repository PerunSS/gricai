<?php
class Admin{
	
	private $username, $passwrod, $dbMan, $id;
	
	function  __construct($username, $password){
		$this->username = $username;
		$this->passwrod = $password;
		$this->dbMan = new DBManager();
	}
	
	function login(){
		$result = $this->dbMan->callProcedure('admin_login', array(0=>$this->username,1=>$this->passwrod));
		if($result[0]['result']>0){
			$this->id = $result[0]['result'];
			return 1;
		}
		return 0;
	}
	
	function blockUser($user_id,$comment){
		$result = $this->dbMan->callProcedure('updateUserStatus', array(0=>$user_id,1=>'1',2=>$comment,3=>$this->username));
	}
	
	function unblockUser($user_id){
		$result = $this->dbMan->callProcedure('updateUserStatus', array(0=>$user_id,1=>'0',2=>$this->username));
	}
	
	function addProduct($product){
		$result = $this->dbMan->callProcedure('blockUser', array(0=>$user_id,1=>$comment,2=>$this->username));
	}
	
	function updateProduct($product){
		$result = $this->dbMan->callProcedure('blockUser', array(0=>$user_id,1=>$comment,2=>$this->username));
	}
	
	function toJson(){
		$array = array("id"=>$this->id,"username"=>$this->username);
		return json_encode($array);
	}
}