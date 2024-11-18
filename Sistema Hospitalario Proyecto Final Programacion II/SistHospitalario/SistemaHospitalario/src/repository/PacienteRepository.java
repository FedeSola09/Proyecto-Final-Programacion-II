package repository;

import enums.EGenero;
import enums.ETipoSangre;
import model.Paciente;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PacienteRepository extends UsoJSON {
    //EXTRACCION Y GUARDADO DE JSON
    public ArrayList<Paciente> leer(){
        ArrayList<Paciente> auxPaciente = new ArrayList<>();
        try{
            FileReader file = new FileReader("pacientes.json");
            JSONArray array = new JSONArray(new JSONTokener(file));
            for (int i = 0; i < array.length(); i++) {
                JSONObject auxJson = array.getJSONObject(i);
                Paciente aux = new Paciente();
                aux.setNombre(auxJson.getString("Nombre"));
                aux.setApellido(auxJson.getString("Apellido"));
                aux.setDni(auxJson.getString("Dni"));
                aux.setEmail(auxJson.getString("Email"));
                aux.setGenero((EGenero) auxJson.get("Genero"));
                aux.setEdad(auxJson.getInt("Edad"));
                aux.setTipoSangre((ETipoSangre) auxJson.get("TipoDeSangre"));
                aux.setEnfermedades((ArrayList<String>) auxJson.get("Enfermedades"));
                aux.setHistoriaClinica((HashMap<Integer, String>) auxJson.get("HistoriaClinica"));

            }
        }catch(JSONException e){
            System.out.println("Error al pasaje de datos");
        }catch (FileNotFoundException e){
            System.out.println("No se pudo leer el archivo");
            throw new RuntimeException(e);
        }
        return auxPaciente;
    }
}
