<?php
class Constants {
	const CODE_TIMEOUT = 990;
	const CODE_MISSING_PARAM = 991;
	
	//login
	const CODE_WRONG_USERNAME = 1001;
	
	//update_profile
	const CODE_WRONG_ID = 1002;
	
	//update_password
	const CODE_WRONG_PASSWORD = 1003;
	const CODE_WRONG_CONFIRM_PASSWORD = 1004;
	
	//select users
	const CODE_NO_USERS = 1005;
	
	//create users
	const CODE_EXIST_USERNAME = 1006;
	
	//print_log
	const CODE_EMPTY_LOG = 1007;
	const CODE_NO_USER = 1008;
}