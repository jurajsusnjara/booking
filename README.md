#Sustav za rezervaciju soba i apartmana

- - -

### [Projektni zadatak](https://bitbucket.org/mihajlo7/mihajlo/raw/bd92eca6827d01edbf4bb4119a1c202d3cb23714/Zadatak.pdf), [Informacije o projektnom zadatku](http://www.fer.unizg.hr/_download/repository/Informacije_o_projektnom_zadatku.pdf), [OPP-Projekt](http://www.fer.unizg.hr/predmet/opp/projekt) ###

## Tehnologije i alati ##
*- Još nije dogovoreno*

## Trenutni problemi ##
- Tehnički
    - dogovor oko tehnologije
- Sadržaj
    - slike i položaj turističkog naselja

- - -

## 1. Opis zadatka ##
- Turističko naselje ima na raspolaganju nekoliko objekata sa sobama i apartmanima
    - različiti kapaciteti
    - pogled na more ili park
    - dodatne usluge: rezervirano parkirno mjesto, bežični pristup internetu i satelitski TV prijamnik
    - tuš i WC u sobama
    - smještajni kapaciteti otvoreni od 1.5. do 30.9., kućni ljubimci nisu dozvoljeni
- Traži se učinkovit informacijski sustav koji će biti dostupan na web stranici
turističkog naselja
    - mogućnost pregleda zauzeća i rezervacije svih
smještajnih jedinica
    - za svaku smještajnu jedinicu mogućnost veze prema odvojenom sadržaju: detaljan opis i fotografije

## 2. Zahtjevi ##
### 2.1. Oznake objekata i smještajnih jedinica ###
- Oblik oznaka: *<oznaka objekta><broj kata>-<broj>*, primjer: *A2-3*
- 4 dvokatna objekta: A, B, C i D
- Neke smještajne jedinice imaju pogled na park, a neke na more
- Broj osoba po smještajnoj jedinici ovisi o objektu

### 2.2. Korisnici informacijskog sustava ###
- Vlasnik
    - Mihajlo upisuje podatke o njemu
    - upisuje podatke o svim smještajnim jedinicama: *objekt, oznaka, kapacitet, strana/pogled i posebni odvojeni sadržaj u kojem se nalazi detaljan opis i fotografije predmetne smještajne jedinice*
    - nakon prvog spajanja na sustav mora upisati svoju adresu elektroničke pošte i broj telefona
    - definira administratore i njihove ovlasti
    - podatke o administratorima može upisivati i mijenjati samo vlasnik
- Administrator
    - može po zahtjevu vlasnika, ali i posebnom pisanom zahtjevu korisnika
mijenjati datume rezervacije i smještajne jedinice registriranih korisnika, kao i njihove
dodatne usluge
    - nakon prve rezervacije registriranog korisnika, administrator ju u roku od 3
dana mora potvrditi ili poništiti
- Registrirani korisnik
    - može odabrati smještajnu jedinicu i dane za koje ju želi rezervirati, broj odraslih osoba i broj djece koja će biti u jedinici, dob djece (0-1 / 2-7 / 8-14 godina), kao i tražiti dodatne usluge. 
    - obavezni podaci kod registracije: ime, prezime, adresa (ulicu, kućni broj, grad i
zemlja) i adresa elektroničke pošte
    - neobavezan unos kontakt broja telefona
- Neregistrirani korisnik
    -  pristup pregledu podataka o smještajnim objektima i jedinicama, njihovom zauzeću ili raspoloživosti u danima u kojima je turističko naselje otvoreno

### 2.3. Posebne funkcionalnosti sustava ###
#### 2.3.1 Registracija korisnika ####
- Korisnik unosi i svoje podatke i potvrđuje zahtjev za registraciju
- Nakon registracije, korisniku se na adresu e-pošte šalje pozdravna poruka i traži se potvrda registracije „klikom na link“.
- Nakon što korisnik potvrdi registraciju, na adresu elektroničke pošte šalju mu se pristupni podaci.

#### 2.3.2. Rezervacija smještajne jedinice ####
- Sustav mora paziti na nemogućnost preklapanja termina, kao i raditi optimizaciju rezervacija
    - rezervacije se nastavljaju jedna na drugu s najmanjim mogućim brojem dana kada je neka
smještajna jedinica prazna
- Nakon postupka rezervacije jedinice, registriranom korisniku se šalje poruka elektroničkom
poštom o primitku rezervacije i informacijom da će ga u roku od tri dana kontaktirati
predstavnik turističkog naselja.

#### 2.3.5. Izvještaji i pregledi ####
- pregled zauzeća (rang lista) svih smještajnih jedinica po danima zauzeća
- pregled i rangiranje gradova i zemalja iz kojih dolaze gosti
- pregled posebnih usluga koje su najviše tražene
- pregled zauzeća jedinica po kalendarskom razdoblju
- ovisnost zahtjeva na posebne usluge o broju gostju i zemlji iz koje oni dolaze

### 2.4. Ostali zahtjevi sustava ###
- Sustav mora omogućiti istovremeni rad vlasnika sustava, svih administratora i neograničenog
broja registriranih korisnika
- Prilikom rada vlasnik i administratori sustava moraju moći
vidjeti broj i imena trenutno aktivnih drugih administratora i broj trenutno aktivnih
registriranih korisnika
- Sustav ima jednog vlasnika i do najviše 3 administratora
- Broj registriranih korisnika je neograničen

- - -

## Primjeri sličnih sustava ##
[[1]](https://www.geobookings.com/pages/demos.aspx?l=1), [[2]](https://dl.dropboxusercontent.com/u/11983574/fer/Room%20Booking%20Help%20-%20Full.pdf)