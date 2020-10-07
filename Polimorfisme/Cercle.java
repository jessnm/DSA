package PolimorFiguras;

public class Cercle extends Figuras{
    private double r;

    public Cercle(double r){
        this.r = r;
    }

    @Override
    public double area() {
        return Math.PI*Math.pow(this.r,2);
    }

    @Override
    public String toString() {
        return "Cercle(" + "radi=" + r +')' + " area= " + this.area();
    }

}
