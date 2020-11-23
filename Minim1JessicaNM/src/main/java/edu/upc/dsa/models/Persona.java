package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Persona {

    String name;
    String apellidos;
    int edad;
    String salud;
    LinkedList<Muestra> muestras;

    public Persona(){
    }

    public Persona(String name, String apellidos, int edad, String salud){
        this.name = name;
        this.apellidos = apellidos;
        this.edad = edad;
        this.salud = salud;
        this.muestras = new LinkedList<Muestra>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSalud() {
        return salud;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    public void addMuestra(Muestra muestra){
            this.muestras.add(muestra);
    }

    public List<Muestra> getMuestras(){
        return muestras;
    }

    @Override
    public String toString() {

        return "Persona [Name = " + name + ", Apellidos = " + apellidos + "Edad= " + edad + "Salud = " + salud + "]";
    }
}
