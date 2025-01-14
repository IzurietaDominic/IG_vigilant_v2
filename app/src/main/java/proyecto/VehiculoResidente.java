package proyecto;

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

    // Método para guardar el registro en el archivo Residente.txt
    public void guardarVehiculoResidenteEnArchivo() {
        try {
            // Se genera el registro reutilizando el método de la clase Vehiculo
            String registro = generarRegistro(getAccion());

            // Se abre el archivo en modo APPEND (agregar al final del archivo)
            FileWriter fileWriter = new FileWriter("Residente.txt", true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            // Escribe el registro y asegura la escritura inmediata
            writer.write(registro);
            writer.flush();  // Asegura que los datos se escriban antes de cerrar
            writer.close();

            // Confirmación de éxito
            System.out.println("Vehículo residente guardado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            // Mensaje de error en caso de fallo
            System.out.println("Error al guardar vehículo residente");
        }
    }

    public static List<String> MostrarArchivoVehiculoResidente() {
        List<String> registroResidentes = new ArrayList<>();
        File archivoResidentes = new File("Residente.txt");
    
        // Verificar si el archivo existe
        if (!archivoResidentes.exists()) {
            registroResidentes.add("No hay registros de vehículos residentes.");
            return registroResidentes;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoResidentes))) {
            StringBuilder registroCompleto = new StringBuilder();
            String line;
    
            while ((line = reader.readLine()) != null) {
                // Detecta el final de un registro
                if (line.equals("--------------------------------------")) {
                    registroResidentes.add(registroCompleto.toString().trim());
                    registroCompleto.setLength(0);  // Reinicia el StringBuilder para el siguiente registro
                } else {
                    registroCompleto.append(line).append("\n");
                }
            }
    
            // Agregar el último registro si no terminó con la línea de separación
            if (registroCompleto.length() > 0) {
                registroResidentes.add(registroCompleto.toString().trim());
            }
    
        } catch (IOException e) {
            e.printStackTrace();
            registroResidentes.add("Error al leer el archivo de vehículos residentes.");
        }
    
        if (registroResidentes.isEmpty()) {
            registroResidentes.add("No hay registros de vehículos residentes.");
        }
    
        return registroResidentes;
        }

}
