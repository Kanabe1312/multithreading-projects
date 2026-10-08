package contorViteze;

import java.net.ConnectException;

public class M05_Var_C {
    static class Contor{
        private int valoare = 0;

        void incrementare(){
            synchronized (this){
                valoare++;
            }
        }
        int getvaloare(){
            return valoare;
        }
    }
    static class Vizitator implements Runnable{
        private final Contor contor;

        public Vizitator(Contor contor){
            this.contor = contor;
        }
        @Override
        public void run() {
            for(int i = 0; i < 50000; i++){
                contor.incrementare();

            }
        }
    }

    public static void main(String[] args)  throws InterruptedException{

        Contor contor = new Contor();

        Thread t1 = new Thread(new Vizitator(contor));
        Thread t2 = new Thread(new Vizitator(contor));
        Thread t3 = new Thread(new Vizitator(contor));
        Thread t4 = new Thread(new Vizitator(contor));

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        long durata = System.currentTimeMillis() - start;

        System.out.println("Valuarea obtinuta: "+ contor.getvaloare());
        System.out.println("Valoare astepata (200k)");
        System.out.println("Durata: " + durata +" ms ");


    }

}
