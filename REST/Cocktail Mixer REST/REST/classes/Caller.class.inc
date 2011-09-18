<?php
class Caller{

	function getCategories($data, &$json){
		$db = new DBManager();
		$result = $db->callProcedure('list_cat', array());
		$status = 403;
		$json = '{"categories":[';
		$i=0;
		if($result>0){
			foreach ($result as $key => $cat){
				if($i>0){
					$json.=",";
				}
				$json.='{"category_name":"'.$cat['category_name'].'","drinks_count":"'.$cat['drinks_count'].'"}';
				$i++;
			}
			$status = 200;
		}
		$json.=']}';
	}

	function listCategory($category,&$json){
		$db = new DBManager();
		$result = $db->callProcedure('list_category',array(0=>$category));
		$status = 403;
		$json = '{"category":{"name":"'.$category.'","drinks":[';
		$i=0;
		print_r($_result);
		if($result>0){
			foreach ($result as $key => $data){
				foreach($data as $drink=>$count){
					if($i>0){
						$json.=",";
					}
					if(is_string($drink)){
						$json.='{"drink":"'.$drink.'","drinks_count":"'.$count.'"}';
						$i++;
					}
				}

			}
			$status = 200;
		}
		$json.=']}}';
	}

	function register($data,&$json){
		$username = $data['username'];
		$password = $data['password'];
		$name = $data['name'];
		$surname = $data['surname'];
		$date = $data['date_of_birth'];
		$db = new DBManager();
		$result = $db->callProcedure('register',array(0=>$username,1=>$password,2=>$name,3=>$surname,4=>$date));
		if($result[0]['result']>0){
			$json = '{"user_id":"'.$result[0]['result'].'"}';
			$status = 200;
		}else{
			$json = '{"error":"Username allready exists"}';
			$status = 406;
		}
		return $status;
	}

	function sendMessage($data,&$json){
		$from = $data['from'];
		$to = $data['to'];
		$text = $data['text'];
		$title = $data['title'];
		$db = new DBManager();
		$status = 406;

	}


	function login($data,&$json){
		$username = $data['username'];
		$password = $data['password'];

		$db = new DBManager();
		$result = $db->callProcedure('login', array(0=>$username,1=>$password));
		$status = 404;
		if($result[0]['result']>0){
			$json='{"user_id":"'.$result[0]['result'].'","username":"'.$username.'"}';
			$status = 200;
		}
		return $status;
	}

	function getArticles($data,&$json){
		$user_id = $data['user_id'];
		$page = $data['page'];
		$db = new DBManager();
		$result = $db->callProcedure('get_articles', array(0=>$user_id,1=>$page));
		$status = 403;
		$json = '{"articles":[';
		$i=0;
		$count = 0;
		if($result>0){
			foreach ($result as $key =>$article){
				if($i>0)
				$json.=",";
				$json.='{"username":"'.$article['username'].'","text":"'.$article['text'].'","time":"'.$article['time'].'"}';
				$i++;
				$count = $article['count'];
			}
			$status = 200;
		}
		$json.='],"count":"'.$count.'"}';
		return $status;
	}

	function addArticle($data,&$json){
		$user_id = $data['user_id'];
		$text = $data['text'];
		$db = new DBManager();
		$result = $db->callProcedure('add_article', array(0=>$user_id,1=>$text));
		$status = 403;
		$i=0;
		if($result>0){
			$json = '{"message":"post added"}';
			$status = 200;
		}
		return $status;
	}
}