package hilos.ejercicio4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.*;

public class LeerNumerosPrimos {
    public static void main(String[] args) {
        int numeroProcesadores = Runtime.getRuntime().availableProcessors();  //nº procesadores del equipo
        ExecutorService executorService = Executors.newFixedThreadPool(numeroProcesadores); //pool hilos nº igual a procesadores
        try (Scanner in = new Scanner(new File("ficheros/numeros_primos.txt"))) { //leemos el fichero
            while (in.hasNextLong()) {
                long numero = in.nextLong();  //leemos número a número
                Future<Boolean> future = executorService.submit(new ComprobarPrimo(numero)); //eviamos a procesar en paralelo
                System.out.printf("%d ¿ es primo ?: %B%n", numero, future.get()); //obtenemos el valor boolean del Callable
            }
            executorService.shutdown();
        } catch (FileNotFoundException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class ComprobarPrimo implements Callable<Boolean> {
    private long numero;

    public ComprobarPrimo(long numero) {
        this.numero = numero;
    }

    @Override
    public Boolean call() throws Exception {
        return isPrime(numero);
    }
    private Boolean isPrime ( long n) {
        if ( n <= 3 ) {
            return n > 1;
        } else if ( n % 2 == 0 || n % 3 == 0 ) {
            return false;
        } else {
            for ( long i = 5 ; i * i <= n ; i += 6 ) {
                if ( n % i == 0 || n % ( i + 2 ) == 0 ) {
                    return false ;
                }
            }
            return true ;
        }
    }
}
