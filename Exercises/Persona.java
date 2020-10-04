import java.util.ArrayList;
import java.util.List;

public class Persona {
    String nombre;
    int id;

    public Persona(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] arg){
        List<Persona> persons = new ArrayList<Persona>();
        persons.add(new Persona("Frodo", persons.size()));
        persons.add(new Persona("Bilbo", persons.size()));
        for(Persona p:persons)
            System.out.println(p.getNombre() + " " + p.getId());
    }
}
