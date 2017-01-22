# Määrittelydokumentti
**Raine Rantanen**


## Aineopintojen harjoitustyö: Tietorakenteet ja algoritmit

Aiheena tiedon tiivistys käyttäen [Huffmanin koodausta](https://fi.wikipedia.org/wiki/Huffmanin_koodaus).

Ohjelman toteutus tapahtuu Javalla. 

### Työssä käsiteltävä ongelma sekä sitä varten käytettävät algoritmit ja tietorakenteet

Työn aiheena on tiedon tiivistäminen. Käytettävä algoritmi on Huffmanin koodaus. Ainakin alustavasti ohjelma on tarkoitus toteuttaa komentorivipohjaisena, optiona myös jonkinlainen graafinen käyttöliittymä. 

Algoritmien ja ohjelmoinnin näkökulmasta aihe on itselleni ennestään täysin tuntematon, mutta vaikutti kiinnostavalta joten päädyin siihen. Tietojenkäsittelyn alueella aihe on luonnollisesti hyvin keskeinen, datan määrän kasvaessa jatkuvasti.

Huffmanin algoritmiin päädyin, koska se on mainittuna kurssin aihelistalla tiedon tiivistämisen yhteydessä ja pikaisen perehtymisen perusteella se vaikuttaa toteutettavalta aiheelta.

Huffmanin algoritmi tiivistää tietoa koodaamalla käsiteltävät merkit bittijonoksi, niin että mitä useammin tietty merkki esiintyy, sitä lyhyempi bittijono sitä kuvaa.

Bittiesitykset muodostetaan puumallilla, jossa aluksi jokainen merkki on oma puunsa, jonka arvona on merkin esiintymistiheys. Näitä puita sitten yhdistetään niin, että kaksi arvoltaan pienintä puuta (eli esiintymistiheydeltään harvinaisinta) yhdistetään uudeksi puuksi niin, että luodaan uusi solmu jonka arvo on yhdistettyjen puiden arvo yhteensä ja yhdistetyt puut liitetään tämän solmun alipuiksi. Tätä jatketaan kunnes tuloksena on yksi puu.

Lopullisessa puussa merkkejä vastaavat solmut ovat aina puun lehtiä ja niiden bittijonokoodi määräytyy sijainnin mukaan niin, että puun juuresta liikutaan lehteen ja siirtyminen vasemmalle vastaa 0-bittiä ja oikealle 1-bittiä.

Algoritmin toteutus esiteltynä mielestäni selkeästi [tässä] (https://www.youtube.com/watch?v=dM6us854Jk0).

Koodauksen purkaminen tapahtuu muuntamalla bitit takaisin merkeiksi vastaavasti niin että kuljetaan juuresta lähtien puuta pitkin bittien mukaan ja kun osutaan lehteen niin merkki on löytynyt.


### Ohjelman käsittelemä syöte

Ohjelma pakkaa ja purkaa tiedostoja. Pakattava tiedosto luetaan tavuina (byte).

### Aika- ja tilavaativuudet

Tavoiteaikavaativuus algoritmille on *O(n log n)*, jossa n on erilaisten merkkien määrä. Tilavaativuus tulisi olla lineaarinen eli *O(n)* sillä algoritmin muodostamassa puussa voi olla korkeintaan 2n-1 solmua.

### Lähteet

*https://fi.wikipedia.org/wiki/Huffmanin_koodaus*

*https://www.siggraph.org/education/materials/HyperGraph/video/mpeg/mpegfaq/huffman_tutorial.html*

*https://www.cs.helsinki.fi/u/thusu/opinnot/tiralab/documents/2009-05-21_husu_maarittely.tex*

*http://planetmath.org/huffmansalgorithm*

*https://www.youtube.com/watch?v=dM6us854Jk0*
