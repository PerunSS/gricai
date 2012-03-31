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
		$this->call_api("GET","add_hero",$data,$result);
	}
	
	function getList(){
		$this->call_api("GET","list",array(), $result);
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