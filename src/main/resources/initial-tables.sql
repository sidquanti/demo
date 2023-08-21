DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `enrollment`;
CREATE TABLE `enrollment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `exam_name` varchar(255) DEFAULT NULL,
  `library_id` bigint DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `slot_id` bigint DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  `total_capacity` int DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `facility`;
CREATE TABLE `facility` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `library`;
CREATE TABLE `library` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city_id` bigint DEFAULT NULL,
  `closing_time` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gstin` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `opening_time` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_capacity` int DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `library_facility`;
CREATE TABLE `library_facility` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `extra_price` double DEFAULT NULL,
  `facility_id` bigint DEFAULT NULL,
  `included` bit(1) DEFAULT NULL,
  `library_id` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `library_image`;
CREATE TABLE `library_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `facility_id` bigint DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `library_id` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `enrollment_id` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `sample`;
CREATE TABLE `sample` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` varchar(255) DEFAULT NULL,
  `dp_updated_at` datetime DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `slot`;
CREATE TABLE `slot` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `library_id` bigint DEFAULT NULL,
  `slot_capacity` int DEFAULT NULL,
  `slot_price` double DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `city` (`id`, `city`, `country`, `created_on`, `state`, `status`, `updated_on`) VALUES
(1, 'Chapra', 'India', '2023-08-15 19:15:18', 'Bihar', 'ACTIVE', '2023-08-15 19:15:18');

INSERT INTO `library` (`id`, `address`, `city_id`, `closing_time`, `created_by`, `created_on`, `email`, `gstin`, `mobile`, `name`, `opening_time`, `status`, `total_capacity`, `updated_by`, `updated_on`) VALUES
(1, 'shakti nagar, chapra', 1, '10:00 PM', 'admin', '2023-08-15 19:11:23', 'linkwithsid@gmail.com', NULL, '8574120014', 'Sid\'s Library', '8:00 AM', 'ACTIVE', 100, 'user', '2023-08-15 19:11:37');

INSERT INTO `sample` (`id`, `data`, `dp_updated_at`, `quantity`) VALUES
(1, 'sample date', '2023-08-15 18:55:16', 10);