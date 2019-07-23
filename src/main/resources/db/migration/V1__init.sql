CREATE TABLE `revinfo` (
  `rev` int(11) NOT NULL AUTO_INCREMENT,
  `revtstmp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fine_master` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fine_type` varchar(255) DEFAULT NULL,
  `standard` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `fine_payment` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` decimal(19,2) NOT NULL,
  `fine_id` bigint(20) NOT NULL,
  `instrument_no` varchar(255) NOT NULL,
  `transaction_mode`varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fine_record` (
  `id` bigint(20) NOT NULL primary key,
`created_by` varchar(50) not NULL,
`created_date` datetime not NULL,
`last_modified_by` varchar(50) default NULL,
`last_modified_date` datetime default NULL,
`amount` decimal(19,2) not NULL,
`description` varchar(255) not NULL,
`enrolment_no` bigint(20) not NULL,
`fine_type` varchar(255) not NULL,
`status` varchar(255) not NULL
) ;



--
--CREATE TABLE `hibernate_sequence` (
--  `next_val` bigint(20) DEFAULT NULL
--) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
--LOCK TABLES `hibernate_sequence` WRITE;
--/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
--
--INSERT INTO `hibernate_sequence` (`next_val`)
--VALUES
--(1),
--(1),
--(1),
--(1),
--(1),
--(1),
--(1),
--(1),
--(1);
--
--/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
--UNLOCK TABLES;



CREATE TABLE `jhi_authority` (
`name` varchar(50) NOT NULL,
PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `jhi_persistent_audit_event` (
`event_id` bigint(20) NOT NULL AUTO_INCREMENT,
`event_date` datetime DEFAULT NULL,
`event_type` varchar(255) DEFAULT NULL,
`principal` varchar(255) NOT NULL,
PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `jhi_persistent_audit_evt_data` (
`event_id` bigint(20) NOT NULL,
`value` varchar(255) DEFAULT NULL,
`name` varchar(255) NOT NULL,
PRIMARY KEY (`event_id`,`name`),
CONSTRAINT `FK2ehnyx2si4tjd2nt4q7y40v8m` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `jhi_user` (
`id` varchar(255) NOT NULL,
`created_by` varchar(50) NOT NULL,
`created_date` datetime DEFAULT NULL,
`last_modified_by` varchar(50) DEFAULT NULL,
`last_modified_date` datetime DEFAULT NULL,
`activated` bit(1) NOT NULL,
`email` varchar(254) DEFAULT NULL,
`first_name` varchar(50) DEFAULT NULL,
`image_url` varchar(256) DEFAULT NULL,
`lang_key` varchar(6) DEFAULT NULL,
`last_name` varchar(50) DEFAULT NULL,
`login` varchar(50) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `UK_9y0frpqnmqe7y6mk109vw3246` (`login`),
UNIQUE KEY `UK_bycanyquvi09q7fh5pgxrqnku` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `jhi_user_authority` (
`user_id` varchar(255) NOT NULL,
`authority_name` varchar(50) NOT NULL,
PRIMARY KEY (`user_id`,`authority_name`),
KEY `FK4psxl0jtx6nr7rhqbynr6itoc` (`authority_name`),
CONSTRAINT `FK290okww5jujghp4el5i7mgwu0` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`),
CONSTRAINT `FK4psxl0jtx6nr7rhqbynr6itoc` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `job_scheduler_information` (
`id` bigint(20) NOT NULL,
`created_by` varchar(50) NOT NULL,
`created_date` datetime DEFAULT NULL,
`last_modified_by` varchar(50) DEFAULT NULL,
`last_modified_date` datetime DEFAULT NULL,
`end_time` datetime DEFAULT NULL,
`job_status` varchar(255) DEFAULT NULL,
`process_name` varchar(255) NOT NULL,
`schedule_status` varchar(255) NOT NULL,
`start_time` datetime NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






