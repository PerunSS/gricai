# ************************************************************
# Sequel Pro SQL dump
# Version 3408
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.16)
# Database: e_commerce
# Generation Time: 2011-10-01 18:58:03 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table admin
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT '',
  `password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;

INSERT INTO `admin` (`id`, `username`, `password`)
VALUES
	(1,'admin','admin');

/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table adrese_za_dostavu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `adrese_za_dostavu`;

CREATE TABLE `adrese_za_dostavu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pravnog_lica` int(11) DEFAULT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `adrese_za_dostavu` WRITE;
/*!40000 ALTER TABLE `adrese_za_dostavu` DISABLE KEYS */;

INSERT INTO `adrese_za_dostavu` (`id`, `id_pravnog_lica`, `adresa`)
VALUES
	(1,4,'adresa 1'),
	(2,4,'adresa 2'),
	(3,4,'adresa 3');

/*!40000 ALTER TABLE `adrese_za_dostavu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table aktivacija
# ------------------------------------------------------------

DROP TABLE IF EXISTS `aktivacija`;

CREATE TABLE `aktivacija` (
  `id` int(11) NOT NULL,
  `kljuc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `aktivacija` WRITE;
/*!40000 ALTER TABLE `aktivacija` DISABLE KEYS */;

INSERT INTO `aktivacija` (`id`, `kljuc`)
VALUES
	(6,'1679091c5a880faf6fb5e6087eb1b2dc'),
	(7,'8f14e45fceea167a5a36dedd4bea2543'),
	(8,'c9f0f895fb98ab9159f51fd0297e236d'),
	(9,'45c48cce2e2d7fbdea1afc51c7c6ad26'),
	(10,'d3d9446802a44259755d38e6d163e820'),
	(11,'6512bd43d9caa6e02c990b0a82652dca'),
	(12,'c20ad4d76fe97759aa27a0c99bff6710'),
	(13,'c51ce410c124a10e0db5e4b97fc2af39'),
	(14,'aab3238922bcc25a6f606eb525ffdc56'),
	(15,'9bf31c7ff062936a96d3c8bd1f8f2ff3'),
	(16,'c74d97b01eae257e44aa9d5bade97baf'),
	(17,'70efdf2ec9b086079795c442636b55fb'),
	(18,'6f4922f45568161a8cdf4ad2299f6d23');

/*!40000 ALTER TABLE `aktivacija` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ban_komentari
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ban_komentari`;

CREATE TABLE `ban_komentari` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '	',
  `id_kor` int(11) DEFAULT NULL,
  `id_admin` int(11) DEFAULT NULL,
  `komentar` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `ban_komentari` WRITE;
/*!40000 ALTER TABLE `ban_komentari` DISABLE KEYS */;

INSERT INTO `ban_komentari` (`id`, `id_kor`, `id_admin`, `komentar`)
VALUES
	(1,1,1,'test'),
	(2,4,1,'test'),
	(3,5,1,'test'),
	(4,1,1,'test');

/*!40000 ALTER TABLE `ban_komentari` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table fizicka_lica
# ------------------------------------------------------------

DROP TABLE IF EXISTS `fizicka_lica`;

CREATE TABLE `fizicka_lica` (
  `id` int(11) NOT NULL,
  `ime` varchar(45) DEFAULT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `jmbg` varchar(45) DEFAULT NULL,
  `telefon` varchar(45) DEFAULT NULL,
  `adresa` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `fizicka_lica` WRITE;
/*!40000 ALTER TABLE `fizicka_lica` DISABLE KEYS */;

INSERT INTO `fizicka_lica` (`id`, `ime`, `prezime`, `jmbg`, `telefon`, `adresa`)
VALUES
	(3,'neko','nekic','123213','67675786',NULL),
	(5,'ime','sifra','email','telefon',NULL),
	(6,'ime','sifra','email2','telefon',NULL),
	(7,'Ime','123456','email@em.com','213213',NULL),
	(8,'Ime','123456','emailA@em.com','213213',NULL),
	(9,'Ime','123456','emailC@em.com','213213',NULL),
	(10,'Ime','123456','emailD@em.com','213213',NULL),
	(11,'Ime','123456','emailE@em.com','213213',NULL),
	(12,'Ime','123456','emailF@em.com','213213',NULL),
	(13,'Ime','123456','emailG@em.com','213213',NULL),
	(15,'Ime','123456','emailH@em.com','213213',NULL),
	(16,'Ime','123456','emailT@em.com','213213',NULL),
	(17,'kara','bananana','fewfwe@blad.com','1231534231',NULL);

/*!40000 ALTER TABLE `fizicka_lica` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table kategorija
# ------------------------------------------------------------

DROP TABLE IF EXISTS `kategorija`;

CREATE TABLE `kategorija` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kategorija` varchar(45) DEFAULT NULL,
  `id_kategorija` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `kategorija` WRITE;
/*!40000 ALTER TABLE `kategorija` DISABLE KEYS */;

INSERT INTO `kategorija` (`id`, `kategorija`, `id_kategorija`)
VALUES
	(1,'Vino',NULL),
	(2,'Belo',1),
	(3,'Crveno',1),
	(4,'Roze',1);

/*!40000 ALTER TABLE `kategorija` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table korisnici
# ------------------------------------------------------------

DROP TABLE IF EXISTS `korisnici`;

CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `korisnik` varchar(45) DEFAULT NULL,
  `sifra` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `tip` tinyint(4) NOT NULL,
  `aktivan` tinyint(1) DEFAULT '0',
  `banovan` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `korisnici` WRITE;
/*!40000 ALTER TABLE `korisnici` DISABLE KEYS */;

INSERT INTO `korisnici` (`id`, `korisnik`, `sifra`, `email`, `tip`, `aktivan`, `banovan`)
VALUES
	(1,'neaktivan','123456','neaktivan',0,0,1),
	(2,'banovan','123456','banovan',0,1,NULL),
	(3,'fizicka','123456','fizicka',0,1,0),
	(4,'pravna','123456','pravna',1,1,1),
	(5,'proba','sifra','email',0,0,1),
	(6,'proba2','sifra','email2',0,0,0),
	(7,'fizickaA','123456','email@em.com',0,0,0),
	(8,'fizickaB','123456','emailA@em.com',0,0,0),
	(9,'fizickaC','123456','emailC@em.com',0,0,0),
	(10,'fizickaD','123456','emailD@em.com',0,0,0),
	(11,'fizickaE','123456','emailE@em.com',0,0,0),
	(12,'fizickaF','123456','emailF@em.com',0,0,0),
	(13,'fizickaG','123456','emailG@em.com',0,0,0),
	(14,'pravnaA','123456','pravnaA@email.com',1,0,0),
	(15,'fizickaH','123456','emailH@em.com',0,0,0),
	(16,'fizickaT','123456','emailT@em.com',0,0,0),
	(17,'kara','bananana','fewfwe@blad.com',0,0,0),
	(18,'pravnaB','123456','pravnaB@email.com',1,0,0);

/*!40000 ALTER TABLE `korisnici` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table korpa_za_kupovinu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `korpa_za_kupovinu`;

CREATE TABLE `korpa_za_kupovinu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `vreme` varchar(45) DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `korpa_za_kupovinu` WRITE;
/*!40000 ALTER TABLE `korpa_za_kupovinu` DISABLE KEYS */;

INSERT INTO `korpa_za_kupovinu` (`id`, `id_user`, `vreme`, `cena`, `status`)
VALUES
	(2,0,'2011-10-01 20:31:14',0,'pending'),
	(3,9,'2011-10-01 20:32:56',1000,'pending');

/*!40000 ALTER TABLE `korpa_za_kupovinu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table kupljeni_proizvodi
# ------------------------------------------------------------

DROP TABLE IF EXISTS `kupljeni_proizvodi`;

CREATE TABLE `kupljeni_proizvodi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_korpe` int(11) DEFAULT NULL,
  `id_proizvoda` varchar(45) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `pojedinacna_cena` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `kupljeni_proizvodi` WRITE;
/*!40000 ALTER TABLE `kupljeni_proizvodi` DISABLE KEYS */;

INSERT INTO `kupljeni_proizvodi` (`id`, `id_korpe`, `id_proizvoda`, `kolicina`, `pojedinacna_cena`)
VALUES
	(6,2,'1',2,200),
	(7,2,'2',4,400),
	(8,2,'3',1,300),
	(9,2,'2',3,100),
	(10,3,'1',2,200),
	(11,3,'2',4,400),
	(12,3,'3',1,300),
	(13,3,'2',3,100);

/*!40000 ALTER TABLE `kupljeni_proizvodi` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pravna_lica
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pravna_lica`;

CREATE TABLE `pravna_lica` (
  `id` int(11) NOT NULL,
  `ime_firme` varchar(45) DEFAULT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  `grad` varchar(45) DEFAULT NULL,
  `pib` varchar(45) DEFAULT NULL,
  `maticni_broj` varchar(45) DEFAULT NULL,
  `telefon` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pravna_lica` WRITE;
/*!40000 ALTER TABLE `pravna_lica` DISABLE KEYS */;

INSERT INTO `pravna_lica` (`id`, `ime_firme`, `adresa`, `grad`, `pib`, `maticni_broj`, `telefon`)
VALUES
	(4,'firma','adresa 1','grad 1','212','324342','899898'),
	(14,'ime','adresa','grad','123','123456','5454'),
	(18,'ko te jebe','neka 1','bg','432423','32423','23423');

/*!40000 ALTER TABLE `pravna_lica` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table proizvodi
# ------------------------------------------------------------

DROP TABLE IF EXISTS `proizvodi`;

CREATE TABLE `proizvodi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_kategorija` int(11) DEFAULT NULL,
  `naziv` varchar(45) DEFAULT NULL,
  `poreklo` varchar(45) DEFAULT NULL,
  `opis` longtext,
  `tip` varchar(45) DEFAULT NULL,
  `pakovanje` varchar(45) DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `id_podkategorija` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `proizvodi` WRITE;
/*!40000 ALTER TABLE `proizvodi` DISABLE KEYS */;

INSERT INTO `proizvodi` (`id`, `id_kategorija`, `naziv`, `poreklo`, `opis`, `tip`, `pakovanje`, `cena`, `id_podkategorija`)
VALUES
	(1,1,'Belo1',NULL,'belance1',NULL,NULL,200,2),
	(2,1,'Belo2',NULL,'belance2',NULL,NULL,300,2),
	(3,1,'Crno1',NULL,'crnance1',NULL,NULL,250,3),
	(4,1,'Roze1',NULL,'rozance1',NULL,NULL,150,4),
	(5,1,'Roze2',NULL,'rozance2',NULL,NULL,170,4),
	(6,1,'Roze3',NULL,'rozance3',NULL,NULL,210,4);

/*!40000 ALTER TABLE `proizvodi` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping routines (PROCEDURE) for database 'e_commerce'
--
DELIMITER ;;

# Dump of PROCEDURE admin_get_porudzbenice
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_get_porudzbenice` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `admin_get_porudzbenice`()
BEGIN
	select * 
	from korpa_za_kupovinu kzk, korisnici k
	where kzk.id_user = k.id;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_get_users
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_get_users` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `admin_get_users`()
BEGIN
	select * from korisnici;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_login
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_login` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `admin_login`(IN uname VARCHAR(64), IN psswd VARCHAR(64))
BEGIN
	DECLARE exist INT;
	SELECT COUNT(*) INTO exist FROM admin a WHERE a.username = uname AND a.password = psswd;
	IF(exist = 1) THEN
		SELECT id AS result FROM admin a WHERE a.username = uname AND a.password = psswd;	
	ELSE
		SELECT 0 AS result;
	END IF;
   
 END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE fizicko_lice_register
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `fizicko_lice_register` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `fizicko_lice_register`(
nov_korisnik varchar(45),
nov_sifra varchar(45),
nov_email varchar(45),

nov_ime varchar(45),
nov_prezime varchar(45),
nov_jmbg varchar(45),
nov_telefon varchar(45)
)
BEGIN
declare id_postoji int default 0;
declare nov_id int;
select count(id) 
    from korisnici
    where email=nov_email or korisnik=nov_korisnik
    into id_postoji;
if id_postoji>0 then
    select 'exists' as error;
else
    insert into korisnici
    (korisnik, sifra, email, tip)
    values (nov_korisnik, nov_sifra, nov_email, 0);
    set nov_id = last_insert_id();
    insert into fizicka_lica
    (id,ime,prezime,jmbg,telefon)
    values (nov_id,nov_ime, nov_sifra, nov_email, nov_telefon);
    insert into aktivacija
    (id,kljuc)
    values (nov_id, md5(nov_id));
    select 'success' as status, md5(nov_id) as code, nov_email as email, nov_id as id;
end if;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE get_user_details
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `get_user_details` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `get_user_details`(uid int)
BEGIN
	declare utip int;
	select tip into utip from korisnici where id = uid;
	if(utip = 0) then
		select fl.ime,fl.prezime,fl.jmbg,fl.telefon,fl.adresa,k.korisnik,k.email,k.tip,k.aktivan,k.banovan
			from fizicka_lica fl ,korisnici k 
			where k.id = uid and k.id=fl.id;
	else
		select pl.ime_firme, pl.adresa, pl.grad, pl.pib, pl.maticni_broj, pl.telefon, azd.adresa as adresa_za_dostavu,k.korisnik,k.email,k.tip,k.aktivan,k.banovan
			from pravna_lica pl, adrese_za_dostavu azd, korisnici k 
			where pl.id = azd.id_pravnog_lica and pl.id = uid and k.id=pl.id;
	end if;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE korisnik_login
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `korisnik_login` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `korisnik_login`(
korisnik_unos nvarchar(45),
sifra_unos nvarchar(45))
BEGIN
declare id_korisnik int;
declare ban tinyint(1);
declare aktiv tinyint(1);
declare tip_korisnika tinyint(4);
set id_korisnik=0;
select id, banovan, aktivan, tip
    from korisnici 
    where korisnik=korisnik_unos and sifra_unos=sifra
    into id_korisnik, ban, aktiv, tip_korisnika;
if id_korisnik!=0 then
    if aktiv=1 and ban=0 then
        if tip_korisnika=0 then
            select korisnici.id, ime
            from korisnici
            left join fizicka_lica on korisnici.id=fizicka_lica.id
            where korisnici.id=id_korisnik;
        else
            select korisnici.id, ime_firme
            from korisnici
            left join pravna_lica on korisnici.id=pravna_lica.id
            where korisnici.id=id_korisnik;
        end if;
    elseif aktiv=0 then
        select 'not_activated' as error;
    else 
        select 'banned' as error;
    end if;
    
else
    select 'wrong' as error;
end if;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE kupi
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `kupi` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `kupi`(
user_id int,
total double,
proizvodi varchar(2000)
)
BEGIN
declare pomocni varchar(2000) default proizvodi;
declare id_proiz varchar(20);
declare po_cena varchar(20);
declare po_kol varchar(20);
declare korpa_id int;
insert into korpa_za_kupovinu (id_user,vreme,cena,status)
    values (user_id,now(),total,'pending');
set korpa_id=last_insert_id();    
while length(pomocni)>0 do
    set id_proiz=substring(pomocni,-length(pomocni),locate(';',pomocni)-1);
    set pomocni=substring(pomocni,locate(';',pomocni)+1);
    set po_cena=substring(pomocni,-length(pomocni),locate(';',pomocni)-1);
    set pomocni=substring(pomocni,locate(';',pomocni)+1);
    set po_kol=substring(pomocni,-length(pomocni),locate(';',pomocni)-1);
    set pomocni=substring(pomocni,locate(';',pomocni)+1);
    insert into kupljeni_proizvodi (id_korpe,id_proizvoda,kolicina,pojedinacna_cena) 
    values (korpa_id,id_proiz,po_kol,po_cena);    
end while;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE pravno_lice_register
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `pravno_lice_register` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `pravno_lice_register`(
nov_korisnik varchar(45),
nov_sifra varchar(45),
nov_email varchar(45),

nov_ime_firme varchar(45),
nov_adresa varchar(45),
nov_grad varchar(45),
nov_pib varchar(45),
nov_maticni_broj varchar(45),
nov_telefon varchar(45)
)
BEGIN
declare id_postoji int default 0;
declare nov_id int;
select count(id) 
    from korisnici
    where email=nov_email or korisnik=nov_korisnik
    into id_postoji;
if id_postoji>0 then
    select 'exists' as error;
else
    insert into korisnici
    (korisnik, sifra, email, tip)
    values (nov_korisnik, nov_sifra, nov_email, 1);
    set nov_id = last_insert_id();
    insert into pravna_lica
    (id,ime_firme,adresa,grad,pib,maticni_broj,telefon)
    values (nov_id,nov_ime_firme,nov_adresa,nov_grad,nov_pib,nov_maticni_broj,nov_telefon);
    insert into aktivacija
    (id,kljuc)
    values (nov_id, md5(nov_id));
    select 'success' as status, md5(nov_id) as code, nov_email as email, nov_id as id;
end if;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE select_kategorije
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `select_kategorije` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `select_kategorije`()
BEGIN
select id,kategorija from kategorija where id_kategorija is NULL;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE select_podkategorije
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `select_podkategorije` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `select_podkategorije`(
upis_id int
)
BEGIN
select id,kategorija from kategorija where id_kategorija=upis_id;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE select_proizvodi
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `select_proizvodi` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `select_proizvodi`(
naziv_proizvoda varchar(45),
kategorija_broj varchar(45),
podkategorija_broj varchar(45)
)
BEGIN
select proizvodi.id, naziv, cena, opis, kat.kategorija, pod.kategorija as podkategorija
    from proizvodi
    left join kategorija kat on proizvodi.id_kategorija=kat.id
    left join kategorija pod on proizvodi.id_podkategorija=pod.id
    where naziv like naziv_proizvoda 
    and proizvodi.id_kategorija like kategorija_broj
    and proizvodi.id_podkategorija like podkategorija_broj;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE update_user_status
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `update_user_status` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`pishta`@`%`*/ /*!50003 PROCEDURE `update_user_status`(uid int, ban tinyint, comment text, aname varchar(45))
BEGIN
	declare adminid int;
	update korisnici set banovan = ban where id = uid;
	if (comment <>'') then
		select id into adminid from admin where username = aname;
		insert into ban_komentari(id_kor,id_admin,komentar) values (uid,adminid,comment);
	end if;
	select banovan from korisnici where id=uid;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
