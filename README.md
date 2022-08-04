Program zczytuje zawartosc plikow tekstowych z rozszerzeniem .txt, .json, .csv 
po czym usuwa wszystkie spacje i wyrzuca tekst na konsole. Program mozna uruchomic w metodzie Main odpalajac metode
parseInput() na instancji obiektu TextReader2. Plik, ktory chcemy zczytac musi znajdowac sie w folderze resources,
tak aby podczas kompilacji w fazie package zostaly zapisane do folderu target.

W testach wykorzystalem bilioteki Junit oraz AssertJ (uzywalem ich jednoczesnie do tych samych testow- patrz komentarze
przy metodach). Chcialem jedynie pokazac, ze metody z obu bibliotek spokojnie radza sobie do testowania naszych przypadkow.
Wykorzystalem rowniez biblioteke hamcrest. Ciekawa biblioteka z mozliwoscia tworzenia wlasnych klas implementujacych
Matchery. Dzieki za podsuniecie takiego rozwiazania!

Co do 2 ostatnich pytan;
wypisać na konsoli drzewko zależności na konsoli mozemy za pomoca komendy mvn dependency:tree

Maven trzyma ściągnięte biblioteki na dysku w katalogu .m2/repository uzytkownika (np.C:\Users\Tomek\.m2).
Znajduja sie w nim pluginy, zaleznosci i pliki JAR z naszymi projektami.

