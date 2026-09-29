# Multithreading — mini-proiecte

Aici nu ai exercitii cu pasi si nu ai hinturi. Ai **cerinte** si un
**criteriu de acceptare**. Clasele le proiectezi tu.

Fiecare mini-proiect trece prin cate un element din teoria pe care ai
parcurs-o deja, in ordinea lectiilor. Teoria ramane in celalalt repo,
`multithreading`, in `src/teorie/` — ai voie s-o recitesti oricand.

## Regula jocului

1. Cerinta **nu-ti spune ce clasa din Java sa folosesti**. Alegerea uneltei
   face parte din exercitiu. Daca nu stii care e, cauta in teorie dupa ce
   COMPORTAMENT iti trebuie, nu dupa nume.
2. Fiecare program **isi tipareste singur verdictul** la final (asteptat vs.
   real, sau o durata comparata cu alta). Daca verdictul nu apare, proiectul
   nu e terminat.
3. Fiecare program **se inchide singur**, fara `Ctrl+C`.
4. Un mini-proiect = un folder = o clasa `MNN`. Nu le amesteci.

## Cum rulezi

```bash
cd src/M01-descarcator-pagini
javac M01.java && java M01
```

## Lotul 1 — lectia 01

| # | Mini-proiect | Ce trebuie sa iasa |
|---|---|---|
| M01 | Descarcator de pagini | total ~= cea mai lenta pagina, nu suma |
| M02 | Cronometrul pacalit | A ~500 ms cu fire diferite, B ~2000 ms pe acelasi fir |
| M03 | Jurnal de fundal | programul iese singur, ultima linie e sumarul |
| M04 | Calculator de facturi | factura 3 raportata cu motivul real; prima linie tarziu, restul instant |
| M05 | Contor de vizite (3 stagii) | A pierde, B si C dau 200.000, C mai rapid ca B |

## Ce urmeaza

Loturile 2 si 3 (lectiile 02 si 03) vin dupa ce lotul 1 e terminat:
buton de oprire, contor de clickuri, distribuitor de bonuri, licitatie,
seif, cabina de proba, banda de asamblare, tabela de sosiri, numaratoare
de cuvinte, notificari catre abonati, transferuri bancare.
