package hilos;

public class Hilos2 {
    public static void main(String[] args) throws InterruptedException {
        Suma t1 = new Suma();
        Suma t2 = new Suma();
        Suma t3 = new Suma();
        Suma t4 = new Suma();
        t1.start(); t2.start(); t3.start(); t4.start();
        t1.join(); t2.join(); t3.join(); t4.join();
        long resultado = t1.getResultado() + t2.getResultado() + t3.getResultado() +
                ((Suma)t4).getResultado();
        System.out.printf("El resultado vale %d%n", resultado);

    }
}
class Suma extends Thread {
    private long resultado = 0;

    public long getResultado() {
        return resultado;
    }

    @Override
    public void run() {
        for (long i = 0; i < 1_000_000_000L; i++) {
            resultado++;
        }
    }
}