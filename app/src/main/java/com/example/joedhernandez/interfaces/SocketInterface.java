package com.example.joedhernandez.interfaces;

/**
 * Created by joedhernandez on 3/15/15.
 */
public interface SocketInterface {

    public String enviarRequestHTTP(String Params);//Va a enviar un request al otro usuario y va a verificar si una conexion es posible o no
    public int startListeningPort(int port);
    //public void stopListening();
    //public void exit();


}
