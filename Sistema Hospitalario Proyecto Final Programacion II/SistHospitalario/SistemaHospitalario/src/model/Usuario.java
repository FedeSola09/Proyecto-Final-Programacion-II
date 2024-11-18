package model;

import enums.EGenero;
import enums.ETipoEmpleado;
import enums.Especialidad;
import exception.NoSePudoAgregarException;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario extends Persona {
    private String matricula;
    private String password;
    private Empleado rol;
    private Especialidad especialidad;

    public Usuario(String nombre, String apellido, String dni, String email, EGenero genero, String matricula,
                   String password, Empleado rol, Especialidad especialidad) {
        super(nombre, apellido, dni, email, genero);
        this.matricula = matricula;
        this.password = password;
        this.rol = rol;
        this.especialidad = especialidad;
    }

    public Usuario(String matricula, String password) {
        this.matricula = matricula;
        this.password = password;
    }
    public Usuario() {}

    public Empleado getRol() {
        return rol;
    }

    public void setRol(Empleado rol) {
        this.rol = rol;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(matricula, usuario.matricula) && Objects.equals(password, usuario.password) && Objects.equals(rol, usuario.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, password, rol);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "matricula='" + matricula + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
