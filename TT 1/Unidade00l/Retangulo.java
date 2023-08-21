import java.lang.Math;

public class Retangulo {
    int LadoA;
    int LadoB;

    public Retangulo(int a, int b) {
        LadoA = a;
        LadoB = b;
    }

    public Retangulo() {
        LadoA = 0;
        LadoB = 0;
    }

    public double getArea() {
        return (LadoA * LadoB);
    }

    public double getPerimetro() {
        return ((LadoA * 2) + (LadoB * 2));
    }

    public double getDiagonal() {
        double diag = Math.sqrt(LadoA + LadoB);
        return diag;
    }
}