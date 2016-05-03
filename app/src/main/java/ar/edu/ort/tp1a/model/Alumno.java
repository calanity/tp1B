package ar.edu.ort.tp1a.model;

/**
 * Created by 41587805 on 19/4/2016.
 */
public class Alumno extends Persona {

    public  String Año;

    public Alumno (String Nombre, String Apellido, int sexo, String año){
        super(Nombre, Apellido, sexo);
        this.Año = año;
    }

    @Override
    public String imprimir() throws Exception {
        if (Año.isEmpty())
            throw new Exception("Falta un dato");

        return super.imprimir() + " " + Año;
    }
}