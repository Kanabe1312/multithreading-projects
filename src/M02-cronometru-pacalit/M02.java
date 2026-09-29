// =============================================================
// MINI-PROIECT 02 - Cronometrul pacalit
// =============================================================
// Ai 4 sarcini identice, fiecare dureaza 500 ms.
//
// Le rulezi in DOUA variante si cronometrezi fiecare varianta:
//   Varianta A - sarcinile chiar se desfasoara in acelasi timp.
//   Varianta B - codul arata aproape la fel, dar tot ce se intampla se
//                intampla pe firul principal, unul dupa altul.
//
// Diferenta dintre A si B trebuie sa fie de UN SINGUR cuvant in cod.
//
// La final afisezi, pentru fiecare varianta, durata masurata si numele
// firelor de executie care au lucrat efectiv.
//
// CRITERIU DE ACCEPTARE:
//   Varianta A: ~500 ms, iar numele firelor sunt diferite intre ele.
//   Varianta B: ~2000 ms, iar toate sarcinile raporteaza acelasi nume de fir.
//   Programul tipareste la final: "A a fost de N ori mai rapid" cu N calculat.
// =============================================================

public class M02 {

    static class Sarcina extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("Executat de: "+ Thread.currentThread().getName());
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }



    public static void main(String[] args)  throws InterruptedException{

        //Medtoda A

        long startA = System.currentTimeMillis();

        Sarcina s1 = new Sarcina();
        Sarcina s2 = new Sarcina();
        Sarcina s3 = new Sarcina();
        Sarcina s4 = new Sarcina();

        s1.start();
        s2.start();
        s3.start();
        s4.start();

        s1.join();
        s2.join();
        s3.join();
        s4.join();

        long endA = System.currentTimeMillis();
        long durataA = endA - startA;

        System.out.println("Vaianta A : " + durataA + "ms");




        //Metoda B

        long startB = System.currentTimeMillis();

        s1.run();
        s2.run();
        s3.run();
        s4.run();
        long endB = System.currentTimeMillis();
        long durataB = endB - startB;

        System.out.println("Vaianta B : " + durataB + "ms");


        double n = (double)durataA/durataB;
        System.out.println("Vaianta A a fost de " + n + "ori mai rapid ");
    }
}
