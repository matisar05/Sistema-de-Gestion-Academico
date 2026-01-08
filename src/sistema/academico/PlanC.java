public class PlanC implements PlanState {

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {
        boolean cumpleCorrelativas = materia.getCorrelativas().stream()
                .allMatch(alumno::aproboCursadaDe);

        if (!cumpleCorrelativas) {
            return false;
        }

        return aproboFinalesPrevios(alumno, materia, 5);
    }

    private boolean aproboFinalesPrevios(Alumno alumno, Materia materia, int cuatrimestres) {
        int actual = materia.getCuatrimestre();
        return alumno.getMaterias().stream()
                .filter(am -> am.getCuatrimestreCursado() >= actual - cuatrimestres
                        && am.getCuatrimestreCursado() < actual)
                .allMatch(AlumnoMateria::estaAprobada);
    }

    @Override
    public String getNombre() {
        return "Plan C";
    }
}
