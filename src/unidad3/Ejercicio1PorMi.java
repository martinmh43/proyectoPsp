package unidad3;

import java.io.*;
import java.net.*;
import java.util.regex.*;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;

public class Ejercicio1PorMi {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java DescargarImagenes <URL>");
            return;
        }

        // Guarda el valor que le paso por parametros
        String urlPagina = args[0];
        try {
            String contenidoHtml = descargarContenido(urlPagina);
            List<String> urlsImagenes = extraerUrlsImagenes(contenidoHtml, urlPagina);
            Path directorioImagenes = Paths.get("imagenes_descargadas");
            if (!Files.exists(directorioImagenes)) {
                Files.createDirectory(directorioImagenes);
            }
            for (String urlImagen : urlsImagenes) {
                descargarImagen(urlImagen, directorioImagenes);
            }
            System.out.println("Descarga completada. Imágenes guardadas en: " + directorioImagenes.toAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String descargarContenido(String urlPagina) throws IOException {
        StringBuilder contenido = new StringBuilder();
        URL url = new URL(urlPagina);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }
        return contenido.toString();
    }

    private static List<String> extraerUrlsImagenes(String contenidoHtml, String urlBase) throws MalformedURLException {
        List<String> urlsImagenes = new ArrayList<>();
        Pattern patron = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"]", Pattern.CASE_INSENSITIVE); // Expresion regular para detectar las etiquetas <img> y </img>
        Matcher matcher = patron.matcher(contenidoHtml);

        URL base = new URL(urlBase);
        while (matcher.find()) {
            String urlImagen = matcher.group(1);
            if (!urlImagen.startsWith("http")) {
                urlImagen = new URL(base, urlImagen).toString();
            }
            urlsImagenes.add(urlImagen);
        }
        return urlsImagenes;
    }

    private static void descargarImagen(String urlImagen, Path directorio) {
        try (InputStream in = new URL(urlImagen).openStream()) {
            String nombreArchivo = Paths.get(new URL(urlImagen).getPath()).getFileName().toString();
            Path destino = directorio.resolve(nombreArchivo);
            Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Imagen descargada: " + destino.toString());
        } catch (IOException e) {
            System.err.println("Error al descargar la imagen: " + urlImagen);
            e.printStackTrace();
        }
    }
}
