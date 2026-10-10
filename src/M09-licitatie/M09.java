// =============================================================
// MINI-PROIECT 09 - Licitatie cu o singura oferta (2 stagii)
// =============================================================
// 8 ofertanti liciteaza in acelasi timp pe acelasi tablou. Fiecare face
// 20.000 de oferte crescatoare: oferta numarul j are suma j * 10 plus un
// numar aleator intre 0 si 9.
//
// Licitatia tine minte cea mai mare oferta si cine a facut-o. O oferta mai
// mare decat cea curenta trece printr-o VALIDARE care dureaza (simuleaza cu
// un mic calcul care consuma timp) si abia apoi devine oferta curenta. O
// oferta care oricum pierde nu se mai valideaza.
//
// Cand un ofertant isi termina ofertele, incearca sa ADJUDECE licitatia:
// anunta castigatorul. Anuntul dureaza si el: inainte sa inchida licitatia,
// valideaza inca o data oferta castigatoare. Licitatia se adjudeca O SINGURA
// DATA - primul care ajunge o inchide, ceilalti o gasesc deja inchisa.
//
// Rulezi 20 de licitatii la rand pentru fiecare stagiu. Dupa fiecare
// licitatie, programul calculeaza singur cea mai mare oferta reala (fiecare
// ofertant isi tine propriul maxim) si o compara cu cea retinuta de
// licitatie. Numara si de cate ori s-a anuntat adjudecarea.
//
//   STAGIUL A - verifici, apoi scrii: "daca oferta mea e mai mare decat cea
//               curenta, o pun pe a mea"; "daca licitatia nu e inchisa, o
//               inchid si anunt".
//   STAGIUL B - repari ambele, fara `synchronized` si fara lacate.
//
// CRITERIU DE ACCEPTARE:
//   La stagiul A, in majoritatea celor 20 de runde, maximul retinut difera
//   de maximul real sau adjudecarea se anunta de mai multe ori. Programul
//   numara singur rundele gresite.
//   La stagiul B, toate cele 20 de runde au maximul corect si exact o
//   adjudecare.
// =============================================================

public class M09 {
    public static void main(String[] args) {

    }
}
