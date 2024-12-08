CREATE DATABASE IF NOT EXISTS stadium01;

use stadium01;

DROP TABLE IF EXISTS stadium;

-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
-- set auto_increment_offset=10;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
-- set auto_increment_increment=1; 
CREATE TABLE stadium (
	stdm_id	     	  INT AUTO_INCREMENT NOT NULL,
	stdm_name	  	  VARCHAR(20) NOT NULL,
	stdm_addr	  	  VARCHAR(50) NOT NULL,
	loc_id            INT NOT NULL,
	longitude         DECIMAL(11, 8) NOT NULL,
	latitude          DECIMAL(10, 8) NOT NULL,
	stdm_intro  	  TEXT NOT NULL,
	court_count       INT NOT NULL,
	court_price       INT NOT NULL,
	opr_sta  		  BOOLEAN NOT NULL,
	stdm_pic   		  MEDIUMBLOB,
	adm_id            INT NOT NULL,
	business_hr    	  INT NOT NULL,
	stdm_start_time   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	
	-- CONSTRAINT fk_location_stadium_loc_id
--         FOREIGN KEY (loc_id) REFERENCES location (loc_id),
--     CONSTRAINT fk_admin_stadium_admin_id
--         FOREIGN KEY (adm_id) REFERENCES admin (adm_id),
	CONSTRAINT stadium_PRIMARY_KEY PRIMARY KEY (stdm_id)
) AUTO_INCREMENT = 1;


INSERT INTO stadium (stdm_name, stdm_addr, loc_id, longitude, latitude, stdm_intro, court_count, court_price, opr_sta, stdm_pic, adm_id, business_hr)
VALUES
('飆汗羽球_信義總部', '110台北市信義區松勤街100號', 1, 121.56672000, 25.03189000, '北市最牛羽球館，給你最極致的羽球饗宴', 10, 600, 1, NULL, 101, 8),
('飆汗羽球_大甲格鬥館', '437台中市大甲區中山路一段876號', 2, 120.62902000, 24.34826000, '呼風喚羽大甲格鬥館', 5, 450, 1, NULL, 102, 10),
('飆汗羽球_東泰分館', '334桃園市八德區東泰街201號', 3, 121.27369000, 24.97413000, '桃園最大羽球館(目前閉館維護中)', 15, 250, 0, NULL, 103, 9);



UPDATE stadium
SET stdm_pic = LOAD_FILE('/path/to/stadium_photo.jpg')
WHERE stdm_id = 1;

-- 以下測試變量用:
-- show variables like '%auto_inc%';
-- show session variables like '%auto_inc%';  -- //session變量
-- show global variables  like '%auto_inc%';  -- //global變量

-- 以下測試環境的 版本、SSL、 字元編碼用:
-- select version();
-- show variables like '%ssl%';  [ 或執行 mysql> \s ]
-- show variables like '%character%';