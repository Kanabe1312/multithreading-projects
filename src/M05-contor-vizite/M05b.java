// =============================================================
// MINI-PROIECT 05b - Casa de bilete (3 stagii)
// =============================================================
// Un concert are 40.000 de bilete. 4 casieri vand in acelasi timp, fiecare
// incearca exact 10.000 de vanzari - deci se cer exact atatea bilete cate
// exista.
//
// O vanzare face DOUA lucruri: scade stocul si creste numarul de bilete
// vandute, apoi tipareste bonul (o operatie lenta, care nu atinge nici
// stocul nici contorul - simuleaza cu un mic calcul care consuma timp).
// Nu se vinde daca stocul e gol.
//
// Scrii trei stagii, in aceeasi clasa, si le rulezi pe rand:
//
//   STAGIUL A - varianta naiva. Casa iese pe minus.
//   STAGIUL B - repari, protejand toata vanzarea.
//   STAGIUL C - repari, protejand doar partea care are nevoie.
//
// Fiecare stagiu afiseaza: biletele vandute, stocul ramas, SUMA dintre ele
// si durata.
//
// CRITERIU DE ACCEPTARE:
//   La orice stagiu corect, vandute + stoc ramas = 40.000. Programul verifica
//   singur egalitatea asta si o marcheaza cand nu se respecta.
//   Stagiul A o incalca: raporteaza mult mai putine bilete vandute decat
//   40.000 si, desi s-au cerut toate biletele, ramane stoc nevandut.
//   Stagiile B si C vand exact 40.000 si lasa stocul pe 0.
//   Stagiul C este vizibil mai rapid decat B, iar programul tipareste
//   raportul dintre cele doua durate.
// =============================================================

public class M05b {
    public static void main(String[] args) {

    }
}
