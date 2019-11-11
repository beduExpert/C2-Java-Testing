package org.bedu.geometria;

public class CalculadoraPerimetro {

    public double cuadrado(double lado){
        return 4 * lado;
    }

    public double rectangulo(double base, double altura){
        return (2 * base) + (2 * altura);
    }

    public double triangulo(double lado1, double lado2, double lado3){
        return lado1 + lado2 + lado3;
    }

    public double circulo(double diametro){
        return Math.PI * diametro;
    }
}
