
DROP TABLE IF EXISTS appointment;
DROP TABLE IF EXISTS hairdresser;
DROP TABLE IF EXISTS hairdresser_serv;
DROP TABLE IF EXISTS user;


CREATE TABLE `appointment` (
                               `id` bigint(20) NOT NULL,
                               `start` datetime DEFAULT NULL,
                               `hairdresser_id` bigint(20) DEFAULT NULL,
                               `serv_id` bigint(20) DEFAULT NULL,
                               `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `appointment`
--

INSERT INTO `appointment` (`id`, `start`, `hairdresser_id`, `serv_id`, `user_id`) VALUES
                                                                                      (1, '2021-11-23 07:30:00', 1, 1, 1),
                                                                                      (2, '2021-11-24 08:30:00', 1, 4, 2),
                                                                                      (3, '2021-11-22 10:30:00', 1, 1, 1),
                                                                                      (4, '2021-11-25 11:00:00', 1, 4, 2),
                                                                                      (5, '2021-11-27 09:29:50', 2, 5, 1),
                                                                                      (6, '2021-11-28 09:29:50', 2, 6, 2),
                                                                                      (7, '2021-11-25 09:29:50', 3, 7, 1),
                                                                                      (8, '2021-11-24 09:29:50', 3, 11, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hairdresser`
--

CREATE TABLE `hairdresser` (
                               `id` bigint(20) NOT NULL,
                               `description` varchar(255) DEFAULT NULL,
                               `firstname` varchar(255) DEFAULT NULL,
                               `image` varchar(255) DEFAULT NULL,
                               `lastname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `hairdresser`
--

INSERT INTO `hairdresser` (`id`, `description`, `firstname`, `image`, `lastname`) VALUES
                                                                                      (1, 'Master', 'Maria', 'resources/images/fris2.jpg', 'Majer'),
                                                                                      (2, 'Azubi', 'Ina', 'resources/images/fris3.jpg', 'Stein'),
                                                                                      (3, 'Master', 'Lena', 'resources/images/fris1.jpg', 'Müller');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hairdresser_serv`
--

CREATE TABLE `hairdresser_serv` (
                                    `id` bigint(20) NOT NULL,
                                    `description` varchar(255) DEFAULT NULL,
                                    `duration` bigint(20) DEFAULT NULL,
                                    `image` varchar(255) DEFAULT NULL,
                                    `price` int(11) DEFAULT NULL,
                                    `service_category` varchar(255) DEFAULT NULL,
                                    `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `hairdresser_serv`
--

INSERT INTO `hairdresser_serv` (`id`, `description`, `duration`, `image`, `price`, `service_category`, `title`) VALUES
                                                                                                                    (1, 'Ob kurz oder lang - wir scneiden die deine Wunschfrisur!', 1800000000000, 'resources/images/schneiden.jpg', 4000, 'DAMEN_HAARSCHNITT', 'Waschen & Schneiden'),
                                                                                                                    (2, 'Angesagte Styles und Looks - jetzt umstylen lassen!', 1800000000000, 'resources/images/FoehnenLegenStylen.jpg', 4000, 'DAMEN_HAARSCHNITT', 'Föhnen, Legen & Stylen'),
                                                                                                                    (3, 'Typveränderung gefällig? Unsere Locken halten in jedem Haar', 1800000000000, 'resources/images/locken.jpg', 2500, 'DAMEN_HAARSCHNITT', 'Locken machen'),
                                                                                                                    (4, 'Ansatz rausgewachsen? Jetzt schnell nachfärben lassen!', 3600000000000, 'resources/images/faerben.jpg', 3500, 'DAMEN_COLORATION', 'Ansatzfärbung'),
                                                                                                                    (5, 'Egal ob langes oder kurzes, glattes oder welliges Haar -Balayage ist im Trendund steht jedem!', 8100000000000, 'resources/images/Straenchen.jpg', 5500, 'DAMEN_COLORATION', 'Foliensträhnen'),
                                                                                                                    (6, 'Wir frischen deine Haarfarbe wieder auf - schnell &unkompliziert', 7800000000000, 'resources/images/Farbauffrischung.jpg', 2500, 'DAMEN_COLORATION', 'Farbauffrischung'),
                                                                                                                    (7, 'Lass dich Inspirieren. Wir haben den passenden Look für deinen Typ!', 1800000000000, 'resources/images/herrenschnitt_lang.jpg', 2400, 'HERREN_HAARSCHNITT', 'Langhaarschnitt'),
                                                                                                                    (8, 'Coole Looks für kurze Haare - unsere Maschinenhaarschnitte sind immer voll im Trend!', 1800000000000, 'resources/images/Maschinenhaarschnitt.jpg', 1500, 'HERREN_HAARSCHNITT', 'Maschinenhaarschnitt'),
                                                                                                                    (9, 'Typveränderung gefällig? Unsere Locken halten in jedem Haar', 1800000000000, 'resources/images/Kopfmassage.jpg', 550, 'HERREN_HAARSCHNITT', 'Kopfmassage mit Haartonik'),
                                                                                                                    (10, 'Beratungstermin für deinen Look zu besonderen Anlässen.', 1800000000000, 'resources/images/beratung.jpg', 2000, 'HOCHZEIT', 'Beratung - Frisur/Makeup'),
                                                                                                                    (11, 'Termin zum Stylen deiner Hochzeitfrisur.', 3600000000000, 'resources/images/hochzeit.jpg', 4000, 'HOCHZEIT', 'Hochzeitfrisur'),
                                                                                                                    (12, 'Wir nehmen dir den Hochzeitstress - mit unserem vollumfänglichem Hochzeitstyling', 5400000000000, 'resources/images/hochzeitspacket.jpg', 7000, 'HOCHZEIT', 'Hochzeitpacket');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `firstname` varchar(255) DEFAULT NULL,
                        `lastname` varchar(255) DEFAULT NULL,
                        `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `email`, `firstname`, `lastname`, `phone`) VALUES
                                                                         (1, 'max@web.de', 'Max', 'Mustermann', '0123456789'),
                                                                         (2, 'maja@web.de', 'Maja', 'Müller', '23452357888');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `appointment`
--
ALTER TABLE `appointment`
    ADD PRIMARY KEY (`id`),
  ADD KEY `hairdresser_id` (`hairdresser_id`),
  ADD KEY `serv_id` (`serv_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indizes für die Tabelle `hairdresser`
--
ALTER TABLE `hairdresser`
    ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `hairdresser_serv`
--
ALTER TABLE `hairdresser_serv`
    ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `appointment`
--
ALTER TABLE `appointment`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT für Tabelle `hairdresser`
--
ALTER TABLE `hairdresser`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `hairdresser_serv`
--
ALTER TABLE `hairdresser_serv`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `appointment`
--
ALTER TABLE `appointment`
    ADD CONSTRAINT `hairdresser_id` FOREIGN KEY (`hairdresser_id`) REFERENCES `hairdresser` (`id`),
  ADD CONSTRAINT `serv_id` FOREIGN KEY (`serv_id`) REFERENCES `hairdresser_serv` (`id`),
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;