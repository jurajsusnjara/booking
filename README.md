#Sustav za rezervaciju soba i apartmana

###[Projektni zadatak](https://bitbucket.org/mihajlo7/mihajlo/raw/bd92eca6827d01edbf4bb4119a1c202d3cb23714/Zadatak.pdf), [Informacije o projektnom zadatku](http://www.fer.unizg.hr/_download/repository/Informacije_o_projektnom_zadatku.pdf)

##Tehnologije i alati
*- Još nije dogovoreno*

##1. Opis zadatka
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

##2. Zahtjevi
###2.1. Oznake objekata i smještajnih jedinica
- Oblik oznaka: *<oznaka objekta><broj kata>-<broj>*, primjer: *A2-3*
- 4 dvokatna objekta: A, B, C i D
- Neke smještajne jedinice imaju pogled na park, a neke na more
- Broj osoba po smještajnoj jedinici ovisi o objektu

###2.2. Korisnici informacijskog sustava 
- 4 vrste korisnika
    - vlasnik
        - Mihajlo upisuje podatke o njemu
        - upisuje podatke o svim smještajnim jedinicama: *objekt, oznaka, kapacitet, strana/pogled i posebni odvojeni sadržaj u kojem se nalazi detaljan opis i fotografije predmetne smještajne jedinice*
        - nakon prvog spajanja na sustav mora upisati svoju adresu elektroničke pošte i broj telefona
        - definira administratore i njihove ovlasti
        - podatke o administratorima može upisivati i mijenjati samo vlasnik
    - administrator
        - može po zahtjevu vlasnika, ali i posebnom pisanom zahtjevu korisnika
mijenjati datume rezervacije i smještajne jedinice registriranih korisnika, kao i njihove
dodatne usluge
        - nakon prve rezervacije registriranog korisnika, administrator ju u roku od 3
dana mora potvrditi ili poništiti

    - registrirani korisnik
    - neregistrirani korisnik