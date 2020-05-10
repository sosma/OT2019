<h1> Snake peli </h1>

Tällä sovelluksella voit pelata vanhoista puhelimista tuttua Snake peliä. Pelissä sinun on tarkoitus kerätä mahdollisimman paljon ruokaa ennen kuin osut itseesi, jolloin hävität pelin



<h2>Dokumentaatio</h2>

[Käyttöohjeet](dokumentointi/ohjeet.md)

[Vaatimusmäärittely](dokumentointi/vaatimusmaarittely.md)

[Tuntikirjanpito](dokumentointi/tuntikirjanpito.md)


<h2> Releaset </h2>


[ensimmäinen release](https://github.com/sosma/OT2019/releases/tag/0.1)

<h2> Komentorivitoiminnot </h2>
<h3> Testaus </h3>

Yksikkötestit ajetaan komennolla
<code>mvn test</code>

testikattavuusraportti tehdään komennolla
<code>mvn jacoco:report</code>

<h3> Ohjelman suorittaminen </h3>
aja ensiksi komento <code> mvn package </code>

tämä luo sinulle target hakemistoon snake-0.1.0.jar nimisen tiedoston jonka käynnistämällä voit käyttää ohjelmaa

<h3> JavaDoc </h3>
JavaDoc generoidaan komennolla <code> mvn javadoc:javadoc </code>
