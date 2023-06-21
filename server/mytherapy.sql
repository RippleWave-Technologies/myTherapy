-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2023 at 03:41 PM
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
('02070301852', '094222211', '2023-06-15 15:00:00', 3, 'SS2'),
('02070301852', '999645183', '2023-05-28 10:00:00', 2, 'uAS'),
('02070301852', '999645183', '2023-05-28 10:30:00', 2, 'uAS'),
('02070301853', '094222211', '2023-06-15 12:30:00', 3, 'SS2'),
('02070301854', '094222211', '2023-06-15 09:30:00', 3, 'SS2'),
('02070301855', '999645183', '2023-06-02 13:30:00', 2, 'uAS'),
('02070301856', '094222211', '2023-06-10 11:00:00', 2, 'uAS'),
('02070301856', '094222211', '2023-06-15 10:00:00', 3, 'SS2'),
('02070301857', '999645183', '2023-06-19 11:30:00', 2, 'uAS'),
('02070301858', '094222211', '2023-06-15 09:00:00', 2, 'uAS'),
('02070301859', '094222211', '2023-05-30 13:00:00', 2, 'uAS'),
('02070301859', '094222211', '2023-06-15 13:30:00', 3, 'SS2'),
('02070301860', '094222211', '2023-06-05 10:30:00', 2, 'uAS');

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
('02070301852', 'Γιάννης', 'Καραγκούνης', 'Εγνατία', '22', 'Θεσσαλονίκη', '54636', '12345678'),
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
('SS1', 'testService1', 10, 'This is a testDescription. testDescription1'),
('SS2', 'testService2', 20, 'This is a testDescription. testDescription2'),
('SS3', 'testService3', 30, 'This is a testDescription. testDescription3'),
('SS4', 'testService4', 40, 'This is a testDescription. testDescription4'),
('SS5', 'testService5', 50, 'This is a testDescription. testDescription5'),
('SS6', 'testService6', 60, 'This is a testDescription. testDescription6'),
('SS7', 'testService7', 70, 'This is a testDescription. testDescription7'),
('SS8', 'testService8', 80, 'This is a testDescription. testDescription8'),
('SS9', 'testService9', 90, 'This is a testDescription. testDescription9'),
('uAS', 'unfinishedAppointmentService', 0, 'A service attribute placeholder for appointments of status 1 or 2.');

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
('02070301851', '094077783'),
('02070301851', '094222211'),
('02070301851', '094508587'),
('02070301851', '998775895'),
('02070301852', '094077783'),
('02070301852', '094508587'),
('02070301852', '801005806'),
('02070301852', '801470140'),
('02070301853', '094019245'),
('02070301853', '094222211'),
('02070301853', '094352564'),
('02070301853', '094508587'),
('02070301853', '801470140'),
('02070301854', '094019245'),
('02070301854', '094077783'),
('02070301854', '094352564'),
('02070301854', '801470140'),
('02070301854', '999645183'),
('02070301855', '094019245'),
('02070301855', '094352564'),
('02070301855', '094508587'),
('02070301855', '801005806'),
('02070301855', '998775895'),
('02070301855', '999645183'),
('02070301856', '094077783'),
('02070301856', '094352564'),
('02070301856', '801005806'),
('02070301856', '999645183'),
('02070301857', '094077783'),
('02070301857', '094222211'),
('02070301857', '094352564'),
('02070301857', '801005806'),
('02070301857', '998775895'),
('02070301858', '094222211'),
('02070301858', '998775895'),
('02070301858', '999645183'),
('02070301859', '094019245'),
('02070301859', '094222211'),
('02070301859', '801005806'),
('02070301859', '801470140'),
('02070301859', '999645183'),
('02070301860', '094019245'),
('02070301860', '094508587'),
('02070301860', '801470140'),
('02070301860', '998775895');

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
('094019245', 'testClinic4', 'testClinic4@testing.com', 'testSt4', '12', '54454', 'Thessaloniki', '12345678'),
('094077783', 'testClinic3', 'testClinic3@testing.com', 'testSt3', '22', '54453', 'Thessaloniki', '12345678'),
('094222211', 'testClinic1', 'testClinic1@testing.com', 'testSt1', '20', '54451', 'Thessaloniki', '12345678'),
('094352564', 'testClinic6', 'testClinic6@testing.com', 'testSt6', '23', '54456', 'Thessaloniki', '12345678'),
('094508587', 'testClinic7', 'testClinic7@testing.com', 'testSt7', '4', '54457', 'Thessaloniki', '12345678'),
('801005806', 'testClinic9', 'testClinic9@testing.com', 'testSt9', '22', '54459', 'Thessaloniki', '12345678'),
('801470140', 'testClinic8', 'testClinic8@testing.com', 'testSt8', '31', '54458', 'Thessaloniki', '12345678'),
('998775895', 'testClinic5', 'testClinic5@testing.com', 'testSt5', '31', '54455', 'Thessaloniki', '12345678'),
('999645183', 'testClinic2', 'testClinic2@testing.com', 'testSt2', '32', '54452', 'Thessaloniki', '12345678');

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
