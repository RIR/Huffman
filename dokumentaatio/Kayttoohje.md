# Käyttöohje

Ohjelman jar-tiedosto sijaitsee projektin juurikansiossa ja ohjelmaa voi käyttää sieltä käsin. Ohjelman käyttö toimii seuraavasti:

Anna juurikansiossa komento muodossa:

 ```java -jar Huffman-1.0-SNAPSHOT.jar <-toiminto> <lähdetiedoston nimi> <kohdetiedoston nimi>```

Esimerkkikomento: 

```java -jar Huffman-1.0-SNAPSHOT.jar -compress pakattava.txt pakattu.txt```


## Ohjelman toiminnot:

**-compress** 

Pakkaa lähdetiedoston ja nimeää pakatun tiedoston antamallasi kohdetiedoston nimellä lisäten .hf tiedostopäätteen.

**-decompress** 

Purkaa antamasi lähdetiedoston pakkauksen ja nimeää puretun tiedoston antamallasi kohdetiedoston nimellä.
               

**-help (ei muita argumentteja)**

Tulostaa käyttöohjeen.

###*Huom!*

Ohjelma nimeää pakatun tiedoston lisäämällä antamasi tiedostonimen loppuun .hf-tiedostopäätteen.

Purkaus toimii vain .hf-päätteisille tiedostoille.
             
