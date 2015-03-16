package com.example.joedhernandez.toolbox;

import com.example.joedhernandez.typo.InformacionMensaje;

/**
 * Created by joedhernandez on 3/15/15.
 */
public class ControladorDeMensajes {

    public static final String taken = "taken";
    public static InformacionMensaje[] info_mensaje = null;

    public static void setMessageInfo(InformacionMensaje[] info_mensajes){
        info_mensaje = info_mensajes;
    }

    public static InformacionMensaje checkMessage(String username){
        InformacionMensaje resultado = null;
        for(int i = 0; i < info_mensaje.length; i++){
            resultado = info_mensaje[i];
        }
        return resultado;
    }

    public static InformacionMensaje[] getMessages(){
        return info_mensaje;
    }
}
