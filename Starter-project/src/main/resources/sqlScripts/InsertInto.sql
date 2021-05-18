-- KREDIT PODACI

INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (nextval('kredit_seq'), 'Gotovinski', '1', 'Gotovinski krediti predstavljaju dodatna novcana sredstva koja banke odobravaju, a obicno ih karakterise jednostavan i brz postupak odobravanja.');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (nextval('kredit_seq'), 'Stambeni', '2', 'Stambeni kredit je namenjen kupovini nekog stambenog objekta i formalno klijent postaje njegov vlasnik ali i u principu na taj stan se stavlja hipoteka u korist banke koja je odobrila kredit.');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (nextval('kredit_seq'), 'Studentski', '3', 'Studentski kredit je namenjen samo i iskljucivo studentima i koristi se za finansiranje studija ili studentskih potreba poput placanja smestaja i hrane u studentskim domovima');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (nextval('kredit_seq'), 'Poljoprivredni', '4', 'Poljoprivredni kredit je vezan samo za ljude koji se bave poljoprivredom i sredstva koja su im potrebna moraju biti namenjena iskljucivo toj svrsi.');

-- KLIJENT PODACI

INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Ana', 'Zuber', 285937204, 2);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Darko', 'Daric', 176930472, 4);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Marija', 'Maric', 494520578, 1);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Luka', 'Lukic', 985648276, 3);

-- TIP_RACUNA PODACI

INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (nextval('tip_racuna_seq'), 'Tekuci', '1', 'Racun na temelju kojega se mogu uzimati cekovi i dogovoriti PREKORACENJE. Tekuci racuni obicno ne nose znatnije iznose KAMATA na pozitivna salda');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (nextval('tip_racuna_seq'),'Ziro', '2', 'Sluzi za uplatu primanja poput rente ili honorara, kao i za primanja u slucaju da ste clan nekog odbora te sukladno tome primate proviziju ili naknadu po sastanku');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (nextval('tip_racuna_seq'),'Stedni', '3', 'Racun s posebnom namjenom da se pomogne klijentima akumulirati velike svote novca ulaganjem malih, redovitih ustedjevina');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (nextval('tip_racuna_seq'),'Budzetski', '4', 'Racun kojim se pomaze individualnim korisnicima bancinih usluga da jednostavnije placaju redovite izdatke');


-- RACUN PODACI

INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Racun1', '1', 'opisRacuna1', 1, 4);
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Racun4', '2', 'opisRacuna2', 4, 1);
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Racun3', '3', 'opisRacuna3', 3, 2);
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Racun2', '4', 'opisRacuna4', 2, 3);


-- TEST PODACI

INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (-100, 'Test Kredit', '1', 'Test');
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (-100, 'Test Klijent', 'Test Klijent', 445787464, 2);
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (-100, 'Test tip racuna', '1', 'Test');
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (-100, 'Test Kredit', 'Test', 'Test', 1, 4);

