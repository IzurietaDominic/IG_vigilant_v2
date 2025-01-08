package com.example.ig_vigilant_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import proyecto.Etapa;

public class Opcion_1  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_1);
        Button botonConfirmar = findViewById(R.id.m1_boton_confirmar);
        TextView textoNombre = findViewById(R.id.m1_texto_nombre);
        TextView textoUbicacion = findViewById(R.id.m1_texto_ubicacion);
        TextView textoDescripcion = findViewById(R.id.m1_texto_descripcion);
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los TextViews
                String nombre = textoNombre.getText().toString();
                String ubicacion = textoUbicacion.getText().toString();
                String descripcion = textoDescripcion.getText().toString();

                // Llamar al método para guardar los datos en el archivo
                Etapa.guardarEtapaEnArchivo(Opcion_1.this, nombre, ubicacion, descripcion);
                Acciones_Comunes.cambiarDeActividad(Opcion_1.this,MainActivity.class);
                Acciones_Comunes.reproducirSonido(Opcion_1.this, R.raw.efecto_confirmar);

            }
        });




    }

}
