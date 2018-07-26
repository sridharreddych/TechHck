
CREATE TABLE `role` (
  `ROLE_ID` bigint(20) NOT NULL ,
  `ROLE_NAME` varchar(30) NOT NULL,
  `ROLE_DESC` varchar(30) DEFAULT NULL,
  `CREATED_BY` bigint(20) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `MODIFIED_BY` bigint(20) DEFAULT NULL,
  `MODIFIED_DATE` date DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into role(role_id,role_name,role_desc,created_by,created_date,modified_by,modified_date)
values(1,"ADMIN","Administrator Role",1,sysdate(),1,sysdate());
insert into role(role_id,role_name,role_desc,created_by,created_date,modified_by,modified_date)
values(2,"CUSTOMER","Customer Role",1,sysdate(),1,sysdate());
insert into role(role_id,role_name,role_desc,created_by,created_date,modified_by,modified_date)
values(3,"APPROVER","Approver Role",1,sysdate(),1,sysdate());

CREATE TABLE `user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(30) NOT NULL,
  `LAST_NAME` varchar(30) NOT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  `GENDER` varchar(6) NOT NULL,
  `DATEOFBIRTH` date NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `HNI_FLAG` Varchar(1) NOT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `MOBILE_NUMBER` varchar(20) NOT NULL,
  `STATUS` varchar(10) NOT NULL,
  `PREFERRED_LANG` varchar(20) DEFAULT NULL,
  `CURRENT_EMPLOYER` varchar(30) DEFAULT NULL,
  `BANK_ACCOUNT_NO` varchar(30) DEFAULT NULL,
  `BANK_NAME` varchar(30) DEFAULT NULL,
  `BANK_IFSC_CODE` varchar(20) DEFAULT NULL,
  `REPAYMENT_MODE` varchar(20) DEFAULT NULL,
  `CREATED_BY` bigint(20) DEFAULT NULL,
  `CREATED_DATE` date NOT NULL,
  `MODIFIED_BY` bigint(20) DEFAULT NULL,
  `MODIFIED_DATE` date NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `EMAIL_ID` (`EMAIL_ID`),
  KEY `ROLE_FK` (`ROLE_ID`),
  CONSTRAINT `ROLE_FK` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_address` (
  `USER_ID` bigint(20) NOT NULL,
  `ADDRESS_TYPE` varchar(20) NOT NULL,
  `ADDRESS1` varchar(50) NOT NULL,
  `ADDRESS2` varchar(50) DEFAULT NULL,
  `CITY` varchar(30) NOT NULL,
  `STATE` varchar(30) NOT NULL,
  `COUNTRY` varchar(30) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ADDRESS_TYPE`),
  CONSTRAINT `user_to_address` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `email_notification` (
  `MESSAGE_ID` varchar(50) NOT NULL,
  `TO_MAIL_ID` varchar(50) NOT NULL,
  `SOURCE_ID` bigint(20) DEFAULT NULL,
  `MESSAGE_BODY` varchar(500) DEFAULT NULL,
  `SENT_STATUS` char(1) NOT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `MESSAGE_SENT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`MESSAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `loan_type` (
  `LOAN_TYPE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOAN_TYPE` varchar(20) NOT NULL,
  `INTEREST_RATE` double NOT NULL,
  PRIMARY KEY (`LOAN_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `loan` (
  `LOAN_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOAN_TYPE_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `COMMENCEMENT_DATE` date NOT NULL,
  `COMPLETION_DATE` date NOT NULL,
  `INTEREST_RATE` double NOT NULL,
  `TENURE` int(11) NOT NULL,
  `APPLIED_LOAN_AMOUNT` double NOT NULL,
  `APPROVED_LOAN_AMOUNT` double NOT NULL,
  `TOTAL_REPAID_AMOUNT` double NOT NULL,
  `LOAN_STATUS` varchar(50) NOT NULL,
  `PENDING_WITH` varchar(50) DEFAULT NULL,
  `REMARKS` varchar(2000) DEFAULT NULL,
  `DISBURSEMENT_ID` double DEFAULT NULL,
  PRIMARY KEY (`LOAN_ID`),
  KEY `loan_to_user_idx` (`USER_ID`),
  KEY `loan_to_loan_disbursement_idx` (`DISBURSEMENT_ID`),
  CONSTRAINT `loan_to_loan_disbursement` FOREIGN KEY (`DISBURSEMENT_ID`) REFERENCES `loan_disbursement` (`DISBURSEMENT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `loan_to_user` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `loan_auditlog` (
  `AUDIT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOAN_ID` bigint(20) NOT NULL,
  `LOAN_STATUS` varchar(30) NOT NULL,
  `PENDING_WITH` varchar(30) DEFAULT NULL,
  `REMARKS` varchar(40) DEFAULT NULL,
  `CREATED_BY` bigint(20) NOT NULL,
  `CREATED_DATE` date NOT NULL,
  `MODIFY_BY` bigint(20) NOT NULL,
  `MODIFY_DATE` date NOT NULL,
  PRIMARY KEY (`AUDIT_ID`),
  KEY `loan_auditing_to_loan_idx` (`LOAN_ID`),
  CONSTRAINT `loan_auditing_to_loan` FOREIGN KEY (`LOAN_ID`) REFERENCES `loan` (`LOAN_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `loan_disbursement` (
  `DISBURSEMENT_ID` double NOT NULL,
  `LOAN_ID` bigint(20) NOT NULL,
  `CREATED_BY` bigint(20) NOT NULL,
  `CREATED_DATE` date NOT NULL,
  `MODIFY_BY` bigint(20) NOT NULL,
  `MODIFY_DATE` date NOT NULL,
  `DISBURSEMENT_DATE` date NOT NULL,
  `NO_PDC_COLLECTED` int(11) DEFAULT NULL,
  `DISBURSEMENT_MODE` varchar(20) DEFAULT NULL,
  `NOTIFICATION_SENT` char(1) NOT NULL,
  `DISBURSED_TO_ACCOUNT` varchar(20) NOT NULL,
  PRIMARY KEY (`DISBURSEMENT_ID`),
  KEY `loan_disbursement_to_loan_idx` (`LOAN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

