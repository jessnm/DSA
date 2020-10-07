import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private static int lastid;
    private int id;

    public Persona(String nombre) {
        this.nombre = nombre;
        this.id = lastid++;
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

    public String toString(){
        return "id: " +this.id + "\nname: " +this.nombre + "\n";
    }

    public static void main(String[] arg){
        List<Persona> persons = new ArrayList<Persona>();
        persons.add(new Persona("Frodo"));
        persons.add(new Persona("Bilbo"));
        persons.add(new Persona("Christiaan"));
        for(Persona p:persons)
            System.out.println(p);
    }
}
