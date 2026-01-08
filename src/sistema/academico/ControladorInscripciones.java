import java.util.ArrayList;
import java.util.List;

public class ControladorInscripciones {

    public List<Materia> materiasDisponiblesParaCursar(Alumno alumno) {
        List<Materia> disponibles = new ArrayList<>();

        if (!alumno.estaInscriptoEnCarrera())
            return disponibles;

        Carrera carrera = alumno.getCarrera();
        PlanEstudio plan = carrera.getPlanEstudio();

        for (Materia m : plan.getMaterias()) {
            if (alumno.buscarCursada(m) != null)
                continue;

            if (plan.puedeCursar(alumno, m))
                disponibles.add(m);
        }

        return disponibles;
    }

    public boolean inscribirEnMateria(Alumno a, Materia m) {
        if (!materiasDisponiblesParaCursar(a).contains(m))
            return false;
        AlumnoMateria cursada = new AlumnoMateria(m, m.getCuatrimestre());
        a.agregarMateria(cursada);
        return true;
    }

    public boolean alumnoTerminoCarrera(Alumno a) {
        Carrera c = a.getCarrera();
        if (c == null)
            return false;

        List<Materia> aprobadas = a.materiasAprobadas();

        boolean obligatorias = c.getMateriasObligatorias().stream().allMatch(aprobadas::contains);
        long optativas = c.getMateriasOptativas().stream().filter(aprobadas::contains).count();

        return obligatorias && optativas >= c.getCantidadOptativasRequeridas();
    }
}