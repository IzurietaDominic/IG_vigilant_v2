package proyecto;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Contacto {
    private String nombre;
    private String telefono;
   // private Residencia residencia;

    //constructor

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // getters y settters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

//    public Residencia getResidencia() {
 //       return residencia;
 //   }

 //   public void setResidencia(Residencia residencia) {
 //       this.residencia = residencia;
 //   }

    public static void guardarContactoEnArchivo(Context context, String nombre, String telefono, String residencia) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("contactos.txt", Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write("Nombre: " + nombre + "\n");
            writer.write("teléfono: " + telefono + "\n");
            writer.write("residencia: " + residencia + "\n");
            writer.write("--------------------------------------\n");
            writer.close();
            Toast.makeText(context, "Contacto guardado correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}









