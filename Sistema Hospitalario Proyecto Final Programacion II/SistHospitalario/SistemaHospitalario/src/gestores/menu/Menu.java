package gestores.menu;

import enums.EGenero;
import enums.ETipoEmpleado;
import enums.ETipoSangre;
import enums.Especialidad;
import exception.AccionIlegalException;
import exception.ElementoDuplicadoException;
import exception.NotFoundException;
import gestores.GestorMedicoPaciente;
import gestores.GestorUsuarios;
import model.Paciente;
import model.Usuario;
import repository.PacienteRepository;
import repository.UsoJSON;

import javax.lang.model.util.ElementScanner6;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    GestorUsuarios gestorUsuario = new GestorUsuarios();
    HashMap<ETipoEmpleado, Especialidad> rol = null;

    public HashMap<ETipoEmpleado, Especialidad> menuPrincipal() {
        int opcion=0;
        HashMap<ETipoEmpleado, Especialidad> rol = null;
        boolean entradaValida = false;

        do {
            System.out.println("BIENVENIDO \n" +
                    "Que desea hacer? \n" +
                    "1) Ingresar \n" +
                    "2) Salir del programa");

            while (!entradaValida) {
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    entradaValida = true;
                } catch (Exception e) {
                    System.out.println("Por favor, ingresa un numero valido");
                    scanner.nextLine();
                }
            }

            Usuario usuario = new Usuario();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese su matricula:");
                    var matricula = scanner.nextLine();
                    System.out.println("Ingrese su contraseña:");
                    var password = scanner.nextLine();

                    usuario.setMatricula(matricula);
                    usuario.setPassword(password);

                    try {
                        rol = gestorUsuario.ingresar(usuario);
                        if (rol != null && !rol.isEmpty()) {
                            System.out.println("Ingreso exitoso. Bienvenido!");
                        }
                    } catch (NotFoundException e) {
                        System.err.println("Error: " + e.getMessage());
                        rol = null; // Aseguramos que rol sea nulo si el usuario no es encontrado
                    }
                    break;

                case 2:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Ingrese un numero valido...");
            }
        } while (opcion != 2 && (rol == null || rol.isEmpty()));

        return rol;
    }

    public void menuMedico(Especialidad especialidad) throws NotFoundException, ElementoDuplicadoException, AccionIlegalException {
        int opcion = 0;
        boolean opcionValida = false;
        do {
            opcionValida=false;
            System.out.println("Que desea hacer? \n" +
                    "Ingrese 1 para agregar un paciente: \n" +
                    "Ingrese 2 para eliminar un paciente: \n" +
                    "Ingrese 3 para buscar un paciente: \n" +
                    "Ingrese 4 para modificar un paciente: \n" +
                    "Ingrese 5 para pedirle un estudio a un paciente: \n" +
                    "Ingrese 6 para derivar a un paciente: \n" +
                    "Ingrese 7 para solicitarle medicamentos a un paciente: \n" +
                    "Ingrese 8 para mostrar una lista de pacientes \n" +
                    "Ingrese 9 para salir del programa");

            while (!opcionValida) {
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    opcionValida = true;
                } catch (Exception e) {
                    System.out.println("Por favor, ingresa un numero valido");
                    scanner.nextLine();
                }
            }

            String dniPaciente = "";
            Usuario usuario = new Usuario();
            Paciente aux = new Paciente();
            GestorMedicoPaciente gestorMedicoPaciente = new GestorMedicoPaciente();
            UsoJSON.cargarListaPaciente(gestorMedicoPaciente);
            try {
                switch (opcion) {
                    case 1:
                        aux = gestorMedicoPaciente.datosNuevoPaciente();
                        if (gestorMedicoPaciente.agregar(aux)) {
                            System.out.println("Se agrego con exito");
                        }
                        break;

                    case 2:
                        System.out.println("Ingrese el DNI del paciente: \n");
                        dniPaciente = scanner.nextLine();
                        aux = gestorMedicoPaciente.buscarByDNI(dniPaciente);
                        if (gestorMedicoPaciente.eliminar(aux)) {
                            System.out.println("Se elimino con exito");
                        }
                        break;

                    case 3:
                        System.out.println("Ingrese el DNI del paciente: \n");
                        dniPaciente = scanner.nextLine();
                        aux = gestorMedicoPaciente.buscarByDNI(dniPaciente);
                        if (gestorMedicoPaciente.buscar(aux)) {
                            System.out.println("El paciente se encuentra en la lista de pacientes");
                        }
                        break;

                    case 4:
                        System.out.println("Ingrese el DNI del paciente: \n");
                        dniPaciente = scanner.nextLine();
                        aux = gestorMedicoPaciente.buscarByDNI(dniPaciente);
                        gestorMedicoPaciente.modificar(aux);
                        break;

                    case 5:
                        System.out.println("Ingrese el DNI del paciente: \n");
                        dniPaciente = scanner.nextLine();
                        System.out.println("Ingrese el estudio solicitado: \n");
                        String estudio = scanner.nextLine();
                        gestorMedicoPaciente.pedirEstudio(estudio, dniPaciente);
                        break;

                    case 6:
                        System.out.println("Ingrese el DNI del paciente: \n");
                        dniPaciente = scanner.nextLine();
                        System.out.println("Ingrese la especialidad a la que desea derivar: \n");
                        String derivada = scanner.nextLine();
                        Especialidad eDerivada = Especialidad.valueOf(derivada.toUpperCase());
                        gestorMedicoPaciente.derivar(dniPaciente, eDerivada, especialidad);
                        break;

                    case 7:
                        System.out.println("Ingrese el DNI del paciente: \n");
                        dniPaciente = scanner.nextLine();
                        System.out.println("Ingrese el medicamento que desea recetar: \n");
                        String medicamento = scanner.nextLine();
                        if (gestorMedicoPaciente.solicitarMedicamento()) {
                            System.out.println("El medicamento se encuentra recetado");
                        }
                        break;

                    case 8:
                        System.out.println(gestorMedicoPaciente.getListadoPacientes());
                        break;
                    case 9:
                        System.out.println("Saliendo del programa......");
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                        opcionValida = false;
                        break;
                }

            } catch (ElementoDuplicadoException f) {
                System.out.println(f.getMessage());
            } catch (AccionIlegalException e) {
                System.out.println(e.getMessage());
            } catch (NotFoundException n) {
                System.out.println(n.getMessage());
            }
            UsoJSON.guardarListaPacienteEnJSON(gestorMedicoPaciente);
        }while (opcion != 9) ;
    }

}
