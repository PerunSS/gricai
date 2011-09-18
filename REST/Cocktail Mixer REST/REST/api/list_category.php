<?php
require_once '../classes/AutoloadClasses.inc';

$data = RestUtils::processRequest();
switch($data->getMethod()){
	case Method::$GET:
		$caller = new Caller();
		$response = $caller->listCategory($data->getRequestVars(),$json);
		RestUtils::sendResponse($response, $json, 'application/json');
		break;
	default:
		RestUtils::sendErrorResponse('{"error":"unsuported method"}', 403);
		break;
}