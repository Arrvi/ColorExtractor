# Color Extractor

Aplikacja do tworzenia palet kolorystycznych na podstawie grafik rastrowych

## Opis

Aplikacja ma być zaawansowanym narzędziem do tworzenia palet kolorystycznych. Docelowy użytkownik to grafik/designer tworzący ilustrację, której głównym elementem jest zdjęcie. Narzędzie ułatwi mu dobór koloru do innych elementów tejże ilustracji. Głęboka parametryzacja algorytmu pozwoli pobrać żądaną ilość próbek kolorów, a także dostosować ich odcienie i różnorodność. Dodatkowo paleta będzie automatycznie porównywana z modelami kolorów (np. triada), aby wstępnie określić jej estetykę i ostrzec przed źle dobranymi, niewspółgrającymi barwami.

Istnieją na rynku podobne narzędzia realizujące tę funkcjonalność, w tym Adobe Kuler, na którym wzorowany był oryginalny pomysł. Omawiana aplikacja ma ją rozszerzać o możliwość parametryzacji algorytmu. Dodatkowo projekt jest z założenia ćwiczeniem edukacyjnym z zakresu tworzenia aplikacji z interfejsem graficznym, więc dopuszczam powielanie funkcjonalności istniejących rozwiązań.

## Założenia

- Aplikacja otwiera jedno zdjęcie do analizy
- Za pomocą prostego interfejsu można dostosować parametry algorytmu
	- Liczba pobieranych próbek kolorów
	- Odcień (temperatura, jasność, nasycenie)
	- Tolerancja (mniejsza oznacza więcej znalezionych plam barwnych, czyli większą dokładność, ale dłuższy czas obliczeń)
	- Dokładność (zmniejszanie rozdzielczości zdjęcia przed wykonywaniem obliczeń)
- Każdą konfigurację można zapisać jako tzw. preset w wewnętrznej bazie danych aplikacji. Możliwy będzie eksport i import przez format XML lub JSON.
- Wyniki będą prezentowane jako lista kolorów (podgląd, kody HSV, RGB, LAB)
- Wyniki można będzie dodatkowo zapisać w dowolnym formacie tekstowym (prosty system szablonów).
Dodatkowo, jeżeli czas pozwoli:
- Eksporty do formatów popularnych programów graficznych (Adobe Photoshop, Adobe Illustrator, Corel, Gimp).
- Wyłączanie obszarów zdjęcia z analizy
- Edycja gotowych próbek kolorów w programie
- Przedstawienie wyników na kole barw.
 
## Algorytm (opis)

1. Zmniejszenie rozdzielczości zdjęcia (ograniczenie czasu wykonania).
2. Wyeliminowanie szumu rozmyciem Gaussa (rzędu 1-3px).
3. Wyszukanie plam barwnych na podstawie różnicy pomiędzy sąsiadującymi pikselami.
4. Wybranie plam na podstawie parametrów: unikatowość (odległość kolorystyczna od innych plam), intensywność, jasność, temperatura, rozmiar, zgodność z modelem
5. Prezentacja wyników

## Technologia

Aplikacja zostanie wykonana w języku Java z użyciem komponentów Swing. Interfejs nie będzie modyfikowalny, ale będzie skalowalny
 
## Oryginalny pomysł

Pod koniec 2010 roku na¬pisałem krótki skrypt PHP o nazwie kodowej ColorPalette realizujący podobne zadanie, ale z us¬ta¬lo¬ny¬mi pa¬ra¬me¬tra¬mi. Dodatkowo miał on pewne wady. Wyszukiwał on pla¬my wy¬łącz¬nie wierszami, czyli tra¬kto¬wał gra¬fikę o roz¬mia¬rach w × h jako h pasków w × 1. Mimo to produkował całkiem niezłe zestawienia.
