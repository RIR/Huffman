# Testausdokumentti
## Tiedon tiivistys käyttäen Huffmanin koodausta

### Yksikkötestaus 

Ohjelman yksikkötestaus on toteutettu Junit:lla. Testejä on kattavasti kaikille ohjelman toiminnoille. Jonkun verran yksittäisiä metodeja tulee testatuksi epäsuoraan, koska ne on määritelty luokkien sisäisiksi yksityisiksi metodeiksi. Lisäksi tiedostojenkäsittelyyn sisältyy paljon poikkeusten hallintaa, jotka vaikuttavat hieman testikattavuuteen, mutta joita en koe tarpeelliseksi testata erikseen. 

### Pakkaamisen ja purkamisen testaus erilaisilla tiedostomuodoilla

#### Tapaukset joissa pakkauksesta oli hyötyä

*TIFF-muotoinen kuva (häviötön tiedostomuoto)*
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress kuva.tif kuva.tif 
Antamasi tiedosto kuva.tif on pakattu. Tiedosto löytyy nimellä kuva.tif.hf
Alkuperäisen tiedoston koko: 490117880 bittiä (59828,84 KB).
Pakatun tiedoston koko 253035970 bittiä (30888,18 KB).
Pakkausteho 48,37 % 
Aikaa tiedoston pakkaamiseen kului 6385ms

fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -jar Huffman-1.0-SNAPSHOT.jar -decompress kuva.tif.hf kuva2.tif
Antamasi tiedostokuva.tif.hf on purettu. Tiedosto löytyy antamallasi nimellä kuva2.tif
Alkuperäisen tiedoston koko: 253035970 bittiä. (30888,18 KB).
Puretun tiedoston koko 490117880 bittiä (59828,84 KB).
Aikaa tiedoston purkamiseen kului 4896ms
```

*MPEG-muotoinen video*
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress thisIsMpeg2File.mpeg thisIsMpeg2File.mpeg 
Antamasi tiedosto thisIsMpeg2File.mpeg on pakattu. Tiedosto löytyy nimellä thisIsMpeg2File.mpeg.hf
Alkuperäisen tiedoston koko: 223379456 bittiä (27268 KB).
Pakatun tiedoston koko 145690216 bittiä (17784,45 KB).
Pakkausteho 34,78 % 
Aikaa tiedoston pakkaamiseen kului 3276ms
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -decompress thisIsMpeg2File.mpeg.hf thisIsMpeg2File.mpeg
Antamasi tiedostothisIsMpeg2File.mpeg.hf on purettu. Tiedosto löytyy antamallasi nimellä thisIsMpeg2File.mpeg
Alkuperäisen tiedoston koko: 145690216 bittiä. (17784,45 KB).
Puretun tiedoston koko 223379456 bittiä (27268 KB).
Aikaa tiedoston purkamiseen kului 2769ms
```

*Kokonainen kirja txt.muotoisena tekstitiedostona*
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress musketeers.txt musketeers.txt 
Antamasi tiedosto musketeers.txt on pakattu. Tiedosto löytyy nimellä musketeers.txt.hf
Alkuperäisen tiedoston koko: 11086640 bittiä (1353,35 KB).
Pakatun tiedoston koko 6536227 bittiä (797,88 KB).
Pakkausteho 41,04 % 
Aikaa tiedoston pakkaamiseen kului 478ms

fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -decompress musketeers.txt.hf musketeers.txt 
Antamasi tiedostomusketeers.txt.hf on purettu. Tiedosto löytyy antamallasi nimellä musketeers.txt
Alkuperäisen tiedoston koko: 6536227 bittiä. (797,88 KB).
Puretun tiedoston koko 11086640 bittiä (1353,35 KB).
Aikaa tiedoston purkamiseen kului 212ms
```

*Kokonainen kirja suomenkielisenä txt-muotoisena tekstitiedostona*
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress suomiKirja.txt suomiKirja.txt 
Antamasi tiedosto suomiKirja.txt on pakattu. Tiedosto löytyy nimellä suomiKirja.txt.hf
Alkuperäisen tiedoston koko: 8884008 bittiä (1084,47 KB).
Pakatun tiedoston koko 4928955 bittiä (601,68 KB).
Pakkausteho 44,52 % 
Aikaa tiedoston pakkaamiseen kului 195ms

fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -decompress suomiKirja.txt.hf suomiKirja.txt 
Antamasi tiedostosuomiKirja.txt.hf on purettu. Tiedosto löytyy antamallasi nimellä suomiKirja.txt
Alkuperäisen tiedoston koko: 4928955 bittiä. (601,68 KB).
Puretun tiedoston koko 8884008 bittiä (1084,47 KB).
Aikaa tiedoston purkamiseen kului 147ms
```

