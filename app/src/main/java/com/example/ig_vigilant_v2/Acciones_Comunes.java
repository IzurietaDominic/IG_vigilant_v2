package com.example.ig_vigilant_v2;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.VideoView;

public class Acciones_Comunes {

    private static MediaPlayer mediaPlayer;   //para el sonido de confirmación



    //método para reproducir sonido//
    public static void reproducirSonido(Context context, int recursoSonido) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Crear y reproducir el sonido
        mediaPlayer = MediaPlayer.create(context, recursoSonido);
        mediaPlayer.start();
    }





    // Método estático para configurar y reproducir un video en bucle//
    public static void reproducirVideoIndefinido(Context context, VideoView videoView, int recursoVideo) {
        // Obtiene la URI del recurso de video
        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + recursoVideo);

        // Configura el video en el VideoView
        videoView.setVideoURI(uri);

        // Listener para reiniciar el video al finalizar
        videoView.setOnCompletionListener(mp -> videoView.start());

        // Reproduce el video
        videoView.start();
    }


    // Método estático para cambiar de actividad//
    public static void cambiarDeActividad(Context context, Class<?> destinoActividad) {
        // Crea un nuevo Intent para iniciar la actividad destino
        Intent intent = new Intent(context, destinoActividad);

        // Inicia la nueva actividad
        context.startActivity(intent);
    }






}
