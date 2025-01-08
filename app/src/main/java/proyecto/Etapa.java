package proyecto;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Etapa {
    private String nombre;
    private String ubicacion;
    private String descripcion;

    public Etapa(String nombre, String ubicacion, String descripcion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    //Metodo para guardar en un archivo//
    public static void guardarEtapaEnArchivo(Context context, String nombre, String ubicacion, String descripcion) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("etapas.txt", Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Ubicación: " + ubicacion + "\n");
            writer.write("Descripción: " + descripcion + "\n");
            writer.write("--------------------------------------\n");
            writer.close();
            Toast.makeText(context, "Etapa guardada correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //                                    //





    // Método para leer nombres de etapas desde el archivo//
    public static List<String> leerEtapasDesdeArchivo(Context context) {
        List<String> etapas = new ArrayList<>();
        File archivoEtapas = new File(context.getFilesDir(), "etapas.txt");

        if (!archivoEtapas.exists()) {
            etapas.add("No hay etapas disponibles");
            return etapas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoEtapas))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Nombre: ")) {
                    String etapaNombre = line.substring(8).trim(); // Extraer después de "Nombre: "
                    if (!etapaNombre.isEmpty()) {
                        etapas.add(etapaNombre);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            etapas.add("Error al leer el archivo");
        }

        if (etapas.isEmpty()) {
            etapas.add("No hay etapas disponibles");
        }

        return etapas;
    }
    //                                        //



}

