--
-- Tabelstructuur voor tabel `Spel`
--

CREATE TABLE IF NOT EXISTS `Spel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(25) NOT NULL,
  `nummer` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1000 ;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Spelbord`
--

CREATE TABLE IF NOT EXISTS `Spelbord` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(25) DEFAULT '',
  `spel_id` int(11) NOT NULL,
  `nummer` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Speler`
--

CREATE TABLE IF NOT EXISTS `Speler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(30) DEFAULT NULL,
  `naam` varchar(30) DEFAULT NULL COMMENT '	',
  `gebruikersnaam` varchar(30) NOT NULL,
  `wachtwoord` varchar(60) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=210 ;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Vak`
--

CREATE TABLE IF NOT EXISTS `Vak` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `spelbord_id` int(11) unsigned DEFAULT NULL,
  `type` tinyint(1) unsigned DEFAULT NULL,
  `posX` int(2) unsigned DEFAULT NULL,
  `posY` int(2) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=201 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
