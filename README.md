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

## Hinturi

Le citesti **dupa** ce te-ai blocat, nu inainte. Niciunul nu-ti da solutia:
iti spune unde sa cauti si ce intrebare sa-ti pui.

### M01 - Descarcator de pagini
- Incearca intai varianta gresita: pune asteptarea imediat dupa pornirea
  fiecarei pagini, in aceeasi bucla. Masoara. Apoi mut-o si masoara din nou —
  diferenta iti arata ce face de fapt. Vezi `teorie/A3-join`.
- Durata totala se masoara in jurul intregului bloc, nu in interiorul
  fiecarei pagini.

### M02 - Cronometrul pacalit
- Cuvantul care difera intre A si B e numele unei metode a aceluiasi obiect.
  `teorie/A4-start-vs-run` le pune fata in fata.
- Numele firului care lucreaza se afla din INTERIORUL sarcinii, nu din
  afara ei.

### M03 - Jurnal de fundal
- Intrebarea e: ce fel de fir NU tine programul in viata dupa ce `main`
  s-a terminat? `teorie/A5-daemon`.
- Marcajul acela trebuie pus inainte de pornire. Dupa, nu mai are efect.

### M04 - Calculator de facturi
- Pentru partea cu timpii, `teorie/B4-pool` e facut exact pentru asta:
  uita-te la ce moment se intoarce fiecare cerere de rezultat si de ce
  primele doua difera atat de mult intre ele.
- O exceptie aruncata pe alt fir nu ajunge la tine asa cum a plecat.
  `teorie/B3-exception-wrapping` arata ce trebuie sa intrebi obiectul de
  exceptie ca sa afli motivul real.

### M05 - Contor de vizite
- Stagiul A e deja scris ca exemplu in `teorie/C1-race-condition`.
- Intre B si C nu difera DACA protejezi, ci CAT tii protectia. Operatia
  lenta nu atinge contorul — pune-ti intrebarea daca are ce cauta inauntru.
  `teorie/C2-synchronized-method` vs `teorie/C3-synchronized-block`.

## Ce urmeaza

Loturile 2 si 3 (lectiile 02 si 03) vin dupa ce lotul 1 e terminat:
buton de oprire, contor de clickuri, distribuitor de bonuri, licitatie,
seif, cabina de proba, banda de asamblare, tabela de sosiri, numaratoare
de cuvinte, notificari catre abonati, transferuri bancare.
