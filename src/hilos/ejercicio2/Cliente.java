package hilos.ejercicio2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5050);
        System.out.println("Conectando al servidor");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                socket.getOutputStream()));
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce un número:");
            String mensaje = sc.nextLine();
            out.println(mensaje);
            out.flush();
            if (mensaje.equalsIgnoreCase("fin")) {
                System.out.println("Cerrando conexion");
                socket.close();
                System.out.println("Cerrada conexión");
                break;
            }
            String linea = in.readLine();
            System.out.println("Recibido desde el servidor: " + linea);

        }
        sc.close();
        in.close();
        out.close();
    }
}