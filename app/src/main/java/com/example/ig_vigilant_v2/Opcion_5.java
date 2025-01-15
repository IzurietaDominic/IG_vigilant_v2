package com.example.ig_vigilant_v2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import proyecto.Evento;

public class Opcion_5 extends Activity {

    private EditText editNombreEvento;
    private CalendarView calendarView;
    private Spinner spinnerEventos;
    private Button btnConfirmar;
    private List<Evento> listaEventos;
    private Date fechaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_5);

        // Inicializar vistas según el layout proporcionado
        editNombreEvento = findViewById(R.id.m5_texto_nombreEvento);
        calendarView = findViewById(R.id.calendarView);
        spinnerEventos = findViewById(R.id.spinnerEvents);
        btnConfirmar = findViewById(R.id.m5_boton_confirmar);

        listaEventos = new ArrayList<>();
        fechaSeleccionada = new Date();

        // Capturar la fecha seleccionada en el CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                fechaSeleccionada = new Date(year - 1900, month, dayOfMonth);
            }
        });

        // Acción del botón Confirmar
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarEvento();
            }
        });
    }

    private void guardarEvento() {
        String nombreEvento = editNombreEvento.getText().toString();

        if (nombreEvento.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el nombre del evento.", Toast.LENGTH_SHORT).show();
            return;
        }

        Evento nuevoEvento = new Evento(nombreEvento, "Ubicación no especificada", 0, fechaSeleccionada, "Residencia no especificada");
        listaEventos.add(nuevoEvento);

        guardarEventoEnArchivo(nuevoEvento);
        Toast.makeText(this, "Evento guardado exitosamente.", Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    private void guardarEventoEnArchivo(Evento evento) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("visitantesEsperados.txt", MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            writer.write("Nombre del Evento: " + evento.getNombreEvento() + "\n");
            writer.write("Fecha: " + sdf.format(evento.getFechaEvento()) + "\n");
            writer.write("--------------------------------------\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar el evento.", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        editNombreEvento.setText("");
        calendarView.setDate(System.currentTimeMillis());
    }
}


