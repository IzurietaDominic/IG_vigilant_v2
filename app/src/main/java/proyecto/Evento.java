package proyecto;

import android.content.Context;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

public class Evento {
    private String nombreEvento;
    private String ubicacionEvento;
    private int personasEsperadas;
    private Date fechaEvento;
    private String residenciaPerteneciente;
    private List<Visitante> visitantesDelEvento;



    public String getNombreEvento() {
        return nombreEvento;
    }
    public Evento(String nombreEvento, String ubicacionEvento, int personasEsperadas, Date fechaEvento, String residenciaPerteneciente ){
        this.nombreEvento =nombreEvento;
        this.ubicacionEvento = ubicacionEvento;
        this.personasEsperadas = personasEsperadas;
        this.fechaEvento = fechaEvento;
        this.residenciaPerteneciente = residenciaPerteneciente;
        //OJO AQUI OOOOOOOOO
        visitantesDelEvento = null;

    }
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getUbicacionEvento() {
        return ubicacionEvento;
    }

    public void setUbicacionEvento(String ubicacionEvento) {
        this.ubicacionEvento = ubicacionEvento;
    }


    public List<Visitante> getVisitantesDelEvento() {
        return visitantesDelEvento;
    }

    public void setVisitantesDelEvento(List<Visitante> visitantesDelEvento) {
        this.visitantesDelEvento = visitantesDelEvento;
    }

    public int getPersonasEsperadas() {
        return personasEsperadas;
    }

    public void setPersonasEsperadas(int personasEsperadas) {
        this.personasEsperadas = personasEsperadas;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getResidenciaPerteneciente() {
        return residenciaPerteneciente;
    }

    public void setResidenciaPerteneciente(String residenciaPerteneciente) {
        this.residenciaPerteneciente = residenciaPerteneciente;
    }
    public static void guardarEventoEnArchivo(Context context, String nombreEvento, String ubicacionEvento, int personasEsperadas, String fechaEvento, String residenciaPerteneciente) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("eventos.txt", Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write("Nombre del Evento: " + nombreEvento + "\n");
            writer.write("Ubicación del Evento: " + ubicacionEvento + "\n");
            writer.write("Personas Esperadas: " + personasEsperadas + "\n");
            writer.write("Fecha del Evento: " + fechaEvento + "\n");
            writer.write("Residencia Perteneciente: " + residenciaPerteneciente + "\n");
            writer.write("--------------------------------------\n");
            writer.close();
            Toast.makeText(context, "Evento guardado correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static List<String> leerEventosDesdeArchivo(Context context) {
        List<String> eventos = new ArrayList<>();
        File archivoEventos = new File(context.getFilesDir(), "eventos.txt");

        if (!archivoEventos.exists()) {
            eventos.add("No hay eventos disponibles");
            return eventos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoEventos))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Nombre del Evento: ")) {
                    String eventoNombre = line.substring(19).trim(); // Extrae después de "Nombre del Evento: "
                    if (!eventoNombre.isEmpty()) {
                        eventos.add(eventoNombre);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            eventos.add("Error al leer el archivo");
        }

        if (eventos.isEmpty()) {
            eventos.add("No hay eventos disponibles");
        }

        return eventos;
    }
}

