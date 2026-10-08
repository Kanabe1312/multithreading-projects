// =============================================================
// MINI-PROIECT 03b - Bara de progres
// =============================================================
// Aplicatia ta copiaza 15 fisiere, unul dupa altul, fiecare durand intre
// 80 si 200 ms.
//
// In paralel ruleaza o bara de progres: la fiecare 100 ms se redeseneaza si
// arata cate fisiere s-au copiat din 15, ca procent. Bara nu stie dinainte
// cat dureaza copierea si nu are un numar fix de redesenari - se redeseneaza
// cat timp exista aplicatie.
//
// Cand cele 15 fisiere s-au copiat, programul afiseaza un sumar si se
// INCHIDE IMEDIAT. Nu are voie sa mai astepte nimic.
//
// CRITERIU DE ACCEPTARE:
//   Programul se termina singur, fara Ctrl+C, in aproximativ timpul celor
//   15 fisiere.
//   ULTIMA linie din output este sumarul, nu o redesenare a barei.
//   Procentul creste de la 0% la 100%, iar in output barele apar intercalate
//   cu liniile de fisier copiat.
//   Sumarul afiseaza 15 din 15.
// =============================================================

import java.util.Random;

public class M03b {
    static class Progres{
        private int copiate = 0;
        synchronized void copiat(){
            copiate++;
        }
        synchronized int getCopiate(){
            return copiate;
        }
    }

    static class COpiere implements Runnable{
        private final Progres progres;
        private final Random rand = new Random();


        public COpiere(Progres progres){
            this.progres = progres;
        }
        @Override
        public void run(){
            try {
                for(int i= 1;i <= 15;i++){
                    int timp = 80 + rand.nextInt(120);
                    Thread.sleep(timp);

                    progres.copiat();

                    System.out.println("Fisier " + i + " copiat");
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

    }
    static class Bara implements Runnable{
        private final Progres progres;

        public Bara(Progres progres){
            this.progres = progres;
        }
        @Override
        public void run(){
            try {
                while(!Thread.currentThread().isInterrupted()){
                    int copiate = progres.getCopiate();
                    int procent = copiate * 100/15;

                    System.out.println("Progress: " + procent + "%");

                    Thread.sleep(100);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            Thread bara = new Thread(new Bara(progres), "Bara");

            bara.setDaemon(true);

        }

    }
    public static void main(String[] args) throws InterruptedException {
        Progres progres = new Progres();

        Thread copiere = new Thread(new COpiere(progres), "Copiere");
        Thread bara = new Thread(new Bara(progres), "Bara");
        bara.setDaemon(true);
        bara.start();
        copiere.start();
        copiere.join();

        System.out.println("Gata: " + progres.getCopiate() + "/15 fisiere copiate.");
    }

}

