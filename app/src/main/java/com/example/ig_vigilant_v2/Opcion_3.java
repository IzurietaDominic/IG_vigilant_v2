package com.example.ig_vigilant_v2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Opcion_3 extends Activity {
    private EditText etNombreResidencia, etNombreVehiculo, etPlacaVehiculo;
    private Button btnBuscarResidencia;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_3);

        etNombreResidencia = findViewById(R.id.editTextTextPassword);
        etNombreVehiculo = findViewById(R.id.editTextNombreVehiculo);
        etPlacaVehiculo = findViewById(R.id.editTextPlacaVehiculo);
        btnBuscarResidencia = findViewById(R.id.button3);

        btnBuscarResidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarResidencia();
            }
        });
    }

    private void buscarResidencia() {
        String nombreResidencia = etNombreResidencia.getText().toString();
        String nombreVehiculo = etNombreVehiculo.getText().toString();
        String placaVehiculo = etPlacaVehiculo.getText().toString();

        if (nombreResidencia.isEmpty() || nombreVehiculo.isEmpty() || placaVehiculo.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (residenciaExiste(nombreResidencia)) {
            guardarInformacionContacto(nombreResidencia, nombreVehiculo, placaVehiculo);
            Toast.makeText(this, "Información guardada correctamente.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Residencia no encontrada.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean residenciaExiste(String nombreResidencia) {
        File archivoResidencias = new File(getFilesDir(), "residencias.txt");

        if (!archivoResidencias.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoResidencias))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Nombre: ")) {
                    String residenciaRegistrada = line.substring(8).trim();
                    if (residenciaRegistrada.equalsIgnoreCase(nombreResidencia)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void guardarInformacionContacto(String nombreResidencia, String nombreVehiculo, String placaVehiculo) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("contactos.txt", MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write("Residencia: " + nombreResidencia + "\n");
            writer.write("Vehículo: " + nombreVehiculo + "\n");
            writer.write("Placa: " + placaVehiculo + "\n");
            writer.write("--------------------------------------\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar la información.", Toast.LENGTH_SHORT).show();
        }
    }
}


