package PolimorFiguras;

public class Quadrat extends Rectangle{

    public Quadrat(int r){
        super(r,r);
    }

    //@Override
    //public double area() {
    //    return this.l*this.l;
    //}

    @Override
    public String toString() {
        return "Quadrat(" + "lado=" + super.getL1()+ ')' + " area= " + this.area();
    }

}