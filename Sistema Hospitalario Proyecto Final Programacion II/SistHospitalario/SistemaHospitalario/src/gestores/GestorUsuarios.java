package gestores;

import enums.ETipoEmpleado;
import enums.Especialidad;
import exception.NoSePudoAgregarException;
import exception.NoSePudoModificarException;
import exception.NotFoundException;
import model.Paciente;
import model.Usuario;
import repository.PacienteRepository;
import repository.UsuarioRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GestorUsuarios implements GestorBasic<Usuario>{
    UsuarioRepository repository = new UsuarioRepository();
    List<Usuario> listadoUsuarios = new ArrayList<>();

    public List<Usuario> getListadoUsuarios() {
        return listadoUsuarios;
    }

    public boolean agregar(Usuario usuario) throws NotFoundException {
        if(!listadoUsuarios.contains(usuario)){
            throw new NotFoundException("No se encontro el usuario");
        }else{
            return listadoUsuarios.add(usuario);
        }
    }

    public boolean eliminar(Usuario usuario) throws NotFoundException {
        if(!listadoUsuarios.contains(usuario)){
            throw new NotFoundException("No se encontro el usuario para eliminar");
        }else{
            return listadoUsuarios.remove(usuario);
        }
    }

    //MODIFICAR CONTRASEÑA
    public Usuario modificar(Usuario usuario) throws NoSePudoModificarException {
        String newPassword = "";
        Scanner scanner = new Scanner(System.in);

        try {
            if (usuario != null) {
                System.out.println("Ingrese la nueva contraseña: ");
                newPassword = scanner.nextLine();
                usuario.setPassword(newPassword);
            } else {
                throw new NoSePudoModificarException("No se encontro el usuario a modificar");
            }
        }
        catch (NoSePudoModificarException p) {
            System.out.println(p.getMessage());
        }

        return usuario;
    }

    public boolean buscar(Usuario usuario) throws NotFoundException {
        if(listadoUsuarios.contains(usuario)){
            return true;
        }else{
            throw new NotFoundException("No se encontro el usuario");
        }
    }

    public HashMap<ETipoEmpleado, Especialidad> ingresar(Usuario user) throws NotFoundException {
        listadoUsuarios = repository.leer();
        HashMap<ETipoEmpleado, Especialidad> credenciales = new HashMap<>();

        for (Usuario usuario : listadoUsuarios) {
            if (usuario.getMatricula().equals(user.getMatricula())
                    && usuario.getPassword().equals(user.getPassword())) {

                credenciales.put(usuario.getRol().getTipoDeEmpleado(), usuario.getEspecialidad());
                return credenciales;
            }
        }
        throw new NotFoundException("Usuario no encontrado");
    }

}
