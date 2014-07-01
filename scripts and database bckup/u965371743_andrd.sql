
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 18, 2014 at 01:56 PM
-- Server version: 5.1.67
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `u965371743_andrd`
--

-- --------------------------------------------------------

--
-- Table structure for table `Faculty`
--

CREATE TABLE IF NOT EXISTS `Faculty` (
  `Teacher_ID` varchar(10) NOT NULL,
  `T_Firstname` varchar(30) NOT NULL,
  `T_Lastname` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`Teacher_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Faculty`
--

INSERT INTO `Faculty` (`Teacher_ID`, `T_Firstname`, `T_Lastname`, `Password`) VALUES
('tcs1', 'Teacher', 'One', 'teacherone'),
('vineeth', 'v', 'p', '123'),
('gagan', 'sfsdf', 'dsdsd', '321'),
('abhinaba', 'asa', 'asas', '173405');

-- --------------------------------------------------------

--
-- Table structure for table `Faculty_Sub`
--

CREATE TABLE IF NOT EXISTS `Faculty_Sub` (
  `Sub_Code` varchar(7) NOT NULL,
  `Teacher_ID` varchar(10) NOT NULL,
  PRIMARY KEY (`Sub_Code`,`Teacher_ID`),
  KEY `Teacher_ID` (`Teacher_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Faculty_Sub`
--

INSERT INTO `Faculty_Sub` (`Sub_Code`, `Teacher_ID`) VALUES
('CS3002', 'vineeth'),
('CS3003', 'gagan'),
('CS3004', 'abhinaba'),
('CS3005', 'vineeth'),
('sub001', 'tcs1');

-- --------------------------------------------------------

--
-- Table structure for table `Student`
--

CREATE TABLE IF NOT EXISTS `Student` (
  `Roll` varchar(9) NOT NULL,
  `Firstname` varchar(30) NOT NULL,
  `Lastname` varchar(20) NOT NULL,
  `Mobile` varchar(12) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Branch` varchar(8) NOT NULL,
  `Year` year(4) NOT NULL,
  PRIMARY KEY (`Roll`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Student`
--

INSERT INTO `Student` (`Roll`, `Firstname`, `Lastname`, `Mobile`, `Email`, `Branch`, `Year`) VALUES
('M120358CA', 'Sourav', 'Das', '8089272154', 'sourav.fb.90@gmail.com', 'MCA', 2012),
('M120388CA', 'Gagandeep', 'Kaur', '7736794218', 'gagandeepkaur025@gmail.com', 'MCA', 2012),
('M1203600', 'Sujay', 'Mandal', '9989', 'ssss', 'ssss', 1978),
('M120360CA', 'ABHINABA', 'AUDHYA', '9898', 'sssddd', 'ddff', 1978);

-- --------------------------------------------------------

--
-- Table structure for table `Stud_Sub`
--

CREATE TABLE IF NOT EXISTS `Stud_Sub` (
  `Sub_Code` varchar(7) NOT NULL,
  `Roll` varchar(9) NOT NULL,
  `Absent_Days` int(11) DEFAULT '0',
  PRIMARY KEY (`Sub_Code`,`Roll`),
  KEY `Roll` (`Roll`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Stud_Sub`
--

INSERT INTO `Stud_Sub` (`Sub_Code`, `Roll`, `Absent_Days`) VALUES
('sub001', 'M120358CA', 0),
('CS3005', 'M120360CA', 0),
('CS3005', 'M1203600', 0),
('CS3002', 'M120388CA', 0),
('CS3002', 'M1203600', 0);

-- --------------------------------------------------------

--
-- Table structure for table `Subject`
--

CREATE TABLE IF NOT EXISTS `Subject` (
  `Sub_Code` varchar(7) NOT NULL,
  `Sub_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Sub_Code`),
  UNIQUE KEY `Sub_Name` (`Sub_Name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Subject`
--

INSERT INTO `Subject` (`Sub_Code`, `Sub_Name`) VALUES
('sub001', 'OOPS'),
('CS3005', 'Software Engineering'),
('CS3004', 'Networking'),
('CS3003', 'Data Mining'),
('CS3002', 'Web Programming');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `pswd` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(40) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`name`, `pswd`, `email`) VALUES
('sourav', '123', 'xxx'),
('gagan', '321', 'yyy'),
('abhinaba', '173405', 'zzz'),
('vineeth', '123', 'dsfsfsfs');

-- --------------------------------------------------------

--
-- Table structure for table `Vikash`
--

CREATE TABLE IF NOT EXISTS `Vikash` (
  `Name` int(40) NOT NULL,
  `Roll` int(40) NOT NULL,
  PRIMARY KEY (`Roll`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
