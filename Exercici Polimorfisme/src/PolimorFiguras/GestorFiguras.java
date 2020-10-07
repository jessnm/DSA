package PolimorFiguras;

import java.util.ArrayList;
import java.util.Arrays;

public class GestorFiguras {

    public static void sort(Figuras[] v){
        Arrays.sort(v,((o1, o2) -> (int)(o1.area()- o2.area())));
    }

    public static double suma (Figuras[] v){
        double sum = 0;
        for(Figuras f:v){
            sum+= f.area();
        }
        return sum;
    }

    public static void main(String[] args) {
        Figuras[] v = new Figuras[3];
        v[0] = new Quadrat(4);
        v[1] = new Rectangle (2,1);
        v[2] = new Cercle(2);

        double a = GestorFiguras.suma(v);
        System.out.println(Arrays.asList(v));
        GestorFiguras.sort(v);
        System.out.println(Arrays.asList(v));
    }
}
