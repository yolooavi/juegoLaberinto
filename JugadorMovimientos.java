public class JugadorMovimientos implements Comparable<JugadorMovimientos> {
    private int movimientos;

    public JugadorMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void incrementarMovimientos() {
        this.movimientos++;
    }

    @Override
    public int compareTo(JugadorMovimientos otroJugador) {
        return Integer.compare(this.movimientos, otroJugador.movimientos);
    }
}
