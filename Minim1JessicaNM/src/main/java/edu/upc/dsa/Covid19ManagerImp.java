package edu.upc.dsa;

import edu.upc.dsa.models.Muestra;
import edu.upc.dsa.models.Persona;
import org.apache.log4j.Logger;

import java.util.*;

public class Covid19ManagerImp implements Covid19Manager{

    final static Logger log = Logger.getLogger(Covid19ManagerImp.class);

    //Referencia privada estatica a l'única instancia de la classe
    private static Covid19ManagerImp instance;

    //Creo lo que necesito
    public HashMap<String, Persona> contenedorUsuarios;
    public String[] arrayLab;
    public HashMap<String, LinkedList<Muestra>> contenedorMuestras;
    public Queue<Muestra> colaMuestras;

    //Constructor privado. Aqui es donde se inicializa lo creado
    private Covid19ManagerImp(){
        //Inicializo lo que necesito
        this.arrayLab = new String[2];
        this.colaMuestras = new LinkedList<Muestra>();
        this.contenedorUsuarios = new HashMap<String,Persona>();
    }

    //mètode d’accés estàtic per tornar una referència a l’única instància
    public static Covid19ManagerImp getInstance(){
        if(instance == null){
            instance = new Covid19ManagerImp();
        }
        return instance;
    }

    public String[] getArrayLab() {
        return arrayLab;
    }

    public void setArrayLab(String[] arrayLab) {
        this.arrayLab = arrayLab;
    }

    @Override
    public void crearUsuario(String id, Persona persona) {
        //Añado usuario al hashmap
        this.contenedorUsuarios.put(id,persona);
        log.info("El usuario " + id + "ha sido creado");
    }

    @Override
    public Muestra extraerMuestra(Muestra muestra) {
        //Añada la muestra a la cola
        colaMuestras.add(muestra);
        log.info("La muestra " + muestra + "ha sido extraida");
        return muestra;
    }

    @Override
    public void procesarMuestra(Boolean positivo, String comentario) {
        //Quito la muestra de la cola
        Muestra muestra = this.colaMuestras.poll();
        String idUser = muestra.getIdPersona();

        muestra.setPositivo(positivo);
        muestra.setComentario(comentario);

        Persona persona = contenedorUsuarios.get(idUser);

        if(contenedorUsuarios.get(idUser) == null){
            log.warn("El usuario no existe");
        }else{
            persona.addMuestra(muestra);
            log.info("La muestra de " + idUser + " ha sido procesada");
        }
    }

    @Override
    public List<Muestra> muestrasPorUsuario(String idUser) {
        Persona persona = contenedorUsuarios.get(idUser);
        if(contenedorUsuarios.get(idUser) == null){
            log.warn("El usuario no existe");
            return null;
        }else{
            log.info("Las muestras por usuario " + idUser);
            return persona.getMuestras();
        }
    }

    public int size() {
        //
        int ret = this.contenedorUsuarios.size();
        log.info("size " + ret);

        return ret;
    }
}
