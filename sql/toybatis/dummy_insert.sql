DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
 
CREATE PROCEDURE loopInsert()
BEGIN
	DECLARE i INT DEFAULT 1;
        
	WHILE i <= 50 DO
   	INSERT INTO tb_user (email , password, reg_date , mod_date)
   	VALUES (concat('user_',i), concat('pass_',i), NOW(), now());
   	SET i = i + 1;
   END WHILE;
END$$
DELIMITER $$


CALL loopInsert;

TRUNCATE tb_user;