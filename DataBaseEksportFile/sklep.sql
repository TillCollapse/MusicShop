-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 24 Kwi 2016, 21:03
-- Wersja serwera: 5.6.21
-- Wersja PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `sklep`
--

DELIMITER $$
--
-- Procedury
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `adduser`(IN `imiep` VARCHAR(30) CHARSET utf8, IN `nazwiskop` VARCHAR(30) CHARSET utf8, IN `emailp` VARCHAR(60) CHARSET utf8, IN `haslop` VARCHAR(64) CHARSET utf8, IN `miastop` VARCHAR(30) CHARSET utf8, IN `kodp` VARCHAR(10) CHARSET utf8, IN `adresp` VARCHAR(30) CHARSET utf8)
    MODIFIES SQL DATA
insert into users(imie,nazwisko,email,haslo,miasto,kodpocztowy,adres)
values(imiep,nazwiskop,emailp,haslop,miastop,kodp,adresp)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dodaj_album`(IN `nazwap` VARCHAR(32) CHARSET utf8, IN `kategoriaidp` INT(11), IN `gatunekidp` INT(11), IN `producenciidp` INT(11), IN `iloscp` INT(4), IN `cenap` FLOAT(3), IN `opisp` VARCHAR(700) CHARSET utf8, IN `zdjeciep` VARCHAR(40) CHARSET utf8)
    MODIFIES SQL DATA
begin 
insert into produkt values('',nazwap,kategoriaidp,gatunekidp,producenciidp,iloscp,truncate(cenap,2),opisp,zdjeciep);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dodaj_autora`(IN `imiep` VARCHAR(30) CHARSET utf8, IN `nazwiskop` VARCHAR(30) CHARSET utf8, IN `pseudonimp` VARCHAR(30) CHARSET utf8)
    NO SQL
begin 

if imiep='' and nazwiskop='' then
insert into autor(pseudonim) values (upper(pseudonimp));

elseif  pseudonimp='' then
insert into autor(imie,nazwisko) values (upper(imiep),upper(nazwiskop));

else
insert into autor(imie,nazwisko,pseudonim) values (upper(imiep),upper(nazwiskop),upper(pseudonimp));
                                                   
end if;
                                                   
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dodaj_gatunek`(IN `nazwa` VARCHAR(30) CHARSET utf8)
    NO SQL
begin 

insert into gatunek values ('',upper(nazwa));

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dodaj_kategorie`(IN `nazwa` VARCHAR(32) CHARSET utf8)
    MODIFIES SQL DATA
begin 

insert into kategoria values ('',upper(nazwa));

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dodaj_prawa_dostepu`(IN `emailp` VARCHAR(60), IN `authorityp` VARCHAR(30))
    NO SQL
insert into authorities(email, authority) values(emailp, upper(authorityp))$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dodaj_producenta`(IN `nazwa` VARCHAR(30) CHARSET utf8)
    NO SQL
begin 

