# Toteutusdokumentti
## Tiedon tiivistys käyttäen Huffmanin koodausta
 

### Ohjelman toiminta ja rakenne yleisesti

Ohjelma pakkaa ja purkaa tiedostoja käyttäen Huffmanin koodausta. Ohjelma toimii komentoriviltä.
Käyttäjä käynnistää ohjelman antamalla ohjeistuksen mukaisen komennon ja riippuen siitä valitseeko käyttäjä tiedoston pakkaamisen vai purkamisen suorittaa ohjelma halutun toimenpiteen. Ohjelma osaa pakata kaiken mallisia tiedostoja, mutta purkaa vain samalla ohjelmalla pakattuja tiedostoja, joille on annettu .hf tiedostopääte.

Ohjelman toiminta perustuu Huffmanin koodaukseen, jossa pakattavasta tiedostosta luetaan sen sisältämät merkit merkki eli tavu (8 bittiä) kerrallaan ja luettuja merkkejä käsitellään niin, että niistä lasketaan yksittäisten merkkien toistumiskerrat joita apuna käyttäen muodostetaan Huffmanin puu. Huffmanin puun avulla annetaan tietylle merkille aina tietty binäärikoodi (useimmin esiintyvä merkki saa lyhyemmän koodin, harvemmin esiintyvä pidemmän koodin). Näin luetut merkit saadaan pakattua bitteinä tiiviimpään muotoon.

Itse Huffmanin puu, luettujen tavujen määrä sekä luettuja tavuja vastaavat binäärikoodit kirjoitetaan pakattuna uuteen tiedostoon. Ohjelma osaa sitten myöhemmin purkaa tämän pakatun tiedoston niin, että siitä luetaan ensin binäärinä kirjoitettu puu, pakkaamattoman tiedoston pituus ja näitä apuna käyttäen muunnetaan binäärit takaisin pakkaamattomaan muotoon. 

Huffmanin koodaus on tehokkaimmillaan kun luettavassa datassa samat merkit esiintyvät usein. Tällöin esim. 8-bittisenä esitettävä merkki voidaan kuvata yhdellä (1) bitillä ja joka kerta kun luettua merkkiä kirjoitetaan säästetään seitsemän (7) bittiä. 

### Ohjelman käyttämät algoritmit ja tietorakenteet

Ohjelman toiminnan voi jakaa selkeästi kahteen eri kokonaisuuteen: Tiedon pakkaamiseen ja purkamiseen.

***Tiedoston pakkaaminen***

Pakattaessa tiedostoa luetaan sen data käyttäen Javan BufferedInputStreamia joka käsittelee tietoa tavu kerrallaan. Tiedoston lukutoiminnot sijaitsevat Input-luokassa. Luettu tavu välitetään CharArrayBuilder-luokalle joka tallentaa sen merkkitaulukkoon. Näin tehdään kunnes kaikki tavut on luettu ja tallennettu. CharArrayBuilder palauttaa merkkitaulukon Inputille, joka taasen välittää sen Compress-luokalle.

Seuraavaksi Compress-luokka kutsuu FrequencyCounter-luokkaa laskemaan yksittäisten merkkien toistumiskerrat välittämällä sille parametrina aiemmin luodun merkkitaulukon. 

Kun Merkit ja niiden toistumiset ovat tallessa, luodaan Huffmanin puu. Compress-luokka välittää parametrina toistumistaulukon HuffmanTree-luokalle joka käyttäen apuna MinHeap-luokkaa (minimikeko) järjestää puulle solmut niin, että ne sijoittuvat toistumisen mukaan oikein. Lisäksi luokka rakentaa merkkijonotaulukon, jonka avulla merkkiä vastaavat bitit myöhemmin kirjoitetaan pakattuun tiedostoon. 

Puun solmujen järjestyslogiikka toimii niin, että aluksi minimikeko alustetaan yksittäisillä solmuilla, joille annetaan merkki, sen toistumisluku ja lapsiksi annetaan null-arvot. Solmuja yhdistellään niin kauan kunnes keon koko on yksi (1) seuraavalla tavalla: puusta poistetaan ensin kaksi (2) solmua toistumisarvon mukaisessa pienuusjärjestyksessä, luodaan uusi solmu jolle annetaan merkkiarvoksi null-arvo, toistumisarvoksi kahden poistetun solmun toistumisarvojen yhteenlaskettu arvo ja lapsisolmuiksi poistetut solmut. Solmu lisätään takaisin kekoon. Lopuksi palautetaan tämä puu Node-oliona eli viittaus puun juurisolmuun.

Luettuja merkkejä vastaavat binäärikoodit sisältävä merkkijonotaulukko luodaan niin, että luotua Huffmanin puuta käydään läpi rekursion avulla juuresta alkaen ja tyhjään merkkijonoon lisätään aina merkki 0 jos liikutaan vasempaan lapseen ja 1 jos liikutaan oikeaan lapseen. Kun sitteh kohdalle osuu lehtisolmu, eli sellainen solmu johon on tallennettu merkki, merkataan binäärikooditaulukkoon tämän merkin kohdalle liikutut askeleet eli esim. ```codes[k]= "001"``` .

Huffmanin puun ja binäärikooditaulukon rakentamisen jälkeen Compress-luokka kutsuu Output-luokan WriteHuffmanTree-metodia joka kirjoittaa Huffmanin puun binäärinä tiedostoon, jotta pakattu tiedosto saadaan myöhemmin purettua. Puun kirjoitus binäärinä onnistuu kulkemalla puuta rekursiolla juurisolmusta lapsisolmuihin ja aina edetessä seuraavaan solmuun kirjoitetaan merkki 0, kunnes tullaan lehtisolmun kohdalle, jolloin kirjoitetaan merkki 1 ja solmussa oleva merkki.

Seuraavaksi kirjoitetaan muistiin luetun tiedoston pituus tavuina. Tämä on tarpeellista, koska muuten voi syntyä virhetilanteita kun pakattua tiedostoa luetaan ja kirjoitettujen bittien määrä ei olekaan kahdeksalla jaollinen eli osu tasatavuihin. Pituus kirjoitetaan int-muuttujatyyppinä joka on 32-bittinen.

Lopuksi, käyttäen apuna aiemmin luotua merkkitaulukkoa ja binäärikooditaulukkoa kirjoitetaan pakattavan tiedoston merkit pakattuun tiedostoon niin, että luettava merkki jota normaalisti esittää yksi tavu eli 8 bittiä, esitetään binäärikooditaulukossa olevalla merkkiä vastaavalla bittijonolla, joka siis on yleensä lyhyempi kuin kahdeksan bittiä (varsinkin usein esiintyvillä merkeillä). Tässä vaiheessa tiedoston merkit luetaan siis toiseen kertaan, tosin merkkitaulukosta eikä uudestaan tiedostosta. 

Luettujen merkkien sijaan pakattuun tiedostoon kirjoitettiin siis:

Huffmanin puu = (Puun solmut-juurisolmu-lehtisolmut(erilaiset merkit) * 0-bitti) + (erilaiset merkit * (1-bitti + merkin tavuesitys 8 bittiä))

Luettujen merkkien määrä int-muuttujana = 32 bittiä

Luetut merkit * jokaista merkkiä vastaava uusi Huffmanin koodauksen mukainen binääriesitys

***Pakatun tiedoston purkaminen***



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
