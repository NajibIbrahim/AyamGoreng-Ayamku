<?php


 //Mendapatkan Nilai ID
 $id = $_GET['id'];

 //Import File Koneksi Database
 require_once('koneksi.php');

 //Membuat SQL Query
 $sql = "DELETE FROM barang WHERE kd_brg='$id';";


 //Menghapus Nilai pada Database
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus Barang';
 }else{
 echo 'Gagal Menghapus Barang';
 }

 mysqli_close($con);
 ?>
