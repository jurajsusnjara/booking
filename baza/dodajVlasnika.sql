-- korisnikID = "vlasnik"
-- lozinka = "00000000"

INSERT INTO `mihajlodb`.`korisnik`
(`korisnikID`,
`ime`,
`prezime`,
`email`,
`telefon`,
`datumReg`,
`lozinka`,
`adresaID`,
`uloga`)
VALUES
("vlasnik",
"Vlasnik",
"Vlasnik",
"vlasnik@najljepsi.hr",
033681000,
date("1.1.1723."),
"p5/AÚOóĂ\"\tJ\đ\h\ş\p\Ă\ł\‹",  -- 00000000
Null,
3);
