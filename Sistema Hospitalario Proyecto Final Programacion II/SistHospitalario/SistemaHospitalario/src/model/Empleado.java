package model;

import enums.EGenero;
import enums.ETipoEmpleado;

public class Empleado extends Persona {
    private ETipoEmpleado tipoDeEmpleado;
    private Double salario;

    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String dni, String email, EGenero genero, ETipoEmpleado tipoDeEmpleado, Double salario) {
        super(nombre, apellido, dni, email, genero);
        this.tipoDeEmpleado = tipoDeEmpleado;
        this.salario = salario;
    }

    public Empleado(ETipoEmpleado tipoDeEmpleado, Double salario) {
        this.tipoDeEmpleado = ETipoEmpleado.MANTENIMIENTO;
        this.salario = (Double) 0.00;
    }

    public ETipoEmpleado getTipoDeEmpleado() {
        return tipoDeEmpleado;
    }

    public void setTipoDeEmpleado(ETipoEmpleado tipoDeEmpleado) {
        this.tipoDeEmpleado = tipoDeEmpleado;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "tipoDeEmpleado=" + tipoDeEmpleado +
                ", salario=" + salario +
                '}';
    }

}
