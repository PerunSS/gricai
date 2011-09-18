<?php
require_once '../classes/DBManager.class.inc';
$db = new DBManager();
print_r($db->callProcedure('get_articles', array(0=>'1')));