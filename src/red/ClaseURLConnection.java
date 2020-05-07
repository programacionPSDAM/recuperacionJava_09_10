package red;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ClaseURLConnection {

    public static void main(String[] args) {
        PrintWriter out = null;
        Scanner in = null;
        try {
            URL url = new URL("http://rediris.es");
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            out = new PrintWriter(urlConnection.getOutputStream());
            out.println("GET / HTTP/1.1");
            out.println("Host:rediris.es\n");
            out.flush();
            in = new Scanner(urlConnection.getInputStream());
            while (in.hasNextLine())
                System.out.println(in.nextLine());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
        }


    }
}
