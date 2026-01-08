public enum TipoPlan {
    A, B, C, D, E;

    public PlanState createState() {
        switch (this) {
            case A:
                return new PlanA();
            case B:
                return new PlanB();
            case C:
                return new PlanC();
            case D:
                return new PlanD();
            case E:
                return new PlanE();
            default:
                throw new IllegalStateException("Tipo de plan no soportado: " + this);
        }
    }
}
