package proyecto;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Visitante {

    public enum Estado {
        AUTORIZADO,
        NO_AUTORIZADO
    }
    private String nombre;
    private String documentoIdentidad;
    private String telefono;
    private Residencia residenciaDestino;
    private Estado estado;;

    //*  Constructor */
    public Visitante(String nombre, String documentoIdentidad, String telefono, Estado estado) {
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.telefono = telefono;
        this.estado = estado;
        //this.residenciaDestino = residenciaDestino;
    }

    //* Getters y Setters */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Residencia getResidenciaDestino() {
        return residenciaDestino;
    }

    public void setResidenciaDestino(Residencia residenciaDestino) {
        this.residenciaDestino = residenciaDestino;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    //metodo estático para guardar visitantes para futuras operaciones//
    public static void guardarVisitanteEnArchivo(Context context, String nombre, String documento, String telefono, Estado estado) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("visitantes_creados.txt", Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Documento de Identidad: " + documento + "\n");
            writer.write("Teléfono: " + telefono + "\n");
            writer.write("Estado: " + estado + "\n");
            writer.write("--------------------------------------\n");
            writer.close();
            Toast.makeText(context, "Visitante guardado correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al guardar visitante", Toast.LENGTH_SHORT).show();
        }
    }
    //metodo estático para guardar visitantes para futuras operaciones//

}
