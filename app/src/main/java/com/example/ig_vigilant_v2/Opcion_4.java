package com.example.ig_vigilant_v2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import proyecto.Evento;
import proyecto.Residencia;

public class Opcion_4 extends AppCompatActivity {
    private String residenciaSeleccionada = null;
    private List<Residencia> listaResidencias = new ArrayList<>();
    private List<String> listaNombresResidencias = new ArrayList<>();
    private List<Evento> listaEventos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_4);

        //nombre del evento//
        TextView txtNombreEvento = findViewById(R.id.m4_texto_nombre);
        //ubicación del evento//
        TextView txtUbicacionEvento = findViewById(R.id.m4_texto_ubicacion);
        //numero de personas esperadas//
        EditText numeroInput = findViewById(R.id.m4_personas_esperadas);
        //boton para eleccionar fecha//
        Button btnPickDate = findViewById(R.id.m4_fechaPick);
        //texto que muestra la fecha//
        TextView txtSelectedDate = findViewById(R.id.m4_fechaSeleccionada);
        //spinner que muestra las residencias//
        Spinner spinner = findViewById(R.id.m4_spinner_residencias);
        //boton de aceptar y guardar//
        Button botonConfirmar = findViewById(R.id.m4_boton_confirmar);




        //proceso de seleccion de residencia//
        Residencia.leerResidencias(this, listaResidencias, listaNombresResidencias);
        // List<String> nombresResidencias = Residencia.leerResidenciasDesdeArchivo(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                listaNombresResidencias
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                residenciaSeleccionada = listaNombresResidencias.get(position);
                Toast.makeText(Opcion_4.this, "Seleccionaste: " + residenciaSeleccionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                residenciaSeleccionada = null;
            }
        });
        //proceso de seleccion de residencia//

        //proceso de elección de fecha//
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Opcion_4.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Actualizar el TextView con la fecha seleccionada
                                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                txtSelectedDate.setText(selectedDate);
                            }
                        },
                        year, month, day
                );
                datePickerDialog.show();
            }
        });
        //proceso de elección de fecha//

        //proceso al darle click//
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreEvento = txtNombreEvento.getText().toString();
                String ubicacionEvento = txtUbicacionEvento.getText().toString();
                int personasEsperadas = Integer.parseInt(numeroInput.getText().toString());
                String fechaEvento = txtSelectedDate.getText().toString();
                String residenciaPerteneciente = residenciaSeleccionada;


                String[] fechaParts = fechaEvento.split("/");
                int day = Integer.parseInt(fechaParts[0]);
                int month = Integer.parseInt(fechaParts[1]) - 1; // El mes comienza en 0
                int year = Integer.parseInt(fechaParts[2]);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                Date date = calendar.getTime();
                Evento nuevoEvento = new Evento(nombreEvento, ubicacionEvento, personasEsperadas, date, residenciaPerteneciente);
                Evento.guardarEventoEnArchivo(Opcion_4.this, nombreEvento, ubicacionEvento, personasEsperadas, fechaEvento, residenciaSeleccionada);
                listaEventos.add(nuevoEvento);



                Toast.makeText(Opcion_4.this, "Evento creado con éxito", Toast.LENGTH_SHORT).show();
                Acciones_Comunes.cambiarDeActividad(Opcion_4.this, MainActivity.class);
                Acciones_Comunes.reproducirSonido(Opcion_4.this, R.raw.efecto_confirmar);

            }

        });
        //proceso al darle click//

    }
}