*Edellisistä muodostettu ja monistettu iso txt-muotoinen tiedosto (yli 80MB)*
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress BigText.txt BigText.txt
Antamasi tiedosto BigText.txt on pakattu. Tiedosto löytyy nimellä BigText.txt.hf
Alkuperäisen tiedoston koko: 665832040 bittiä (81278,33 KB).
Pakatun tiedoston koko 391044544 bittiä (47734,93 KB).
Pakkausteho 41,27 % 
Aikaa tiedoston pakkaamiseen kului 9190ms

fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -decompress BigText.txt.hf BigText.txt
Antamasi tiedostoBigText.txt.hf on purettu. Tiedosto löytyy antamallasi nimellä BigText.txt
Alkuperäisen tiedoston koko: 391044544 bittiä. (47734,93 KB).
Puretun tiedoston koko 665832040 bittiä (81278,33 KB).
Aikaa tiedoston purkamiseen kului 7582ms
```

*BMP-muotoinen kuvatiedosto*
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress talo.BMP talo.BMP 
Antamasi tiedosto talo.BMP on pakattu. Tiedosto löytyy nimellä talo.BMP.hf
Alkuperäisen tiedoston koko: 49766832 bittiä (6075,05 KB).
Pakatun tiedoston koko 41336080 bittiä (5045,91 KB).
Pakkausteho 16,94 % 

Aikaa tiedoston pakkaamiseen kului 1382ms
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -decompress talo.BMP.hf talo.BMP 
Antamasi tiedostotalo.BMP.hf on purettu. Tiedosto löytyy antamallasi nimellä talo.BMP
Alkuperäisen tiedoston koko: 41336080 bittiä. (5045,91 KB).
Puretun tiedoston koko 49766832 bittiä (6075,05 KB).
Aikaa tiedoston purkamiseen kului 734ms
```
Tässä pakkausteho melko onneton ja odotin parempaa. Pakkausteho saattaisi olla parempi isommalla tiedostolla.

*EPS-muotoinen tiedosto (vektorigrafiikkaa)*
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress vektori.eps vektori.eps
Antamasi tiedosto vektori.eps on pakattu. Tiedosto löytyy nimellä vektori.eps.hf
Alkuperäisen tiedoston koko: 14323280 bittiä (1748,45 KB).
Pakatun tiedoston koko 10202296 bittiä (1245,4 KB).
Pakkausteho 28,77 % 
Aikaa tiedoston pakkaamiseen kului 292ms

fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -decompress vektori.eps.hf vektori.eps
Antamasi tiedostovektori.eps.hf on purettu. Tiedosto löytyy antamallasi nimellä vektori.eps
Alkuperäisen tiedoston koko: 10202296 bittiä. (1245,4 KB).
Puretun tiedoston koko 14323280 bittiä (1748,45 KB).
Aikaa tiedoston purkamiseen kului 221ms
```
Samoin tässä, pakkausteho voisi olla ehkä parempi isommilla tiedostoilla.

#### Tapaukset joissa pakkauksesta ei ollut hyötyä

*WAV-muotoinen äänitiedosto (pitäisi olla pakkaamaton tiedostomuoto)*
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress noyoumove.wav noyoumove.wav 
Antamasi tiedosto noyoumove.wav on pakattu. Tiedosto löytyy nimellä noyoumove.wav.hf
Alkuperäisen tiedoston koko: 2326688 bittiä (284,02 KB).
Pakatun tiedoston koko 2313841 bittiä (282,45 KB).
Pakkausteho 0,55 % 
Aikaa tiedoston pakkaamiseen kului 123ms

fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -decompress noyoumove.wav.hf noyoumove.wav 
Antamasi tiedostonoyoumove.wav.hf on purettu. Tiedosto löytyy antamallasi nimellä noyoumove.wav
Alkuperäisen tiedoston koko: 2313841 bittiä. (282,45 KB).
Puretun tiedoston koko 2326688 bittiä (284,02 KB).
Aikaa tiedoston purkamiseen kului 99ms
```
Tämä hieman hämmentää sillä pakkausta ei käytännössä tapahtunut ja WAV:n pitäisi normaalisti olla käsittääkseni pakkamaton tiedostomuoto.

