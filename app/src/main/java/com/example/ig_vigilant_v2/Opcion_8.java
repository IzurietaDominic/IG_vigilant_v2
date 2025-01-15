package com.example.ig_vigilant_v2;

import static com.example.ig_vigilant_v2.R.id.textViewResumen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Opcion_8 extends AppCompatActivity {

    private TextView resumenTextView;
    private Button btnGuardar, btnCancelar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_8);

        resumenTextView = findViewById(textViewResumen);
        btnGuardar = findViewById(R.id.buttonGuardar);
        mostrarResumenVehiculos();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarResumenEnArchivo();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mostrarResumenVehiculos() {
        int contadorResidentes = contarVehiculos("Vehiculo_Residente.txt");
        int contadorVisitantes = contarVehiculos("Vehiculo_Visitante.txt");

        String resumen = "Vehículos Residentes: " + contadorResidentes + "\n" +
                "Vehículos Visitantes: " + contadorVisitantes;
        resumenTextView.setText(resumen);
    }

    private int contarVehiculos(String nombreArchivo) {
        int contador = 0;
        File archivo = new File(getFilesDir(), nombreArchivo);
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                while (br.readLine() != null) {
                    contador++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contador;
    }

    private void guardarResumenEnArchivo() {
        File archivo = new File(getFilesDir(), "vehiculos.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(resumenTextView.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


