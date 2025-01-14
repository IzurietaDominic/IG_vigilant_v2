package com.example.ig_vigilant_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import proyecto.Vehiculo;

public class Menu_Vehiculos extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_vehiculo);

        Button botonVehiculoVisitante = findViewById(R.id.menu_vehiculo_visitante);
        botonVehiculoVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acciones_Comunes.cambiarDeActividad(Menu_Vehiculos.this,Menu_VehiculoVisitante.class);
                Acciones_Comunes.reproducirSonido(Menu_Vehiculos.this, R.raw.efecto_confirmar);
            }
        });

        Button botonVehiculoResidente = findViewById(R.id.menu_vehiculo_residente);
        botonVehiculoResidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acciones_Comunes.cambiarDeActividad(Menu_Vehiculos.this,Menu_VehiculoResidente.class);
                Acciones_Comunes.reproducirSonido(Menu_Vehiculos.this, R.raw.efecto_confirmar);
            }
        });

    }

}