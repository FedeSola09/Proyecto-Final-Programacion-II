import enums.ETipoEmpleado;
import enums.Especialidad;
import exception.AccionIlegalException;
import exception.ElementoDuplicadoException;
import exception.NotFoundException;
import gestores.GestorMedicoPaciente;
import gestores.menu.Menu;
import repository.PacienteRepository;

import java.util.HashMap;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        HashMap<ETipoEmpleado, Especialidad> rol = menu.menuPrincipal();


        try {
            if (rol.containsKey(ETipoEmpleado.MEDICO)) {
                try {
                    menu.menuMedico(rol.get(ETipoEmpleado.MEDICO));
                } catch (ElementoDuplicadoException f) {
                    System.out.println(f.getMessage());
                } catch (AccionIlegalException e) {
                    System.out.println(e.getMessage());
                } catch (NotFoundException n) {
                    System.out.println(n.getMessage());
                } catch (InputMismatchException i) {
                    System.out.println(i.getMessage());
                }
            }
        }catch (Exception e){
            System.out.println("....");
        }
    }
}