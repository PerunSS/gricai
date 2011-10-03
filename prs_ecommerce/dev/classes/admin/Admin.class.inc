<?php
class Admin{

	private $username, $passwrod, $dbMan, $id;

	function  __construct($username, $password, $id=''){
		$this->username = $username;
		$this->passwrod = $password;
		$this->id = $id;
		$this->dbMan = new DBManager();
	}

	/**
	 * 
	 */
	function login(){
		$result = $this->dbMan->callProcedure('admin_login', array(0=>$this->username,1=>$this->passwrod));
		if($result[0]['result']>0){
			$this->id = $result[0]['result'];
			return 1;
		}
		return 0;
	}

	/**
	 * 
	 */
	function getUsers(/*$data=''*/){
//		if($data==''){
		return $this->dbMan->callProcedure('admin_get_users',array());
//		}
//		$i = 0;
//		$values[$i++] = (empty($data['a_korisnik_un_search'])?'%':$data['a_korisnik_un_search']);
//		$values[$i++] = (empty($data['a_korisnik_tip_search'])
//				?'%':$data['a_korisnik_tip_search']);
//		$values[$i++] = (empty($data['a_korisnik_aktivan_search'])?0:1);
//		$values[$i++] = (empty($data['a_korisnik_banovan_searcH'])?0:1);
//		return $this->dbMan->callProcedure('admin_search_users',$values);		
	}
	
	/**
	 * 
	 * @param unknown_type $user_id
	 * @param unknown_type $comment
	 */
	function banUser($user_id,$comment){
		$result = $this->dbMan->callProcedure('update_user_status', array(0=>$user_id,1=>'1',2=>$comment,3=>$this->username));
	}
	/**
	 * 
	 * @param $user_id
	 */
	function unbanUser($user_id){
		$result = $this->dbMan->callProcedure('update_user_status', array(0=>$user_id,1=>'0',2=>'',3=>$this->username));
	}
	/**
	 * 
	 * @param unknown_type $user_id
	 */
	function getUserDetails($user_id){
		return $this->dbMan->callProcedure('get_user_details', array(0=>$user_id));
	}
	/**
	 * 
	 */
	function getPourdzbenice(){
		return $this->dbMan->callProcedure('admin_get_porudzbenice', array());
	}
	
	/**
	 * 
	 * @param unknown_type $id
	 */
	function getPorudzbenicaDetails($id){
		return $this->dbMan->callProcedure('admin_porudzbenica_details', array(0=>$id));
	}
	
	function changePass($old_pass,$new_pass,$new_pass_rep){
		
	}

	function addProduct($product_data){
		return $this->dbMan->callProcedure('admin_add_proizvod',  array(0=>1,1=>$product_data['naziv'],
											2=>$product_data['poreklo'],3=>$product_data['opis'],4=>$product_data['tip'],
											5=>$product_data['pakovanje'],6=>$product_data['cena'],7=>$product_data['id_podkategorija']));
	}

	function updateProduct(){
		return $this->dbMan->callProcedure('', array(0=>1,1=>$product_data['naziv'],
											2=>$product_data['poreklo'],3=>$product_data['opis'],4=>$product_data['tip'],
											5=>$product_data['pakovanje'],6=>$product_data['cena'],7=>$product_data['id_podkategorija']));
	}

	function toJson(){
		$array = array("id"=>$this->id,"username"=>$this->username);
		return json_encode($array);
	}

	static function fromJson($json){
		$jsonobj = json_decode($json);
		$admin = new Admin($jsonobj->username,'',$jsonobj->id);
		return $admin;
	}
	
	function getPodkategorije(){
		return $this->dbMan->callProcedure('admin_get_podkategorije', array());
	}
	
	
}