package ar.edu.ort.tp1a.model;

import ar.edu.ort.tp1a.model.Persona;

/**
 * Created by 41587805 on 19/4/2016.
 */
public class Profesor extends Persona {

    public String Materia;

    public  Profesor (){

    }
    public Profesor(String nombre, String a, int sexo, String Materia) {
        super(nombre, a, sexo);
        this.Materia= Materia;
    }

    @Override
    public String imprimir() throws Exception {
        if (Materia.isEmpty())
            throw new Exception("Falta un dato");

        return super.imprimir() + " " +Materia;
    }
}

