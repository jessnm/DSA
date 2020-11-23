import edu.upc.dsa.Covid19Manager;
import edu.upc.dsa.Covid19ManagerImp;
import edu.upc.dsa.models.Muestra;
import edu.upc.dsa.models.Persona;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Covid19ManagerImpTest {

    //Primero hay que crear todas lo que tenemos que usar
    final static Logger log = Logger.getLogger(Covid19ManagerImp.class);
    //Declare the main program
    Covid19ManagerImp covid19ManagerImp;

    @Before
    public void setUp() throws Exception{
        //Aqui inicializamos todas las estructuras de datos
        this.covid19ManagerImp = Covid19ManagerImp.getInstance();
    }

    @After
    public void tearDown() throws Exception{
        this.covid19ManagerImp = null;
    }

    @Test
    public void crearUsuario() throws Exception{
        Assert.assertEquals(0,this.covid19ManagerImp.contenedorUsuarios.size());
        Persona persona = new Persona("Jessica", "Nunez Maturano", 30, "D");

        this.covid19ManagerImp.crearUsuario("B012345", persona);

        Assert.assertEquals(1,this.covid19ManagerImp.contenedorUsuarios.size());
    }

    @Test
    public void procesarMuestra() throws Exception{
        String labs[] = {"L1", "L2"};
        this.covid19ManagerImp.setArrayLab(labs);
        Muestra muestra = new Muestra("M323557", "C234345", "B012345",20201123,this.covid19ManagerImp.getArrayLab()[0]);

        this.covid19ManagerImp.extraerMuestra(muestra);
        Assert.assertEquals(1,this.covid19ManagerImp.colaMuestras.size());

        this.covid19ManagerImp.procesarMuestra(true,"Positivo y con sintomas");
        Assert.assertEquals(0,this.covid19ManagerImp.colaMuestras.size());
        Assert.assertEquals(1,this.covid19ManagerImp.muestrasPorUsuario("B012345").size());
    }
}
