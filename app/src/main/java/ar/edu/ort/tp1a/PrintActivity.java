package ar.edu.ort.tp1a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import ar.edu.ort.tp1a.model.Persona;

public class PrintActivity extends AppCompatActivity {
Persona per= new Persona();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        per = (Persona)extras.getSerializable("Persona");

        TextView nombreyapVw = (TextView)findViewById(R.id.nombreyapellido);
        TextView sexoVw = (TextView)findViewById(R.id.sexo);
        TextView tipoPerVw = (TextView) findViewById(R.id.tipoPer);
        TextView descripcionVw = (TextView) findViewById(R.id.descripcion);

        try {
            nombreyapVw.setText(per.imprimir());
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //String sexoStr = String.valueOf(p.getSexo());
        String sexoStr;
        if (per.getSexo() == Persona.FEMENINO){
            sexoStr = "Femenino";
            nombreyapVw.setTextColor(Color.parseColor("#8258FA"));
        }
        else {
            sexoStr = "Masculino";
            nombreyapVw.setTextColor(Color.parseColor("#01DF74"));
        }

        sexoVw.setText(sexoStr);


    }
        public void btnInvertir (View v)
        {
            String nombre= per.getNombre();
            String nomInvertido="";
            for(int i= (nombre.length()-1); i>-1; i--)
            {
                char result= nombre.charAt(i);
                nomInvertido += result;
            }
            TextView inv= (TextView)findViewById(R.id.mostrar);
            inv.setText(nomInvertido);
        }

        public void btnContar(View v)
        {
            String nombre= per.getNombre();
            int caracteres= nombre.length();
            TextView cont = (TextView) findViewById(R.id.mostrar);
            cont.setText("La cantidad de caracteres es: " + caracteres);


        }


}

