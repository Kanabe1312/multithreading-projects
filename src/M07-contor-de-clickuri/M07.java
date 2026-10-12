// =============================================================
// MINI-PROIECT 07 - Contor de clickuri (2 stagii)
// =============================================================
// Butonul "Like" al unei postari e apasat de 4 utilizatori in acelasi
// timp, fiecare de exact 100.000 de ori. Contorul de like-uri e comun.
//
// Scrii doua stagii, in aceeasi clasa, si le rulezi pe rand:
//
//   STAGIUL A - contorul e declarat astfel incat fiecare fir vede imediat
//               ultima valoare scrisa de celelalte (aceeasi garantie ca
//               butonul de la M06, stagiul B) - si nimic in plus.
//   STAGIUL B - repari contorul fara sa blochezi firele.
//
// Fiecare stagiu afiseaza like-urile numarate, cele asteptate si cate s-au
// pierdut.
//
// CRITERIU DE ACCEPTARE:
//   Stagiul A pierde like-uri (mai putin de 400.000), desi fiecare fir
//   "vede" valorile celorlalte. Programul tipareste singur cate s-au pierdut.
//   Stagiul B numara exact 400.000 la fiecare rulare.
//   In tot fisierul nu apare cuvantul `synchronized`.
// =============================================================

import java.util.concurrent.atomic.AtomicInteger;

public class M07 {

    static class Contor{
        private volatile int likeuri = 0;

        void click(){
            likeuri++;
        }

        int getlikeuri(){
            return likeuri;
        }
    }

    static class ContorAtomic{
        private final AtomicInteger likeuri = new AtomicInteger(0);
        void click(){
            likeuri.getAndIncrement();
        }
        int getlikeuri(){
            return likeuri.get();
        }
    }

    static class Utilizator implements Runnable{
        private final Contor contor;

        public Utilizator(Contor contor){
            this.contor = contor;
        }
        @Override
        public void run() {
            for(int i = 0; i < 100000; i++){
                contor.click();
            }
        }
    }

    static class UtilizatorAtomic implements Runnable{
        private final ContorAtomic contor;
        public UtilizatorAtomic(ContorAtomic contor){
            this.contor = contor;
        }
        @Override
        public void run() {
            for(int i = 0; i < 100000; i++){
                contor.click();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {

        Contor contor = new Contor();

        Thread t1 = new Thread(new Utilizator(contor));
        Thread t2 = new Thread(new Utilizator(contor));
        Thread t3 = new Thread(new Utilizator(contor));
        Thread t4 = new Thread(new Utilizator(contor));
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        int obtinute = contor.getlikeuri();
        int asteptate = 400000;
        int pierdute = asteptate - obtinute;

        System.out.println("Stagiul A");
        System.out.println("like-uri numarate: " + obtinute);
        System.out.println("like-uri asteptate: " + asteptate);
        System.out.println("like-uri pierdute : " + pierdute);

        stagiulB();

    }
    static void stagiulB() throws InterruptedException {

        ContorAtomic contor = new ContorAtomic();

        Thread t1 = new Thread(new UtilizatorAtomic(contor));
        Thread t2 = new Thread(new UtilizatorAtomic(contor));
        Thread t3 = new Thread(new UtilizatorAtomic(contor));
        Thread t4 = new Thread(new UtilizatorAtomic(contor));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        int obtinute = contor.getlikeuri();
        int asteptate = 400_000;
        int pierdute = asteptate - obtinute;

        System.out.println("Stagiul B");
        System.out.println("like-uri numarate: " + obtinute);
        System.out.println("like-uri asteptate: " + asteptate);
        System.out.println("Like-uri pierdute: " + pierdute);
    }
}
