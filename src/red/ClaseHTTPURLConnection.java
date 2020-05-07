package red;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ClaseHTTPURLConnection {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar");
            String file = "uno.jar";
            URLConnection connection = url.openConnection();
            BufferedInputStream in = new BufferedInputStream(url.openStream());
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("ficheros/" + file));
            int leido;
            while ((leido = in.read()) != -1) {
                out.write(leido);
                out.flush();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
