package hilos;

import java.util.Random;

public class Hilos3 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread t1 = new HilosUsuarioDemonio();
            t1.setDaemon(true);
            t1.start();

        }
        Thread t2 = new HilosUsuarioDemonio();
        t2.start();
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FIN DE PROGRAMA");

    }
}

class HilosUsuarioDemonio extends Thread {
    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(new Random().nextInt(2_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
