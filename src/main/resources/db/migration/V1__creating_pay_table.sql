CREATE TABLE payments (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 value decimal(19,2) NOT NULL,
 names varchar(100) DEFAULT NULL,
 numbers varchar(19) DEFAULT NULL,
 expirations varchar(7) DEFAULT NULL,
 codes varchar(3) DEFAULT NULL,
 status varchar(255) NOT NULL,
 form_payment_id bigint(20) NOT NULL,
 order_id bigint(20) NOT NULL,
 actives TINYINT(1) NOT NULL DEFAULT 1,
PRIMARY KEY (id)
);