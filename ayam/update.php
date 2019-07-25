<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		//MEndapatkan Nilai Dari Variable
		$username = $_POST['username'];
		$password = $_POST['password'];
		//import file koneksi database
		require_once('koneksi.php');

		//Membuat SQL Query
		$sql = "UPDATE user SET username = '$username', password='$password' WHERE username = '$username';";

		//Meng-update Database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data User';
		}else{
			echo 'Gagal Update Data User';
		}

		mysqli_close($con);
	}
?>
