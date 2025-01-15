package com.example.ig_vigilant_v2;

import static com.example.ig_vigilant_v2.Acciones_Comunes.reproducirVideoIndefinido;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //un mediaplayer paa comenzar las canciones//
    private MediaPlayer mediaPlayer;
    private int currentSongIndex = 0;
    private int[] songs = { // Listado de canciones
            R.raw.song1,
            R.raw.song2,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_p);

        //musica en el menu//
        Random random = new Random();
        currentSongIndex = random.nextInt(songs.length);
        playSong(currentSongIndex);
        //                 //

        //Reproducir video//
       //VideoView videoView = findViewById(R.id.video_mP);
      //reproducirVideoIndefinido(this, videoView, R.raw.wabe_blue);
        //                //


        ////BOTONES PARA DIRIGIRSE A LOS OTROS MENUS////

        //menu1//
        Button botonM1 = findViewById(R.id.boton_m1);
        botonM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        // Reproducir sonido al presionar el botón
                        Acciones_Comunes.reproducirSonido(MainActivity.this, R.raw.efecto_confirmar);

                        // Cambiar de actividad
                        Acciones_Comunes.cambiarDeActividad(MainActivity.this, Opcion_1.class);
                    } catch (Exception e) {
                        // Manejo de excepciones
                        Toast.makeText(MainActivity.this, "Error al reproducir sonido o cambiar de actividad", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

        });
        //    //
        //menu2//
        Button botonM2 = findViewById(R.id.boton_m2);
        botonM2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Reproducir sonido al presionar el botón
                    Acciones_Comunes.reproducirSonido(MainActivity.this, R.raw.efecto_confirmar);

                    // Cambiar de actividad
                    Acciones_Comunes.cambiarDeActividad(MainActivity.this,Opcion_2.class);
                } catch (Exception e) {
                    // Manejo de excepciones
                    Toast.makeText(MainActivity.this, "Error al reproducir sonido o cambiar de actividad", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });
        Button botonM3 = findViewById(R.id.boton_m3);
        botonM3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Reproducir sonido al presionar el botón
                    Acciones_Comunes.reproducirSonido(MainActivity.this, R.raw.efecto_confirmar);

                    // Cambiar de actividad
                    Acciones_Comunes.cambiarDeActividad(MainActivity.this, Opcion_3.class);
                } catch (Exception e) {
                    // Manejo de excepciones
                    Toast.makeText(MainActivity.this, "Error al reproducir sonido o cambiar de actividad", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });

        //    //
        //menu4//
        Button botonM4 = findViewById(R.id.boton_m4);
        botonM4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Reproducir sonido al presionar el botón
                    Acciones_Comunes.reproducirSonido(MainActivity.this, R.raw.efecto_confirmar);

                    // Cambiar de actividad
                    Acciones_Comunes.cambiarDeActividad(MainActivity.this, Opcion_4.class);
                } catch (Exception e) {
                    // Manejo de excepciones
                    Toast.makeText(MainActivity.this, "Error al reproducir sonido o cambiar de actividad", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }


        });
        Button botonM5 = findViewById(R.id.boton_m5);
        botonM5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Reproducir sonido al presionar el botón
                    Acciones_Comunes.reproducirSonido(MainActivity.this, R.raw.efecto_confirmar);

                    // Cambiar de actividad
                    Acciones_Comunes.cambiarDeActividad(MainActivity.this, Opcion_5.class);
                } catch (Exception e) {
                    // Manejo de excepciones
                    Toast.makeText(MainActivity.this, "Error al reproducir sonido o cambiar de actividad", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });
        Button botonM8 = findViewById(R.id.boton_m8);
        botonM8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Reproducir sonido al presionar el botón
                    Acciones_Comunes.reproducirSonido(MainActivity.this, R.raw.efecto_confirmar);

                    // Cambiar de actividad
                    Acciones_Comunes.cambiarDeActividad(MainActivity.this,Opcion_8.class);
                } catch (Exception e) {
                    // Manejo de excepciones
                    Toast.makeText(MainActivity.this, "Error al reproducir sonido o cambiar de actividad", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });

        //    //
        //crear una nueva persona//
        Button botonPersona = findViewById(R.id.boton_personas);
        botonPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Reproducir sonido al presionar el botón
                    Acciones_Comunes.reproducirSonido(MainActivity.this, R.raw.efecto_confirmar);

                    // Cambiar de actividad
                    Acciones_Comunes.cambiarDeActividad(MainActivity.this, Menu_Personas.class);
                } catch (Exception e) {
                    // Manejo de excepciones
                    Toast.makeText(MainActivity.this, "Error al reproducir sonido o cambiar de actividad", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });
        //                       //



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }










    //Reproducir aleatoriamente//
    private void playSong(int songIndex) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, songs[songIndex]);

        // Listener para pasar a la siguiente canción al terminar
        mediaPlayer.setOnCompletionListener(mp -> {
            // Alternar al índice de la siguiente canción
            currentSongIndex = (currentSongIndex + 1) % songs.length; // Loop entre canciones
            playSong(currentSongIndex);
        });
        mediaPlayer.start();
    }
    //                          //


    //Liberar los MediaPlayers//
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar recursos del MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
           mediaPlayer = null;
       }
   }
    //                         //
}