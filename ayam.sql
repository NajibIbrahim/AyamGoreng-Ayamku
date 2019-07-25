-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 29, 2018 at 03:29 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ayam`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id` int(3) NOT NULL,
  `kd_brg` varchar(6) NOT NULL,
  `nm_brg` varchar(30) NOT NULL,
  `satuan` varchar(10) NOT NULL,
  `deskripsi` text NOT NULL,
  `harga` double NOT NULL,
  `stok` int(5) NOT NULL,
  `stok_min` int(5) NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id`, `kd_brg`, `nm_brg`, `satuan`, `deskripsi`, `harga`, `stok`, `stok_min`, `image`) VALUES
(1, 'B-0001', 'Ayam Guring', 'buah', 'Ini adalah ayam dengan rasa yang enak dengan rasa yang dibuat dengan rasa menggunakan barang yang enak', 6500, 100, 1, 'http://2.bp.blogspot.com/-xbZONLiJGqo/VjcaaeiTJHI/AAAAAAAAB-U/oDSWGYck27Q/s640/ayam%2Bgoreng%2Btepung.jpg'),
(2, 'B-0002', 'Roti', 'Buah', 'Roti rasanya mantab sekali', 1100, 100, 2, 'https://www.kreasisasa.com/upload/recipe/391b33d32c4222ca9bfb556153c8ba64.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(4) NOT NULL,
  `username` varchar(10) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(32) NOT NULL,
  `no_tlp` varchar(12) NOT NULL,
  `lokasi` text NOT NULL,
  `hak_akses` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`, `no_tlp`, `lokasi`, `hak_akses`) VALUES
(1, 'admin', 'paijo', '21232f297a57a5a743894a0e4a801fc3', '', '', 0),
(7, 'inoxxe', 'Januario Tristano Santoso', '123', '08122504729', '-6.971114, 110.385756', 0),
(8, 'belalkhan', 'belal@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', '', '', 0),
(9, 'abdiel', 'abdiel@gmail.com', 'inoa', '082227171545', '-6.982548, 110.409269', 0),
(10, 'asu', 'asu@gmail.com', '202cb962ac59075b964b07152d234b70', '', '', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
