# Toteutusdokumentti
## Tiedon tiivistys käyttäen Huffmanin koodausta
 

### Ohjelman toiminta ja rakenne yleisesti

Ohjelma pakkaa ja purkaa tiedostoja käyttäen Huffmanin koodausta. Ohjelma toimii komentoriviltä.
Käyttäjä käynnistää ohjelman antamalla ohjeistuksen mukaisen komennon ja riippuen siitä valitseeko käyttäjä tiedoston pakkaamisen vai purkamisen suorittaa ohjelma halutun toimenpiteen. Ohjelma osaa pakata kaiken mallisia tiedostoja, mutta purkaa vain samalla ohjelmalla pakattuja tiedostoja, joille on annettu .hf tiedostopääte.

Ohjelman toiminta perustuu Huffmanin koodaukseen, jossa pakattavasta tiedostosta luetaan sen sisältämät merkit merkki eli tavu (8 bittiä) kerrallaan ja luettuja merkkejä käsitellään niin, että niistä lasketaan yksittäisten merkkien toistumiskerrat joita apuna käyttäen muodostetaan Huffmanin puu. Huffmanin puun avulla annetaan tietylle merkille aina tietty binäärikoodi (useimmin esiintyvä merkki saa lyhyemmän koodin, harvemmin esiintyvä pidemmän koodin). Näin luetut merkit saadaan pakattua bitteinä tiiviimpään muotoon.

Itse Huffmanin puu, luettujen tavujen määrä sekä luettuja tavuja vastaavat binäärikoodit kirjoitetaan pakattuna uuteen tiedostoon. Ohjelma osaa sitten myöhemmin purkaa tämän pakatun tiedoston niin, että siitä luetaan ensin binäärinä kirjoitettu puu, pakkaamattoman tiedoston pituus ja näitä apuna käyttäen muunnetaan binäärit takaisin pakkaamattomaan muotoon. 


### Ohjelman käyttämät algoritmit ja tietorakenteet



### Ohjelman käsittelemä syöte
Ohjelma saa syötteenä polut pakattavaan ja purettavaan tiedostoon. Ohjelma lukee pakattavan tiedoston tavuvirran (BufferedInputStream) kautta tavu kerrallaan. Kirjoitus pakattavaan tiedostoon tapahtuu samoin tavuvirran kautta tavu kerrallaan. Näin, koska Javan tiedostoihin luku/kirjoittaminen tapahtuu tavuvirtojen kautta ja yksittäisiä bittejä ei voi suoraan siirtää. Tiedoston lukemisen ja kirjoittamisen välillä ohjelma käsittelee luettua tietoa kuitenkin myös bitti kerrallaan, jotta pakkaaminen on mahdollista. 


### Aika- ja tilavaativuudet

### Puutteet ja parannusehdotukset
Ohjelma ei pysty käsittelemään todella suuria tiedostoja, koska merkkien luku taulukkoon aiheuttaa OutOfMemoryError-virhetilanteen. Ohjaaja myös huomioi, että ohjelman kaikki testit eivät menneet läpi Windows-käyttöjärjestelmässä. Itselläni käytössä Linux Ubuntu ja kaikki testit toimivat.  Testit joissa tuli virheitä testasivat merkkilaskureita jne. ja itse ohjelman toimintaan eli pakkaamiseen ja purkamiseen tällä ei pitäisi olla vaikutusta.


Ohjelman lähdekoodin [API-kuvaus] (https://htmlpreview.github.io/?https://github.com/RIR/Huffman/blob/master/dokumentaatio/JavaDoc/apidocs/overview-summary.html)

### Lähteet

*https://fi.wikipedia.org/wiki/Huffmanin_koodaus*

*https://www.siggraph.org/education/materials/HyperGraph/video/mpeg/mpegfaq/huffman_tutorial.html*

*https://www.cs.helsinki.fi/u/thusu/opinnot/tiralab/documents/2009-05-21_husu_maarittely.tex*

*http://planetmath.org/huffmansalgorithm*

*https://www.youtube.com/watch?v=dM6us854Jk0*




... Vaiheessa ...
