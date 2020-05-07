package hilos;


import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Hilos4 {
    public static void main(String[] args)  {
        List<String> palabrasATraducir = new ArrayList<>();
        System.out.println("Introduce palabras castellanas a traducir, finaliza con 0");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String palabraLeida = in.nextLine();
            if (palabraLeida.equals("0"))
                break;
            palabrasATraducir.add(palabraLeida);
        }
        try {
            List<String> lineasFichero = Files.readAllLines(Paths.get("ficheros", "diccionario.csv"));
            //System.out.println(lineasFichero.size());
            int procesadores = Runtime.getRuntime().availableProcessors();
            ExecutorService ex = Executors.newFixedThreadPool(procesadores);
            for (String palabra: palabrasATraducir) {
                Future<String> valor = ex.submit(new Traduccion(lineasFichero, palabra));
                System.out.println(valor.get());
            }
            ex.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
class Traduccion implements Callable<String> {
    private List<String> lineasFichero;
    private String palabraATraducir;

    public Traduccion(List<String> lineasFichero, String palabraATraducir) {
        this.lineasFichero = lineasFichero;
        this.palabraATraducir = palabraATraducir;
    }

    @Override
    public String call() throws Exception {
        for (String linea: lineasFichero) {
            String[] tokens = linea.split("@");
            if (tokens[1].equals(palabraATraducir))
                return palabraATraducir + ": " + tokens[0];
        }
        return palabraATraducir + ": " + "no encontrada";
    }
}


