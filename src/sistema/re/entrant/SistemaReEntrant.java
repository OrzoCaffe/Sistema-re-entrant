package sistema.re.entrant;

public class SistemaReEntrant {

    public static void main(String[] args) throws InterruptedException {
        ThreadProva r = new ThreadProva();
        Thread tp = new Thread(r);

        ThreadProva r2 = new ThreadProva();
        Thread tp2 = new Thread(r2);
        tp.start();
        Thread.sleep(2000);
//        tp.join();
        tp2.start();
    }
}
