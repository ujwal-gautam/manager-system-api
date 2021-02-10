Step-1:-
CREATE DATABASE `management_system` /*!40100 DEFAULT CHARACTER SET latin1 */;

Step-2:-
CREATE TABLE `employee` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `first_name` varchar(45) DEFAULT NULL,
                            `last_name` varchar(255) DEFAULT NULL,
                            `address` varchar(255) DEFAULT NULL,
                            `mobile` bigint(20) DEFAULT NULL,
                            `email_id` varchar(255) DEFAULT NULL,
                            `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            `UpdatedAt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                            `status` enum('Active','Inactive','Deleted') NOT NULL DEFAULT 'Inactive',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

Step-3:-
CREATE TABLE `manager` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `email` varchar(255) DEFAULT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           `user_role` varchar(45) DEFAULT NULL,
                           `first_name` varchar(45) DEFAULT NULL,
                           `last_name` varchar(45) DEFAULT NULL,
                           `address` varchar(255) DEFAULT NULL,
                           `mobile` decimal(19,2) DEFAULT NULL,
                           `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `UpdatedAt` timestamp NULL DEFAULT NULL,
                           `status` enum('Active','Inactive','Deleted') NOT NULL DEFAULT 'Inactive',
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

