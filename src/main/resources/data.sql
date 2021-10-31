INSERT Into Partner (ID, EMAIL, NAME, VORNAME)
values (10001, 'claus@genion.de', 'Schaeffner', 'Claus');

INSERT Into Partner (ID, EMAIL, NAME, VORNAME)
values (10002, 'tanja@genion.de', 'Schaeffner', 'Tanja');

INSERT Into Partner (ID, EMAIL, NAME, VORNAME)
values (10003, 'philipp@genion.de', 'Schaeffner', 'Philipp');

INSERT Into Adresse (ID, HAUSNUMMER, ORT, POSTLEITZAHL, STRASSE, PARTNER_ID)
values (20001, '9b','FORCHHEIM', 91301, 'Am Kressenacker', 10001);

INSERT Into Adresse (ID, HAUSNUMMER, ORT, POSTLEITZAHL, STRASSE, PARTNER_ID)
values (20002, '6','FORCHHEIM', 91301, 'Georg-Kaffer-Strasse', 10001);