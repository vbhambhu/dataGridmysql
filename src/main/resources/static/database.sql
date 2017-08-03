-- phpMyAdmin SQL Dump
-- version 4.6.5.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 03, 2017 at 06:07 PM
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
  `validations` text NOT NULL,
  `sheet_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fields`
--

INSERT INTO `fields` (`id`, `identifier`, `label`, `type`, `position`, `default_value`, `validations`, `sheet_id`) VALUES
(1, 'first_name', 'first_name', 'text', 1, '', 'numeric', 1),
(2, 'last_name', 'last name', 'text', 2, '', '', 1),
(3, 'gender', 'password', 'select', 1, '', '', 1),
(4, 'dsescriptiopn', 'Descriptiopn', 'textarea', 1, '', '', 1),
(5, 'dsescriptiopn', 'Descriptiopn', 'radio', 1, '', '', 1),
(6, 'dasdda', 'dasddad', 'check', 1, '', '', 1);

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
-- Table structure for table `folders`
--

CREATE TABLE `folders` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `folders`
--

INSERT INTO `folders` (`id`, `name`, `parent_id`, `project_id`) VALUES
(1, 'F1 P1', 0, 1),
(2, 'F2 P1', 0, 1),
(3, 'F2 P1', 2, 1),
(4, 'F1 P1', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `meta_data`
--

CREATE TABLE `meta_data` (
  `id` int(11) NOT NULL,
  `sheet_id` int(11) NOT NULL,
  `col_name` varchar(100) NOT NULL,
  `col_label` varchar(150) NOT NULL,
  `input_type` varchar(50) NOT NULL,
  `input_options` text,
  `validations` varchar(255) NOT NULL,
  `is_searchable` tinyint(1) NOT NULL DEFAULT '1',
  `is_hidden` tinyint(1) NOT NULL DEFAULT '0',
  `is_editable` tinyint(1) NOT NULL DEFAULT '1',
  `position` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `meta_data`
--

INSERT INTO `meta_data` (`id`, `sheet_id`, `col_name`, `col_label`, `input_type`, `input_options`, `validations`, `is_searchable`, `is_hidden`, `is_editable`, `position`) VALUES
(1, 1, 'name', 'Name', 'text', NULL, '', 1, 0, 1, 1),
(2, 1, 'comment', 'Comment', 'textarea', NULL, '', 1, 0, 1, 2),
(3, 1, 'date_of_birth', 'Date Of Birth', 'date', NULL, '', 1, 0, 1, 3),
(4, 1, 'gender', 'Gender', 'select_gender', NULL, '', 1, 0, 1, 4),
(5, 1, 'salary', 'Salary', 'api_getshelves', NULL, '', 1, 0, 1, 5),
(6, 1, 'last_seen_time', 'Last Seen Time', 'text', NULL, '', 1, 0, 1, 6),
(7, 1, 'category', 'Category', 'join_locations_name', NULL, '', 1, 0, 1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `project_view` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `permissions`
--

INSERT INTO `permissions` (`id`, `user_id`, `project_id`, `project_view`) VALUES
(1, 1, 2, 1),
(2, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `name`) VALUES
(1, 'Project 1'),
(2, 'Project 2');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'spadmin'),
(2, 'editor'),
(3, 'writer');

-- --------------------------------------------------------

--
-- Table structure for table `sheets`
--

CREATE TABLE `sheets` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `folder_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sheets`
--

INSERT INTO `sheets` (`id`, `name`, `folder_id`, `project_id`) VALUES
(1, 'DNA', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sheet_data`
--

CREATE TABLE `sheet_data` (
  `id` int(11) NOT NULL,
  `sheet_id` int(11) NOT NULL,
  `record_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sheet_data`
--

INSERT INTO `sheet_data` (`id`, `sheet_id`, `record_id`, `name`, `value`) VALUES
(1, 1, 1, 'first_name', 'Vinod'),
(2, 1, 1, 'last_name', 'Kumar'),
(3, 1, 2, 'email', 'Kumar');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `status`, `updated_at`, `created_at`) VALUES
(1, 'Vinod', 'Kumar', 'vkumar', 'v.bhambhu083@gmail.com', '$2a$10$92JyRLjZjimvpvou8D./b.gtY.gHPnStmBR548LtJ9WXtMdM50P2O', 1, NULL, '2017-07-27 10:29:01');

-- --------------------------------------------------------

--
-- Table structure for table `users_to_role`
--

CREATE TABLE `users_to_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users_to_role`
--

INSERT INTO `users_to_role` (`id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(3, 2, 3),
(4, 1, 2);

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
-- Indexes for table `folders`
--
ALTER TABLE `folders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `meta_data`
--
ALTER TABLE `meta_data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sheets`
--
ALTER TABLE `sheets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sheet_data`
--
ALTER TABLE `sheet_data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_to_role`
--
ALTER TABLE `users_to_role`
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
-- AUTO_INCREMENT for table `folders`
--
ALTER TABLE `folders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `meta_data`
--
ALTER TABLE `meta_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `sheets`
--
ALTER TABLE `sheets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `sheet_data`
--
ALTER TABLE `sheet_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `users_to_role`
--
ALTER TABLE `users_to_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;