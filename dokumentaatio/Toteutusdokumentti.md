# Toteutusdokumentti
## Tiedon tiivistäminen käyttäen Huffmanin koodausta
 
### Ohjelman toiminta ja rakenne yleisesti

Ohjelma pakkaa ja purkaa tiedostoja käyttäen Huffmanin koodausta. Ohjelma toimii komentoriviltä.
Käyttäjä käynnistää ohjelman antamalla ohjeistuksen mukaisen komennon ja riippuen siitä valitseeko käyttäjä tiedoston pakkaamisen vai purkamisen suorittaa ohjelma halutun toimenpiteen. Ohjelma osaa pakata kaiken mallisia tiedostoja, mutta purkaa vain samalla ohjelmalla pakattuja tiedostoja, joille on annettu .hf tiedostopääte.

Ohjelman toiminta perustuu Huffmanin koodaukseen, jossa pakattavasta tiedostosta luetaan sen sisältämät merkit merkki eli tavu (8 bittiä) kerrallaan ja luettuja merkkejä käsitellään niin, että niistä lasketaan yksittäisten merkkien toistumiskerrat joita apuna käyttäen muodostetaan Huffmanin puu. Huffmanin puun avulla annetaan tietylle merkille aina tietty binäärikoodi (useimmin esiintyvä merkki saa lyhyemmän koodin, harvemmin esiintyvä pidemmän koodin). Näin luetut merkit saadaan pakattua bitteinä tiiviimpään muotoon.

Itse Huffmanin puu, luettujen tavujen määrä sekä luettuja tavuja vastaavat binäärikoodit kirjoitetaan pakattuna uuteen tiedostoon. Ohjelma osaa sitten myöhemmin purkaa tämän pakatun tiedoston niin, että siitä luetaan ensin binäärinä kirjoitettu puu, pakkaamattoman tiedoston pituus ja näitä apuna käyttäen muunnetaan binäärit takaisin pakkaamattomaan muotoon. 

### Ohjelman käyttämät algoritmit ja tietorakenteet

Ohjelman toiminnan voi jakaa selkeästi kahteen eri kokonaisuuteen: Tiedon pakkaamiseen ja purkamiseen.

#### Tiedoston pakkaaminen

Pakattaessa tiedostoa luetaan sen data käyttäen Javan ```BufferedInputStream```ia joka käsittelee tietoa tavu kerrallaan. Tiedoston lukutoiminnot sijaitsevat ```Input```-luokassa. Luettu tavu välitetään ```CharArrayBuilder```-luokalle joka tallentaa sen merkkitaulukkoon. Näin tehdään kunnes kaikki tavut on luettu ja tallennettu. ```CharArrayBuilder``` palauttaa merkkitaulukon ```Input```ille, joka taasen välittää sen ```Compress```-luokalle.

Seuraavaksi ```Compress```-luokka kutsuu ```FrequencyCounter```-luokkaa laskemaan yksittäisten merkkien toistumiskerrat välittämällä sille parametrina aiemmin luodun merkkitaulukon. 

Kun Merkit ja niiden toistumiset ovat tallessa, luodaan Huffmanin puu. ```Compress```-luokka välittää parametrina toistumistaulukon ```HuffmanTree```-luokalle joka käyttäen apuna ```MinHeap```-luokkaa (minimikeko) järjestää puulle solmut niin, että ne sijoittuvat toistumisen mukaan oikein. Lisäksi luokka rakentaa merkkijonotaulukon ```String[] binaryCodes```, jonka avulla merkkiä vastaavat bitit myöhemmin kirjoitetaan pakattuun tiedostoon. 

Puun solmujen järjestyslogiikka toimii niin, että aluksi minimikeko alustetaan yksittäisillä solmuilla, joille annetaan merkki, sen toistumisluku ja lapsiksi annetaan null-arvot. Solmuja yhdistellään niin kauan kunnes keon koko on yksi (1) seuraavalla tavalla: puusta poistetaan ensin kaksi (2) solmua toistumisarvon mukaisessa pienuusjärjestyksessä, luodaan uusi solmu jolle annetaan merkkiarvoksi ```null```-arvo, toistumisarvoksi kahden poistetun solmun toistumisarvojen yhteenlaskettu arvo ja lapsisolmuiksi poistetut solmut. Solmu lisätään takaisin kekoon. Lopuksi palautetaan tämä puu ```Node```-oliona eli viittaus puun juurisolmuun.

Luettuja merkkejä vastaavat binäärikoodit sisältävä merkkijonotaulukko luodaan niin, että luotua Huffmanin puuta käydään läpi rekursion avulla juuresta alkaen ja tyhjään merkkijonoon lisätään aina merkki ```0``` jos liikutaan vasempaan lapseen ja ```1``` jos liikutaan oikeaan lapseen. Kun sitten kohdalle osuu lehtisolmu, eli sellainen solmu johon on tallennettu merkki, merkataan binäärikooditaulukkoon tämän merkin kohdalle liikutut askeleet eli esim. ```codes[k]= "001"``` .

