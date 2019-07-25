<?php

 /*

 penulis: Muhammad yusuf
 website: http://www.kodingindonesia.com/

 */

	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Mendapatkan Nilai Variable
		$kode = $_POST['kode'];
		$name = $_POST['nama'];
		$jual = $_POST['jual'];
		$beli = $_POST['beli'];
		$stok = $_POST['stok'];

		//Pembuatan Syntax SQL
		$sql = "INSERT INTO barang (kd_brg,nm_brg,harga,harga_beli,stok) VALUES ('$kode','$name','$jual','$beli','$stok')";

		//Import File Koneksi database
		require_once('koneksi.php');

		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Barang';
		}else{
			echo 'Gagal Menambahkan Barang';
		}

		mysqli_close($con);
	}
?>
