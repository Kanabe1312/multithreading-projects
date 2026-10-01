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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class M03 {



    static class Jurnal implements Runnable {
        private final Comenzi  comenzi;
        public Jurnal( Comenzi procesate) {
            this.comenzi = procesate;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println("procesate pana acum : "+comenzi.procesate);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    static  class Comenzi{
        private  int procesate=0;
        void inregistreaza(){
            procesate++;
        }
        int getProcesate(){
            return procesate;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Comenzi procesate = new Comenzi();


       Random rand = new Random();
       Thread jurnal = new Thread(new Jurnal(procesate),"jurnal : ");
       jurnal.setDaemon(true);
       jurnal.start();
       long start=System.currentTimeMillis();
       for(int i=1;i<=20;i++){
           Thread.sleep(50+rand.nextInt(101));
           procesate.inregistreaza();
           System.out.println("comanda: "+i+" este gata");

       }

       long total=System.currentTimeMillis()-start;

        System.out.println("total: "+total);
        System.out.println("Comenzi procesatre: "+procesate.getProcesate());
    }
}
