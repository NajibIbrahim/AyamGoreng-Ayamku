<?php
    $username = $_GET['username'];
    $password = $_GET['password'];
    $password = md5($password);
	require_once('koneksi.php');

	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT * FROM user WHERE user_id= '$username' AND password= '$password';";

	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);

	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"nama"=>$row['nm_brg'],
			"jual"=>$row['harga'],
			"beli"=>$row['harga_beli'],
			"stok"=>$row['stok']
		));
	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>