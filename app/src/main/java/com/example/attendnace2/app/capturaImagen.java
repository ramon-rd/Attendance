package com.example.attendnace2.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO on 1/06/14.
 */
public class capturaImagen extends Activity {
    static int TAKE_PICTURE=1;
    Uri outputFileUri;
    private EditText dni, nombre,apellidos,fecha,asignatura;
    private String[] eventos = {"hola","adios"};
    private Spinner spinner;
    private String nom_evento;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foto);
/*********************************************************************************************/


        List<String> item = new ArrayList<String>();

        //Defino la ruta donde busco los ficheros
        File f = new File(Environment.getExternalStorageDirectory() + "/Asistencias/");
        //Creo el array de tipo File con el contenido de la carpeta
        File[] files = f.listFiles();

        //Hacemos un Loop por cada fichero para extraer el nombre de cada uno
        for (int i = 0; i < files.length; i++)

        {
            //Sacamos del array files un fichero
            File file = files[i];

            //Si es directorio...
            if (file.isDirectory())

                item.add(file.getName());

            //Si es fichero...
            else

                item.add(file.getName());
        }

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                nom_evento = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

    }

    public void hacerFoto(View view)
    {
        nombre=(EditText) findViewById(R.id.editTextnom);
        apellidos=(EditText) findViewById(R.id.editText2ape);
        dni=(EditText) findViewById(R.id.editText2DNI);
        fecha=(EditText) findViewById(R.id.editText3fech);

        String nom,ape,DNI,fech,asig;

        nom = nombre.getText().toString();
        ape = apellidos.getText().toString();
        DNI = dni.getText().toString();
        fech = fecha.getText().toString();


        /***********************************************************************************************/
        File sdCard, directory, file;
        sdCard=Environment.getExternalStorageDirectory();
        FileOutputStream fout;
        try {
            // instanciamos un onjeto File para crear un nuevo
            // directorio
            // la memoria externa
            directory = new File(sdCard.getAbsolutePath()
                    + "/Alumnos/");
            // se crea el nuevo directorio donde se creará el
            // archivo
            directory.mkdirs();

            // creamos el archivo en el nuevo directorio creado
            file = new File(directory, nom_evento + ".txt");

            fout = new FileOutputStream(file,true);
            // Convierte un stream de caracteres en un stream de
            // bytes
            OutputStreamWriter ows = new OutputStreamWriter(fout);
            ows.write(nom); // Escribe en el buffer la cadena de texto
            ows.write("\n");
            ows.write(ape); // Escribe en el buffer la cadena de texto
            ows.write("\n");
            ows.write(DNI); // Escribe en el buffer la cadena de texto
            ows.write("\n");
            ows.write(fech); // Escribe en el buffer la cadena de texto
            ows.write("\n");
            ows.flush(); // Vuelca lo que hay en el buffer dentro del archivo
            ows.close(); // Cierra el archivo de texto

            Toast.makeText(getBaseContext(),
                    "El archivo se ha almacenado!!!",
                    Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        /***********************************************************************************************/

        Intent i = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        File file2 = new File(Environment.getExternalStorageDirectory()+"/Imagenes/",DNI+".jpg");
        outputFileUri = Uri.fromFile(file2);
        i.putExtra(MediaStore.EXTRA_OUTPUT,outputFileUri);
        Toast.makeText(getBaseContext(),
                "foto realizada",
                Toast.LENGTH_LONG).show();
        startActivityForResult(i,TAKE_PICTURE);
    }

}
