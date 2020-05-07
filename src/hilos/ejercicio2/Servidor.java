package hilos.ejercicio2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        final int PORT = 5050;
        ServerSocket servidor = new ServerSocket(PORT);
        while (true) {
            Socket socket = servidor.accept();
            System.out.printf("Aceptando peticiones en el puerto %d%n%n", PORT);

            Thread manejador = new Manejador(socket);
            manejador.start();
        }
    }
}