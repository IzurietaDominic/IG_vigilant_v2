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

import proyecto.Evento;
import proyecto.Residencia;
import proyecto.VehiculoVisitante;

public class Menu_VehiculoVisitante extends AppCompatActivity {

    private String residenciaSeleccionada = "";
    private String eventoSeleccionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_vehiculo_visitante);

        Button botonConfirmar = findViewById(R.id.boton_guardar);
        EditText textoPlaca = findViewById(R.id.placa_visitante);
        EditText textoPropietario = findViewById(R.id.propietario_visitante);
        Spinner spinnerResidencia = findViewById(R.id.spinner_residencia_visitante);
        Spinner spinnerEvento = findViewById(R.id.spinner_evento_visitante);

        // Cargar residencias en el spinner
        List<String> nombresResidencias = Residencia.leerResidenciasDesdeArchivo(this);
        ArrayAdapter<String> adapterResidencia = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, nombresResidencias);
        adapterResidencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerResidencia.setAdapter(adapterResidencia);

        // Cargar eventos en el spinner
        List<String> nombresEventos = Evento.leerEventosDesdeArchivo(this);
        ArrayAdapter<String> adapterEvento = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, nombresEventos);
        adapterEvento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEvento.setAdapter(adapterEvento);

        // Listener para spinner de residencia
        spinnerResidencia.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                residenciaSeleccionada = parent.getItemAtPosition(position).toString();
                Toast.makeText(Menu_VehiculoVisitante.this, "Residencia seleccionada: " + residenciaSeleccionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                residenciaSeleccionada = "";
            }
        });

        // Listener para spinner de evento
        spinnerEvento.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                eventoSeleccionado = parent.getItemAtPosition(position).toString();
                Toast.makeText(Menu_VehiculoVisitante.this, "Evento seleccionado: " + eventoSeleccionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                eventoSeleccionado = "";
            }
        });

        // Botón Confirmar
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placa = textoPlaca.getText().toString();
                String propietario = textoPropietario.getText().toString();

                if (placa.isEmpty() || propietario.isEmpty() || residenciaSeleccionada.isEmpty() || eventoSeleccionado.isEmpty()) {
                    Toast.makeText(Menu_VehiculoVisitante.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    VehiculoVisitante vehiculo = new VehiculoVisitante(eventoSeleccionado, residenciaSeleccionada, placa, propietario, "Pendiente");
                    vehiculo.guardarVehiculoVisitanteEnArchivo(Menu_VehiculoVisitante.this);
                    ;

                    Toast.makeText(Menu_VehiculoVisitante.this, "Vehículo visitante registrado correctamente", Toast.LENGTH_SHORT).show();
                    Acciones_Comunes.cambiarDeActividad(Menu_VehiculoVisitante.this, MainActivity.class);
                    Acciones_Comunes.reproducirSonido(Menu_VehiculoVisitante.this, R.raw.efecto_confirmar);
                }
            }
        });
    }
}
