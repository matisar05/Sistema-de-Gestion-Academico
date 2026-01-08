public class PlanB implements PlanState {

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {
        return materia.getCorrelativas().stream()
                .allMatch(alumno::aproboFinalDe);
    }

    @Override
    public String getNombre() {
        return "Plan B";
    }
}