*PNG-muotoinen kuva, joka pakattu käyttäen Deflate-pakkausmenetelmää, johon sisältyy osana myös Huffmanin koodausta* 
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress LinuxKuva.png LinuxKuva.png 
Antamasi tiedosto LinuxKuva.png on pakattu. Tiedosto löytyy nimellä LinuxKuva.png.hf
Alkuperäisen tiedoston koko: 6371664 bittiä (777,79 KB).
Pakatun tiedoston koko 6373169 bittiä (777,97 KB).
Pakkausteho -0,02 % 
Aikaa tiedoston pakkaamiseen kului 277ms

fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -decompress LinuxKuva.png.hf LinuxKuva.png 
Antamasi tiedostoLinuxKuva.png.hf on purettu. Tiedosto löytyy antamallasi nimellä LinuxKuva.png
Alkuperäisen tiedoston koko: 6373169 bittiä. (777,97 KB).
Puretun tiedoston koko 6371664 bittiä (777,79 KB).
Aikaa tiedoston purkamiseen kului 161ms
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ 
```
Tässä oli odotettavissakin, että pakkausta ei tapahdu.

*JPEG-muotoinen kuva (pakattu tiedostomuoto)*
```
fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -Xmx1024M -jar Huffman-1.0-SNAPSHOT.jar -compress maisemakuva.jpg maisemakuva.jpg 
Antamasi tiedosto maisemakuva.jpg on pakattu. Tiedosto löytyy nimellä maisemakuva.jpg.hf
Alkuperäisen tiedoston koko: 76070768 bittiä (9285,98 KB).
Pakatun tiedoston koko 76035226 bittiä (9281,64 KB).
Pakkausteho 0,05 % 
Aikaa tiedoston pakkaamiseen kului 1690ms

