// =============================================================
// MINI-PROIECT 02b - Colete si curieri
// =============================================================
// Ai 6 colete de livrat. Fiecare livrare dureaza 400 ms.
//
// Le livrezi in DOUA variante si cronometrezi fiecare varianta:
//   Varianta A - cele 6 livrari chiar se desfasoara in acelasi timp.
//   Varianta B - codul arata aproape la fel, dar tot ce se intampla se
//                intampla pe firul principal, unul dupa altul.
//
// Diferenta dintre A si B trebuie sa fie de UN SINGUR cuvant in cod.
//
// Fiecare colet afla, din interiorul livrarii, numele firului care l-a dus.
// La final, pentru fiecare varianta, afisezi durata si CATE FIRE DISTINCTE
// au lucrat efectiv - numarul, nu lista.
//
// CRITERIU DE ACCEPTARE:
//   Varianta A: ~400 ms si 6 fire distincte.
//   Varianta B: ~2400 ms si 1 singur fir distinct.
//   Programul tipareste la final "A a fost de N ori mai rapid" cu N calculat.
// =============================================================

import java.util.HashSet;
import java.util.Set;

public class M02b {
    static class Colet extends Thread {
        private final Set<String> fire;



        public Colet(Set<String> fire) {
            this.fire = fire;
        }
        @Override
        public void run() {
            fire.add(Thread.currentThread().getName());
            try {
                Thread.sleep(400);
                System.out.println("Livrat de: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // Varianta A
        Set<String> fireA = new HashSet<>();

        long startA = System.currentTimeMillis();

        Colet c1 = new Colet(fireA);
        Colet c2 = new Colet(fireA);
        Colet c3 = new Colet(fireA);
        Colet c4 = new Colet(fireA);
        Colet c5 = new Colet(fireA);
        Colet c6 = new Colet(fireA);

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();

        c1.join();
        c2.join();
        c3.join();
        c4.join();
        c5.join();
        c6.join();

        long durataA = System.currentTimeMillis() - startA;

        System.out.println("Varianta A: " + durataA + " ms");
        System.out.println("Fire distincte A: " + fireA.size());


        // Varianta B

        Set<String> fireB = new HashSet<>();

        long startB = System.currentTimeMillis();

        Colet c11 = new Colet(fireB);
        Colet c22 = new Colet(fireB);
        Colet c33 = new Colet(fireB);
        Colet c44 = new Colet(fireB);
        Colet c55 = new Colet(fireB);
        Colet c66 = new Colet(fireB);

        c11.run();
        c22.run();
        c33.run();
        c44.run();
        c55.run();
        c66.run();

        long durataB = System.currentTimeMillis() - startB;

        System.out.println("Varianta B: " + durataB + " ms");
        System.out.println("Fire distincte B: " + fireB.size());
        System.out.println("A a fost de " + Math.round(durataB * 10.0 / durataA) / 10.0 + " ori mai rapid");
    }
}