package repository;

import enums.ETipoEmpleado;
import enums.Especialidad;
import model.Empleado;
import model.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class UsuarioRepository {
    public ArrayList<Usuario> leer() {
        ArrayList<Usuario> arrayUsuario = new ArrayList<>();
        try {
            FileReader file = new FileReader("usuarios.json");
            JSONArray array = new JSONArray(new JSONTokener(file));

            for (int i = 0; i < array.length(); i++) {
                JSONObject auxJson = array.getJSONObject(i);
                Usuario auxUsu = new Usuario();
                Empleado empleado = new Empleado();

                auxUsu.setMatricula(auxJson.getString("matricula"));
                auxUsu.setPassword(auxJson.getString("password"));

                JSONObject rolJson = auxJson.getJSONObject("rol");
                String tipoDeEmpleadoStr = rolJson.getString("tipoDeEmpleado");

                    ETipoEmpleado tipoDeEmpleado = ETipoEmpleado.valueOf(tipoDeEmpleadoStr.toUpperCase());
                    empleado.setTipoDeEmpleado(tipoDeEmpleado);
                    empleado.setSalario(rolJson.getDouble("salario"));
                    auxUsu.setRol(empleado);

                String especialidadStr = auxJson.getJSONObject("especialidad").getString("nombre");

                    Especialidad especialidad = Especialidad.valueOf(especialidadStr.toUpperCase());
                    auxUsu.setEspecialidad(especialidad);

                arrayUsuario.add(auxUsu);
            }
        } catch (JSONException e) {
            System.out.println("Error al pasaje de datos");
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo");
            throw new RuntimeException(e);
        }
        return arrayUsuario;
    }

}