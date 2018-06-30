SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `article`
-- ----------------------------
#2017年中国软件业务收入百强
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `company_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `company_name`   varchar(255) NOT NULL,
  `company_address` varchar(255) DEFAULT NULL,
  `company_income` INT  NOT NULL,#万元
  `company_logo` varchar(50) DEFAULT NULL,
  `company_sort` int(10) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
