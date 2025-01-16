package com.example.ig_vigilant_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import proyecto.Residencia;
import proyecto.VehiculoResidente;

public class Menu_VehiculoResidente extends AppCompatActivity {

    private String residenciaSeleccionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_vehiculo_residente);

        Button botonConfirmar = findViewById(R.id.boton_guardar);
        EditText textoPlaca = findViewById(R.id.placa_residente);
        EditText textoPropietario = findViewById(R.id.propietario_residente);
        Spinner spinnerResidencia = findViewById(R.id.spinner_residencia_residente);

        // Cargar residencias en el spinner
        List<String> nombresResidencias = Residencia.leerResidenciasDesdeArchivo(this);
        ArrayAdapter<String> adapterResidencia = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, nombresResidencias);
        adapterResidencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerResidencia.setAdapter(adapterResidencia);

        // Listener para spinner de residencia
        spinnerResidencia.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                residenciaSeleccionada = parent.getItemAtPosition(position).toString();
                Toast.makeText(Menu_VehiculoResidente.this, "Residencia seleccionada: " + residenciaSeleccionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                residenciaSeleccionada = "";
            }
        });

        // Botón Confirmar
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placa = textoPlaca.getText().toString();
                String propietario = textoPropietario.getText().toString();

                if (placa.isEmpty() || propietario.isEmpty() || residenciaSeleccionada.isEmpty()) {
                    Toast.makeText(Menu_VehiculoResidente.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    VehiculoResidente vehiculo = new VehiculoResidente(placa, propietario, residenciaSeleccionada, "Pendiente");
                    vehiculo.guardarVehiculoResidenteEnArchivo(Menu_VehiculoResidente.this);

                    Toast.makeText(Menu_VehiculoResidente.this, "Vehículo residente registrado correctamente", Toast.LENGTH_SHORT).show();
                    Acciones_Comunes.cambiarDeActividad(Menu_VehiculoResidente.this, MainActivity.class);
                    Acciones_Comunes.reproducirSonido(Menu_VehiculoResidente.this, R.raw.efecto_confirmar);
                }
            }
        });
    }
}
