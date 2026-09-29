// =============================================================
// MINI-PROIECT 01 - Descarcator de pagini
// =============================================================
// Construiesti un program care "descarca" 5 pagini web. Nu descarci nimic
// real: fiecare pagina are un nume si o durata (intre 200 si 1200 ms) pe
// care o simulezi facand programul sa astepte acel timp.
//
// Programul trebuie sa porneasca toate descarcarile, sa le lase sa se
// desfasoare in acelasi timp, si abia dupa ce TOATE s-au terminat sa
// afiseze un raport.
//
// Raportul contine:
//   - fiecare pagina cu durata ei
//   - care pagina a durat cel mai mult
//   - durata totala a programului
//
// CRITERIU DE ACCEPTARE:
//   Durata totala afisata trebuie sa fie apropiata de durata celei mai lente
//   pagini, NU de suma tuturor duratelor. Programul afiseaza ambele numere
//   si scrie singur verdictul: "in paralel" daca totalul e mai mic decat
//   jumatate din suma, "secvential" altfel.
//   Raportul apare o singura data, dupa ce toate cele 5 s-au terminat.
// =============================================================


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class M01 {

    static  class Pagina extends Thread{

        private final String nume;
        private final int durata;

        public Pagina(String nume  , int durata){
            this.nume = nume;
            this.durata = durata;
        }

        @Override
        public void run(){
            try{
                Thread.sleep(durata);
                System.out.println("finished "+nume);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        String getNume(){
            return nume;
        }
        int getDurata(){
            return durata;
        }



    }

    public static void main(String[] args) throws InterruptedException {
        String[] nume = {"acasa", "produse", "contact", "blog", "despre"};

        Random random = new Random();

        List<Pagina> pagini = new ArrayList<>();

       for(int i = 0; i < nume.length; i++){
           int durata = 200 + random.nextInt(1000);
           pagini.add(new Pagina(nume[i], durata));
       }
        long start = System.currentTimeMillis();

        for (Pagina p : pagini) {
            p.start();
        }
        for (Pagina p : pagini) {
            p.join();
        }
        System.out.println("test");
        long total = System.currentTimeMillis() - start;


       long s=0;

       Pagina lenta=pagini.get(0);

       for(Pagina pagina : pagini){
           System.out.println(pagina.getNume()+"-"+pagina.getDurata());
           s+=pagina.durata;
           if(pagina.durata>lenta.durata){
               lenta=pagina;
           }
       }



        System.out.println();
        System.out.println("Cea mai lenta : " + lenta.getNume()
                + " (" + lenta.getDurata() + " ms)");
        System.out.println("Suma duratelor: " + s + " ms");
        System.out.println("Durata totala : " + total + " ms");
        System.out.println("Verdict       : " + (total < s / 2 ? "in paralel" : "secvential"));




    }
}
