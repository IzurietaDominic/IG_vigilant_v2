package proyecto;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Vehiculo {
    protected String placa;
    protected String propietario;

    public Vehiculo(String placa, String propietario) {
        this.placa = placa;
        this.propietario = propietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    // Método para mostrar y guardar los vehículos dentro de la urbanización con detalles completos
    public static void mostrarVehiculosDentroUrbanizacion() {
        Set<String> vehiculosResidentes = new HashSet<>();
        Set<String> vehiculosVisitantes = new HashSet<>();
        Map<String, String> detallesVehiculos = new HashMap<>();

        String[] archivos = {"Residente.txt", "Visitante.txt"};

        for (String archivo : archivos) {
            File file = new File(archivo);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    String placa = "";
                    String estado = "";
                    String propietario = "";
                    String residencia = "";
                    String evento = "";
                    boolean esVisitante = archivo.equals("Visitante.txt");

                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("Placa: ")) {
                            placa = line.substring(7).trim();
                        } else if (line.startsWith("Estado: ")) {
                            estado = line.substring(8).trim();
                        } else if (line.startsWith("Propietario: ")) {
                            propietario = line.substring(12).trim();
                        } else if (line.startsWith("Residencia: ")) {
                            residencia = line.substring(11).trim();
                        } else if (line.startsWith("Evento: ")) {
                            evento = line.substring(8).trim();
                        } else if (line.equals("----------------------------------------")) {
                            String detalles = "Placa: " + placa + "\n" +
                                              "Propietario: " + propietario + "\n" +
                                              "Residencia: " + residencia + (esVisitante ? ("\nEvento: " + evento) : "") + "\n";
                            if (estado.equalsIgnoreCase("Ingreso")) {
                                if (esVisitante) {
                                    vehiculosVisitantes.add(placa);
                                } else {
                                    vehiculosResidentes.add(placa);
                                }
                                detallesVehiculos.put(placa, detalles);
                            } else {
                                if (esVisitante) {
                                    vehiculosVisitantes.remove(placa);
                                } else {
                                    vehiculosResidentes.remove(placa);
                                }
                                detallesVehiculos.remove(placa);
                            }
                            placa = "";
                            estado = "";
                            propietario = "";
                            residencia = "";
                            evento = "";
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Guardar resultados detallados en Vehiculo.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Vehiculo.txt"))) {
            writer.write("========== VEHÍCULOS RESIDENTES DENTRO ==========" + "\n");
            writer.write("Cantidad: " + vehiculosResidentes.size() + "\n\n");
            for (String placa : vehiculosResidentes) {
                writer.write(detallesVehiculos.get(placa) + "----------------------------------------\n");
            }

            writer.write("========== VEHÍCULOS VISITANTES DENTRO ==========" + "\n");
            writer.write("Cantidad: " + vehiculosVisitantes.size() + "\n\n");
            for (String placa : vehiculosVisitantes) {
                writer.write(detallesVehiculos.get(placa) + "----------------------------------------\n");
            }

            System.out.println("Informe detallado de vehículos generado en Vehiculo.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el informe de vehículos.");
        }
    }
}
