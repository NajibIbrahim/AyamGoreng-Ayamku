<?php
	$id = $_GET['id'];
	require_once('koneksi.php');

	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT * FROM barang WHERE nm_brg= '$id';";

	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);

	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"price"=>$row['harga'],
			"desc"=>$row['deskripsi'],
			"image"=>$row['image']
		));
	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>
