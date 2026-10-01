// =============================================================
// MINI-PROIECT 04 - Calculator de facturi
// =============================================================
// Ai 6 facturi. Fiecare factura are un numar si o lista de sume.
// Pentru fiecare factura trebuie calculat totalul, iar calculul dureaza
// (simuleaza cu o asteptare): factura 0 dureaza cel mai mult, factura 5
// cel mai putin.
//
// Factura 3 contine o suma invalida. Calculul ei trebuie sa arunce o
// exceptie cu mesajul "Invalid amount on invoice 3".
//
// Calculele se desfasoara in acelasi timp, dar rezultatele le ceri in
// ordinea facturilor: intai factura 0, apoi 1, si asa mai departe.
//
// Pentru fiecare factura afisezi, in ordine, o linie cu:
//   numarul facturii, totalul (sau motivul erorii) si la cate milisecunde
//   de la pornirea programului ai obtinut acel rezultat.
//
// La final afisezi suma totala a facturilor valide si cate au esuat.
//
// CRITERIU DE ACCEPTARE:
//   Pentru factura 3 afisezi mesajul "Invalid amount on invoice 3", nu
//   numele unei exceptii de invelis.
//   Prima linie apare tarziu, iar urmatoarele apar aproape instantaneu una
//   dupa alta - si explici in output, intr-o singura propozitie scrisa de
//   tine, de ce se intampla asta.
//   Celelalte 5 facturi au totalul corect.
// =============================================================

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class M04 {

    static class Factura{

        private final int nrFactura;
        private final int[] sume;

        public Factura(int nrFactura, int[] sume) {
            this.nrFactura = nrFactura;
            this.sume = sume;
        }

        int calculeaza() throws Exception{
            Thread.sleep((6-nrFactura)*300L);
            int total = 0;

            for(int suma : sume){
                if(suma < 0){
                    throw new Exception( "Invalid amount on invoice " + nrFactura);
                }
                total += suma;
            }
            return total;
        }


    }
    public static void main(String[] args)  throws InterruptedException {
        List<Factura> facturi = new ArrayList<>();
        facturi.add(new Factura(0, new int[]{120, 80, 45}));
        facturi.add(new Factura(1, new int[]{200, 15}));
        facturi.add(new Factura(2, new int[]{60, 60, 60}));
        facturi.add(new Factura(3, new int[]{90, -40, 12}));
        facturi.add(new Factura(4, new int[]{310}));
        facturi.add(new Factura(5, new int[]{25, 25, 25, 25}));



        ExecutorService executor = Executors.newFixedThreadPool(3);


        List<Future<Integer>> futures = new ArrayList<>();

        long start=System.currentTimeMillis();


        for(Factura factura : facturi){
            futures.add(executor.submit(factura::calculeaza));
        }
        int total=0;
        int esuate=0;
        for(int i = 0; i < futures.size(); i++){
            try{
                int rezultate = futures.get(i).get();
                System.out.println("Factura " + i + " total: " + rezultate);
            }catch(Exception e){
                System.out.println("Factura "+ i + "  :  " + e.getMessage());
                esuate++;
            }
        }

        executor.shutdown();

        System.out.println("Suma totala : "+ total);
        System.out.println("Suma esuate : "+esuate);
    }
}
