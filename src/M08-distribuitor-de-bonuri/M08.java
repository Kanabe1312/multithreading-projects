// =============================================================
// MINI-PROIECT 08 - Distribuitor de bonuri
// =============================================================
// Un automat de bonuri de ordine are 4 usi de intrare. Prin fiecare usa
// intra 25 de clienti, toti in acelasi timp cu cei de la celelalte usi.
// Fiecare client primeste un bon cu un numar. Numerele pornesc de la 1 si
// cresc din 1 in 1. Deasupra ghiseului, un ecran arata numarul ultimului
// bon dat.
//
// Fiecare usa isi noteaza numerele bonurilor date clientilor ei. La final
// programul verifica toate cele 100 de bonuri.
//
// Afisezi: primul bon, ultimul bon, ce arata ecranul, cate bonuri sunt
// dubluri si cate numere lipsesc.
//
// CRITERIU DE ACCEPTARE:
//   Bonurile sunt exact numerele de la 1 la 100, fiecare o singura data:
//   0 dubluri, 0 lipsa. Primul bon e 1, nu 0.
//   Ecranul arata 100 - nu 99, nu 101.
//   Programul verifica singur fiecare conditie si o marcheaza cand nu se
//   respecta.
// =============================================================

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class M08 {

   static class Distribuitor {
       private final AtomicInteger ultimulbon = new AtomicInteger(0);

       int daBon(){
           return ultimulbon.incrementAndGet();
       }
       int getUltimulbon(){
           return ultimulbon.get();
       }
   }

   static class Usa implements Runnable {
       private final Distribuitor distribuitor;
       private final List<Integer> bonuri = new ArrayList<>();

       public Usa(Distribuitor distribuitor) {
           this.distribuitor = distribuitor;
       }

       @Override
       public void run() {
           for (int i = 0; i < 25; i++) {
               int bon = distribuitor.daBon();
               bonuri.add(bon);
           }
       }

       List<Integer> getBonuri() {
           return bonuri;
       }
   }
    public static void main(String[] args) throws InterruptedException {
        Distribuitor distribuitor = new Distribuitor();

        Usa usa1 = new Usa(distribuitor);
        Usa usa2 = new Usa(distribuitor);
        Usa usa3 = new Usa(distribuitor);
        Usa usa4 = new Usa(distribuitor);

        Thread t1 = new Thread(usa1);
        Thread t2 = new Thread(usa2);
        Thread t3 = new Thread(usa3);
        Thread t4 = new Thread(usa4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Ultimul bon: " + distribuitor.getUltimulbon());

        Set<Integer> toateBonurile = new HashSet<>();

        toateBonurile.addAll(usa1.getBonuri());
        toateBonurile.addAll(usa2.getBonuri());
        toateBonurile.addAll(usa3.getBonuri());
        toateBonurile.addAll(usa4.getBonuri());

        int primulBon = Integer.MAX_VALUE;
        int ultimulBon = 0;
        int dubluri = 0;

        List<Integer> listaBonuri = new ArrayList<>();

        listaBonuri.addAll(usa1.getBonuri());
        listaBonuri.addAll(usa2.getBonuri());
        listaBonuri.addAll(usa3.getBonuri());
        listaBonuri.addAll(usa4.getBonuri());

        for (int bon : listaBonuri) {
            if (bon < primulBon) {
                primulBon = bon;
            }

            if (bon > ultimulBon) {
                ultimulBon = bon;
            }

            if (!toateBonurile.remove(bon)) {
                dubluri++;
            }
        }

        int lipsa = 100 - (listaBonuri.size() - dubluri);

        System.out.println("Primul bon: " + primulBon);
        System.out.println("Ultimul bon: " + ultimulBon);
        System.out.println("Ecranul arata: " + distribuitor.getUltimulbon());
        System.out.println("Dubluri: " + dubluri);
        System.out.println("Numere lipsa: " + lipsa);
    }
}
