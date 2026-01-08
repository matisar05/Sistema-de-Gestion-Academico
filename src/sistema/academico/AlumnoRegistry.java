import java.util.HashMap;
import java.util.Map;

public class AlumnoRegistry {
    private static final Map<String, Alumno> alumnosPorLegajo = new HashMap<>();

    private AlumnoRegistry() {}

    public static Alumno crearAlumno(String nombre, String legajo) {
        if (alumnosPorLegajo.containsKey(legajo)) {
            throw new IllegalArgumentException("Ya existe un alumno con el legajo: " + legajo);
        }
        Alumno alumno = new Alumno(nombre, legajo);
        alumnosPorLegajo.put(legajo, alumno);
        return alumno;
    }

    public static Alumno getAlumnoPorLegajo(String legajo) {
        return alumnosPorLegajo.get(legajo);
    }

    public static void limpiarRegistro() {
        alumnosPorLegajo.clear();
    }

    public static boolean existeLegajo(String legajo) {
        return alumnosPorLegajo.containsKey(legajo);
    }
}
