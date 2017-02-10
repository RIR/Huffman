## Viikkoraportti 3

### 6.2-12.2.2017 ###

Viime viikosta johtuen, jäi tälle viikolle todella paljon tehtävää. 

Tein Alkuviikosta heti koodikatselmoinnin, minkä koin taas hyödylliseksi sillä niistä oppii yleensä aina jotain. Sitten olenkin vääntänyt oman työni parissa, johon olen tehnyt paljon uusia metodeja ja niille testausta. Muokkasin myös ohjelman tulostuksia ja virheilmoituksia johdonmukaisemmiksi (kaikki suomeksi). Vähemmän yllättäen, luokkien ja metodien lisääminen on johtanut loppumattomaan refaktorointiin ja testaaminen on ollut välillä kohtalaisen hankalaa. Onneksi aiheeseen liittyen on löytynyt paljon tietoa ihan googlella jota ilman työstä ei varmaan tulisikaan mitään. 

Pakkausalgoritmi toimii nyt ainakin tiettyyn tiedostonkokoon asti. Isojen tiedostojen kanssa on vielä ongelma, koska Tiedoston lukeva luokkani palauttaa isoa tiedostoa (700MB +)lukiessa java heap space errorin (liittyy luokassa käytössä olevaan StringBuilderiin, mihin luetut tavut lisätään merkkeinä.  

Muita kuin omia tietorakenteita ei ole juuri käytössä, lukuun ottamatta HuffmanTree-luokassa oleva PriorityQueue sekä tuo Input-luokassa oleva StringBuilder (Pitääkö tämä implementoida itse?). 

Muita huomioita/epäselvyyksiä: Input/Output-luokissa käytössä laskurit yhteensä luetuille biteille, joita tarkoitus käyttää mm. tulostuksen yhteydessä havainnoimaan tiedon tiivistystä. Laskurin lisäys toistuu useassa kohdassa, eikä siis kovin siistiä koodia, mutta en tehnyt erillistä metodia, koska välillä lisäyslause aina sen verran pieni, enkä nähnyt että yksityisellä metodilla esim. addToReadBitsTotal(int bits) tulisi juurikaan sen siistimpää.

Tällä viikolla tuli työtunteja > 20 h.

VAIHEESSA...
