package com.example.ig_vigilant_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import proyecto.Etapa;

public class Menu_Personas extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_personas);

        Button botonContactos = findViewById(R.id.menu_personas_bContacto);
        botonContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acciones_Comunes.cambiarDeActividad(Menu_Personas.this,Menu_Contactos.class);
                Acciones_Comunes.reproducirSonido(Menu_Personas.this, R.raw.efecto_confirmar);
            }
        });

        Button botonVisitantes = findViewById(R.id.menu_personas_bVisitante);
        botonVisitantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acciones_Comunes.cambiarDeActividad(Menu_Personas.this,Menu_Visitantes.class);
                Acciones_Comunes.reproducirSonido(Menu_Personas.this, R.raw.efecto_confirmar);
            }
        });

    }

}
