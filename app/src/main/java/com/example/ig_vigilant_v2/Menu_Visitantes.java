package com.example.ig_vigilant_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import proyecto.Visitante;

public class Menu_Visitantes extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_visitantes);
        //nombre del visitante//
        TextView txtNombre = findViewById(R.id.mVisitante_texto_nombre);
        //documento de identidad//
        EditText txtDocumento = findViewById(R.id.mVisitante_documentoIdentidad);
        //telefono visitante//
        EditText txtTelefono = findViewById(R.id.mVisitante_telefono);
        //boton confrmar//
        Button botonConfirmar = findViewById(R.id.mVisitante_boton_confirmar);




        //Funcionamiento del switch para mostrar texto//
        Switch switchAuthorization = findViewById(R.id.switchAuthorization);

        switchAuthorization.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchAuthorization.setText("Autorizado");
            } else {
                switchAuthorization.setText("No autorizado");
            }
        });
        //Funcionamiento del switch para mostrar texto//

        //proceso al darle click//
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                String documento = txtDocumento.getText().toString();
                String telefono = txtTelefono.getText().toString();
                Visitante.Estado estado = switchAuthorization.isChecked()
                        ? Visitante.Estado.AUTORIZADO
                        : Visitante.Estado.NO_AUTORIZADO;
                if (nombre.isEmpty() || documento.isEmpty() || telefono.isEmpty()) {
                    Toast.makeText(Menu_Visitantes.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                Visitante.guardarVisitanteEnArchivo(Menu_Visitantes.this, nombre, documento, telefono, estado);
                Toast.makeText(Menu_Visitantes.this, "Visitante registrado correctamente", Toast.LENGTH_SHORT).show();
                txtNombre.setText("");
                txtDocumento.setText("");
                txtTelefono.setText("");
                switchAuthorization.setChecked(false);

                Acciones_Comunes.cambiarDeActividad(Menu_Visitantes.this, MainActivity.class);
                Acciones_Comunes.reproducirSonido(Menu_Visitantes.this, R.raw.efecto_confirmar);

            }

        });
        //proceso al darle click//
            }


    }

