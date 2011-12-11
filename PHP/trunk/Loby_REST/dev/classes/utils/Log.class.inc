<?php
class Log {
	
	const PATH = "";
	
	public final static function exception($message){
		passthru("EXCEPTION\t".Log::date()."\t".$message."\r\n >> ".Log::PATH."log_file.log 2>&1 &");
	}
	
	public final static function date(){
		return date('Y-m-d H:i:s');
	}
}