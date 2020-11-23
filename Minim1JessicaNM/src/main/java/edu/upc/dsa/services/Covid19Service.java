package edu.upc.dsa.services;

import edu.upc.dsa.Covid19ManagerImp;
import edu.upc.dsa.models.Muestra;
import edu.upc.dsa.models.Persona;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/minim1Jessica", description = "Covid19 Service")
@Path("/minim1Jessica")

public class Covid19Service {
    private Covid19ManagerImp cm;

    public Covid19Service() {
        this.cm = Covid19ManagerImp.getInstance();
        if (cm.size()==0) {
            //Tengo que mirar que el tqmqño sea 0 para que no lo hqgq cqdq vez.
            Persona persona = new Persona("Jessica", "Nunez Maturano", 30, "D");
            this.cm.crearUsuario("B012345", persona);

            String labs[] = {"L1", "L2"};
            this.cm.setArrayLab(labs);
            Muestra muestra = new Muestra("M323557", "C234345", "B012345",20201123,this.cm.getArrayLab()[0]);
            Muestra muestra2 = new Muestra("M323559", "C234345", "B012345",20201123,this.cm.getArrayLab()[0]);

            this.cm.extraerMuestra(muestra);
            this.cm.extraerMuestra(muestra2);

            this.cm.procesarMuestra(true,"Positivo y con sintomas");
            this.cm.procesarMuestra(false,"Negativo y curada");
        }

    }

    @GET
    @ApiOperation(value = "Show muestras by idUser", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Muestra.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMuestrasByUser(@PathParam("id") String id) {

        List<Muestra> muestraList = this.cm.muestrasPorUsuario(id);

        GenericEntity<List<Muestra>> entity = new GenericEntity<List<Muestra>>(muestraList) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Find a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Persona.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/getUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUser(@PathParam("id") String id) {
        Persona user = this.cm.contenedorUsuarios.get(id);
        if (user == null) return Response.status(404).build();
        else  return Response.status(201).entity(user).build();
    }
}