-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2023 at 12:42 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mytherapy`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `amka` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `afm` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `date` datetime NOT NULL,
  `status` tinyint(1) NOT NULL,
  `service` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`amka`, `afm`, `date`, `status`, `service`) VALUES
('02070301851', '111111114', '2023-06-13 09:30:00', 2, 'SS1'),
('02070301852', '111111114', '2023-06-13 10:30:00', 2, 'SS1'),
('02070301853', '111111114', '2023-06-13 08:30:00', 1, 'SS2'),
('02070301854', '121231410', '2023-06-13 10:30:00', 2, 'SS2');

-- --------------------------------------------------------

--
-- Table structure for table `association`
--

CREATE TABLE `association` (
  `id` varchar(4) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `password` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `association`
--

INSERT INTO `association` (`id`, `password`) VALUES
('001', '12345678');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `amka` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `name` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `surname` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `address` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `addressNumber` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `city` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `postcode` varchar(5) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `password` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`amka`, `name`, `surname`, `address`, `addressNumber`, `city`, `postcode`, `password`) VALUES
('02070301851', 'Γιώργος', 'Γκούμας', 'Καραμαούνα', '13', 'Θεσσαλονίκη', '55132', '12345678'),
('02070301852', 'Γιάννης', 'Καραγκούνης', 'Εγνατία', '100', 'Θεσσαλονίκη', '54636', '12345678'),
('02070301853', 'Μυρτώ', 'Τσολακίδη', 'Μακροχορίου', '15', 'Θεσσαλονίκη', '55132', '12345678'),
('02070301854', 'Μαρία', 'Θεοχάρη', 'Μοσχόπουλου', '17', 'Αθήνα', '11526', '12345678'),
('02070301855', 'Μαρίνα', 'Ξένου', 'Μπούσιο', '15', 'Αθήνα', '11524', '12345678'),
('02070301856', 'Χριστίνα', 'Παπανδρέου', '25ης Μαρτίου', '5', 'Βόλος', '38223', '12345678'),
('02070301857', 'Θεοφάνης', 'Γκίκας', 'Αγάπης', '3', 'Βόλος', '38223', '12345678'),
('02070301858', 'Βασίλης', 'Τσάτσο', 'Αγησιλάου', '8', 'Κατερίνη', '60133', '12345678'),
('02070301859', 'Γιάννης', 'Ξένου', 'Αγίας Λαύρας', '17', 'Κατερίνη', '60132', '12345678'),
('02070301860', 'Χριστίνα', 'Τσολακίδη', 'Εγνατία', '13', 'Θεσσαλονίκη', '54636', '12345678');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `code` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `name` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `price` int(11) NOT NULL,
  `description` text CHARACTER SET utf16 COLLATE utf16_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`code`, `name`, `price`, `description`) VALUES
('Example1', 'discordExample', 50, 'This is an example.'),
('SS1', 'testService1', 50, 'testDescription1.'),
('SS2', 'testService2', 60, 'testDescription2.'),
('SS3', 'testService3', 70, 'testDescription3.');

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `amka` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `afm` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`amka`, `afm`) VALUES
('02070301851', '111111114'),
('02070301852', '111111114'),
('02070301853', '111111114'),
('02070301854', '121231410'),
('02070301855', '121231410'),
('02070301856', '121231410');

-- --------------------------------------------------------

--
-- Table structure for table `therapy`
--

CREATE TABLE `therapy` (
  `afm` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `name` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `email` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `address` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `addressNumber` varchar(8) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `postcode` varchar(5) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `city` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `password` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `therapy`
--

INSERT INTO `therapy` (`afm`, `name`, `email`, `address`, `addressNumber`, `postcode`, `city`, `password`) VALUES
('111111114', 'testClinic1', 'testClinic1@testing.com', 'testSt1', '20', '54452', 'Thessaloniki', '12345678'),
('121231410', 'testClinic2', 'testClinic2@testing.com', 'testSt2', '122', '54453', 'Thessaloniki', '12345678'),
('121824195', 'testClinic3', 'testClinic3@testing.com', 'testSt3', '22', '54454', 'Thessaloniki', '12345678'),
('178237123', 'testClinic4', 'testClinic4@testing.com', 'testSt4', '412', '54455', 'Thessaloniki', '12345678'),
('342134120', 'testClinic5', 'testClinic5@testing.com', 'testSt5', '31', '54456', 'Thessaloniki', '12345678'),
('451231245', 'testClinic6', 'testClinic6@testing.com', 'testSt6', '23', '54457', 'Thessaloniki', '12345678');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`amka`,`afm`,`date`),
  ADD KEY `afm` (`afm`),
  ADD KEY `service` (`service`);

--
-- Indexes for table `association`
--
ALTER TABLE `association`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`amka`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`amka`,`afm`),
  ADD KEY `afm` (`afm`);

--
-- Indexes for table `therapy`
--
ALTER TABLE `therapy`
  ADD PRIMARY KEY (`afm`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`afm`) REFERENCES `therapy` (`afm`),
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`amka`) REFERENCES `patient` (`amka`),
  ADD CONSTRAINT `appointment_ibfk_3` FOREIGN KEY (`service`) REFERENCES `service` (`code`);

--
-- Constraints for table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`afm`) REFERENCES `therapy` (`afm`),
  ADD CONSTRAINT `session_ibfk_2` FOREIGN KEY (`amka`) REFERENCES `patient` (`amka`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
