create database mmstechnologybank;
use mmstechnologybank;
CREATE TABLE USERS (
    USERNAME VARCHAR(128) PRIMARY KEY,
    PASSWORD VARCHAR(128) NOT NULL,
    ENABLED CHAR(1) CHECK (ENABLED IN ('Y','N') ) NOT NULL
);


CREATE TABLE AUTHORITIES (
    USERNAME VARCHAR(128) NOT NULL,
    AUTHORITY VARCHAR(128) NOT NULL
);
ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_UNIQUE UNIQUE (USERNAME, AUTHusersORITY);
ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_FK1 FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME);

INSERT INTO users (USERNAME, PASSWORD, ENABLED)
VALUES ('happy', '12345', 'Y');

INSERT INTO authorities (USERNAME, AUTHORITY) values ('happy', 'write');


CREATE TABLE CUSTOMER (
	`id` INT NOT NULL auto_increment,
    `email` VARCHAR(45) NOT NULL,
    `pwd` VARCHAR(200) NOT NULL,
    `role` VARCHAR(45) NOT NULL,
    primary key(`id`)
);

INSERT INTO Customer (`email`, `pwd`, `role`)
	VALUES('a@a.com','123','admin');

SELECT * FROM mmstechnologybank.customer;

drop table customer;


CREATE TABLE `customer` (
	`customer_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `pwd` varchar(500) NOT NULL,
    `role` varchar(100) NOT NULL,
    `create_dt` date DEFAULT NULL,
    PRIMARY KEY(`customer_id`)
);

INSERT INTO `customer` (`name`,`email`,`mobile_number`,`pwd`,`role`,`create_dt`)
	VALUES ("Happy","happy@example","987654837","$2y$12oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2","admin",CURDATE());

CREATE TABLE `accounts` (
	`customer_id` int NOT NULL,
	`account_number` int NOT NULL,
	`account_type` varchar(100) NOT NULL,
	`branch_address` varchar(200) NOT NULL,
	`create_dt` date DEFAULT NULL,
	PRIMARY KEY(`account_number`),
	KEY `customer_id` (`customer_id`),
	CONSTRAINT `customer_ibfk_1` FOREIGN KEY(`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
	);
ALTER TABLE `accounts` MODIFY COLUMN `account_number` BIGINT NOT NULL;
INSERT INTO `accounts` (`customer_id`,`account_number`,`account_type`,`branch_address`,`create_dt`)
	VALUES (1,186576453434,"Savings","123 Main Street, New York", CURDATE());


CREATE TABLE `account_transactions` (
	`transaction_id` varchar(200) NOT NULL,
	`account_number` bigint NOT NULL,
    `customer_id` int NOT NULL,
    `transaction_dt` date NOT NULL,
    `transaction_summary` varchar(200) NOT NULL,
    `transaction_type` varchar(100) NOT NULL,
    `transaction_amt` int NOT NULL,
    `closing_balance` int NOT NULL,
    `create_dt` date DEFAULT NULL,
    PRIMARY KEY(`transaction_id`),
    KEY `customer_id` (`customer_id`),
    KEY `account_number` (`account_number`),
    CONSTRAINT `accounts_ibfk_2` foreign key (`account_number`) REFERENCES `accounts` (`account_number`) ON DELETE CASCADE,
    CONSTRAINT `acct_user_ibfk_1` foreign key (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);


INSERT INTO `account_transactions` (`transaction_id`, `account_number`,`customer_id`,`transaction_dt`,`transaction_summary`,`transaction_type`,`transaction_amt`,`closing_balance`,`create_dt`)
	VALUES (UUID(), 186576453434,1,CURDATE() - INTERVAL 7 DAY,"Coffee Shop", "Withdrawal",30,34500,CURDATE() - INTERVAL 7 DAY);
INSERT INTO `account_transactions` (`transaction_id`, `account_number`,`customer_id`,`transaction_dt`,`transaction_summary`,`transaction_type`,`transaction_amt`,`closing_balance`,`create_dt`)
	VALUES (UUID(), 186576453434,1,CURDATE() - INTERVAL 7 DAY,"Coffee Shop", "Withdrawal",30,34500,CURDATE() - INTERVAL 7 DAY);
INSERT INTO `account_transactions` (`transaction_id`, `account_number`,`customer_id`,`transaction_dt`,`transaction_summary`,`transaction_type`,`transaction_amt`,`closing_balance`,`create_dt`)
	VALUES (UUID(), 186576453434,1,CURDATE() - INTERVAL 7 DAY,"Coffee Shop", "Withdrawal",30,34500,CURDATE() - INTERVAL 7 DAY);
