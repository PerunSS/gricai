<?php
class MMSCaller{
	function getCategories($data, &$json){
		$db = new DBManager();
		if(!isset($data['type'])){
			return '{"error":"required parameter - type"}';
		}
		$type = $data['type'];
		$function = 'get_'.$type.'_categories';
		$result = $db->callProcedure($function, array());
		$status = 403;
		$json = '{"'.$type.'_categories":[';
		$i=0;
		$count = $type.'_count';
		if($result>0){
			foreach ($result as $key => $cat){
				if($i>0){
					$json.=",";
				}
				$json.='{"category_name":"'.$cat['category_name'].'","'.$count.'_count":"'.$cat[$count].'"}';
				$i++;
			}
			$status = 200;
		}
		$json.=']}';
		return $status;
	}

	function getCategoryData($data,&$json){
		$db = new DBManager();
		if(!isset($data['type'])){
			return '{"error":"required parameter - type"}';
		}
		$type = $data['type'];
		$category = '%';
		if(isset($data['category'])){
			$category = $data['category'];
		}
		$data_count = 9;
		if(isset($data['count'])){
			$data_count = $data['count'];
		}
		$page = 1;
		if(isset($data['page'])){
			$page = $data['page'];
		}
		$function = 'get_'.$type.'_data';
		$result = $db->callProcedure($function, array(0=>$data_count,1=>$page,2=>$category));
		$status = 404;
		$resonse_data = array();
		$i = 0;
		if($result>0){
			foreach ($result as $key => $cat){
				if($type == 'picture'){
					$object = new Picture($cat['path'],$cat['description'],$cat['name'],$cat['index'],$cat['category']);
				}
				if($type == 'text'){
					$description = $cat['description'];
					$description_array = preg_split(',',$description);
					$object = new Text($cat['text'],$cat['index'],$cat['category'],$description_array);
				}
				if($type == 'sound_clip'){
					$object = new SoundClip($cat['path'],$cat['description'],$cat['name'],$cat['index'],$cat['category']);
				}
				$result_data[$i] = $object;
			}
			$status = 200;
		}
		$json = json_encode(array($type=>$response_data));
		return $status;
	}

}