insert into producent values ('',upper(nazwa));

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dodaj_produkt`(IN `nazwap` VARCHAR(32) CHARSET utf8, IN `kategoriaidp` INT(11), IN `producentidp` INT(11), IN `iloscp` INT(4), IN `cenap` FLOAT(3), IN `opisp` VARCHAR(700), IN `zdjeciep` VARCHAR(40))
    MODIFIES SQL DATA
begin 
insert into produkt(nazwa,kategoriaid,producentid,ilosc,cena,opis,zdjecie) values(nazwap,kategoriaidp,producentidp,iloscp,truncate(cenap,2),opisp,zdjeciep);
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `albumy`
--
CREATE TABLE IF NOT EXISTS `albumy` (
`produktid` int(11)
,`nazwa_produktu` varchar(64)
,`kategoriaid` int(11)
,`nazwa_kategorii` varchar(32)
,`gatunekid` int(11)
,`nazwa_gatunku` varchar(30)
);
-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
`authorityid` int(11) NOT NULL,
  `email` varchar(60) COLLATE utf8_bin NOT NULL,
  `authority` varchar(15) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `authorities`
--

INSERT INTO `authorities` (`authorityid`, `email`, `authority`) VALUES
(13, 'wiktorwiacek@o2.pl', 'ROLE_USER'),
(14, 'admin@admin.pl', 'ROLE_ADMIN'),
(15, 'krzysio@o2.pl', 'ROLE_USER'),
(16, 'halina@wp.pl', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `autor`
--

CREATE TABLE IF NOT EXISTS `autor` (
`AUTORID` int(11) NOT NULL,
  `IMIE` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `NAZWISKO` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `PSEUDONIM` varchar(30) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `autor`
--

INSERT INTO `autor` (`AUTORID`, `IMIE`, `NAZWISKO`, `PSEUDONIM`) VALUES
(2, NULL, NULL, 'THE ROLLING STONES'),
(3, NULL, NULL, 'LINKIN PARK');

--
-- Wyzwalacze `autor`
--
DELIMITER //
CREATE TRIGGER `autor` BEFORE INSERT ON `autor`
 FOR EACH ROW SET 
	NEW.imie = upper(NEW.imie),
    NEW.nazwisko = upper(NEW.nazwisko),
    NEW.pseudonim = upper(NEW.pseudonim)
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `autors`
--

CREATE TABLE IF NOT EXISTS `autors` (
`AUTORSID` int(11) NOT NULL,
  `AUTORID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `gatunek`
--

