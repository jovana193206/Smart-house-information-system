# Smart-house-information-system

Uvod:
Potrebno je projektovati informacioni sistem pametne kuce. Sistem se sastoji iz četiri
tipa uređaja:
● Uređaj za reprodukciju zvuka
● Alarm
● Planer
● Korisnički uređaj
Uređaji komuniciraju međusobno i sa deljenom bazom podataka. Potrebno je da
svaki uređaj ima zasebnu, specijalno osmišljenu aplikaciju, Aplikacije različitih
uređaja međusobno moraju komunicirati putem JMS. Baza podataka mora biti
MySQL. Aplikacije sa bazom komuniciraju korišćenjem JPA.

Opis rada sistema:
Uređaj za reprodukciju zvuka
Uređaj za reprodukciju zvuka može da:
● Reprodukuje zadatu pesmu korišćenjem internet pretraživača.
● Na zahtev pošalje imena svih pesama prethodno reprodukovanih od strane
zadatog korisnika.

Alarm:
Alarm može da:
● Navije alarm da zvoni u trenutku koji je zadat.
● Navije periodične alarme.
● Navije alarm da zvoni u jednom od ponuđenih trenutaka.
● Konfiguriše željeno zvono alarma.

Planer:
Planer služi za beleženje obaveza u kalendar. Prilikom unošenja obaveze moguće je
uneti destinaciju. Obaveze je moguće izlistavati, dodavati, menjati i brisati.
Planer u sebi ima implementiran i kalkulator razdaljine. Kalkulator razdaljine može
da:
● Izračuna vreme potrebno da se stigne od lokacije A na lokaciju B
● Izračuna vreme potrebno da se stigne od trenutne lokacije na lokaciju B
○ Određivanje trenutne lokacije mora se izvršiti programski.
Planer može da aktivira i podsetnik za polazak koji na osnovu vremena početka
obaveze i vremena potrebnog za dolazak navija potrebni alarm.

Korisnički uređaj:
Korisnički uređaj omogućava komunikaciju između korisnika sistema i ostalih
uređaja. On ne treba direktno da pristupa bazi.
