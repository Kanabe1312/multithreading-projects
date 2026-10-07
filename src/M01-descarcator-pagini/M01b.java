// =============================================================
// MINI-PROIECT 01b - Trei cuptoare
// =============================================================
// O pizzerie are 3 cuptoare care coc in acelasi timp. Fiecare pizza are un
// nume si un timp de coacere intre 400 si 900 ms, ales la intamplare la
// pornirea programului.
//
// La final afisezi un raport cu:
//   - ordinea in care ai PORNIT cuptoarele
//   - ordinea in care pizzele au IESIT efectiv din cuptor, cu timpul fiecareia
//   - timpul celei mai lente pizza, suma timpilor si durata totala a
//     programului
//
// Ordinea iesirii trebuie sa fie cea reala, nu cea in care le-ai pornit.
//
// CRITERIU DE ACCEPTARE:
//   In raport, timpii din ordinea iesirii sunt crescatori - prima pizza
//   listata are cel mai mic timp de coacere, ultima are cel mai mare.
//   Durata totala e apropiata de cea mai lenta pizza, NU de suma timpilor.
//   Programul scrie singur verdictul: "in paralel" daca totalul e mai mic
//   decat jumatate din suma, "secvential" altfel.
// =============================================================

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class M01b {

    static class Pizza extends Thread {

        private final String nume;
        private final int timp;
        private final List<Pizza> iesite;

        public Pizza(String nume, int timp, List<Pizza> iesite) {
            this.nume = nume;
            this.timp = timp;
            this.iesite = iesite;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timp);

                synchronized (iesite) {
                    iesite.add(this);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String getNume() {
            return nume;
        }

        int getTimp() {
            return timp;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        List<Pizza> iesite = new ArrayList<>();

        Pizza p1 = new Pizza("Margherita", 400 + random.nextInt(501), iesite);
        Pizza p2 = new Pizza("Pepperoni", 400 + random.nextInt(501), iesite);
        Pizza p3 = new Pizza("Hawaii", 400 + random.nextInt(501), iesite);
        long start = System.currentTimeMillis();

        p1.start();
        p2.start();
        p3.start();

        p1.join();
        p2.join();
        p3.join();

        long total = System.currentTimeMillis() - start;

        int suma = 0;
        Pizza lenta = iesite.get(0);

        for (Pizza pizza : iesite) {
            suma += pizza.getTimp();

            if (pizza.getTimp() > lenta.getTimp()) {
                lenta = pizza;
            }
        }

        System.out.println("Ordinea iesirii:");

        for (Pizza pizza : iesite) {
            System.out.println(pizza.getNume() + " - " + pizza.getTimp() + " ms");
        }

        System.out.println();
        System.out.println("Cea mai lenta: " + lenta.getNume()
                + " (" + lenta.getTimp() + " ms)");

        System.out.println("Suma timpilor: " + suma + " ms");
        System.out.println("Durata totala: " + total + " ms");

        System.out.println("Verdict: "
                + (total < suma / 2 ? "in paralel" : "secvential"));

    }
}
