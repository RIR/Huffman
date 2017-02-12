## Viikkoraportti 4

### 6.2-12.2.2017 ###

Viime viikosta johtuen, jäi tälle viikolle todella paljon tehtävää. 

Tein Alkuviikosta heti koodikatselmoinnin, minkä koin taas hyödylliseksi sillä niistä oppii yleensä aina jotain. Sitten olenkin vääntänyt oman työni parissa, joka nyt on ainakin perusosiltaan toimintakunnossa. Onneksi aiheeseen liittyen on löytynyt paljon tietoa ihan googlella jota ilman työstä ei varmaan tulisikaan mitään. Olen tutkinut paljon erilaisia versioita Huffmanin koodauksesta ja näistä on muokkautunut myös toimintamalleja omaan ohjelmaani. Aika paljon etenemistä on tapahtunut yrityksen ja erehdyksen kautta ja refaktorointi on ollut melko loputonta. Olen muokannut myös luokat ja paketit mielestäni paremmin kuvaaviksi.

Pakkaaminen ja purkaminen toimii nyt ainakin tiettyyn kokoon asti. Todella isojen tiedostojen kanssa on vielä ongelma, koska tiedoston lukeva luokkani palauttaa isoa tiedostoa (koetiedosto 700MB +)lukiessa java heap space errorin. Tämä virhe tulee lukiessa tavuja talteen StrinBuilderiin, jonka vaihdoinkin jo kertaalleen pois, niin että luin tavut suoraan taulukkoon jonka koon määritin inputStreamin read.available() metodilla, kunnes huomasin, että se ei anna tarkkaa tavumäärää ja johtaa virheelliseen suoritukseen ja joka tapauksessa taulukoilla on maksimikoko, joka ilmeisesti nyt ylittyy.

Muita kuin omia tietorakenteita ei ole juuri käytössä, lukuun ottamatta HuffmanTree-luokassa oleva PriorityQueue (jota alan tekemään ensi viikolla) sekä tuo Input-luokassa oleva StringBuilder (Pitääkö tämä implementoida itse?).

Testikattavuus pitäisi nyt olla myös aika kohdillaan (pit-raportti löytyy myös projektista), joitain testejä aion vielä kuitenkin lisätä.

Muita huomioita/epäselvyyksiä: Input/Output-luokissa käytössä laskurit yhteensä luetuille biteille, joita tarkoitus käyttää mm. tulostuksen yhteydessä havainnoimaan tiedon tiivistystä. Laskurin lisäys toistuu useassa kohdassa, eikä siis kovin siistiä koodia, mutta en tehnyt erillistä metodia, koska lisäyslause sen verran pieni, enkä nähnyt että yksityisellä metodilla esim. addToReadBitsTotal(int bits) tulisi juurikaan sen siistimpää.

Olen kommentoinut koodia melko paljon selkeyttääkseni asioita (myös itselleni) onko tämä ongelma ja pitääkö muu kuin Javadoc-kommentointi poistaa?

Tällä viikolla tuli työtunteja > 20 h luultavasti lähemmäs 30, ajantaju katosi ajoittain ja tarkka kirjanpito jäi hunningolle.
