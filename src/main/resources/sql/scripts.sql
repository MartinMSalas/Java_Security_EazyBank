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

-- Step 1: Store the minimum transaction_id in a variable
SET @min_id = (SELECT MIN(transaction_id) FROM account_transactions);

-- Step 2: Delete all records except the one with the minimum transaction_id
DELETE FROM account_transactions
WHERE transaction_id != @min_id;

INSERT INTO `account_transactions` (`transaction_id`, `account_number`,`customer_id`,`transaction_dt`,`transaction_summary`,`transaction_type`,`transaction_amt`,`closing_balance`,`create_dt`)
	VALUES (UUID(), 186576453434,1,CURDATE() - INTERVAL 7 DAY,"Coffee Shop", "Withdrawal",30,34500,CURDATE() - INTERVAL 7 DAY);
INSERT INTO `account_transactions` (`transaction_id`, `account_number`,`customer_id`,`transaction_dt`,`transaction_summary`,`transaction_type`,`transaction_amt`,`closing_balance`,`create_dt`)
	VALUES (UUID(), 186576453434,1,CURDATE() - INTERVAL 7 DAY,"Coffee Shop", "Withdrawal",30,34500,CURDATE() - INTERVAL 7 DAY);
INSERT INTO `account_transactions` (`transaction_id`, `account_number`,`customer_id`,`transaction_dt`,`transaction_summary`,`transaction_type`,`transaction_amt`,`closing_balance`,`create_dt`)
	VALUES (UUID(), 186576453434,1,CURDATE() - INTERVAL 7 DAY,"Coffee Shop", "Withdrawal",30,34500,CURDATE() - INTERVAL 7 DAY);

