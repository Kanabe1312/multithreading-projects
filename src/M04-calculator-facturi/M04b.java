// =============================================================
// MINI-PROIECT 04b - Cinci furnizori
// =============================================================
// Ceri pretul aceluiasi produs la 5 furnizori. Fiecare furnizor are un nume
// si raspunde dupa un timp propriu, intre 300 si 700 ms (simuleaza cu o
// asteptare). Preturile le alegi tu, dar cel mai ieftin furnizor NU are voie
// sa fie si cel mai rapid.
//
// Al treilea furnizor nu are stoc: cererea catre el trebuie sa arunce o
// exceptie cu mesajul "Supplier gama is out of stock".
//
// Cererile pleaca toate deodata, dar sunt duse de DOUA fire de lucru care se
// refolosesc - nu cate un fir pentru fiecare furnizor. Rezultatele le ceri
// in ordinea furnizorilor.
//
// Fiecare furnizor care raspunde afiseaza numele firului care i-a dus cererea.
//
// La final afisezi care furnizor e cel mai ieftin si cu ce pret, cate oferte
// au esuat, durata totala si suma timpilor ceruti.
//
// CRITERIU DE ACCEPTARE:
//   Pentru al treilea furnizor afisezi "Supplier gama is out of stock", nu
//   numele unei exceptii de invelis.
//   In output, acelasi nume de fir apare de mai multe ori - dovada ca cele
//   5 cereri au fost duse de 2 fire.
//   Durata totala e clar mai mica decat suma timpilor si clar mai mare decat
//   cel mai lent furnizor.
//   Furnizorul anuntat ca cel mai ieftin nu e primul care a raspuns.
// =============================================================

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class M04b {
    static class Furnizor implements Callable<Integer> {

        private final String nume;
        private final int pret;
        private final int timp;
        private final boolean stoc;

        public Furnizor(String nume, int pret, int timp, boolean stoc) {
            this.nume = nume;
            this.pret = pret;
            this.timp = timp;
            this.stoc = stoc;
        }

        @Override
        public Integer call() throws Exception {

            Thread.sleep(timp);

            if (!stoc) {
                throw new Exception("Supplier " + nume + " is out of stock");
            }

            System.out.println(nume + " raspuns de " + Thread.currentThread().getName());
            return pret;
        }
    }
    public static void main(String[] args) {
        List<Furnizor> furnizori = new ArrayList<>();


        furnizori.add(new Furnizor("digi", 150, 500, true));
        furnizori.add(new Furnizor("orange", 120, 300, true));
        furnizori.add(new Furnizor("gama", 80, 600, false));
        furnizori.add(new Furnizor("vodafone", 100, 700, true));
        furnizori.add(new Furnizor("telecom", 130, 400, true));


        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Future<Integer>> futures = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(Furnizor furnizor : furnizori){
            futures.add(executor.submit(furnizor));
        }
        executor.shutdown();

        int totalEsuate = 0;
        int celMaiIeftin = Integer.MAX_VALUE;
        String furnizorIeftin = "";

        for (int i = 0; i < futures.size(); i++) {
            try {
                int pret = futures.get(i).get();

                System.out.println("Pret " + furnizori.get(i).nume + ": " + pret);

                if (pret < celMaiIeftin) {
                    celMaiIeftin = pret;
                    furnizorIeftin = furnizori.get(i).nume;
                }

            } catch (Exception e) {
                System.out.println(e.getCause().getMessage());
                totalEsuate++;
            }
        }

        long total = System.currentTimeMillis() - start;

        int sumaTimpilor = 0;

        for (Furnizor furnizor : furnizori) {
            sumaTimpilor += furnizor.timp;

        }

        System.out.println();
        System.out.println("Cel mai ieftin: " + furnizorIeftin  + " este " + celMaiIeftin );
        System.out.println("Oferte esuate : " + totalEsuate);
        System.out.println("Durata totala : " + total + " ms");
        System.out.println("Suma timpilor : " + sumaTimpilor + " ms");
    }
}
