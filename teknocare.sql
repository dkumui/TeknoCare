-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2025 at 09:19 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `teknocare`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `kontak` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `nama`, `kontak`) VALUES
(1, 'Dewi Lusiantoro', '081298765432'),
(2, 'Rian Pratama', 'rian.pratama@example.com');

-- --------------------------------------------------------

--
-- Table structure for table `layanan_katalog`
--

CREATE TABLE `layanan_katalog` (
  `id_katalog` int(11) NOT NULL,
  `nama_layanan` varchar(255) NOT NULL,
  `deskripsi_layanan` text DEFAULT NULL,
  `tipe_perangkat` enum('Laptop','PC','Umum') NOT NULL DEFAULT 'Umum',
  `biaya_standar` int(11) NOT NULL DEFAULT 0,
  `aktif` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `layanan_katalog`
--

INSERT INTO `layanan_katalog` (`id_katalog`, `nama_layanan`, `deskripsi_layanan`, `tipe_perangkat`, `biaya_standar`, `aktif`) VALUES
(1, 'Instal Ulang OS Windows', 'Instalasi bersih Sistem Operasi Windows 10/11 beserta driver dasar.', 'Laptop', 150000, 1),
(2, 'Pembersihan Virus & Malware', 'Pembersihan menyeluruh dari virus, malware, dan spyware.', 'Laptop', 100000, 1),
(3, 'Ganti Keyboard Laptop', 'Penggantian keyboard laptop dengan unit baru (harga part belum termasuk).', 'Laptop', 75000, 1),
(4, 'Upgrade RAM PC', 'Pemasangan atau penambahan RAM pada PC (harga part belum termasuk).', 'PC', 50000, 1),
(5, 'Perbaikan Motherboard Laptop (Level 1)', 'Perbaikan komponen dasar pada motherboard, bukan ganti chipset.', 'Laptop', 250000, 1),
(6, 'Ganti Thermal Paste & Cleaning', 'Pembersihan heatsink dan penggantian thermal paste CPU/GPU.', 'PC', 85000, 1),
(7, 'Rakit PC Kustom', 'Jasa perakitan komponen PC dari awal sampai siap pakai.', 'PC', 300000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `layanan_service`
--

CREATE TABLE `layanan_service` (
  `id` int(11) NOT NULL,
  `jenis_layanan` varchar(50) NOT NULL,
  `id_katalog_fk` int(11) NOT NULL,
  `jenis_kerusakan` varchar(255) NOT NULL,
  `biaya_service` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `layanan_service`
--

INSERT INTO `layanan_service` (`id`, `jenis_layanan`, `id_katalog_fk`, `jenis_kerusakan`, `biaya_service`) VALUES
(1, 'Laptop', 1, 'Instal Ulang OS Windows (Laptop lemot, kena virus)', 150000),
(2, 'PC', 4, 'Upgrade RAM PC (Tambah RAM 8GB)', 50000),
(3, 'Laptop', 6, 'Ganti Thermal Paste & Cleaning', 85000),
(4, 'Laptop', 3, 'Ganti Keyboard Laptop', 75000),
(5, 'PC', 1, 'Instal Ulang OS Windows', 150000),
(6, 'Laptop', 4, 'Upgrade RAM PC', 50000),
(7, 'Laptop', 4, 'Upgrade RAM PC', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `struk`
--

CREATE TABLE `struk` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `teknisi_id` int(11) NOT NULL,
  `layanan_id` int(11) NOT NULL,
  `tanggal_service` date NOT NULL,
  `tanggal_jadi` date DEFAULT NULL,
  `status_servis` varchar(50) NOT NULL DEFAULT 'Baru Masuk',
  `catatan_teknisi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `struk`
--

INSERT INTO `struk` (`id`, `customer_id`, `teknisi_id`, `layanan_id`, `tanggal_service`, `tanggal_jadi`, `status_servis`, `catatan_teknisi`) VALUES
(1, 1, 1, 1, '2025-06-08', '2025-06-10', 'Selesai', 'Sedang pengecekan spesifikasi laptop.'),
(2, 2, 2, 2, '2025-06-09', '2025-06-09', 'Selesai', 'RAM sudah terpasang dan terdeteksi dengan baik.'),
(5, 1, 2, 5, '2025-06-10', '2025-06-13', 'Selesai', 'Menunggu Sparepart dari bandung'),
(6, 1, 2, 6, '2025-06-10', '2025-06-14', 'Selesai', ''),
(7, 1, 1, 7, '2025-06-10', '2025-06-13', 'Selesai', '');

-- --------------------------------------------------------

--
-- Table structure for table `teknisi`
--

CREATE TABLE `teknisi` (
  `id` int(11) NOT NULL,
  `nama_teknisi` varchar(100) NOT NULL,
  `spesialisasi` varchar(100) DEFAULT NULL,
  `kontak_teknisi` varchar(50) DEFAULT NULL,
  `aktif` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teknisi`
--

INSERT INTO `teknisi` (`id`, `nama_teknisi`, `spesialisasi`, `kontak_teknisi`, `aktif`) VALUES
(1, 'Agus Setiawan', 'Spesialis Laptop & Software', '081234567890', 1),
(2, 'Citra Amelia', 'Spesialis PC & Hardware', '087654321098', 1),
(3, 'Doni Firmansyah', 'All-rounder', '089987654321', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `layanan_katalog`
--
ALTER TABLE `layanan_katalog`
  ADD PRIMARY KEY (`id_katalog`),
  ADD UNIQUE KEY `nama_layanan` (`nama_layanan`);

--
-- Indexes for table `layanan_service`
--
ALTER TABLE `layanan_service`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_layanan_katalog` (`id_katalog_fk`);

--
-- Indexes for table `struk`
--
ALTER TABLE `struk`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_struk_customer` (`customer_id`),
  ADD KEY `fk_struk_teknisi` (`teknisi_id`),
  ADD KEY `fk_struk_layanan` (`layanan_id`);

--
-- Indexes for table `teknisi`
--
ALTER TABLE `teknisi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `layanan_katalog`
--
ALTER TABLE `layanan_katalog`
  MODIFY `id_katalog` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `layanan_service`
--
ALTER TABLE `layanan_service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `struk`
--
ALTER TABLE `struk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `teknisi`
--
ALTER TABLE `teknisi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `layanan_service`
--
ALTER TABLE `layanan_service`
  ADD CONSTRAINT `fk_layanan_katalog` FOREIGN KEY (`id_katalog_fk`) REFERENCES `layanan_katalog` (`id_katalog`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `struk`
--
ALTER TABLE `struk`
  ADD CONSTRAINT `fk_struk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_struk_layanan` FOREIGN KEY (`layanan_id`) REFERENCES `layanan_service` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_struk_teknisi` FOREIGN KEY (`teknisi_id`) REFERENCES `teknisi` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
