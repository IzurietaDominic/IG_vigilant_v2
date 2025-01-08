package com.example.ig_vigilant_v2;
import static proyecto.Etapa.leerEtapasDesdeArchivo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import proyecto.Etapa;
import proyecto.Residencia;

public class Opcion_2 extends AppCompatActivity {
    private String etapaSeleccionada = "";
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_2);

        Button botonConfirmar = findViewById(R.id.m2_boton_confirmar);
        TextView textoNombre = findViewById(R.id.m2_texto_nombreDeResidencia);
        TextView textoUbicacion = findViewById(R.id.m2_texto_ubicacionDeResidencia);
        TextView textoManzana = findViewById(R.id.m2_texto_ManzanaResidencia);
        Spinner spinner = findViewById(R.id.m2_spinner);

        List<String> nombresEtapas = Etapa.leerEtapasDesdeArchivo(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nombresEtapas
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etapaSeleccionada = parent.getItemAtPosition(position).toString();
                Toast.makeText(Opcion_2.this, "Seleccionaste: " + etapaSeleccionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                etapaSeleccionada = ""; // Valor predeterminado si no se selecciona nada
            }
        });




            // Seleccionar un elemento del Spinner//
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String etapaSeleccionada = parent.getItemAtPosition(position).toString();
                Toast.makeText(Opcion_2.this, "Seleccionaste: " + etapaSeleccionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Acción cuando no se selecciona nada
            }
        });
        //                                               //
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombre = textoNombre.getText().toString();
                String ubicacion = textoUbicacion.getText().toString();
                String manzana = textoManzana.getText().toString();
                Residencia.guardarResidenciaEnArchivo(Opcion_2.this, Nombre, ubicacion, manzana, etapaSeleccionada);


                Acciones_Comunes.cambiarDeActividad(Opcion_2.this, MainActivity.class);
                Acciones_Comunes.reproducirSonido(Opcion_2.this, R.raw.efecto_confirmar);
            }
        });
    }
}
