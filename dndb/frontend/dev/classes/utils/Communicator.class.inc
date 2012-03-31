<?php
class Communicator{

	const PATH ="192.168.100.122:8080/";
	
	function getClasses(){
		$this->call_api("GET","classes",array(),$result);
		return $result;
	}
	
	function addHero($name, $hero){
		$data['name'] = $name;
		$data['hero'] = $hero;
		$this->call_api("GET","add_player",$data,$result);
		return $result;
	}
	
	function changeTeam($name, $team){
		$data['name'] = $name;
		$data['team'] = $team;
		$this->call_api("GET","change_team",$data,$result);
		return $result;
	}
	
	function ready($name){
		$data['name'] = $name;
		$this->call_api("GET","ready",$data,$result);
		return $result;
	}
	
	function delete(){
		$this->call_api("GET","delete_all",array(),$result);
		return $result;
	}
	
	function getList(){
		$this->call_api("GET","list_players",array(), $result);
		return $result;		
	}
	
	function getMap(){
		$this->call_api("GET","get_map",array(), $result);
		return $result;	
	}
	
	function move($x, $y, $name){
		$data['x'] = $x;
		$data['y'] = $y;
		$data['name'] = $name;
		$this->call_api("GET","do_move",$data, $result);
		return $result;	
	}
	
	function endTurn($name){
		$data['name'] = $name;
		$this->call_api("GET","end_turn",$data, $result);
		return $result;	
	}
	
	function attack($source, $target){
		$data['source'] = $source;
		$data['target'] = $target;
		$this->call_api("GET","do_attack",$data, $result);
		return $result;	
		
	}

	function call_api($http_method, $function, $input_data, &$output_data){
		// URL of function
		$url = Communicator::PATH.$function;
		// Init Curl and set parameters
		$handle = curl_init();

		curl_setopt($handle, CURLOPT_HTTPHEADER, array('Accept: application/json','Content-Type: application/x-www-form-urlencoded'));
		curl_setopt($handle, CURLOPT_RETURNTRANSFER, true);
		curl_setopt($handle, CURLOPT_SSL_VERIFYHOST, false);
		curl_setopt($handle, CURLOPT_SSL_VERIFYPEER, false);
		curl_setopt($handle, CURLOPT_USERPWD, ':');
		curl_setopt($handle, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);

		switch($http_method){
			case 'GET':
				$url = $url."?".http_build_query($input_data);
				break;

			case 'POST':
				curl_setopt($handle, CURLOPT_POST, true);
				curl_setopt($handle, CURLOPT_POSTFIELDS, http_build_query($input_data));
				break;

			case 'PUT':
				curl_setopt($handle, CURLOPT_CUSTOMREQUEST, 'PUT');
				curl_setopt($handle, CURLOPT_POSTFIELDS, http_build_query($input_data));
				break;

			case 'DELETE':
				curl_setopt($handle, CURLOPT_CUSTOMREQUEST, 'DELETE');
				break;
		}
		syslog(LOG_INFO, "calling: ".$url."\tmethod:".$http_method);
		// Set URL
		curl_setopt($handle, CURLOPT_URL, $url);

		// Call function and return
		if(($output_data = curl_exec($handle)) == FALSE
		&& curl_errno($handle))	{
			error_log("curl_exec error on function ".$function.": (".curl_errno($handle).") " .curl_error($handle));
			$http_code = 500;
		} else	{
			$http_code = curl_getinfo($handle, CURLINFO_HTTP_CODE);
		}

		syslog(LOG_INFO, "return code: ".$http_code);

		// Call function and return
		curl_close($handle);
		return $http_code;
	}

}