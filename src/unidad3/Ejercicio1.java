package unidad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Ejercicio1 {

    public static void main(String[] args) throws URISyntaxException, IOException {

        URL url = new URI(args[0]).toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader((con.getInputStream())));
            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
            }
            con.disconnect();
        } else {
            System.out.println(responseCode);
        }
    }
}
