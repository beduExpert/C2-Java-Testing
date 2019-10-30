package org.bedu.geometria;

public class CalculadoraArea {

    public double cuadrado(double lado){
        return lado * lado;
    }

    public double rectangulo(double base, double altura){
        return base * altura;
    }

    public double triangulo(double base, double altura){
        return base * altura / 2;
    }

    public double circulo(double radio){
        return Math.PI * Math.pow(radio, 2);
    }
}
