public class PlanA implements PlanState {

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {
        return materia.getCorrelativas().stream()
                .allMatch(alumno::aproboCursadaDe);
    }

    @Override
    public String getNombre() {
        return "Plan A";
    }
}
