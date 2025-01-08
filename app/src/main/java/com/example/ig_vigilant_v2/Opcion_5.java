package com.example.ig_vigilant_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Opcion_5 extends AppCompatActivity {
    //boton de aceptar y guardar//
    Button botonConfirmar = findViewById(R.id.m5_boton_confirmar);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_5);






    //proceso al darle click//
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Acciones_Comunes.cambiarDeActividad(Opcion_5.this, MainActivity.class);
            Acciones_Comunes.reproducirSonido(Opcion_5.this, R.raw.efecto_confirmar);

        }

    });
    //proceso al darle click//
        }
}

