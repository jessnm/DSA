import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Student extends Persona {
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Student(String nombre, Date dataNaix) {
        super(nombre);
        this.data = dataNaix;
    }

    public String toString(){
        return super.toString() + "data Naixament: " + this.data;
    }

    public static void main(String[] args) {
        List<Student> estudiante= new ArrayList<Student>();
        estudiante.add(new Student("Frodo",new Date()));
        estudiante.add(new Student("Bilbo",new Date()));
        for(Student p:estudiante)
            System.out.println(p);
    }
}
