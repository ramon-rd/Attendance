package com.example.attendnace2.app;

/**
 * Created by Juan Francisco on 11/03/14.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.Calendar;

public class AdapterAlumnos extends ArrayAdapter<Alumno>{

    Activity context;
    private ArrayList<Alumno> datos;
    private ArrayList<Boolean> marcados = new ArrayList<Boolean>();

    /**
     * Constructor del Adapter.
     *
     * @param context
     *            context de la Activity que hace uso del Adapter.
     * @param datos
     *            Datos que se desean visualizar en el ListView.
     */
    public AdapterAlumnos(Activity context, ArrayList<Alumno> datos) {
        super(context, R.layout.item_lista_alumnos, datos);
        // Guardamos los parámetros en variables de clase.
        this.context = context;
        this.datos = datos;
        for(int i =0; i < this.getCount(); i++){
            marcados.add(i,false);
        }
    }
    /* Tamaño de la lista de alumnos. */
    @Override
    public int getCount() {
        return datos.size();
    }
    /* */
    @Override
    public Alumno getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // En primer lugar "inflamos" una nueva vista, que será la que se
        // mostrará en la celda del ListView.
        //View item = LayoutInflater.from(context).inflate(R.layout.item_lista_alumnos, null);
        View item = convertView;
        //item.findViewById(R.id.checkbox).setOnClickListener(this);

        final Element elem;

        if(item == null){

            LayoutInflater inflater = context.getLayoutInflater();
            // Definimos en la vista de vuelta el tipo de diseño
            item = inflater.inflate(R.layout.item_lista_alumnos, null);



            // Definimos el objeto que vamos a almacenar en el nuevo elemento
            elem = new Element();
            // Obtenemos los punteros a las etiquetas recién infladas y al icono
            elem.name = (TextView)item.findViewById(R.id.nombre);
            elem.surname = (TextView)item.findViewById(R.id.apellidos);
            elem.checker = (CheckBox)item.findViewById(R.id.chkAsist);
            elem.icon = (ImageView) item.findViewById(R.id.foto);

            // Guardamos el objeto en el elemento
            item.setTag(elem);
        }
        else{
            // Si estamos reutilizando una Vista, recuperamos el objeto interno
            elem = (Element)item.getTag();
        }

        // Cargamos los datos de las opciones de la matriz de datos
        elem.name.setText(datos.get(position).getNombre());
        elem.surname.setText(datos.get(position).getApellidos());
        elem.checker.setChecked(datos.get(position).getAsiste());
        elem.icon.setImageBitmap(datos.get(position).getFoto());

        elem.checker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //boolean selec = false;
                if(elem.checker.isChecked()){
                    marcados.set(position, true);
                    //selec = true;
                }
                else{
                    marcados.set(position, false);
                    //selec = false;
                }

                //elem.checker.setChecked(selec);
                //notifyDataSetChanged();


            }
        });
        elem.checker.setChecked(marcados.get(position));
        // Cambiar el texto de los CheckBox.
        elem.checker.setOnCheckedChangeListener(
                new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        if (isChecked) {
                            elem.checker.setText("Asiste.");
                        }
                        else {
                            elem.checker.setText("Falta.");
                        }
                    }
                });

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opción
        return(item);

    }


    public String obtenerCadenaDeFecha(Calendar fecha){
        return (fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR));
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

}

/* Esta clase almacenará las etiquetas de un alumno en la vista. */
class Element{
    TextView name;
    TextView surname;
    TextView DNI;
    TextView birth_date;
    CheckBox checker;
    ImageView icon;
}