CREATE TABLE IF NOT EXISTS `gatunek` (
`GATUNEKID` int(11) NOT NULL,
  `NAZWA` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `gatunek`
--

INSERT INTO `gatunek` (`GATUNEKID`, `NAZWA`) VALUES
(1, 'POP'),
(2, 'TECHNO'),
(3, 'ROCK'),
(4, 'DISCO'),
(5, 'RAP'),
(6, 'METAL'),
(7, 'COUNTRY'),
(8, 'TRANCE'),
(9, 'DANCE'),
(10, 'CLASSIC');

--
-- Wyzwalacze `gatunek`
--
DELIMITER //
CREATE TRIGGER `gatunek` BEFORE INSERT ON `gatunek`
 FOR EACH ROW SET NEW.nazwa = upper(NEW.nazwa)
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kategoria`
--

CREATE TABLE IF NOT EXISTS `kategoria` (
`KATEGORIAID` int(11) NOT NULL,
  `NAZWA` varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `kategoria`
--

INSERT INTO `kategoria` (`KATEGORIAID`, `NAZWA`) VALUES
(1, 'ALBUMY MUZYCZNE'),
(3, 'MP3'),
(4, 'MP4'),
(2, 'SŁUCHAWKI'),
(5, 'WINYLE');

--
-- Wyzwalacze `kategoria`
--
DELIMITER //
CREATE TRIGGER `kategoria` BEFORE INSERT ON `kategoria`
 FOR EACH ROW SET NEW.nazwa = upper(NEW.nazwa)
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `katprodgat`
--
CREATE TABLE IF NOT EXISTS `katprodgat` (
`produktid` int(11)
,`kategoria_nazwa` varchar(32)
,`gatunek_nazwa` varchar(30)
,`producent_nazwa` varchar(30)
);
-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `koszyk`
--

CREATE TABLE IF NOT EXISTS `koszyk` (
`KOSZYKID` int(11) NOT NULL,
  `STATUSID` int(11) NOT NULL,
  `USERID` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `koszyk`
--

INSERT INTO `koszyk` (`KOSZYKID`, `STATUSID`, `USERID`, `data`) VALUES
(1, 3, 25, '2016-03-02 17:18:18'),
(2, 2, 24, '2016-03-01 13:31:11'),
(3, 1, 24, '2016-03-01 13:31:37'),
(4, 2, 27, '2016-03-01 13:33:12'),
(5, 2, 27, '2016-03-01 13:33:32'),
(6, 1, 25, '2016-03-02 17:15:16');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `listazakupow`
--

CREATE TABLE IF NOT EXISTS `listazakupow` (
`LISTAZAKUPOWID` int(11) NOT NULL,
  `KOSZYKID` int(11) NOT NULL,
  `PRODUKTID` int(11) NOT NULL,
  `ILOSC` int(4) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `listazakupow`
--

INSERT INTO `listazakupow` (`LISTAZAKUPOWID`, `KOSZYKID`, `PRODUKTID`, `ILOSC`) VALUES
(1, 1, 23, 1),
(2, 1, 27, 1),
(3, 1, 26, 1),
(4, 1, 46, 1),
(6, 2, 43, 1),
(7, 3, 29, 1),
(8, 4, 47, 2),
(9, 5, 26, 1),
(10, 6, 23, 3),
(11, 6, 23, 3),
(12, 6, 23, 3),
(13, 3, 26, 1),
(14, 3, 46, 1),
(15, 3, 24, 1);

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `niealbumy`
--
CREATE TABLE IF NOT EXISTS `niealbumy` (
`produktid` int(11)
,`nazwa_produktu` varchar(64)
,`kategoriaid` int(11)
,`nazwa_kategorii` varchar(32)
,`producentid` int(11)
,`nazwa_producenta` varchar(30)
);
-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `persistent_logins`
--

CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `series` varchar(64) COLLATE utf8_bin NOT NULL,
  `token` varchar(64) COLLATE utf8_bin NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `producent`
--

CREATE TABLE IF NOT EXISTS `producent` (
`PRODUCENTID` int(11) NOT NULL,
  `NAZWA` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `producent`
--

INSERT INTO `producent` (`PRODUCENTID`, `NAZWA`) VALUES
(1, 'SONY MUSIC '),
(5, 'PROSTO'),
(6, 'LG'),
(7, 'OWSIAKMUSICPOLAND'),
(9, 'SONY'),
(10, 'ARGO RECORDS '),
(11, 'HARMONY'),
(12, 'ASUS'),
(13, 'SAMSUNG'),
(17, 'DECCA RECORDS'),
(18, 'WARNER BROS. RECORDS');

--
-- Wyzwalacze `producent`
--
DELIMITER //
CREATE TRIGGER `producent` BEFORE INSERT ON `producent`
 FOR EACH ROW SET NEW.nazwa = upper(NEW.nazwa)
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkt`
--

CREATE TABLE IF NOT EXISTS `produkt` (
`PRODUKTID` int(11) NOT NULL,
  `NAZWA` varchar(64) COLLATE utf8_bin NOT NULL,
  `KATEGORIAID` int(11) NOT NULL,
  `GATUNEKID` int(11) DEFAULT NULL,
  `AUTORSID` int(11) DEFAULT NULL,
  `PRODUCENTID` int(11) NOT NULL,
  `ILOSC` int(11) NOT NULL,
  `CENA` float NOT NULL,
  `OPIS` varchar(1000) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `ZDJECIE` varchar(40) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `produkt`
--

INSERT INTO `produkt` (`PRODUKTID`, `NAZWA`, `KATEGORIAID`, `GATUNEKID`, `AUTORSID`, `PRODUCENTID`, `ILOSC`, `CENA`, `OPIS`, `ZDJECIE`) VALUES
(23, 'Nirvana', 1, 3, NULL, 1, 23, 25, 'Składanka najpopularniejszych piosenek zespołu Nirvana, które podbijały światowe listy przebojów.', 'a926587af79eacd806f545c3bd186b4e.jpeg'),
(24, 'No fixed address', 1, 3, NULL, 1, 21, 45, 'Album z 2014 roku', '9988248d0836392173ccfc469f94eabc.jpg'),
(25, 'Perpetuum debile', 1, 3, NULL, 7, 3, 23.98, 'Nowa płyta', '0b8569986f3a2f9ccf53853f6c58f9a5.jpg'),
(26, 'QuadBeat2', 2, NULL, NULL, 6, 0, 100, 'Słuchawki do czołowych modeli telefonów LG G2 i G3', 'b1d248fd13919e12123bff1d55863c92.jpg'),
(27, 'Sony NWZ-W273B', 3, NULL, NULL, 9, 2, 299.98, 'Pływaj przy dźwiękach ulubionych nagrań: odtwarzacz NWZ-W273 jest całkowicie wodoodporny do głębokości 2 metrów (klasa ochrony IPX8).Brak jakichkolwiek przewodów gwarantuje autentyczną swobodę ruchów w czasie treningu', '34d796187b970e8d8ad24025f5fd4582.jpg'),
(28, 'Kaleidoscope', 1, 2, NULL, 1, 4, 55, 'Kaleidoscope – czwarty studyjny album holenderskiego DJ-a Tiësto wydany 6 października 2009 r.[2] Pierwszym singlem z tej płyty był utwór "I Will Be Here", który powstał we współpracy z zespołem Sneaky Sound System.', 'de8951a289970f7a8f364717e5b9b179.jpg'),
(29, 'Allegri String Quartet', 5, 10, NULL, 10, 2, 67, '', '0319d1816ef4264e462fe9271159e500.jpg'),
(34, 'XT10', 2, NULL, NULL, 9, 4, 5, '', '8415c09038955dea92e2eab4c9598e60.jpg'),
(43, 'After-Math', 5, 3, 2, 17, 1, 399.99, 'Czwarty w Wielkiej Brytanii i siódmy w Stanach Zjednoczonych album grupy The Rolling Stones.\r\nW 2003 album został sklasyfikowany na 108. miejscu listy 500 albumów wszech czasów magazynu Rolling Stone', 'After-Math.jpg'),
(45, 'A Thousand Suns', 1, 3, 3, 18, 50, 50, '"A Thousand Suns" – czwarty studyjny album amerykańskiej grupy Linkin Park, który swoją premierę w Polsce miał 13 września, a światową miał 14 września 2010 roku. Album wydany przez wytwórnię płytową Warner Bros. Records, zaś wyprodukowany przez Ricka Rubina i wokalistę zespołu Mike''a Shinodę.Pierwszym singlem promującym wydawnictwo był wydany 2 sierpnia The Catalyst. Płyta zadebiutowała na 2. miejscu listy OLiS w Polsce.13 października 2010 roku album uzyskał status złotej płyty w Polsce sprzedając się w nakładzie 10 000 egzemplarzy', 'A thousand suns.jpg'),
(46, 'Living Things', 1, 3, 3, 18, 48, 50, 'Living Things – piąty album studyjny amerykańskiego zespołu rockowego Linkin Park, który w Polsce ukazał się 25 czerwca, podczas gdy światowa premiera odbyła się 20 czerwca 2012. Chester Bennington, wokalista zespołu, zapowiedział, że płyta będzie połączeniem muzyki, którą zespół grał na początku działalności, i muzyki z ostatniej płyty, jednak piosenki w brzmieniu będą bardziej rockowe. Album promuje piosenka „Burn It Down”, która ukazała się 16 kwietnia. 24 maja został wydany nowy utwór zespołu „Lies Greed Misery”. Premiera odbyła się na antenie radia BBC 1 o godzinie 20:30. Tego dnia również wydano teledysk do utworu „Burn It Down”. Piosenka „Lies Greed Misery” pierwszy raz na żywo została zaśpiewana na koncercie Rock In Rio - Lisbona 2012. W wersji rozszerzonej albumu znajdują się dodatkowe utwory In The End, What I''ve Done, New Divide w wersji Live. 9 czerwca 2012, w celu promowania albumu, zespół Linkin Park wystąpił w Warszawie na Orange Warsaw Festival.', 'Living Things.jpg'),
(47, 'Minutes to Midnight', 1, 3, 3, 18, 3, 25.99, '"Minutes to Midnight" – trzeci studyjny album kalifornijskiej grupy Linkin Park, którego światowa premiera odbyła się 14 maja 2007 r. Europejska premiera miała miejsce 11 maja 2007 r., a amerykańska – 15 maja 2007 r. Płyta jednak wcześniej (4 maja) wyciekła do Internetu.\r\nProducentem "Minutes to Midnight" jest współzałożyciel zespołu Mike Shinoda i Rick Rubin.', 'Minutes to Midnight.jpg');

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `prodwkosz`
--
CREATE TABLE IF NOT EXISTS `prodwkosz` (
`koszykid` int(11)
,`userid` int(11)
,`statusid` int(11)
,`nazwa` varchar(64)
,`ilosc` int(4)
,`cena` float
);
-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `sessions`
--

CREATE TABLE IF NOT EXISTS `sessions` (
  `session_id` varchar(64) COLLATE utf8_bin NOT NULL,
  `session_user` int(11) NOT NULL,
  `session_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `session_ip` varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `STATUSID` int(11) NOT NULL,
  `STATUS` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `status`
--

INSERT INTO `status` (`STATUSID`, `STATUS`) VALUES
(1, 'niezarejestrowane'),
(2, 'zarejestrowane'),
(3, 'realizowane'),
(4, 'wysłane');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `userbackup`
--

CREATE TABLE IF NOT EXISTS `userbackup` (
`USERID` int(11) NOT NULL,
  `USERTYPEID` int(11) NOT NULL,
  `IMIE` varchar(30) COLLATE utf8_bin NOT NULL,
  `NAZWISKO` varchar(30) COLLATE utf8_bin NOT NULL,
  `EMAIL` varchar(60) COLLATE utf8_bin NOT NULL,
  `HASLO` varchar(64) COLLATE utf8_bin NOT NULL,
  `MIASTO` varchar(30) COLLATE utf8_bin NOT NULL,
  `KODPOCZTOWY` varchar(10) COLLATE utf8_bin NOT NULL,
  `ADRES` varchar(30) COLLATE utf8_bin NOT NULL,
  `czyadmin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`USERID` int(11) NOT NULL,
  `IMIE` varchar(30) COLLATE utf8_bin NOT NULL,
  `NAZWISKO` varchar(30) COLLATE utf8_bin NOT NULL,
  `EMAIL` varchar(60) COLLATE utf8_bin NOT NULL,
  `HASLO` varchar(64) COLLATE utf8_bin NOT NULL,
  `MIASTO` varchar(30) COLLATE utf8_bin NOT NULL,
  `KODPOCZTOWY` varchar(10) COLLATE utf8_bin NOT NULL,
  `ADRES` varchar(30) COLLATE utf8_bin NOT NULL,
  `enabled` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`USERID`, `IMIE`, `NAZWISKO`, `EMAIL`, `HASLO`, `MIASTO`, `KODPOCZTOWY`, `ADRES`, `enabled`) VALUES
(24, 'Wiktor', 'Wiącek', 'wiktorwiacek@o2.pl', '$2a$10$w/YRnjh1C1WsxjpZTLureemoa1oaWhtR7BN6gP8hl.f5/nsbHBHbS', 'Mielec', '39-300', 'Borowa 115', 1),
(25, 'admin', 'admin', 'admin@admin.pl', '$2a$10$HKtDiss/ManOdlzoZ13bYukkLtw3LwwhdaF/9/k16KKNb17Ets2vi', 'admin', '11-111', 'admin 111', 1),
(26, 'Krzysztof', 'Krzywy', 'krzysio@o2.pl', '$2a$10$aClqKs4KVc/KrL1xOpPSjOz1m.0J6Znd9h7N71CkodpKH8JJ4zRj.', 'Mielec', '39-300', 'Konopnickiej 14\\2', 1),
(27, 'Grażyna', 'Halina', 'halina@wp.pl', '$2a$10$aN8qjitXkKOdvBT55lYdp.HFMS2eRQJQJmkvCXox0J4sYQqblMQqi', 'Kraków', '12-345', 'Topolowa 32\\11', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uuser`
--

CREATE TABLE IF NOT EXISTS `uuser` (
`USERID` int(11) NOT NULL,
  `IMIE` varchar(30) COLLATE utf8_bin NOT NULL,
  `NAZWISKO` varchar(30) COLLATE utf8_bin NOT NULL,
  `EMAIL` varchar(60) COLLATE utf8_bin NOT NULL,
  `HASLO` varchar(30) COLLATE utf8_bin NOT NULL,
  `MIASTO` varchar(30) COLLATE utf8_bin NOT NULL,
  `KODPOCZTOWY` varchar(10) COLLATE utf8_bin NOT NULL,
  `ADRES` varchar(30) COLLATE utf8_bin NOT NULL,
  `czyadmin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `uuser`
--

INSERT INTO `uuser` (`USERID`, `IMIE`, `NAZWISKO`, `EMAIL`, `HASLO`, `MIASTO`, `KODPOCZTOWY`, `ADRES`, `czyadmin`) VALUES
(1, 'w', 'w', 'w', 'w', 'w', 'w', 'w', NULL);

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `widok`
--
CREATE TABLE IF NOT EXISTS `widok` (
`produktid` int(11)
,`prodnaz` varchar(64)
,`katnaz` varchar(32)
,`ilosc` int(4)
);
-- --------------------------------------------------------

--
-- Struktura widoku `albumy`
--
DROP TABLE IF EXISTS `albumy`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `albumy` AS select distinct `p`.`PRODUKTID` AS `produktid`,`p`.`NAZWA` AS `nazwa_produktu`,`k`.`KATEGORIAID` AS `kategoriaid`,`k`.`NAZWA` AS `nazwa_kategorii`,`g`.`GATUNEKID` AS `gatunekid`,`g`.`NAZWA` AS `nazwa_gatunku` from ((`gatunek` `g` join `produkt` `p`) join `kategoria` `k`) where ((`p`.`KATEGORIAID` = `k`.`KATEGORIAID`) and (`p`.`GATUNEKID` = `g`.`GATUNEKID`));

-- --------------------------------------------------------

--
-- Struktura widoku `katprodgat`
--
DROP TABLE IF EXISTS `katprodgat`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `katprodgat` AS select distinct `p`.`PRODUKTID` AS `produktid`,`kat`.`NAZWA` AS `kategoria_nazwa`,`gat`.`NAZWA` AS `gatunek_nazwa`,`prod`.`NAZWA` AS `producent_nazwa` from (((`kategoria` `kat` join `gatunek` `gat`) join `produkt` `p`) join `producent` `prod`) where ((`kat`.`KATEGORIAID` = `p`.`KATEGORIAID`) and ((`gat`.`GATUNEKID` = `p`.`GATUNEKID`) or isnull(`p`.`GATUNEKID`)) and (`p`.`PRODUCENTID` = `prod`.`PRODUCENTID`));

-- --------------------------------------------------------

--
-- Struktura widoku `niealbumy`
--
DROP TABLE IF EXISTS `niealbumy`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `niealbumy` AS select distinct `p`.`PRODUKTID` AS `produktid`,`p`.`NAZWA` AS `nazwa_produktu`,`k`.`KATEGORIAID` AS `kategoriaid`,`k`.`NAZWA` AS `nazwa_kategorii`,`prod`.`PRODUCENTID` AS `producentid`,`prod`.`NAZWA` AS `nazwa_producenta` from ((`produkt` `p` join `kategoria` `k`) join `producent` `prod`) where ((`p`.`KATEGORIAID` = `k`.`KATEGORIAID`) and isnull(`p`.`GATUNEKID`) and (`p`.`PRODUCENTID` = `prod`.`PRODUCENTID`));

-- --------------------------------------------------------

--
-- Struktura widoku `prodwkosz`
--
DROP TABLE IF EXISTS `prodwkosz`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `prodwkosz` AS select `koszyk`.`KOSZYKID` AS `koszykid`,`koszyk`.`USERID` AS `userid`,`koszyk`.`STATUSID` AS `statusid`,`produkt`.`NAZWA` AS `nazwa`,`listazakupow`.`ILOSC` AS `ilosc`,`produkt`.`CENA` AS `cena` from ((`produkt` join `koszyk`) join `listazakupow`) where (`listazakupow`.`PRODUKTID` = `produkt`.`PRODUKTID`);

-- --------------------------------------------------------

--
-- Struktura widoku `widok`
--
DROP TABLE IF EXISTS `widok`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `widok` AS select `produkt`.`PRODUKTID` AS `produktid`,`produkt`.`NAZWA` AS `prodnaz`,`kategoria`.`NAZWA` AS `katnaz`,`listazakupow`.`ILOSC` AS `ilosc` from ((`produkt` join `kategoria`) join `listazakupow`) where `produkt`.`PRODUKTID` in (select `listazakupow`.`PRODUKTID` from `listazakupow` where (`listazakupow`.`PRODUKTID` in ('24','23','22','21','20')));

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `authorities`
--
ALTER TABLE `authorities`
 ADD PRIMARY KEY (`authorityid`), ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
 ADD PRIMARY KEY (`AUTORID`);

--
-- Indexes for table `autors`
--
ALTER TABLE `autors`
 ADD PRIMARY KEY (`AUTORSID`,`AUTORID`), ADD KEY `AUTORSID` (`AUTORSID`,`AUTORID`), ADD KEY `AUTORID` (`AUTORID`);

--
-- Indexes for table `gatunek`
--
ALTER TABLE `gatunek`
 ADD PRIMARY KEY (`GATUNEKID`);

--
-- Indexes for table `kategoria`
--
ALTER TABLE `kategoria`
 ADD PRIMARY KEY (`KATEGORIAID`), ADD UNIQUE KEY `NAZWA` (`NAZWA`);

--
-- Indexes for table `koszyk`
--
ALTER TABLE `koszyk`
 ADD PRIMARY KEY (`KOSZYKID`), ADD KEY `ZAMID` (`STATUSID`), ADD KEY `STATUSID` (`STATUSID`), ADD KEY `USERID` (`USERID`);

--
-- Indexes for table `listazakupow`
--
ALTER TABLE `listazakupow`
 ADD PRIMARY KEY (`LISTAZAKUPOWID`), ADD KEY `KOSZYKID_2` (`KOSZYKID`), ADD KEY `PRODUKTID` (`PRODUKTID`);

--
-- Indexes for table `persistent_logins`
--
ALTER TABLE `persistent_logins`
 ADD PRIMARY KEY (`series`);

--
-- Indexes for table `producent`
--
ALTER TABLE `producent`
 ADD PRIMARY KEY (`PRODUCENTID`);

--
-- Indexes for table `produkt`
--
ALTER TABLE `produkt`
 ADD PRIMARY KEY (`PRODUKTID`), ADD KEY `GATUNEKID` (`GATUNEKID`,`PRODUCENTID`), ADD KEY `GATUNEKID_2` (`GATUNEKID`,`PRODUCENTID`), ADD KEY `PORDUCENTID` (`PRODUCENTID`), ADD KEY `PORDUCENTCIID` (`PRODUCENTID`), ADD KEY `KATEGORIAID` (`KATEGORIAID`), ADD KEY `KATEGORIAID_2` (`KATEGORIAID`), ADD KEY `autorsid` (`AUTORSID`);

--
-- Indexes for table `sessions`
--
ALTER TABLE `sessions`
 ADD PRIMARY KEY (`session_id`), ADD UNIQUE KEY `session_id` (`session_id`), ADD UNIQUE KEY `session_user_2` (`session_user`), ADD KEY `session_user` (`session_user`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
 ADD PRIMARY KEY (`STATUSID`);

--
-- Indexes for table `userbackup`
--
ALTER TABLE `userbackup`
 ADD PRIMARY KEY (`USERID`), ADD UNIQUE KEY `EMAIL` (`EMAIL`), ADD KEY `USERTYPEID` (`USERTYPEID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`USERID`), ADD UNIQUE KEY `EMAIL` (`EMAIL`);

--
-- Indexes for table `uuser`
--
ALTER TABLE `uuser`
 ADD PRIMARY KEY (`USERID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `authorities`
--
ALTER TABLE `authorities`
MODIFY `authorityid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT dla tabeli `autor`
--
ALTER TABLE `autor`
MODIFY `AUTORID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `autors`
--
ALTER TABLE `autors`
MODIFY `AUTORSID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `gatunek`
--
ALTER TABLE `gatunek`
MODIFY `GATUNEKID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT dla tabeli `kategoria`
--
ALTER TABLE `kategoria`
MODIFY `KATEGORIAID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT dla tabeli `koszyk`
--
ALTER TABLE `koszyk`
MODIFY `KOSZYKID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `listazakupow`
--
ALTER TABLE `listazakupow`
MODIFY `LISTAZAKUPOWID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT dla tabeli `producent`
--
ALTER TABLE `producent`
MODIFY `PRODUCENTID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT dla tabeli `produkt`
--
ALTER TABLE `produkt`
MODIFY `PRODUKTID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT dla tabeli `userbackup`
--
ALTER TABLE `userbackup`
MODIFY `USERID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
MODIFY `USERID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT dla tabeli `uuser`
--
ALTER TABLE `uuser`
MODIFY `USERID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `authorities`
--
ALTER TABLE `authorities`
ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`email`) REFERENCES `users` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `autors`
--
ALTER TABLE `autors`
ADD CONSTRAINT `autors_ibfk_2` FOREIGN KEY (`AUTORID`) REFERENCES `autor` (`AUTORID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `autors_ibfk_3` FOREIGN KEY (`AUTORSID`) REFERENCES `produkt` (`autorsid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `koszyk`
--
ALTER TABLE `koszyk`
ADD CONSTRAINT `koszyk_ibfk_2` FOREIGN KEY (`STATUSID`) REFERENCES `status` (`STATUSID`) ON DELETE CASCADE,
ADD CONSTRAINT `koszyk_ibfk_3` FOREIGN KEY (`USERID`) REFERENCES `users` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `listazakupow`
--
ALTER TABLE `listazakupow`
ADD CONSTRAINT `listazakupow_ibfk_1` FOREIGN KEY (`KOSZYKID`) REFERENCES `koszyk` (`KOSZYKID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `listazakupow_ibfk_2` FOREIGN KEY (`PRODUKTID`) REFERENCES `produkt` (`PRODUKTID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `produkt`
--
ALTER TABLE `produkt`
ADD CONSTRAINT `produkt_ibfk_4` FOREIGN KEY (`GATUNEKID`) REFERENCES `gatunek` (`GATUNEKID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `produkt_ibfk_5` FOREIGN KEY (`KATEGORIAID`) REFERENCES `kategoria` (`KATEGORIAID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `produkt_ibfk_6` FOREIGN KEY (`PRODUCENTID`) REFERENCES `producent` (`PRODUCENTID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `produkt_ibfk_7` FOREIGN KEY (`AUTORSID`) REFERENCES `autor` (`AUTORID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `sessions`
--
ALTER TABLE `sessions`
ADD CONSTRAINT `sessions_ibfk_1` FOREIGN KEY (`session_user`) REFERENCES `users` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Zdarzenia
--
CREATE DEFINER=`root`@`localhost` EVENT `clear_backup` ON SCHEDULE EVERY 1 MONTH STARTS '2014-12-04 11:17:14' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM uzytkownicy_backup WHERE datediff(data, NOW())>30$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
