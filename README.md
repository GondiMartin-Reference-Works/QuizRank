# Házi feladat specifikáció

## Androidalapú szoftverfejlesztés

### 2023.04.29. - 2023 1. félév

### Göndöcs Martin - (WUPA9P)

### martin.gondocs@gmail.com

### Laborvezető: Kövesdán Gábor

## Bemutatás

Az alkalmazás egy kvízt szimulál. Több, különböző témájú kvíz kitöltése lehetséges. Kitöltés végén az eredményeket tároljuk, egy rangsor készül, melyben összevethetőek pontjaink más játékosok pontjaival egy adott témán belül.
(Ötlet: Quizlet, Kahoot.)

## Főbb funkciók

Az alkalmazás használatához **internet** elérésére van szükség.

Az alkalmazás legelső használatakor egy bejelentkező/regisztráló - név, jelszó párost igénylő - kezdőoldal jelenik meg. A művelet elvégzése után valahányszor újraindítjuk az alkalmazást, **nincs szükség újbóli bejelentkezésre**. Bejelentkezést követően a főoldalon van lehetőségünk kilépni, ami visszavisz a kezdőoldalra.

A főoldalon keresztül elindíthatunk egy kvízt vagy megnézhetjük pontjainkat adott témán belül. A kvízek feleletválasztós kérdésekből állnak, minden helyesen megválaszolt kérdés 1 pontot ér. Az eredményeinket más játékosok eredményeivel együtt láthatjuk egy rangsorolt listában.

Lehetőség van az alkalmazás **offline** használatára is. Ekkor csak és kizárólag a korábban is elérhető kvízek elindítása és az alkalmazásból való kijelentkezés lehetséges. Az eredmény nem kerül eltárolásra, hanem a felhasználó értesítést kap arról.
(A rangsorolt lista offline állapotban nem elérhető.)

## Választott technológiák:

- **UI**: A felhasználói felület Jetpack Compose-ban és MVVM architektúrával.
- **lista**: Az eredmények egy-két fontosabb részlettel (név, pontszám) együtt egy komplex listában jelennek meg.
- **Hálózatkezelés** (FireBase): Az alkalmazás Firebase-t használ a bejelentkezésekhez, regisztráláshoz, majd ezt követően az elérhető kvízek letöltéséhez.
- **Adatbáziskezelés** (Room): A felhasználó bejelentkezési adatait lementi a bejelentkezések után. Hálózatból letöltött kvízek az eszközre is mentésre kerülnek.
- **Értesítések**: Offline használat közben értesítést küld a kitöltött kvíz eredményéről.

---

# Házi feladat dokumentáció

### [QuizRank]

<img src="./assets/icon.png" width="160">

**Legkésőbb a dokumentáció fázisban lecserélendő a saját ikonnal!**

## Bemutatás

Az alkalmazás rövid, 2-3 mondatos bemutatása. Honnan az ötlet, mi szülte az igényt, ki lehetne a célközönség.
A laboron és előadáson bemutatott alkalmazásokat nem lehet házi feladatnak választani.

## Főbb funkciók

Az alkalmazás minden funkciójára kiterjedő leírás. Legyen egyértelműen eldönthető, hogy az adott funkció implementálva van-e!
P.l.: Az alkalmazással lehetőség van térképen megjeleníteni az állomáspontokat és azok A,B,C,D tulajdonságai meg is jelennek (ha elérhetőek).

## Felhasználói kézikönyv

Az alkalmazás minden funkciójára kiterjedő, teljes körű felhasználói leírás. Az összes releváns képernyőhöz/funkcióhoz tartalmaznia kell képernyőképet!

A képernyőképekkel kapcsolatos követelmények:

- Android Device Art Generator-ral telefont/tabletet kell rajzolni a képernyő köré!
  - Mindegy, hogy melyik készüléket választod, de legyen egységes az egész dokumentumban!
  - Telefonra tervezett képernyőket valamelyik telefon skin-nel, tablet képernyőt (amennyiben készítettél külön) tablet skin-nel készítsünk!
- Álló képernyőket álló módban, fekvőket fekvő módban rakjuk be! (Értelemszerűen. Ha fekvő képernyőképet húzol be a generator-ba, akkor fekvő módban rajzolja köré az eszközt)
- Minden képhez legyen képaláírás, ami leírja hogy mit kell nézni a képen!
- A képeket úgy méretezzük, hogy álló telefon méretből kettő elférjen egymás mellett egy sorban (fekvő illetve tablet képeket ehhez viszonyítva nagyítsuk)!
- Amennyiben gesztúra vezérlést akarunk bemutatni a képernyőn, jelezzük a gesztúrát is! (ld példa kép)
- A képeket és a képaláírásokat középre igazítsuk!

<p align="center">
<img src="./assets/image1.png" width="320">
<img src="./assets/image2.png" width="320">

1. ábra: Gesztúrával és gombbal is navigálható képernyők, hasznos kényelmi funkció a felhasználónak ha több lehetőséget is biztosítunk a navigációra

</p>

## Felhasznált technológiák:

Itt kell felsorolni minden technológiát, technikát, külső könyvtárat, komplexebb algoritmust, ami növeli az alkalmazás értékét. Osztályzáskor ezt a fejezetet nézzük meg először.

Külső osztálykönyvtár használata esetén a könyvtár neve legyen link, ami annak elérhetőségére mutat.

A kulcsszavak legyenek **félkövér** betűtípussal szedve.
Például:

- •	Az X és Y képernyők optimalizáltak **álló és fekvő nézetre** is
- [YCharts](https://github.com/yml-org/YCharts) osztálykönyvtár használata a grafikonok rajzolására
- **Fused Location API** használata helymeghatározásra
- **SQLite** alapú adattárolás
- Implicit intent használata **QR kód beolvasáshoz** (telepített Barcode Scanner alkalmazás szükséges a futtatásához)
- A játék fizikáját a [Box2D](https://box2d.org/) motor biztosítja
- **Service** használata zenelejátszáshoz

## Fontosabb technológiai megoldások

**A számodra legnehezebb/legérdekesebb funkciót fejtsd ki kb.  10 mondatban, hogy mi volt a probléma és hogyan oldottad meg.**
