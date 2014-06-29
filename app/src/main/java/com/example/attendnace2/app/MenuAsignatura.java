package com.example.attendnace2.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Juan Francisco on 11/03/14.
 */
public class MenuAsignatura extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_asignaturas);
    }

    // Lanzar la pantalla de lista de alumnos.
    public void lanzarListaAlumnos(View view){
        Intent i = new Intent(this, ListaAsignaturas.class);
        startActivity(i);
    }

    public void creaAsignatura(View view)
    {
        Intent i = new Intent (this,IntroAsignaturas.class);
        startActivity(i);
    }

}
