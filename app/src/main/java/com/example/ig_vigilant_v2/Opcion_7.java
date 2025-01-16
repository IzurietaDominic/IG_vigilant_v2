package com.example.ig_vigilant_v2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import proyecto.VehiculoResidente;


public class Opcion_7 extends Activity {

    private Spinner spinnerPropietarios;
    private Spinner spinnerEstado;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_6);

        spinnerPropietarios = findViewById(R.id.spinner_propietarios);
        spinnerEstado = findViewById(R.id.spinner_estado);
        btnGuardar = findViewById(R.id.boton_guardar);

        cargarPropietarios();
        cargarEstados();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarEstadoVehiculo();
            }
        });
    }

    // Cargar Propietarios y Placas desde Residente.txt
    private void cargarPropietarios() {
        List<String> listaPropietarios = VehiculoResidente.leerVehiculosResidentes(Opcion_7.this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaPropietarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPropietarios.setAdapter(adapter);
    }

    // Cargar opciones de estado (Ingreso / Salida)
    private void cargarEstados() {
        List<String> estados = new ArrayList<>();
        estados.add("Ingreso");
        estados.add("Salida");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);
    }

    // Actualizar el estado del vehículo en Residente.txt
    private void actualizarEstadoVehiculo() {
        String seleccion = spinnerPropietarios.getSelectedItem().toString();
        String nuevoEstado = spinnerEstado.getSelectedItem().toString();

        File archivo = new File(getFilesDir(), "Residente.txt");
        File archivoTemp = new File(getFilesDir(), "Residente_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));
             FileWriter writer = new FileWriter(archivoTemp)) {

            String line;
            boolean actualizar = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Placa: ") && seleccion.contains(line.substring(7).trim())) {
                    actualizar = true;
                }
                if (actualizar && line.startsWith("Estado: ")) {
                    line = "Estado: " + nuevoEstado;
                    actualizar = false;
                }
                writer.write(line + "\n");
            }

            if (archivo.delete()) {
                archivoTemp.renameTo(archivo);
                Toast.makeText(this, "Estado actualizado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al actualizar el estado", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al acceder al archivo", Toast.LENGTH_SHORT).show();
        }
    }
}
