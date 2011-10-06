# ************************************************************
# Sequel Pro SQL dump
# Version 3408
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.16)
# Database: e_commerce
# Generation Time: 2011-10-05 19:36:05 +0000
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
  `username` varchar(64) COLLATE utf8_unicode_ci DEFAULT '',
  `password` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table adrese_za_dostavu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `adrese_za_dostavu`;

CREATE TABLE `adrese_za_dostavu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pravnog_lica` int(11) DEFAULT NULL,
  `adresa` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table aktivacija
# ------------------------------------------------------------

DROP TABLE IF EXISTS `aktivacija`;

CREATE TABLE `aktivacija` (
  `id` int(11) NOT NULL,
  `kljuc` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table ban_komentari
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ban_komentari`;

CREATE TABLE `ban_komentari` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '	',
  `id_kor` int(11) DEFAULT NULL,
  `id_admin` int(11) DEFAULT NULL,
  `komentar` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table fizicka_lica
# ------------------------------------------------------------

DROP TABLE IF EXISTS `fizicka_lica`;

CREATE TABLE `fizicka_lica` (
  `id` int(11) NOT NULL,
  `ime` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prezime` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `jmbg` varchar(13) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefon` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresa` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table forgot_pass
# ------------------------------------------------------------

DROP TABLE IF EXISTS `forgot_pass`;

CREATE TABLE `forgot_pass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `vreme` timestamp NULL DEFAULT NULL,
  `kljuc` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table kategorija
# ------------------------------------------------------------

DROP TABLE IF EXISTS `kategorija`;

CREATE TABLE `kategorija` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kategorija` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_kategorija` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table korisnici
# ------------------------------------------------------------

DROP TABLE IF EXISTS `korisnici`;

CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `korisnik` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sifra` varchar(16) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `email` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `tip` tinyint(1) NOT NULL,
  `aktivan` tinyint(1) DEFAULT '0',
  `banovan` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table korpa_za_kupovinu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `korpa_za_kupovinu`;

CREATE TABLE `korpa_za_kupovinu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `vreme` timestamp NULL DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table kupljeni_proizvodi
# ------------------------------------------------------------

DROP TABLE IF EXISTS `kupljeni_proizvodi`;

CREATE TABLE `kupljeni_proizvodi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_korpe` int(11) DEFAULT NULL,
  `id_proizvoda` int(11) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `pojedinacna_cena` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table pravna_lica
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pravna_lica`;

CREATE TABLE `pravna_lica` (
  `id` int(11) NOT NULL,
  `ime_firme` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresa` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grad` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pib` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `maticni_broj` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefon` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table proizvodi
# ------------------------------------------------------------

DROP TABLE IF EXISTS `proizvodi`;

CREATE TABLE `proizvodi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_kategorija` tinyint(4) DEFAULT NULL,
  `id_podkategorija` tinyint(4) DEFAULT NULL,
  `naziv` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `poreklo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `opis` varchar(1024) COLLATE utf8_unicode_ci DEFAULT '',
  `tip` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pakovanje` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `slika` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `stanje` smallint(6) DEFAULT '0',
  `minimum` tinyint(4) DEFAULT '1',
  `obrisan` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table proizvodi_dodatno
# ------------------------------------------------------------

DROP TABLE IF EXISTS `proizvodi_dodatno`;

CREATE TABLE `proizvodi_dodatno` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_proizvoda` int(11) DEFAULT NULL,
  `ime_atributa` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `opis_atributa` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `faktor_cene` decimal(5,3) DEFAULT '1.000',
  `faktor_kolicine` decimal(5,3) DEFAULT '1.000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




--
-- Dumping routines (PROCEDURE) for database 'e_commerce'
--
DELIMITER ;;

# Dump of PROCEDURE admin_add_akcija
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_add_akcija` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_add_akcija`(pid int, atr_ime varchar(32), opis varchar(512), f_cene double, f_kat double)
BEGIN
	insert into proizvodi_dodatno(id_proizvoda,ime_atributa,opis_atributa,faktor_cene,faktor_kategorije)
	values (pid,atr_ime,opis,f_cene,f_kat);
	select "success" as status;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_add_kategorija
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_add_kategorija` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_add_kategorija`(kategorija varchar(45))
BEGIN
	INSERT INTO kategorija VALUES (kategorija);
	SELECT * FROM kategorija AS k WHERE k.id= LAST_INSERT_ID();
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_add_podkategorija
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_add_podkategorija` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_add_podkategorija`(kategorija varchar(45), id_kategorija int)
BEGIN
	INSERT INTO kategorija VALUES (kategorija, id_kategorija);
	SELECT * FROM kategorija AS k WHERE k.id= LAST_INSERT_ID();
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_add_proizvod
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_add_proizvod` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_add_proizvod`(
  id_kategorija int, 
  naziv varchar(64), 
  poreklo varchar(64), 
	opis varchar(1024),  
  pakovanje varchar(16), 
	cena double, 
  id_podkategorija int,  
  stanje smallint, 
	minimum tinyint)
BEGIN
	INSERT INTO proizvodi (id_kategorija,naziv,poreklo,opis,pakovanje,cena,id_podkategorija,stanje,minimum) 
	VALUES (id_kategorija, naziv, poreklo, opis, pakovanje, cena, id_podkategorija,stanje,minimum);
	SELECT * FROM proizvodi AS p WHERE p.id= LAST_INSERT_ID();
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_change_user_password
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_change_user_password` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_change_user_password`(id int, password varchar(45))
BEGIN
	update korisnici as k set k.sifra= password
	where k.id = id;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_delete_proizvod
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_delete_proizvod` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_delete_proizvod`(id_proizvoda int)
BEGIN
	update proizvodi set obrisan=1
	where id=id_proizvoda;
    select 'success' as status;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_get_podkategorije
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_get_podkategorije` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_get_podkategorije`()
BEGIN
	select * from kategorija where id_kategorija is not NULL;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_get_porudzbenice
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_get_porudzbenice` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_get_porudzbenice`()
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
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_get_users`()
BEGIN
	select * from korisnici;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_login
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_login` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_login`(IN uname VARCHAR(64), IN psswd VARCHAR(64))
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
# Dump of PROCEDURE admin_porudzbenica_details
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_porudzbenica_details` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_porudzbenica_details`(porid int)
BEGIN
	select kzk.vreme, kzk.cena as ukupna_cena, kzk.status, kp.kolicina, kp.pojedinacna_cena, pr.naziv, pr.poreklo,pr.opis,pr.pakovanje, kat1.kategorija, kat2.kategorija as tip from korpa_za_kupovinu kzk, kupljeni_proizvodi kp, proizvodi pr, kategorija kat1, kategorija kat2
	where kp.id_korpe=kzk.id and kzk.id = porid and kp.id_proizvoda = pr.id and kat1.id = pr.id_kategorija and kat2.id = pr.id_podkategorija;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_update_kategorija
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_update_kategorija` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_update_kategorija`(id int, kategorija varchar(45))
BEGIN
	update kategorija as k set k.kategorija=kategorija
	where k.id=id;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_update_podkategorija
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_update_podkategorija` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_update_podkategorija`(id int, kategorija varchar(45), id_kategorija int)
BEGIN
	update kategorija as k set k.kategorija=kategorija, k.id_kategorija=id_kategorija
	where l.id=id;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE admin_update_proizvod
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `admin_update_proizvod` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `admin_update_proizvod`(
	id int, 
  id_kategorija int, 
  naziv varchar(64), 
  poreklo varchar(64), 
	opis varchar(1024),  
  pakovanje varchar(16), 
	cena double, 
  id_podkategorija int,  
  stanje smallint, 
	minimum tinyint)
BEGIN
	update proizvodi p
	set p.id_kategorija=id_kategorija, 
		p.naziv=naziv, 
		p.poreklo=poreklo, 
		p.opis=opis, 
		p.pakovanje=pakovanje, 
		p.cena=cena, 
		p.id_podkategorija=id_podkategorija,
		p.stanje = stanje,
		p.minimum = minimum
	where p.id=id;
    select 'success' as status;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE fizicko_lice_register
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `fizicko_lice_register` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `fizicko_lice_register`(
nov_korisnik varchar(45),
nov_sifra varchar(45),
nov_email varchar(45),

nov_ime varchar(45),
nov_prezime varchar(45),
nov_jmbg varchar(45),
nov_telefon varchar(45),
nov_adresa varchar(45)
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
    (id,ime,prezime,jmbg,telefon, adresa)
    values (nov_id,nov_ime, nov_prezime, nov_jmbg, nov_telefon, nov_adresa);
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
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `get_user_details`(uid int)
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
# Dump of PROCEDURE korisnik_activate
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `korisnik_activate` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `korisnik_activate`(kod varchar(64))
BEGIN
	declare user_id int;
	select id into user_id from aktivacija where kod = kljuc;
	if(user_id>0) then
		update korisnici set aktivan=1 where id=user_id;
		delete from aktivacija where id = user_id;
	end if;
	select user_id;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE korisnik_login
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `korisnik_login` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `korisnik_login`(
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
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `kupi`(
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
select 'success' as status;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE pravno_lice_register
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `pravno_lice_register` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `pravno_lice_register`(
nov_korisnik varchar(45),
nov_sifra varchar(45),
nov_email varchar(45),

nov_ime_firme varchar(45),
nov_adresa varchar(45),
nov_grad varchar(45),
nov_pib varchar(45),
nov_maticni_broj varchar(45),
nov_telefon varchar(45),

adrese varchar(2000)
)
BEGIN
declare id_postoji int default 0;
declare nov_id int;
declare pomocni varchar(2000);
declare pom_adresa varchar(45);
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
    set pomocni = adrese;
    while length(pomocni)>0 do
        set pom_adresa=substring(pomocni,-length(pomocni),locate(';',pomocni)-1);
        set pomocni=substring(pomocni,locate(';',pomocni)+1);
        insert into adrese_za_dostavu (id_pravnog_lica,adresa) 
        values (nov_id,pom_adresa);    
end while;
    select 'success' as status, md5(nov_id) as code, nov_email as email, nov_id as id;
end if;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE reset_pass
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `reset_pass` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `reset_pass`(new_pass varchar(64), hash_kljuc varchar(64))
BEGIN
	declare f_time datetime;
	declare uid int;
	select user_id into uid from forgot_pass where kljuc = hash_kljuc;
	if(uid>0) then
		select (vreme + interval 2 day) into f_time from forgot_pass where uid = user_id;  
		if(f_time>now()) then
			update korisnik set sifra = new_pass where id = uid;
			delete from forgot_pass where user_id = uid;
			select "success" as result;
		else
			select "expired" as result;
		end if;
	else
		select "wrong" as result;
	end if;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE select_kategorije
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `select_kategorije` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `select_kategorije`()
BEGIN
select id,kategorija from kategorija where id_kategorija is NULL;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE select_podkategorije
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `select_podkategorije` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `select_podkategorije`(
upis_id varchar(10)
)
BEGIN
select id,kategorija,id_kategorija from kategorija 
where id_kategorija like upis_id and id_kategorija is not NULL;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE select_proizvodi
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `select_proizvodi` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `select_proizvodi`(
naziv_proizvoda varchar(45),
kategorija_broj varchar(45),
podkategorija_broj varchar(45)
)
BEGIN
select p.id, p.naziv, p.cena, p.opis, p.poreklo, p.tip, p.pakovanje, p.slika,p.stanje,p.minimum, kat.kategorija, pod.kategorija as podkategorija, pd.ime_atributa, pd.opis_atributa,pd.faktor_cene, pd.faktor_kolicine
	from proizvodi p left join proizvodi_dodatno pd on p.id = pd.id_proizvoda, kategorija kat ,kategorija pod 
	where naziv like naziv_proizvoda 
	and p.id_kategorija=kat.id
	and p.id_podkategorija=pod.id
	and p.id_kategorija like kategorija_broj
	and p.id_podkategorija like podkategorija_broj
    and p.obrisan<>1;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE update_user_status
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `update_user_status` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `update_user_status`(uid int, ban tinyint, comment text, aname varchar(45))
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
# Dump of PROCEDURE user_forgot_pass
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `user_forgot_pass` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `user_forgot_pass`(uid int)
BEGIN
	declare cur_time datetime;
	declare hash_key varchar(45);
	declare md5_hash varchar(64);
	select now() into cur_time;
	select concat(cast(uid as char),cur_time) into hash_key;
	select md5(hash_key) into md5_hash;
	insert into forgot_pass(user_id,vreme,kljuc) values (uid,cur_time,md5_hash);
	select md5_hash;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE user_history
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `user_history` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 */ /*!50003 PROCEDURE `user_history`(id int)
BEGIN
	select * from 
		korpa_za_kupovinu kzk
	where kzk.id_user = id;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