Huffmanin puun ja binäärikooditaulukon rakentamisen jälkeen ```Compress```-luokka kutsuu ```Output```-luokan ```writeHuffmanTree```-metodia joka kirjoittaa Huffmanin puun binäärinä tiedostoon, jotta pakattu tiedosto saadaan myöhemmin purettua. Puun kirjoitus binäärinä onnistuu kulkemalla puuta rekursiolla juurisolmusta lapsisolmuihin ja aina edetessä seuraavaan solmuun kirjoitetaan merkki ```0```, kunnes tullaan lehtisolmun kohdalle, jolloin kirjoitetaan merkki ```1``` ja solmussa oleva merkki ```Node.getChar()```.

Seuraavaksi kirjoitetaan muistiin luetun tiedoston pituus tavuina. Tämä on tarpeellista, koska muuten voi syntyä virhetilanteita kun pakattua tiedostoa luetaan ja kirjoitettujen bittien määrä ei olekaan kahdeksalla jaollinen eli osu tasatavuihin. Pituus kirjoitetaan ```int```-muuttujatyyppinä joka on 32-bittinen.

Lopuksi, käyttäen apuna aiemmin luotua merkkitaulukkoa ja binäärikooditaulukkoa kirjoitetaan pakattavan tiedoston merkit pakattuun tiedostoon niin, että luettava merkki jota normaalisti esittää yksi tavu eli 8 bittiä, esitetään binäärikooditaulukossa olevalla merkkiä vastaavalla bittijonolla, joka siis on yleensä lyhyempi kuin kahdeksan bittiä (varsinkin usein esiintyvillä merkeillä). Tässä vaiheessa tiedoston merkit luetaan siis toiseen kertaan, tosin merkkitaulukosta eikä uudestaan tiedostosta. 

Pakattuun tiedostoon kirjoitettiin siis:

n=puun solmut, r=juurisolmu, l=lehtisolmut, eli kaikki erilaiset merkit

Huffmanin puu = n-r-l * 0-bitti + l * (1-bitti + merkin tavuesitys 8 bittiä)

Luettujen merkkien määrä int-muuttujana = 32 bittiä

Luetut merkit * kutakin merkkiä vastaava Huffmanin koodauksen mukainen binääriesitys

#### Pakatun tiedoston purkaminen

Purettaessa pakattua tiedostoa ```Decompress```-luokka kutsuu ensin ```Input```-luokan metodia ```readHuffmanTree```, joka lukee pakatusta tiedostosta käytetyn Huffmanin puun binäärimuodossa ja palauttaa sen ```Node```-oliona eli käytännössä kyseisen puun juurisolmuna. 

Puu luetaan rekursiota apuna käyttäen niin, että jos luettu bitti on ```0```, metodi palauttaa solmun jonka merkkiarvona on ```null```-arvo, toistumisarvona ```-1```, ja lapsina rekursiiviset kutsut ```readHuffmanTree()```. Jos luettu bitti on ```1```, metodi palauttaa solmun jonka merkkiarvo on seuraavan luettavan tavun merkkiesitys, joka saadaan kutsumalla saman luokan ```readChar()```-metodia. Toistumisarvoksi asetetaan ```-1``` ja lapsiksi ```null```-arvot.

Seuraavaksi luetaan alkuperäisen pakkaamattoman tiedoston pituus tavuina, binääriesityksenä (tämä oli siis pakattaessa pakattu ```int```-muotoisena, eli luetaan 32-bittiä = 4 tavua).

Tallennettua pituustietoa ja puun lukemisesta palautettua ```Node```-oliota (puun juurisolmu) apuna käyttäen luetaan alunperin pakatut merkit kirjoittaen ne osoitettuun kohdetiedostoon. Merkit luetaan etenemällä puuta aina juurisolmusta alkaen lukemalla samalla tavuvirrasta bittejä. Jos luettu bitti on  ```1 ``` edetään oikeaan lapsisolmuun ja jos bitti on  ```0 ``` niin edetään vasempaan lapsisolmuun. Aina tullessa lehtisolmuun kirjoitetaan solmussa oleva merkkiarvo kohdetiedostoon kutsumalla ```output.writeChar(node.getChar())```. Tätä toistetaan kunnes kirjoietttuja merkkejä on tallennetun pituuden verran.

Purettaessa pakattua tiedostoa ja kirjoittaessa sitä käyttäjän osoittamaan kohdetiedostoon oli siis tarpeellista lukea ensin myös Huffmanin puu binääriesityksenä, sekä 32-bittinen binääriesitys alkuperäisen pakkaamattoman tiedoston pituudesta.

