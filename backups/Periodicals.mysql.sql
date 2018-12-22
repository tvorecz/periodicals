START TRANSACTION;

CREATE DATABASE periodicals;
USE periodicals;


CREATE TABLE IF NOT EXISTS UserRoles (
	idLocale	INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	nameRole	VARCHAR(50) NOT NULL
);

INSERT INTO UserRoles (idLocale,nameRole) VALUES (1,'администратор'),
 (2,'подписчик');
 
 CREATE TABLE IF NOT EXISTS SubscriptionTypes (
	idSubscriptionType	INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	nameSubscriptionType	VARCHAR(50) NOT NULL
);

INSERT INTO SubscriptionTypes (idSubscriptionType,nameSubscriptionType) VALUES (1,'месячная'),
 (2,'полугодовая'),
 (3,'квартальная'),
 (4,'ведомственная месячная'),
 (5,'ведомственная полугодовая'),
 (6,'ведомственная квартальная'),
 (7,'льготная месячная'),
 (8,'льготная полугодовая'),
 (9,'льготная квартальная');
 
CREATE TABLE IF NOT EXISTS Periodicals (
	idPeriodical	INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	namePeriodical	VARCHAR(50) NOT NULL,
	periodicityInMonth	INT NOT NULL,
	annotation	VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Payments (
	idPayment	INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	amount	DOUBLE NOT NULL,
	payStatus	INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS Internationalization (
	idLocal	INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	nameLocal	VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO Internationalization (idLocal,nameLocal) VALUES (1,'RU'),
 (2,'EN');
 
  CREATE TABLE IF NOT EXISTS Users (
	idUser	INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	login	VARCHAR(50) NOT NULL UNIQUE,
	password	VARCHAR(50) NOT NULL,
	idLocale	INT NOT NULL,
	email	VARCHAR(50) NOT NULL UNIQUE,
	idDefaultLocal	INT NOT NULL DEFAULT 1,
	FOREIGN KEY(idLocale) REFERENCES UserRoles(idLocale),
	FOREIGN KEY(idDefaultLocal) REFERENCES Internationalization(idLocal)
);
 
CREATE TABLE IF NOT EXISTS UserAddresses (
	idAddress	INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	address	VARCHAR(100) NOT NULL,
	idUser	INT,
	FOREIGN KEY(idUser) REFERENCES Users(idUser)
);

CREATE TABLE IF NOT EXISTS SubscriptionVariants (
	idSubscriptionVariant	INT NOT NULL UNIQUE,
	indexSubscription	VARCHAR(10) NOT NULL UNIQUE,
	idPeriodical	INT NOT NULL,
	idSubscriptionType	INT NOT NULL,
	cost	DOUBLE NOT NULL,
	FOREIGN KEY(idSubscriptionType) REFERENCES SubscriptionTypes(idSubscriptionType),
	FOREIGN KEY(idPeriodical) REFERENCES Periodicals(idPeriodical)
); 

CREATE TABLE IF NOT EXISTS UserSubscriptions (
	idUserSubscription	INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	idAddress	INT NOT NULL,
	idSubscriptionVariant	INT NOT NULL,
	dateBegin	DATETIME NOT NULL,
	dateEnd	DATETIME NOT NULL,
	idPayment	INT NOT NULL,
	FOREIGN KEY(idPayment) REFERENCES Payments(idPayment),
	FOREIGN KEY(idAddress) REFERENCES UserAddresses(idAddress),
	FOREIGN KEY(idSubscriptionVariant) REFERENCES SubscriptionVariants(idSubscriptionVariant)
);
COMMIT;
