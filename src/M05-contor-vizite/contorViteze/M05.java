package contorViteze;// =============================================================
// MINI-PROIECT 05 - Contor de vizite (3 stagii)
// =============================================================
// Un site numara vizitele. 4 fire de executie inregistreaza fiecare 50.000
// de vizite, deci la final contorul trebuie sa arate exact 200.000.
//
// Fiecare inregistrare de vizita face DOUA lucruri: creste contorul si
// apoi pregateste un raport (o operatie lenta, care nu atinge contorul -
// simuleaza cu un mic calcul care consuma timp).
//
// Scrii trei stagii, in aceeasi clasa, si le rulezi pe rand:
//
//   STAGIUL A - varianta naiva. Contorul iese gresit.
//   STAGIUL B - repari, protejand toata inregistrarea de vizita.
//   STAGIUL C - repari, protejand doar partea care are nevoie.
//
// Fiecare stagiu afiseaza: valoarea obtinuta, valoarea asteptata si durata.
//
// CRITERIU DE ACCEPTARE:
//   Stagiul A afiseaza un numar mai mic decat 200.000 si cat s-a pierdut.
//   Stagiile B si C afiseaza exact 200.000.
//   Stagiul C este vizibil mai rapid decat B, iar programul tipareste
//   raportul dintre cele doua durate.
// =============================================================

public class M05 {
    //VARIANTA A(NAIV)
    static class Contor{
        private int valoare = 0;

        void incrementare(){
            valoare++;
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
        public void run(){
            for(int i = 0; i< 50000; i++){
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
