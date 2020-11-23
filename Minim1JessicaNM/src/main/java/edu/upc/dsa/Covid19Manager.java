package edu.upc.dsa;

import edu.upc.dsa.models.Muestra;
import edu.upc.dsa.models.Persona;

import java.util.List;

public interface Covid19Manager {

    public void crearUsuario(String id, Persona persona);
    public Muestra extraerMuestra(Muestra muestra);
    public void procesarMuestra(Boolean positive, String comment); //Cola fifo
    public List<Muestra> muestrasPorUsuario(String idUser);
}
