package cuestionario;

import java.util.List;

public class Pregunta {

    private String pregunta;
    private String[] opciones;
    private String respuestaCorrecta;
    private int valor;

    public Pregunta(String pregunta, String[] opciones, String respuestaCorrecta, int valor) {
        this.pregunta = pregunta;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.valor = valor;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public int getValor() {
        return valor;
    }

    public boolean verificaRespuesta(String opcion) {
        return opcion.equalsIgnoreCase(this.respuestaCorrecta);
    }

}
