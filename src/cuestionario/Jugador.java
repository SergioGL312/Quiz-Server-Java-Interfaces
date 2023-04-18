package cuestionario;

public class Jugador {

    private String nombreUsuario;
    private int puntos;
    private long tiempo;

    public Jugador(String nombreUsuario, int puntos, long tiempo) {
        this.nombreUsuario = nombreUsuario;
        this.puntos = puntos;
        this.tiempo = tiempo;
    }

    public String getNombreJugador() {
        return nombreUsuario;
    }

    public int getPuntos() {
        return puntos;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void agregarTiempo(long tiempo) {
        this.tiempo += tiempo;
    }

}
