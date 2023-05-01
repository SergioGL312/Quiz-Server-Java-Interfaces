package servidor;

/**
 *
 * @author sergio
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cuestionario.Jugador;
import cuestionario.Pregunta;
import cuestionario.Quiz;
import java.beans.PropertyChangeSupport;

public class Servidor {

    private static final int PUERTO = 1090;

    private List<Jugador> listaJugadores;
    private Quiz quiz = new Quiz();
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Servidor() {
        this.listaJugadores = new ArrayList<Jugador>();
        this.pcs.firePropertyChange("jugadores", null, listaJugadores);
    }

    public void iniciarServidor() {

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("[ + ] Servidor iniciado. Esperando conexiones . . .\n");

            while (true) {
                Socket clienteSocket = servidor.accept();
                System.out.println("[ * ] Nuevo cliente conectado: " + clienteSocket.getRemoteSocketAddress());

                ManejadorDeCliente manejadorDeCliente = new ManejadorDeCliente(clienteSocket, this);
                new Thread(manejadorDeCliente).start();
            }

        } catch (IOException e) {
            System.err.println("[ - ] Error: " + e.getMessage());
        }
    }

    public boolean agregarJugador(Jugador jugador) {
        boolean encontrado = false;
        for (Jugador j : listaJugadores) {
            if (j.getNombreJugador().equals(jugador.getNombreJugador())) {
                j.agregarPuntos(jugador.getPuntos());
                j.agregarTiempo(jugador.getTiempo());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("[ + ] Jugador agregado a la lista de jugadores: " + jugador.getNombreJugador());
            listaJugadores.add(jugador);
            pcs.firePropertyChange("jugadores", null, listaJugadores);
        }
        return encontrado;
    }

    public void getNombreJugadores() {
        System.out.println("Jugadores conectados: ");
        for (Jugador jugador : listaJugadores) {
            System.out.println(jugador.getNombreJugador());
        }
    }

    public List<Jugador> getJugadores() {
        return listaJugadores;
    }

    public void eliminarJugador(String nombreJugador) {
        for (Iterator<Jugador> iter = listaJugadores.iterator(); iter.hasNext();) {
            Jugador jugador = iter.next();
            if (jugador.getNombreJugador().equals(nombreJugador)) {
                iter.remove();
                System.out.println("[ - ] Jugador eliminado de la lista de jugadores: " + nombreJugador);
                pcs.firePropertyChange("jugadores", null, listaJugadores);
                break;
            }
        }
    }

    public void agregarPregunta(String pregunta, String[] opciones, String resCorrecta, int valor) {
        Pregunta p = new Pregunta(pregunta, opciones, resCorrecta, valor);
        quiz.agregarPregunta(p);
    }

    public Quiz getQuiz() {
        return this.quiz;
    }

    public void addPropertyChangeListener(Lobby aThis) {
        pcs.addPropertyChangeListener(aThis);
    }

}

/*class ManejadorDeCliente implements Runnable {

    private final Socket clienteSocket;
    private final Servidor servidor;
    DataInputStream entrada;
    DataOutputStream salida;
    private String nombreUsuario;

    public ManejadorDeCliente(Socket clienteSocket, Servidor servidor) throws IOException {
        this.clienteSocket = clienteSocket;
        this.servidor = servidor;
        this.entrada = new DataInputStream(clienteSocket.getInputStream());
        this.salida = new DataOutputStream(clienteSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            Jugador jugador = null;

            while (jugador == null) {
                nombreUsuario = entrada.readUTF();
                System.out.println("[ + ] Nombre de usuario recibido desde " + clienteSocket.getInetAddress() + ": " + nombreUsuario);

                jugador = new Jugador(nombreUsuario, 0, 0);

                if (servidor.agregarJugador(jugador)) {
                    System.out.println("[ - ] Nombre de usuario ya existe " + nombreUsuario);
                    salida.writeUTF("no");
                    servidor.getNombreJugadores();
                    jugador = null;
                } else {
                    servidor.getNombreJugadores();
                    salida.writeUTF("ok");
                }
            }
            /*
            List<Pregunta> preguntas = servidor.getQuiz().getP();
            Iterator<Pregunta> iterPreguntas = preguntas.iterator();
            salida.writeInt(preguntas.size());
            while (iterPreguntas.hasNext()) {
                Pregunta pregunta = iterPreguntas.next();

                salida.writeUTF(pregunta.getPregunta());

                for (String opcion : pregunta.getOpciones()) {
                    salida.writeUTF(opcion);
                }

                String respuesta = entrada.readUTF();

                if (respuesta.equals(pregunta.getRespuestaCorrecta())) {
                    jugador.agregarPuntos(pregunta.getValor());
                    salida.writeUTF("Correcto");
                } else {
                    salida.writeUTF("Incorrecto");
                }
            }
            salida.writeUTF("Tu puntaje final es: " + jugador.getPuntos());
            servidor.eliminarJugador(nombreUsuario);
        } catch (IOException e) {
            System.out.println(
                    "[ * ] El cliente " + clienteSocket.getRemoteSocketAddress() + " ha cerrado la conexion.\n");
            servidor.eliminarJugador(nombreUsuario);
            servidor.getNombreJugadores();
        }
    }
}*/
