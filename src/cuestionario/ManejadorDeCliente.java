/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuestionario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import servidor.Lobby;
import servidor.Servidor;

/**
 *
 * @author sergio
 */
public class ManejadorDeCliente implements Runnable {

    private final Socket clienteSocket;
    private final Servidor servidor;
    DataInputStream entrada;
    DataOutputStream salida;
    private String nombreUsuario;
    Lobby l;

    public ManejadorDeCliente(Socket clienteSocket, Servidor servidor) throws IOException {
        this.l = new Lobby(servidor);
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

            while (true) {
                System.out.println("W");
                if (l.getBandera()) {
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
                    
                    break;
                }
            }
            servidor.eliminarJugador(nombreUsuario);
        } catch (IOException e) {
            System.out.println(
                    "[ * ] El cliente " + clienteSocket.getRemoteSocketAddress() + " ha cerrado la conexion.\n");
            servidor.eliminarJugador(nombreUsuario);
            servidor.getNombreJugadores();
        }
    }
}
