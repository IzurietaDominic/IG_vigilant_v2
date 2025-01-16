package proyecto;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehiculoVisitante extends Vehiculo {
    private String evento;
    private String residencia;
    private String accion;

    // Constructor
    public VehiculoVisitante(String evento, String residencia, String placa, String propietario, String accion) {
        super(placa, propietario);
        this.residencia = residencia;
        this.evento = evento;
        this.accion = accion;
    }

    // Getters y setters
    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    // Genera un registro con la acción (Ingreso/Salida)
    public String generarRegistro(String accion) {
        String fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return  "----------- Vehículo Visitante ----------\n" +
                "Estado: " + accion + "\n" +
                "Fecha y hora: " + fechaHora + "\n" +
                "Placa: " + getPlaca() + "\n" +
                "Propietario: " + getPropietario() + "\n" +
                "Residencia: " + residencia + "\n" +
                "Evento: " + evento + "\n" +
                "----------------------------------------\n";
    }

    // Método para guardar el registro en el archivo Visitante.txt en almacenamiento interno
    public void guardarVehiculoVisitanteEnArchivo(Context context) {
        try {
            File archivo = new File(context.getFilesDir(), "Visitante.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));
            writer.write(generarRegistro(getAccion()));
            writer.flush();
            writer.close();
            Toast.makeText(context, "Vehículo visitante guardado correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al guardar vehículo visitante", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para leer solo la Placa y Propietario de los vehículos visitantes desde el archivo Visitante.txt
    public static List<String> leerVehiculosVisitantes(Context context) {
        List<String> registrosVisitantes = new ArrayList<>();
        File archivoVisitantes = new File(context.getFilesDir(), "Visitante.txt");

        if (!archivoVisitantes.exists()) {
            registrosVisitantes.add("No hay registros de vehículos visitantes.");
            return registrosVisitantes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoVisitantes))) {
            String placa = "";
            String propietario = "";
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Placa: ")) {
                    placa = line.substring(7).trim();
                } else if (line.startsWith("Propietario: ")) {
                    propietario = line.substring(12).trim();
                } else if (line.equals("----------------------------------------")) {
                    registrosVisitantes.add("Placa: " + placa + " || Propietario: " + propietario);
                    placa = "";
                    propietario = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            registrosVisitantes.add("Error al leer el archivo de vehículos visitantes.");
        }

        return registrosVisitantes;
    }
}
