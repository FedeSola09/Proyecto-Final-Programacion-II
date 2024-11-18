package gestores;

import enums.EGenero;
import enums.ETipoSangre;
import enums.Especialidad;
import exception.AccionIlegalException;
import exception.ElementoDuplicadoException;
import exception.NoSePudoModificarException;
import exception.NotFoundException;
import model.Paciente;
import model.Usuario;
import repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorMedicoPaciente implements GestorBasic<Paciente> {

    private ArrayList<Paciente> listadoPacientes= new ArrayList<>();

    public ArrayList<Paciente> getListadoPacientes() {
        return listadoPacientes;
    }

    public Paciente datosNuevoPaciente() {
        Paciente aux = new Paciente();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un nombre: \n ");
        aux.setNombre(scanner.nextLine());

        System.out.println("Ingrese un apellido: \n ");
        aux.setApellido(scanner.nextLine());

        System.out.println("Ingrese un DNI: \n ");
        aux.setDni(scanner.nextLine());

        System.out.println("Ingrese un mail: \n ");
        aux.setEmail(scanner.nextLine());

        System.out.println("Ingrese un genero: \n ");
        aux.setGenero(EGenero.valueOf(scanner.nextLine()));

        System.out.println("Ingrese un edad: \n ");
        aux.setEdad(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Ingrese un tipo de sangre: \n ");
        aux.setTipoSangre(ETipoSangre.valueOf(scanner.nextLine()));

        return aux;
    }

    @Override
    public boolean agregar(Paciente p1) throws ElementoDuplicadoException {
           try {
               if (listadoPacientes.contains(p1)) {
                   throw new ElementoDuplicadoException("No se pudo agregar al paciente porque ya existe en la lista de este medico");
               } else {
                   listadoPacientes.add(p1);
               }
           } catch (ElementoDuplicadoException t){
               System.out.println(t.getMessage());
           }
           return true;
    }

    @Override
    public boolean eliminar(Paciente paciente) throws NotFoundException {
       try {
           if (!listadoPacientes.contains(paciente)) {
               throw new NotFoundException("No se encontro el paciente a eliminar");
           } else {
           }
       }catch (NotFoundException r){
           System.out.println(r.getMessage());
       }
        return listadoPacientes.remove(paciente);
    }

    @Override
    public boolean buscar(Paciente p) throws NotFoundException {
         try {
             if (!listadoPacientes.contains(p)) {
                 throw new NotFoundException("No se encontro el paciente");
             } else {
             }
         } catch (NotFoundException y){
             System.out.println(y.getMessage());
         }
         return true;
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        int cortarWhile = 0;
        int opcion = 0;
        Integer auxNum = 0;
        String auxChar = "";
        Scanner scanner = new Scanner(System.in);
        try{
            while (cortarWhile == 0) {
                System.out.println("Ingrese 1 para modificar la edad. \n");
                System.out.println("Ingrese 2 para modificar el nombre. \n");
                System.out.println("Ingrese 3 para modificar el apellido. \n");
                System.out.println("Ingrese 4 para modificar el email. \n");
                System.out.println("Ingrese 5 para agregar un tratamiento\n");
                System.out.println("Ingrese 6 para modificar el alta\n");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        if (paciente.getEdad() != null) {
                            System.out.println("Ingrese la nueva edad: \n");
                            auxNum = scanner.nextInt();
                            scanner.nextLine();
                            paciente.setEdad(auxNum);
                        } else {
                            throw new NoSePudoModificarException("No se encontro el paciente a modificar");
                        }
                        break;

                    case 2:
                        if (paciente.getNombre() != null) {
                            System.out.println("Ingrese el nuevo nombre: \n");
                            auxChar = scanner.nextLine();
                            paciente.setNombre(auxChar);
                        } else {
                            throw new NoSePudoModificarException("No se encontro el paciente a modificar");
                        }
                        break;

                    case 3:
                        if (paciente.getApellido() != null) {
                            System.out.println("Ingrese el nuevo apellido: \n");
                            auxChar = scanner.nextLine();
                            paciente.setApellido(auxChar);
                        } else {
                            throw new NoSePudoModificarException("No se encontro el paciente a modificar");
                        }
                        break;

                    case 4:
                        if (paciente.getEmail() != null) {
                            System.out.println("Ingrese el nuevo email: \n");
                            auxChar = scanner.nextLine();
                            paciente.setEmail(auxChar);
                        } else {
                            throw new NoSePudoModificarException("No se encontro el paciente a modificar");
                        }
                        break;

                    case 5:
                        if(paciente.getHistoriaClinica() != null) {
                            System.out.println("Ingrese el tratamiento: \n");
                            auxChar = scanner.nextLine();
                            paciente.agregarAHistoriaClinica(auxChar);
                            System.out.println(paciente.getHistoriaClinica());
                        }else {
                            throw new NoSePudoModificarException("No se encontro el paciente a modificar");
                        }
                        break;
                        case 6:
                            if(paciente.isAltaBaja()){
                                paciente.setAltaBaja(false);
                                System.out.println("Se ha dado de baja el paciente\n");
                            }
                            else {
                                paciente.setAltaBaja(true);
                                System.out.println("Se ha dado de alta el paciente\n");
                            }
                            break;
                    default:
                        System.out.println("Valor incorrecto, por favor ingrese una de las opciones mostradas en pantalla.");
                        break;
                }
                System.out.println("Para seguir modificando ingrese 0 \n Para terminar ingrese 1 \n");
                cortarWhile = scanner.nextInt();
                scanner.nextLine();
            }

        }catch (NoSePudoModificarException t){
            System.out.println(t.getMessage());
        }
        return paciente;
    }

    public void pedirEstudio(String estudio, String dni) throws NotFoundException {
        Paciente p = buscarByDNI(dni);
        System.out.println("El estudio : " + estudio + " fue pedido para "+ p.getNombre());
    }

    public boolean derivar(String dni, Especialidad especialidadADerivar, Especialidad especialidadMedico) throws AccionIlegalException, NotFoundException {
       try {
           if (especialidadMedico.equals(Especialidad.CLINICO)) {
               Paciente p = buscarByDNI(dni);
               System.out.println("El paciente: " + p.getNombre() + "fue derivado a " + especialidadADerivar);
           } else {
               throw new AccionIlegalException("Solo puede derivar un medico clinico");
           }
       }catch (AccionIlegalException e){
           System.out.println(e.getMessage());
       }
       return true;
    }

    public boolean solicitarMedicamento(){
        System.out.println("Se aviso a farmacia, por favor aguarde");
        return true;
    }

    public Paciente buscarByDNI(String dni) throws NotFoundException {
        for (Paciente p : listadoPacientes){
            if(p.getDni().equals(dni)){
                return p;
            }
        }
        throw new NotFoundException("No se encontro el paciente");
    }

}
