package ar.edu.ort.tp1a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ar.edu.ort.tp1a.model.Alumno;
import ar.edu.ort.tp1a.model.Persona;
import ar.edu.ort.tp1a.model.Profesor;

public class MainActivity extends AppCompatActivity {

    int sexo;
    int tipoP;
    EditText materia, anio;
    ArrayList<Persona> listaPersonas = new ArrayList<>();
    Persona p;

    boolean actividad= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         materia = (EditText) findViewById(R.id.materia);
         anio = (EditText) findViewById(R.id.Año);
    }
    public void btnImprimir(View v) {

        ImageView vv ;
        EditText nombre   = (EditText) findViewById(R.id.nombre);
        EditText apellido   = (EditText) findViewById(R.id.apellido);
        Intent intent = new Intent(this, PrintActivity.class);
        if(tipoP ==1)
        {
            p = new Profesor(nombre.getText().toString(),apellido.getText().toString(),sexo, materia.getText().toString());
        }
        else {
            p = new Alumno(nombre.getText().toString(),apellido.getText().toString(),sexo, anio.getText().toString());
        }
        //Persona p = new Persona(nombre.getText().toString(),apellido.getText().toString(),sexo);
       // Persona p2 = new Persona("Juana","Perez",2);
       intent.putExtra("pers1",p);
        //intent.putExtra("pers2", p2);
        startActivity(intent);
    }

    public void eligeSexo(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.masculino:
                if (checked)
                    //Toast.makeText(this,"Masculino", Toast.LENGTH_SHORT).show();
                    sexo = Persona.MASCULINO;
            break;
            case R.id.femenino:
                if (checked)
                    //Toast.makeText(this,"Femenino", Toast.LENGTH_SHORT).show();
                    sexo = Persona.FEMENINO;
                break;
        }
    }

    public void eligePersona(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.profesor:
                if (checked)
                    //Toast.makeText(this,"Masculino", Toast.LENGTH_SHORT).show();
                    tipoP =  1;
                    anio.setVisibility(View.GONE);
                    materia.setVisibility(View.VISIBLE);
                break;
            case R.id.alumno:
                if (checked)
                    //Toast.makeText(this,"Femenino", Toast.LENGTH_SHORT).show();
                    tipoP = 2;
                    materia.setVisibility(View.GONE);
                anio.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void btnAgregar(View v)
    {
        EditText nombre   = (EditText) findViewById(R.id.nombre);
        EditText apellido   = (EditText) findViewById(R.id.apellido);
        Intent intent = new Intent(this, PrintActivity.class);
        if(tipoP ==1)
        {
            p = new Profesor(nombre.getText().toString(),apellido.getText().toString(),sexo, materia.getText().toString());
        }
        else {
            p = new Alumno(nombre.getText().toString(),apellido.getText().toString(),sexo, anio.getText().toString());
        }


        listaPersonas.add(p);
        Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
        nombre.setText("");
        apellido.setText("");
        materia.setText("");
        anio.setText("");

        //intent.putExtra("pers1",p);
        //startActivity(intent);
    }

    public void btnListar(View v)
    {
        for (Persona p : listaPersonas)
        {
            try {
                Log.d("Persona", p.imprimir());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void eligeproceso(View v)
    {        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.nombrelargo:
                if (checked)
                   actividad=true;
                break;
            case R.id.cantidada:
                if (checked)
                    actividad= false;
                    //recorre en busca de as y las cuenta

                break;

        }

    }
    private Persona nombreMasLargo(){
        Persona PmasLargo = new Persona();
        int MasLargo=0;
        String nom;
        int nomb;
        for (Persona p : listaPersonas)
        {
            nom= p.getNombre().toString();
            nomb= nom.length();

            if (nomb> MasLargo)
            {
                PmasLargo= p;
                MasLargo= nomb;
            }
        }
        return PmasLargo;


    }

    private Persona masAenNombre()
    {
        int contador = 0 ;
        int cantA=0;
        Persona opersona= new Persona();
        String nombree= "";
        if (listaPersonas.size() > 1) {

        for (Persona p : listaPersonas)
        {
           nombree= p.getNombre();
            for(int i=1; i<(nombree.length()-1); i++)
            {
                char result = nombree.charAt(i);
                if (result == 'a')
                {
                    contador++;
                }
            }
            if (contador>cantA)
            {
                opersona= p;
                cantA=contador;
            }
        }
        }
        else
        {
            opersona= p;
        }

        return (opersona);
        //recorrer en busca de la mayor cantidad de "a" if char==a count++;
    }
    public  void  btnProcesar (View v){

        Intent intent = new Intent(this, PrintActivity.class);
        if (actividad== false)
        {
            intent.putExtra("Persona", masAenNombre());
        }
        else {
            intent.putExtra("Persona", nombreMasLargo());
        }
        startActivity(intent);
    }


}

