// =============================================================
// MINI-PROIECT 03 - Jurnal de fundal
// =============================================================
// Aplicatia ta proceseaza 20 de comenzi, una dupa alta, fiecare durand
// intre 50 si 150 ms.
//
// In paralel ruleaza un jurnal: la fiecare 100 ms scrie o linie cu cate
// comenzi s-au procesat pana acum. Jurnalul nu stie dinainte cat dureaza
// prelucrarea si nu are un numar fix de linii de scris - scrie la nesfarsit,
// cat timp exista aplicatie.
//
// Cand cele 20 de comenzi s-au terminat, programul afiseaza un sumar si
// se INCHIDE IMEDIAT. Nu are voie sa mai astepte nimic.
//
// CRITERIU DE ACCEPTARE:
//   Programul se termina singur, fara Ctrl+C, in aproximativ timpul celor
//   20 de comenzi.
//   ULTIMA linie din output este sumarul, nu o linie de jurnal.
//   In timpul rularii au aparut linii de jurnal intercalate cu comenzile.
// =============================================================

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class M03 {

    static class Comanda implements Runnable {
        private final int numar;
        private final AtomicInteger procesate;

        public Comanda(int numar, AtomicInteger procesate) {
            this.numar = numar;
            this.procesate = procesate;
        }
        @Override
        public void run() {
            try {
                int durata = 50 + new Random().nextInt(100);
                Thread.sleep(durata);

                System.out.println("Comanda : " + numar + "procesata de: " + Thread.currentThread().getName());
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Jurnal implements Runnable {
        private final AtomicInteger procesate;
        public Jurnal( AtomicInteger procesate) {
            this.procesate = procesate;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(100);
                    System.out.println("Jurnal procesate : " + procesate.getAndIncrement());
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {

    }
}
