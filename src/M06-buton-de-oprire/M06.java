// =============================================================
// MINI-PROIECT 06 - Buton de oprire (2 stagii)
// =============================================================
// Un robot de curatenie numara rotatiile periei, cat de repede poate, pana
// cand cineva apasa butonul de oprire. In bucla robotul face UN SINGUR
// lucru: numara. Nu tipareste nimic si nu doarme.
//
// main porneste robotul, asteapta 500 ms, apasa butonul si asteapta cel
// mult 2 secunde ca robotul sa se opreasca.
//
// Scrii doua stagii, in aceeasi clasa, si le rulezi pe rand:
//
//   STAGIUL A - butonul e un camp obisnuit.
//   STAGIUL B - acelasi cod, dar butonul e declarat astfel incat robotul
//               sa vada apasarea imediat.
//
// Diferenta dintre A si B trebuie sa fie de UN SINGUR cuvant in cod.
//
// Fiecare stagiu afiseaza daca robotul s-a oprit, dupa cate milisecunde de
// la apasare si cate rotatii a facut.
//
// CRITERIU DE ACCEPTARE:
//   La stagiul A robotul NU se opreste in cele 2 secunde, iar programul o
//   spune singur ("robotul NU s-a oprit") si trece mai departe la B.
//   La stagiul B robotul se opreste in mai putin de 10 ms de la apasare.
//   Programul se termina singur, fara Ctrl+C, desi robotul de la A nu se
//   opreste niciodata.
// =============================================================

public class M06 {

    static class Robot extends Thread{
        //Pt A fara volatile,rezolva vizibilitatea
        private volatile boolean oprit = false;
        private long rotatii = 0;

        @Override
        public void run() {
            while(!oprit){
                rotatii++;
            }
        }
        void apasaButon(){
            oprit = true;
        }

        long getRotatii(){
            return rotatii;
        }

    }
    public static void main(String[] args) throws InterruptedException {

        Robot robot = new Robot();
        robot.setDaemon(true);
        robot.start();

        Thread.sleep(500);

        long apasare = System.currentTimeMillis();
        robot.apasaButon();
        robot.join(2000);

        long durata = System.currentTimeMillis() - apasare;

        if(robot.isAlive()){
            System.out.println("Robotul nu s-a oprit. ");
        }else {
            System.out.println("Roboul  s-a oprit. ");
        }
        System.out.println("Timp de la apasare: " + durata+ " ms");
        System.out.println("Rotatii: " + robot.getRotatii());

    }
}
