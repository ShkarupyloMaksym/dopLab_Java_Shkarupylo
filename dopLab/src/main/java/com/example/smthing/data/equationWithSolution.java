package com.example.smthing.data;

import com.example.smthing.equation.solving.classificationsurfacesenum.ClassifierOfSurfaces;

import javax.persistence.*;
import java.util.Objects;

import static com.example.smthing.equation.writer.WriterConstants.XYZ_IN_EQUATION;

@Entity
@Table(name = "equation_with_solution")
public class equationWithSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String equation_coefs;
    private String solution;

    public equationWithSolution() {

    }

    public String getEquationCoefs() {
        return equation_coefs;
    }

    public equationWithSolution(double[] equation_coefs_d, String solution) {
        setEquation_coefs(equation_coefs_d);
        setSolution(solution);
    }

    public equationWithSolution(String[] equation_coefs_d, String solution) {
        setEquation_coefs(equation_coefs_d);
        setSolution(solution);
    }

    public Integer getId() {
        return id;
    }

    public String getSolution() {
        return solution;
    }

    public double[] getEquationCoefs_doubleArray() {
        String[] coefsSplited = equation_coefs.split(", ");
        double[] coefsDoubleArr = new double[coefsSplited.length];
        for (int i = 0; i < coefsSplited.length; i++) {
            coefsDoubleArr[i] = Double.parseDouble(coefsSplited[i]);
        }
        return coefsDoubleArr;
    }

    public String getEquationInNormalView() {

        String[] coefficientsString = getEquationCoefs().split(", ");
        StringBuilder equation = new StringBuilder();
        for (int i = 0; i < coefficientsString.length; i++) {
            equation.append(Objects.equals(coefficientsString[i], "1") ? "" : coefficientsString[i]).append(XYZ_IN_EQUATION[i]);
            if (i < coefficientsString.length - 1) {
                equation.append(Double.parseDouble(coefficientsString[i + 1]) < 0 ? " " : " + ");
            }
        }
        return equation.toString();
    }

    public String getSolutionText() {
        return ClassifierOfSurfaces.valueOf(solution).getUkrName();
    }

    public void setEquation_coefs(double[] coefsDoubleArr) {
        StringBuilder equationCoefsBuilder = new StringBuilder();
        for (double coef : coefsDoubleArr) {
            equationCoefsBuilder.append(coef).append(", ");
        }
        equationCoefsBuilder.delete(equationCoefsBuilder.length() - 2, equationCoefsBuilder.length());
        equation_coefs = String.valueOf(equationCoefsBuilder);
    }

    public void setEquation_coefs(String[] coefsDoubleArr) {
        StringBuilder equationCoefsBuilder = new StringBuilder();
        for (String coef : coefsDoubleArr) {
            equationCoefsBuilder.append(coef.isEmpty() ? "0" : coef).append(", ");
        }
        equationCoefsBuilder.delete(equationCoefsBuilder.length() - 2, equationCoefsBuilder.length());
        equation_coefs = String.valueOf(equationCoefsBuilder);
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "id: " + id + " coefs: " + equation_coefs + " solution: " + solution;
    }
}