CREATE TABLE `loans` (
	`loan_number` int NOT NULL AUTO_INCREMENT,
	`customer_id` int NOT NULL,
	`start_dt` date NOT NULL,
	`loan_type` varchar(100) NOT NULL,
	`total_loan` int NOT NULL,
	`amount_paid` int NOT NULL,
	`outstanding_amount` int NOT NULL,
	`create_dt` date default NULL,
	primary key(`loan_number`),
    KEY `customer_id` (`customer_id`),
CONSTRAINT `loan_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE);

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
	VALUES (1, '2024-10-13', 'Home', 200000, 50000, 150000, '2024-10-13');
INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
	VALUES (1, '2024-06-06', 'Vehicle', 40000, 10000, 30000, '2024-06-06');
INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
	VALUES (1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14');
INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
	VALUES (1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');


CREATE TABLE `cards` (
	`card_id` int NOT NULL AUTO_INCREMENT,
    `card_number` varchar(100) NOT NULL,
    `customer_id` int NOT NULL,
    `card_type` varchar(100) NOT NULL,
    `total_limit` int NOT NULL,
    `amount_used` int NOT NULL,
    `available_amount` int NOT NULL,
    `create_dt` date DEFAULT NULL,
    PRIMARY KEY(`card_id`),
    KEY `customer_id` (`customer_id`),
    CONSTRAINT `card_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

INSERT INTO `cards` ( `card_number` ,    `customer_id` ,    `card_type` ,    `total_limit`,    `amount_used`,    `available_amount`,    `create_dt`)
    VALUES('4565XXXX4565',1,'Credit',10000,500,9500,CURDATE());
INSERT INTO `cards` (`card_number` ,    `customer_id` ,    `card_type` ,    `total_limit`,    `amount_used`,    `available_amount`,    `create_dt`)
    VALUES('3455XXXX8673',1,'Credit',7500,600,6900,CURDATE());
INSERT INTO `cards` ( `card_number` ,    `customer_id` ,    `card_type` ,    `total_limit`,    `amount_used`,    `available_amount`,    `create_dt`)
    VALUES('2354XXXX1325',1,'Credit',20000,4000,16000,CURDATE());

CREATE TABLE `notice_details` (
	`notice_id` int NOT NULL AUTO_INCREMENT,
    `notice_summary` varchar(200) NOT NULL,
    `notice_details` varchar(500) NOT NULL,
    `notic_beg_dt` date NOT NULL,
    `notic_end_dt` date NOT NULL,
    `create_dt` date DEFAULT NULL,
    `update_dt` date DEFAULT NULL,
    PRIMARY KEY (`notice_id`)
);


INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 1', 'Details about notice 1. This is a detailed description.', '2024-06-13', '2024-07-01', '2024-06-13', '2024-06-25');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 2', 'Details about notice 2. This is a detailed description.', '2024-03-12', '2024-03-14', '2024-03-12', '2024-03-09');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 3', 'Details about notice 3. This is a detailed description.', '2024-10-15', '2024-11-04', '2024-10-15', '2024-10-30');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 4', 'Details about notice 4. This is a detailed description.', '2024-10-12', '2024-10-26', '2024-10-12', '2024-10-16');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 5', 'Details about notice 5. This is a detailed description.', '2024-06-13', '2024-07-09', '2024-06-13', '2024-07-05');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 6', 'Details about notice 6. This is a detailed description.', '2024-07-23', '2024-08-14', '2024-07-23', '2024-08-11');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 7', 'Details about notice 7. This is a detailed description.', '2024-04-14', '2024-05-12', '2024-04-14', '2024-05-12');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 8', 'Details about notice 8. This is a detailed description.', '2024-11-01', '2024-11-09', '2024-11-01', '2024-11-08');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 9', 'Details about notice 9. This is a detailed description.', '2024-06-06', '2024-06-07', '2024-06-06', '2024-06-04');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 10', 'Details about notice 10. This is a detailed description.', '2024-07-06', '2024-07-20', '2024-07-06', '2024-07-17');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 11', 'Details about notice 11. This is a detailed description.', '2024-08-20', '2024-09-17', '2024-08-20', '2024-09-08');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 12', 'Details about notice 12. This is a detailed description.', '2024-09-03', '2024-09-09', '2024-09-03', '2024-09-04');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 13', 'Details about notice 13. This is a detailed description.', '2024-07-07', '2024-07-18', '2024-07-07', '2024-07-08');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 14', 'Details about notice 14. This is a detailed description.', '2024-04-03', '2024-04-10', '2024-04-03', '2024-04-01');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 15', 'Details about notice 15. This is a detailed description.', '2024-11-26', '2024-12-03', '2024-11-26', '2024-12-02');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 16', 'Details about notice 16. This is a detailed description.', '2024-09-28', '2024-10-13', '2024-09-28', '2024-10-11');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 17', 'Details about notice 17. This is a detailed description.', '2024-12-16', '2025-01-04', '2024-12-16', '2025-01-04');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 18', 'Details about notice 18. This is a detailed description.', '2024-04-22', '2024-05-11', '2024-04-22', '2024-05-03');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 19', 'Details about notice 19. This is a detailed description.', '2024-08-06', '2024-08-27', '2024-08-06', '2024-08-27');
INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Notice Summary 20', 'Details about notice 20. This is a detailed description.', '2024-07-11', '2024-07-25', '2024-07-11', '2024-07-24');


CREATE TABLE `contact_messages` (
	`contact_id` varchar(50) NOT NULL,
    `contact_name` varchar(50) NOT NULL,
    `contact_email` varchar(100) NOT NULL,
    `subject` varchar(500) NOT NULL,
    `message` TEXT(20000) NOT NULL,
    `create_dt` date DEFAULT NULL,
    PRIMARY KEY (`contact_id`)
);


RENAME TABLE accounts TO account;
RENAME TABLE cards TO card;
RENAME TABLE loans TO loancontact_messagescontact_messagesaccount_transactionsaccount_transactions;

ALTER TABLE customer
MODIFY COLUMN create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN version INT NOT NULL DEFAULT 0,
ADD COLUMN update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE account
MODIFY COLUMN create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN version INT NOT NULL DEFAULT 0,
ADD COLUMN update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE account_transactions
MODIFY COLUMN create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN version INT NOT NULL DEFAULT 0,
ADD COLUMN update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE card
MODIFY COLUMN create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN version INT NOT NULL DEFAULT 0,
ADD COLUMN update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE contact_messages
MODIFY COLUMN create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN version INT NOT NULL DEFAULT 0,
ADD COLUMN update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;


ALTER TABLE loan
MODIFY COLUMN create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN version INT NOT NULL DEFAULT 0,
ADD COLUMN update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE notice_details
MODIFY COLUMN create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN version INT NOT NULL DEFAULT 0,
ADD COLUMN update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;