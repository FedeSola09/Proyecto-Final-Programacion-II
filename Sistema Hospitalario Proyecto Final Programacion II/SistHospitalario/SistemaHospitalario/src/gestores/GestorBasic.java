package gestores;

import exception.ElementoDuplicadoException;
import exception.NotFoundException;
import model.Paciente;

public interface GestorBasic<T>{
    boolean agregar(T t) throws ElementoDuplicadoException, NotFoundException;
    boolean eliminar(T t) throws NotFoundException;
    boolean buscar(T t) throws NotFoundException;

    T modificar(T t);

}
