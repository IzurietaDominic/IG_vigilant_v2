package com.example.ig_vigilant_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import proyecto.Contacto;
import proyecto.Etapa;
import proyecto.Residencia;

public class Menu_Contactos extends AppCompatActivity {
    private String residenciaSeleccionada = null;
    private List<Residencia> listaResidencias = new ArrayList<>();
    private List<String> listaNombresResidencias = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_contactos);
        TextView nombreContactos = findViewById(R.id.m_contactos_Nombre);
        TextView numeroTelefono = findViewById(R.id.m_contactos_Telefono);
        Button botonConfirmar = findViewById(R.id.m_contactos_boton_confirmar);

        Spinner spinner = findViewById(R.id.m_contactos_spinner_residencias);

        Residencia.leerResidencias(this, listaResidencias, listaNombresResidencias);
//tren al sur es tremendo rolon//
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
                Toast.makeText(Menu_Contactos.this, "Seleccionaste: " + residenciaSeleccionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                residenciaSeleccionada = null;
            }
        });


        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreContactos.getText().toString();
                String telefono = numeroTelefono.getText().toString();

                if (nombre.isEmpty() || telefono.isEmpty()) {
                    Toast.makeText(Menu_Contactos.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (residenciaSeleccionada == null || residenciaSeleccionada.equals("No hay residencias disponibles")) {
                    Toast.makeText(Menu_Contactos.this, "Seleccione una residencia válida", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear el nuevo contacto
                Contacto nuevoContacto = new Contacto(nombre, telefono);
                Contacto.guardarContactoEnArchivo(Menu_Contactos.this, nombre, telefono, residenciaSeleccionada);

                // Agregar el contacto a la residencia seleccionada
                for (Residencia residencia : listaResidencias) {
                    if (residencia.getResidencia().equals(residenciaSeleccionada)) {
                        residencia.getContactos().add(nuevoContacto);
                        Toast.makeText(Menu_Contactos.this, "Contacto agregado a " + residenciaSeleccionada, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                // Limpiar campos después de guardar
                nombreContactos.setText("");
                numeroTelefono.setText("");
                Acciones_Comunes.cambiarDeActividad(Menu_Contactos.this, MainActivity.class);
                Acciones_Comunes.reproducirSonido(Menu_Contactos.this, R.raw.efecto_confirmar);
            }
        });
    }
        //





    }



