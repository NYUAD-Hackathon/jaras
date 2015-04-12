<?php

	if(isset($_POST['country'])){

		//Saving a trip to DB
		/////////////////////
		
		$db_conn = pg_connect('host=localhost dbname=jaras user=mikhail password=LQSP$mishka14')
			or die('Could not connect: ' . pg_last_error());
		
		//file_put_contents("log.txt","pre codition value = " . $_POST['is_driver'] . "\n", FILE_APPEND);
		
		$query = "SELECT * FROM messages WHERE country = $1";
		
		pg_prepare('my_query',$query);
		$sql = pg_execute('my_query', array($_POST['country']))
			or die("Error while retreiving data.");
			
		$fetch = pg_fetch_all($sql);
		
		$response['success'] = 1;
		$response['data'] = $fetch;
		echo json_encode($response);
		
		pg_close($db_conn);	

	} else {
		
		$response['success'] = 0;
		$response['data'] = null;
		echo json_encode($response);

	}	

?>