### Ohjelman käsittelemä syöte

Ohjelma saa syötteenä polut pakattavaan ja purettavaan tiedostoon. Ohjelma lukee pakattavan tiedoston tavuvirran (BufferedInputStream) kautta tavu kerrallaan. Kirjoitus pakattavaan tiedostoon tapahtuu samoin tavuvirran kautta tavu kerrallaan. Näin, koska Javan tiedostoihin luku/kirjoittaminen tapahtuu tavuvirtojen kautta ja yksittäisiä bittejä ei voi suoraan siirtää. Tiedoston lukemisen ja kirjoittamisen välillä ohjelma käsittelee luettua tietoa kuitenkin myös bitti kerrallaan, jotta pakkaaminen on mahdollista. 

### Aika- ja tilavaativuudet

Tavoiteaikavaativuus algoritmille oli *O(n log n)*, jossa n on erilaisten merkkien määrä. Tilavaativuus tulisi olla lineaarinen eli *O(n)* sillä algoritmin muodostamassa puussa voi olla korkeintaan *2n-1* solmua.

#### Tiedoston pakkaamisen aika- ja tilavaativuus

Pakkaamisen aikavaativuus on merkkien luku *O(n)* + toistumisten merkkaaminen *O(n)* + Huffmanin puun luominen *O(k log k)* (tähän sisältyy minimikekotoiminnot) + kooditaulukon luominen *O(k)* + tietojen kirjoitus *O(n)* = *O(k log k)* itse Huffmanin koodaukselle ja joko *O(n)* tai *O(k log k)* koko pakkausoperaatiolle riippuen luettavien merkkien määrästä.

Tilavaativuus algoritmille on luokkaa *O(n)* kuten määrittelyssä todettu (tilaa tarvitaan merkkitaulukkoon *O(n)*, toistumistaulukkoon jonka koko aina sama eli *O(1)* ja puuhun jonka koko korkeintaan *2k-1* solmua ja pakattuun tiedostoon kirjoitetaan korkeintaan *((2k-1)-1-k + k * 9) + 32 + (n * keskimääräinen koodin pituus)* bittiä.

n = yhteensä luettujen merkkien määrä

k = erilaisten merkkien määrä

#### Pakatun tiedoston purkamisen aika- ja tilavaativuus

Purkamisen aikavaativuus on puun luku *O(k)* + kirjoitettavien merkkien määrän luku *O(1)* + merkkien kirjoittaminen *O(n)* = *O(n)*

Purkamisen tilavaativuus on *O(k)* + *O(1)* (tilaa tarvitaan luetulle puulle ja kirjoitettavien merkkien määrälle) = *O(k)*.
Purettuun tiedostoon kirjoitetaan luonnollisesti alkuperäiset n merkkiä.

n = Pakkamisen yhteydessä luettujen merkkien määrä yhteensä 

k = erilaisten merkkien määrä

### Puutteet ja parannusehdotukset

Ohjelma ei pysty käsittelemään todella suuria tiedostoja, koska merkkien luku taulukkoon saattaa aiheuttaa ```OutOfMemoryError```-virhetilanteen (Ainakin omassa käytössäni olevalla koneella). Yli 70MB kokoinen kuva pakkaantui ja purkaantui vielä hyvin. Yli 700MB kokoinen video aiheutti virheen. Ohjelmaa kehittäessä ajattelin tämän johtuvan merkkitaulukkoon tallentamisesta ja muokkasin ohjelmaa niin, että tiedot luettiin kahteen kertaan suoraan tiedostosta tallentamatta merkkitaulukkoon mutta tämä ei auttanut ja hidasti ohjelman suoritusta ja palautin ohjelman vanhaan muotoonsa.

Ohjaaja myös huomioi, että ohjelman kaikki testit eivät menneet läpi Windows-käyttöjärjestelmässä. Itselläni käytössä Linux Ubuntu ja kaikki testit toimivat.  Testit joissa tuli virheitä testasivat merkkilaskureita jne. ja ohjelman varsinaiseen toimintaan eli pakkaamiseen ja purkamiseen tällä ei pitäisi olla vaikutusta.

Ohjelman lähdekoodin [API-kuvaus] (https://htmlpreview.github.io/?https://github.com/RIR/Huffman/blob/master/dokumentaatio/JavaDoc/apidocs/overview-summary.html)

### Lähteet

*https://fi.wikipedia.org/wiki/Huffmanin_koodaus*

*https://www.siggraph.org/education/materials/HyperGraph/video/mpeg/mpegfaq/huffman_tutorial.html*

*https://www.cs.helsinki.fi/u/thusu/opinnot/tiralab/documents/2009-05-21_husu_maarittely.tex*

*http://planetmath.org/huffmansalgorithm*

*https://www.youtube.com/watch?v=dM6us854Jk0*
