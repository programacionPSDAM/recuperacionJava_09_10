package hilos;


import java.util.Random;

public class Hilos1 {

    public static void main(String[] args) {
        Thread t1 = new ClaseThread();
        Thread t2 = new ClaseThread();
        Thread t3 = new ClaseThread();
        Thread t4 = new ClaseThread();
        Thread t5 = new ClaseThread();
        Thread t6 = new Thread(new ClaseRunnable());
        Thread t7 = new Thread(new ClaseRunnable());
        Thread t8 = new Thread(new ClaseRunnable());
        Thread t9 = new Thread(new ClaseRunnable());
        Thread t10 = new Thread(new ClaseRunnable());
        t1.start(); t2.start();t3.start();t4.start();t5.start();
        t6.start(); t7.start();t8.start();t9.start();t10.start();

        try {
            Thread.sleep((long) (Math.random() * 20_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FIN DE PROGRAMA");

    }
}

class ClaseThread extends Thread {

    @Override
    public void run() {
        Random random = new Random();
        while (true){
            System.out.println(Thread.currentThread().getName() + " - " + random.nextInt(100));
            try {
                Thread.sleep((long) (Math.random() * 2_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClaseRunnable implements Runnable {

    @Override
    public void run() {
        Random random = new Random();
        while (true){
            System.out.println(Thread.currentThread().getName() + " - " + random.nextInt(100));
            try {
                Thread.sleep((long) (Math.random() * 2_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}