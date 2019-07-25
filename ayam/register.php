<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Mendapatkan Nilai Variable
		$username = $_POST['userid'];
		$name = $_POST['username'];
		$password = $_POST['userpassword'];
        $password = md5($password);
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO user (user_id,name,password) VALUES ('$name','$username','$password')";

		//Import File Koneksi database
		require_once('koneksi.php');

		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan User';
		}else{
			echo 'Gagal Menambahkan User';
		}

		mysqli_close($con);
	}
?>
