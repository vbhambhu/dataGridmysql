-- phpMyAdmin SQL Dump
-- version 4.6.5.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 13, 2017 at 05:48 PM
-- Server version: 5.6.34
-- PHP Version: 7.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `datagrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `fields`
--

CREATE TABLE `fields` (
  `id` int(11) NOT NULL,
  `identifier` varchar(255) NOT NULL,
  `label` varchar(100) NOT NULL,
  `type` varchar(10) NOT NULL,
  `position` int(11) NOT NULL,
  `default_value` text,
  `form_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fields`
--

INSERT INTO `fields` (`id`, `identifier`, `label`, `type`, `position`, `default_value`, `form_id`) VALUES
(1, 'first_name', 'first_name', 'text', 1, '', 1),
(2, 'last_name', 'last name', 'text', 2, '', 1),
(3, 'gender', 'password', 'select', 1, '', 1),
(4, 'dsescriptiopn', 'Descriptiopn', 'textarea', 1, '', 1),
(5, 'dsescriptiopn', 'Descriptiopn', 'radio', 1, '', 1),
(6, 'dasdda', 'dasddad', 'check', 1, '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `field_options`
--

CREATE TABLE `field_options` (
  `id` int(11) NOT NULL,
  `okey` varchar(255) NOT NULL,
  `ovalue` varchar(255) NOT NULL,
  `field_id` int(11) NOT NULL,
  `position` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `field_options`
--

INSERT INTO `field_options` (`id`, `okey`, `ovalue`, `field_id`, `position`) VALUES
(1, 'fdsfd', 'fdsfds', 3, 1),
(2, 'tereter', '57577575', 3, 1),
(3, 'tereter', 'yes', 5, 1),
(4, 'tereter', 'no', 5, 1),
(5, 'tereter', 'no', 6, 1),
(6, 'tereter', 'no', 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `field_validations`
--

CREATE TABLE `field_validations` (
  `id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `value` text,
  `field_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `forms`
--

CREATE TABLE `forms` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `project_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `forms`
--

INSERT INTO `forms` (`id`, `name`, `description`, `project_id`) VALUES
(1, 'Testing', 'dasddadada', 1),
(2, 'Form 2', 'Form 2 description.', 1);

-- --------------------------------------------------------

--
-- Table structure for table `form_data`
--

CREATE TABLE `form_data` (
  `id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `field_name` varchar(100) NOT NULL,
  `field_value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `name`, `description`) VALUES
(1, 'project 1', 'fss sf sfsfsfsdf'),
(2, 'project 2', 'fss sf sfsfsfsdf'),
(3, 'project 3', 'fss sf sfsfsfsdf'),
(4, 'project 4', 'fss sf sfsfsfsdf'),
(5, 'project 5\\r\\n', 'fss sf sfsfsfsdf'),
(6, 'project 6\\r\\n', 'fss sf sfsfsfsdf'),
(7, 'project 7\\r\\n', 'fss sf sfsfsfsdf');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fields`
--
ALTER TABLE `fields`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `field_options`
--
ALTER TABLE `field_options`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `field_validations`
--
ALTER TABLE `field_validations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `forms`
--
ALTER TABLE `forms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `form_data`
--
ALTER TABLE `form_data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fields`
--
ALTER TABLE `fields`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `field_options`
--
ALTER TABLE `field_options`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `field_validations`
--
ALTER TABLE `field_validations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `forms`
--
ALTER TABLE `forms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `form_data`
--
ALTER TABLE `form_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;s