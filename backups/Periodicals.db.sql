BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Users` (
	`idUser`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`login`	TEXT NOT NULL UNIQUE,
	`password`	TEXT NOT NULL,
	`idLocale`	INTEGER NOT NULL,
	`email`	TEXT NOT NULL UNIQUE,
	`idDefaultLocal`	INTEGER NOT NULL DEFAULT 1,
	FOREIGN KEY(`idLocale`) REFERENCES `UserRoles`(`idLocale`),
	FOREIGN KEY(`idDefaultLocal`) REFERENCES `Internationalization`(`idLocal`)
);
CREATE TABLE IF NOT EXISTS `UserSubscriptions` (
	`idUserSubscription`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`idAddress`	INTEGER NOT NULL,
	`idSubscriptionVariant`	INTEGER NOT NULL,
	`dateBegin`	TEXT NOT NULL,
	`dateEnd`	TEXT NOT NULL,
	`idPayment`	INTEGER NOT NULL,
	FOREIGN KEY(`idPayment`) REFERENCES `Payments`(`idPayment`),
	FOREIGN KEY(`idAddress`) REFERENCES `UserAddresses`(`idAddress`),
	FOREIGN KEY(`idSubscriptionVariant`) REFERENCES `SubscriptionVariants`(`idSubscriptionVariant`)
);
CREATE TABLE IF NOT EXISTS `UserRoles` (
	`idLocale`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`nameRole`	TEXT NOT NULL
);
INSERT INTO `UserRoles` (idLocale,nameRole) VALUES (1,'администратор'),
 (2,'подписчик');
CREATE TABLE IF NOT EXISTS `UserAddresses` (
	`idAddress`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`address`	TEXT NOT NULL,
	`idUser`	INTEGER,
	FOREIGN KEY(`idUser`) REFERENCES `Users`(`idUser`)
);
CREATE TABLE IF NOT EXISTS `SubscriptionVariants` (
	`idSubscriptionVariant`	INTEGER NOT NULL UNIQUE,
	`index`	TEXT NOT NULL UNIQUE,
	`idPeriodical`	INTEGER NOT NULL,
	`idSubscriptionType`	INTEGER NOT NULL,
	`cost`	REAL NOT NULL,
	FOREIGN KEY(`idSubscriptionType`) REFERENCES `SubscriptionTypes`(`idSubscriptionType`),
	FOREIGN KEY(`idPeriodical`) REFERENCES `Periodicals`(`idPeriodical`)
);
CREATE TABLE IF NOT EXISTS `SubscriptionTypes` (
	`idSubscriptionType`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`nameSubscriptionType`	TEXT NOT NULL
);
INSERT INTO `SubscriptionTypes` (idSubscriptionType,nameSubscriptionType) VALUES (1,'месячная'),
 (2,'полугодовая'),
 (3,'квартальная'),
 (4,'ведомственная месячная'),
 (5,'ведомственная полугодовая'),
 (6,'ведомственная квартальная'),
 (7,'льготная месячная'),
 (8,'льготная полугодовая'),
 (9,'льготная квартальная');
CREATE TABLE IF NOT EXISTS `Periodicals` (
	`idPeriodical`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`namePeriodical`	TEXT NOT NULL,
	`periodicityInMonth`	INTEGER NOT NULL,
	`annotation`	TEXT
);
CREATE TABLE IF NOT EXISTS `Payments` (
	`idPayment`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`amount`	REAL NOT NULL,
	`payStatus`	INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS `Internationalization` (
	`idLocal`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`nameLocal`	TEXT NOT NULL UNIQUE
);
INSERT INTO `Internationalization` (idLocal,nameLocal) VALUES (1,'RU'),
 (2,'EN');
COMMIT;