fuksi@dhcp-asv-103:~/Lataukset/TiraLabTestailua$ java -jar Huffman-1.0-SNAPSHOT.jar -decompress maisemakuva.jpg.hf maisemakuva2.jpg
Antamasi tiedostomaisemakuva.jpg.hf on purettu. Tiedosto löytyy antamallasi nimellä maisemakuva2.jpg
Alkuperäisen tiedoston koko: 76035226 bittiä. (9281,64 KB).
Puretun tiedoston koko 76070768 bittiä (9285,98 KB).
Aikaa tiedoston purkamiseen kului 1277ms
```

#### Suorituskykyvertailua Huffman vs gzip

*TIFF-kuva (61,3 MB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| 48,37 %  |   67,9 %    |
|Pakkausnopeus| 6385 ms |   2086 ms   |
|Purkausnopeus| 4896 ms |   1567 ms    |

*MPEG-video (27,9 MB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| 34,78 %  |   47.4 %    |
|Pakkausnopeus| 3276 ms |  1198 ms    |
|Purkausnopeus| 2769ms |    641 ms   |

*Kirja txt-muodossa (1,4 MB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| 41,04 %  |    64,3 %   |
|Pakkausnopeus| 478 ms |   160 ms   |
|Purkausnopeus| 212 ms |    29 ms   |

*Kirja txt.muodossa suomenkielisenä eli ääkkösillä (1,1 MB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| 44,52 %  |   62,3 %    |
|Pakkausnopeus| 195 ms |  147 ms    |
|Purkausnopeus| 147 ms |   21 ms    |

*Iso txt-tiedosto ääkkösillä (83,2 MB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| 41,27 %  |    63,1 %   |
|Pakkausnopeus| 9190 ms |  9720 ms    |
|Purkausnopeus| 7582 ms |   1096 ms    |

*BMP-kuva (6,2 MB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| 16,94 %  |   57,1 %    |
|Pakkausnopeus| 1382 ms |  386 ms    |
|Purkausnopeus| 734 ms |    92 ms   |

*EPS-tiedosto (1,8 MB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| 28,77 % |   56,2 %    |
|Pakkausnopeus| 292 ms |   166 ms   |
|Purkausnopeus| 221 ms |    31 ms   |

*WAV-äänitiedosto (290.8 KB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| 0,55 %  |   3,1 %    |
|Pakkausnopeus| 123 ms |  34 ms    |
|Purkausnopeus| 99 ms |    11 ms   |

*PNG-kuva (796.5 KB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| -0,02 %  |  5,8 %     |
|Pakkausnopeus| 277 ms |   75 ms   |
|Purkausnopeus| 161 ms |   25 ms    |

*JPEG-kuva (9,5 MB)*

|   |  Huffman   | gzip    
:---: | :---: | :---:
|Pakkausteho| 0,05 %  |   0,9 %    |
|Pakkausnopeus| 1690 ms |  621 ms    |
|Purkausnopeus| 1277 ms |    150 ms   |

### Huomioitavaa

Ohjelman ajallinen ja pakkaustehollinen suoriutuminen näkyy testitapauksista. Kuten näistä käy ilmi, on pakatun tiedoston purkaminen käytännössä aina nopeampaa kuin tiedoston pakkaaminen. Pakkaamattomissa tiedostomuodoissa päästään jo lähelle  50 %:n pakkaustehoa (TIFF-kuva 48,37 %), tosin WAV-muotoinen äänitiedosto ei pakkautunut, minkä syy jäi itselleni epäselväksi. 

Verrattaessa nykyisin yleisesti käytössä oleviin tiedonpakkausmenetelmiin, ei ohjelma ole pakkaustehokkuudeltaan vertailukelpoinen. Mm. ZIP-pakkausmenetelmä, gzip-pakkausohjelma ja PNG-muotoiset kuvat käyttävät pakkaukseen Deflate-algoritmia joka yhdistelee Lempel–Zivin (LZ77) ja Huffmanin algoritmeja ja on pelkkää Huffmania paljon tehokkaampi. Huffmanin koodauksella on kuitenkin siis paikkansa nykymuotoisessa tiedon tiivistyksessä. Se ei vaan itsessään ole optimaalisin metodi ja sitä käytetään usein "loppukoodauksena" muiden pakkausmetodien jälkeen. 

Vertailukohdiksi tehokkuuserosta sopii aiemmin edellä tiivistetyt TIFF-muotoinen kuva jolle tällä ohjelmalla saatiin 48,37 % pakkausteho ja jonka gzip pakkaa 67,9 %:n tehokkuudella sekä suomenkielinen kirja .txt-muodossa joka saatiin pakattua tällä ohjelmalla 44,5 %:n tehokkuudella ja jonka gzip pakkaa 62,3 %:n tehokkuudella.

Kokeilin paljon erilaisia tiedostomuotoja vaikka kävikin ilmi, että suurin osa näistä on jo jollain tavalla tiivistetyssä muodossa. Muita kun muotoilemattomia tekstitiedostoja (.txt) oli jopa vaikeaa löytää. Kuten edellä näkee, ohjelma suorittaa pakkaamis- ja purkaamisoperaatiot vaikka tietoa ei saataisikaan pakattua. Puretut tiedostot myös aina toimivat eli purkautuvat oikein.

Testejä on suoritettu edellä näkyvillä syötteillä ja vastaavia testejä voi kukin tehdä kokeilemalla itse ohjelmaa.

*Testauskattavuutta kuvaava [pit-raportti] (https://htmlpreview.github.io/?https://github.com/RIR/Huffman/blob/master/dokumentaatio/pit-raportti/index.html)*

### Lähteet

*https://fi.wikipedia.org/wiki/Huffmanin_koodaus*

*https://fi.wikipedia.org/wiki/Lempel-Ziv*

*https://fi.wikipedia.org/wiki/Deflate*

*https://fi.wikipedia.org/wiki/Gzip*
