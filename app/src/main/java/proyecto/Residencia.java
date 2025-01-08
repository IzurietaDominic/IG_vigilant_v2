package proyecto;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
public class Residencia {
    private String residencia;
    private String ubicacion;
    private int manzana;
    private Etapa etapa;
    private List<Residente> residentes;
    private List<Contacto> contactos;
    // private List<Vehiculos> vehiculoResidente;
    private List<Evento> eventos;


    //*Constructor Residencia */
    public Residencia(String residencia, String ubicacion, int manzana, Etapa etapa) {
        this.residencia = residencia;
        this.ubicacion = ubicacion;
        this.manzana = manzana;
        this.contactos = new ArrayList<>();
        //  this.vehiculoResidente = new ArrayList<>();
        //  this.eventos = new ArrayList<>();
    }
    //*Agregar contacto a la residencia */
    // public void agregarContacto(Contactos contacto) {
    //    contactos.add(contacto);
    // }

    //*Mostrar contactos de la residencia */
    //  public void mostrarContactos() {
    //     if (contactos.isEmpty()) {
    //     System.out.println("No hay contactos registrados para esta residencia.");
    //  } else {
    //      System.out.println("Contactos de la residencia:");
    //      for (Contactos contacto : contactos) {
    //        System.out.println(contacto.toString());
    //    }
    //   }
    // }
    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public int getManzana() {
        return manzana;
    }

    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    public List<Residente> getResidentes() {
        return residentes;
    }

    public void setResidentes(List<Residente> residentes) {
        this.residentes = residentes;
    }

     public List<Contacto> getContactos() {
        return contactos;
      }

//    public void setContactos(List<Contactos> contactos) {
    //       this.contactos = contactos;
//    }

    //   public List<Vehiculos> getVehiculos() {
//        return vehiculoResidente;
//    }

    //   public void setVehiculos(List<Vehiculos> vehiculoResidente) {
    //       this.vehiculoResidente = vehiculoResidente;
    //   }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    // funciones personalizadas de la clase residencia


    //   public void registrarEvento(Evento e) {
//        eventos.add(e);

    //  }
    //   public void agregarVehiculo(Vehiculos v) {
    //      vehiculoResidente.add(v);
    //  }

    //   public void guardarContacto() {
    //       try (BufferedWriter writer = new BufferedWriter(new FileWriter("contactos.txt", true))) {
    //           for (Contactos contacto : contactos) {
    //              writer.write("Residencia: " + villa);
    //              writer.newLine();
    //             writer.write("Nombre: " + contacto.getNombre());
    //           writer.newLine();
    //            writer.write("Teléfono: " + contacto.getTelefono());
    //          writer.newLine();
    //           writer.write("----");
    //            writer.newLine();
    //       }
    //   } catch (IOException e) {
    //       System.out.println("Error al guardar los contactos.");
    //       e.printStackTrace();
    //   }

    //  }


//que hace un pato con una pata????? :D?
    //se Cae..
    //JAJAJAAJAJJAJAJAJAJAJAJJAJAJAAJ


    // Método estático para guardar residencias en un archivo
    public static void guardarResidenciaEnArchivo(Context context, String nombre, String ubicacion, String manzana, String etapa) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("residencias.txt", Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Ubicación: " + ubicacion + "\n");
            writer.write("Manzana: " + manzana + "\n");
            writer.write("Etapa: " + etapa + "\n");
            writer.write("--------------------------------------\n");
            writer.close();
            Log.d("Residencia", "Residencia guardada exitosamente.");
            Toast.makeText(context, "Residencia guardada correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //                                                             //

    //Método estático para leer residencias en el archivo//
    public static List<String> leerResidenciasDesdeArchivo(Context context) {
        List<String> residencias = new ArrayList<>();
        File archivoResidencias = new File(context.getFilesDir(), "residencias.txt");

        if (!archivoResidencias.exists()) {
            residencias.add("No hay etapas disponibles");
            return residencias; // Salida si el archivo no existe
        }

        // Si el archivo existe, intentar leerlo
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoResidencias))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Nombre: ")) {
                    String residenciaNombre = line.substring(8).trim(); // Extraer después de "Nombre: "
                    if (!residenciaNombre.isEmpty()) {
                        residencias.add(residenciaNombre);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            residencias.add("Error al leer el archivo");
        }

        // Si no se encontraron residencias
        if (residencias.isEmpty()) {
            residencias.add("No hay etapas disponibles");
        }

        return residencias;
    }

    //Método estático mejorado para leer residencias en el archivo//
    public static void leerResidencias(Context context, List<Residencia> listaResidencias, List<String> listaNombresResidencias) {
        // Asegurarse de que las listas estén vacías antes de usarlas
        listaResidencias.clear();
        listaNombresResidencias.clear();

        File archivoResidencias = new File(context.getFilesDir(), "residencias.txt");

        if (!archivoResidencias.exists()) {
            listaNombresResidencias.add("No hay residencias disponibles");
            return; // Salida si el archivo no existe
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoResidencias))) {
            String nombre = null, ubicacion = null, etapa = null;
            int manzana = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Nombre: ")) {
                    nombre = line.substring(8).trim();
                } else if (line.startsWith("Ubicación: ")) {
                    ubicacion = line.substring(11).trim();
                } else if (line.startsWith("Manzana: ")) {
                    manzana = Integer.parseInt(line.substring(9).trim());
                } else if (line.startsWith("Etapa: ")) {
                    etapa = line.substring(7).trim();
                } else if (line.startsWith("--------------------------------------")) {
                    // Crear y agregar una nueva residencia si todos los datos son válidos
                    if (nombre != null && ubicacion != null && etapa != null) {
                        Residencia nuevaResidencia = new Residencia(nombre, ubicacion, manzana, null); // Ajusta según tu constructor
                        listaResidencias.add(nuevaResidencia);
                        listaNombresResidencias.add(nombre); // Agregar solo el nombre a la lista de nombres
                    }

                    // Resetear los valores para el siguiente bloque
                    nombre = null;
                    ubicacion = null;
                    etapa = null;
                    manzana = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            listaNombresResidencias.add("Error al leer el archivo");
        }

        // Si no se encontraron residencias
        if (listaResidencias.isEmpty()) {
            listaNombresResidencias.add("No hay residencias disponibles");
        }
    }
}


