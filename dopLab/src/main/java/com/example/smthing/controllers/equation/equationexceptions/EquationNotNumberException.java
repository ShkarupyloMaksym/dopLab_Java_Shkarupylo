package com.example.smthing.controllers.equation.equationexceptions;

public class EquationNotNumberException extends Exception {
    @Override
    public String toString() {
        return super.toString() + " Equation has not only numbers";
    }
}