package org.lasencinas.cuadrado;


import org.lasencinas.figuraGeometrica.FiguraGeometrica;

public class Cuadrado extends FiguraGeometrica {

    /* ---- Properties of the class ---- */
    private double lado = 0d;


    /* ---- Constructor ---- */
    public Cuadrado() {
        super();
    }

    public Cuadrado(double lado) {
        super();
        this.lado = lado;
    }

    public Cuadrado(String nombre, double lado) {
        super(nombre);
        this.lado = lado;
    }


    /* ---- Getters ---- */
    public double getLado() {
        return lado;
    }


    /* ---- Setters ---- */
    public void setLado(double lado) {
        this.lado = lado;
    }


    /* ---- Main methods ---- */
    @Override
    public double area() {
        return Math.pow(getLado(), 2);
    }
}
