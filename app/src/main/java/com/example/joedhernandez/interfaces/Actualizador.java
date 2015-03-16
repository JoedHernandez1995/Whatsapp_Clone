package com.example.joedhernandez.interfaces;

import com.example.joedhernandez.typo.InformacionAmigos;
import com.example.joedhernandez.typo.InformacionMensaje;

/**
 * Created by joedhernandez on 3/15/15.
 */
public interface Actualizador {

    public void actualizarData(InformacionMensaje[] mensaje,InformacionAmigos[] amigos,InformacionAmigos[] amigosSinAprobar,String userkey);
}
