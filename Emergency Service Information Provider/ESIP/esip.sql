-- phpMyAdmin SQL Dump
-- version 3.2.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2014 at 01:15 PM
-- Server version: 5.1.43
-- PHP Version: 5.3.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `esip`
--

-- --------------------------------------------------------

--
-- Table structure for table `group_info`
--

CREATE TABLE IF NOT EXISTS `group_info` (
  `group_id` int(10) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) NOT NULL,
  `created_by` varchar(30) NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `message` varchar(200) DEFAULT NULL,
  `user_count` int(2) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `created_by` (`created_by`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `group_info`
--

INSERT INTO `group_info` (`group_id`, `group_name`, `created_by`, `created_on`, `message`, `user_count`) VALUES
(1, 'myGroup', 'marlonbrando', '2014-02-28 13:57:53', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `group_notifs`
--

CREATE TABLE IF NOT EXISTS `group_notifs` (
  `gn_id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `gid` int(10) NOT NULL,
  `msg` int(100) NOT NULL,
  `sent_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`gn_id`),
  UNIQUE KEY `uid` (`uid`,`gid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `group_notifs`
--


-- --------------------------------------------------------

--
-- Table structure for table `subscription`
--

CREATE TABLE IF NOT EXISTS `subscription` (
  `index` int(5) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `gid` int(10) NOT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `uid` (`uid`,`gid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `subscription`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE IF NOT EXISTS `user_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `login_id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phn_number` int(12) NOT NULL,
  `acc_created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_loc` varchar(100) DEFAULT NULL,
  `login_status` int(1) DEFAULT NULL,
  `gid` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`user_id`, `user_name`, `login_id`, `password`, `phn_number`, `acc_created_on`, `last_loc`, `login_status`, `gid`) VALUES
(1, 'Marlon', 'marlonbrando', 'godfather', 1234567890, '2014-02-10 09:53:45', 'Corleone', 0, NULL),
(2, 'Al Pacino', 'scarface', 'alpacino', 123456654, '2014-03-05 12:42:28', NULL, NULL, NULL),
(3, 'Achilles', 'sparta', 'trojanhorse', 989876, '2014-03-05 12:44:55', NULL, NULL, NULL),
(4, 'Alfred Hitchcock', 'hitchcock', 'psycho', 123234345, '2014-03-05 12:52:12', NULL, NULL, NULL);
