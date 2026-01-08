import java.util.*;

public class MainJavaTest {
    public static void main(String[] args) {

        Carrera carreraA = crearCarrera("Ingeniería A", TipoPlan.A, 2);
        Carrera carreraB = crearCarrera("Ingeniería B", TipoPlan.B, 1);
        Carrera carreraC = crearCarrera("Licenciatura C", TipoPlan.C, 2);
        Carrera carreraD = crearCarrera("Analista D", TipoPlan.D, 2);
        Carrera carreraE = crearCarrera("Técnico E", TipoPlan.E, 1);

        DatosCompartidos.getCarreras().addAll(List.of(carreraA, carreraB, carreraC, carreraD, carreraE));

        Alumno a1 = AlumnoRegistry.crearAlumno("Juan Perez", "1001");
        Alumno a2 = AlumnoRegistry.crearAlumno("Lucía González", "1002");
        Alumno a3 = AlumnoRegistry.crearAlumno("Carlos Díaz", "1003");
        Alumno a4 = AlumnoRegistry.crearAlumno("María López", "1004");
        Alumno a5 = AlumnoRegistry.crearAlumno("Bruno Romero", "1005");
        Alumno a6 = AlumnoRegistry.crearAlumno("Sofía Acosta", "1006");
        Alumno a7 = AlumnoRegistry.crearAlumno("Elena Valle", "1007");
        Alumno a8 = AlumnoRegistry.crearAlumno("Andrés Leiva", "1008");
        Alumno a9 = AlumnoRegistry.crearAlumno("Martina Sosa", "1009");
        Alumno a10 = AlumnoRegistry.crearAlumno("Federico Luna", "1010");

        DatosCompartidos.getAlumnos().addAll(List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10));

        a1.inscribirCarrera(carreraA);
        a2.inscribirCarrera(carreraB);
        a3.inscribirCarrera(carreraC);
        a4.inscribirCarrera(carreraD);
        a5.inscribirCarrera(carreraE);
        a6.inscribirCarrera(carreraA);
        a7.inscribirCarrera(carreraB);
        a8.inscribirCarrera(carreraC);
        a9.inscribirCarrera(carreraD);
        a10.inscribirCarrera(carreraE);

        a1.registrarCursada(getMateria(carreraA, "Matemática I"), true, false);
        a1.registrarCursada(getMateria(carreraA, "Programación I"), true, false);

        a2.registrarCursada(getMateria(carreraB, "Algoritmos"), true, true);
        a2.registrarCursada(getMateria(carreraB, "Sistemas"), true, true);

        a3.registrarCursada(getMateria(carreraC, "Matemática I"), true, true);
        a3.registrarCursada(getMateria(carreraC, "Programación I"), true, true);
        a3.registrarCursada(getMateria(carreraC, "Algoritmos"), true, true);
        a3.registrarCursada(getMateria(carreraC, "Sistemas"), true, true);
        a3.registrarCursada(getMateria(carreraC, "Electiva A"), true, false);

        a4.registrarCursada(getMateria(carreraD, "Matemática I"), true, true);
        a4.registrarCursada(getMateria(carreraD, "Programación I"), true, true);
        a4.registrarCursada(getMateria(carreraD, "Algoritmos"), true, true);
        a4.registrarCursada(getMateria(carreraD, "Sistemas"), true, false);

        a5.registrarCursada(getMateria(carreraE, "Matemática I"), true, true);
        a5.registrarCursada(getMateria(carreraE, "Programación I"), true, true);
        a5.registrarCursada(getMateria(carreraE, "Algoritmos"), true, true);
        a5.registrarCursada(getMateria(carreraE, "Sistemas"), true, true);

        System.out.println("✅ Test setup completo. Abrí la app GUI para probar visualmente.");
    }

    private static Materia getMateria(Carrera carrera, String nombre) {
        return carrera.getPlanEstudio().getMaterias().stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("❌ Materia no encontrada: " + nombre));
    }

    private static Carrera crearCarrera(String nombre, TipoPlan tipo, int optativasRequeridas) {
        PlanEstudio plan = new PlanEstudio(tipo);
        Carrera carrera = new Carrera(nombre, plan, optativasRequeridas);

        Materia m1 = new Materia("Matemática I", 1, true, true);
        Materia m2 = new Materia("Programación I", 1, true, true);
        Materia m3 = new Materia("Algoritmos", 2, true, false);
        Materia m4 = new Materia("Sistemas", 3, false, true);
        Materia m5 = new Materia("Electiva A", 4, false, false);

        m3.agregarCorrelativa(m2);
        m4.agregarCorrelativa(m3);
        m5.agregarCorrelativa(m1);

        plan.agregarMateria(m1);
        plan.agregarMateria(m2);
        plan.agregarMateria(m3);
        plan.agregarMateria(m4);
        plan.agregarMateria(m5);

        return carrera;
    }
}
