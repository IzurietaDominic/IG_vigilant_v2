package proyecto;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class VehiculoResidente extends Vehiculo {
    private String residencia;
    private String accion;

    // Constructor
    public VehiculoResidente(String placa, String propietario, String residencia,String accion) {
        super(placa, propietario);
        this.residencia = residencia;
        this.accion = accion;
    }

    // Getters y setters
    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    // Genera un registro con la acción (Ingreso/Salida)
    public String generarRegistro(String accion) {
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  "----------- Vehículo Residente ----------\n" +
                "Estado: " + accion + "\n" +
                "Fecha y hora: " + fechaHora.format(formatter) + "\n" +
                "Placa: " + getPlaca() + "\n" +
                "Propietario: " + getPropietario() + "\n" +
                "Residencia: " + residencia + "\n" +
                "----------------------------------------\n";
        }

    // Método para guardar el registro en el archivo Residente.txt en almacenamiento interno
    public void guardarVehiculoResidenteEnArchivo(Context context) {
        try {
            File archivo = new File(context.getFilesDir(), "Residente.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));
            writer.write(generarRegistro(getAccion()));
            writer.flush();
            writer.close();
            Toast.makeText(context, "Vehículo Residente guardado correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al guardar vehículo Residente", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para leer solo la Placa y Propietario de los vehículos Residentes desde el archivo Residente.txt
    public static List<String> leerVehiculosResidentes(Context context) {
        List<String> registrosResidentes= new ArrayList<>();
        File archivoResidentes = new File(context.getFilesDir(), "Residente.txt");

        if (!archivoResidentes.exists()) {
            registrosResidentes.add("No hay registros de vehículos residentes.");
            return registrosResidentes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoResidentes))) {
            String placa = "";
            String propietario = "";
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Placa: ")) {
                    placa = line.substring(7).trim();
                } else if (line.startsWith("Propietario: ")) {
                    propietario = line.substring(12).trim();
                } else if (line.equals("----------------------------------------")) {
                    registrosResidentes.add("Placa: " + placa + " || Propietario: " + propietario);
                    placa = "";
                    propietario = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            registrosResidentes.add("Error al leer el archivo de vehículos residentes.");
        }

        return registrosResidentes;
    }
}