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



public class VehiculoVisitante extends Vehiculo {
    public String evento;
    public String residencia;
    public String accion;

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

    public String getAccion(){
        return accion;
    }

    public void setAccion(String accion){
        this.accion = accion;
    }

    // Genera un registro con la acción (Ingreso/Salida)
    public String generarRegistro(String accion) {
    LocalDateTime fechaHora = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return  "----------- Vehículo Visitante ----------\n" +
            "Estado: " + accion + "\n" +
            "Fecha y hora: " + fechaHora.format(formatter) + "\n" +
            "Placa: " + getPlaca() + "\n" +
            "Propietario: " + getPropietario() + "\n" +
            "Residencia: " + residencia + "\n" +
            "Evento: "+ evento + "\n" +
            "----------------------------------------\n";
    }
    
    // Método para guardar el registro en el archivo Visitante.txt
    public void guardarVehiculoVisitanteEnArchivo() {
        try {
            // Se genera el registro reutilizando el método de la clase Vehiculo
            String registro = generarRegistro(getAccion());

            // Se abre el archivo en modo APPEND para no sobrescribir registros anteriores
            FileWriter fileWriter = new FileWriter("Visitante.txt", true); // 'true' activa el modo APPEND
            BufferedWriter writer = new BufferedWriter(fileWriter);
            

            // Escribe el registro y asegura que se guarde inmediatamente
            writer.write(registro);
            writer.flush();  // Asegura que los datos se escriban antes de cerrar
            writer.close();

            // Confirmación de éxito
            System.out.println("Vehículo visitante guardado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            // Mensaje de error en caso de fallo
            System.out.println("Error al guardar vehículo visitante");
        }
    }

    public static List<String> MostrarArchivoVehiculoVisitante() {
    List<String> registrosVisitantes = new ArrayList<>();
    File archivoVisitantes = new File("Visitante.txt");

    // Verificar si el archivo existe
    if (!archivoVisitantes.exists()) {
        registrosVisitantes.add("No hay registros de vehículos visitantes.");
        return registrosVisitantes;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(archivoVisitantes))) {
        StringBuilder registroCompleto = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            // Detecta el final de un registro
            if (line.equals("--------------------------------------")) {
                registrosVisitantes.add(registroCompleto.toString().trim());
                registroCompleto.setLength(0);  // Reinicia el StringBuilder para el siguiente registro
            } else {
                registroCompleto.append(line).append("\n");
            }
        }

        // Agregar el último registro si no terminó con la línea de separación
        if (registroCompleto.length() > 0) {
            registrosVisitantes.add(registroCompleto.toString().trim());
        }

    } catch (IOException e) {
        e.printStackTrace();
        registrosVisitantes.add("Error al leer el archivo de vehículos visitantes.");
    }

    if (registrosVisitantes.isEmpty()) {
        registrosVisitantes.add("No hay registros de vehículos visitantes.");
    }

    return registrosVisitantes;
    }
}
