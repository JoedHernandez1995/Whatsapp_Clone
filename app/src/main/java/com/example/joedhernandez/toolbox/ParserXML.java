package com.example.joedhernandez.toolbox;

import com.example.joedhernandez.interfaces.Actualizador;
import com.example.joedhernandez.typo.InformacionAmigos;
import com.example.joedhernandez.typo.InformacionMensaje;
import com.example.joedhernandez.typo.InformacionEstado;
import java.util.Vector;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

/**
 * Created by joedhernandez on 3/15/15.
 */
public class ParserXML extends DefaultHandler{

    public String userkey = new String();
    public Actualizador updater;

    public ParserXML(Actualizador updater){
        this.updater = updater;
    }

    private Vector<InformacionAmigos> mFriends = new Vector<InformacionAmigos>();
    private Vector<InformacionAmigos> mOnlineFriends = new Vector<InformacionAmigos>();
    private Vector<InformacionAmigos> mUnapprovedFriends = new Vector<InformacionAmigos>();
    private Vector<Actualizador> mUnreadMessages = new Vector<Actualizador>();

    public void endDocument() throws SAXException{
        InformacionAmigos[] friends = new InformacionAmigos[mFriends.size()+mOnlineFriends.size()];
        Actualizador[] messages = new Actualizador[mUnreadMessages.size()];
        int onlineFriendCount = mOnlineFriends.size();
        for(int i = 0; i < onlineFriendCount; i++){
            friends[i] = mOnlineFriends.get(i);
        }
        int offlineFriendCount = mFriends.size();
        for(int i = 0; i < offlineFriendCount; i++){
            friends[i+onlineFriendCount] = mFriends.get(i);
        }
        int unApprovedFriendCount = mUnapprovedFriends.size();
        InformacionAmigos[] unApprovedFriends = new InformacionAmigos[unApprovedFriendCount];
        for(int i = 0; i < unApprovedFriends.length; i++){
            unApprovedFriends[i] = mUnapprovedFriends.get(i);
        }

        int unreadMessagecount = mUnreadMessages.size();
        for(int i = 0; i < unreadMessagecount; i++){
            messages[i] = mUnreadMessages.get(i);
        }
        this.updater.actualizarData((InformacionMensaje[]) messages, friends, unApprovedFriends, userkey);
        super.endDocument();
    }

    public void startElement(String url,String localName,String name, Attributes attributes) throws SAXException{
        if(localName == "friend"){
            InformacionAmigos friend = new InformacionAmigos();

            friend.usuario = attributes.getValue(InformacionAmigos.usuario);
            String status = attributes.getValue(InformacionAmigos.puerto);

            if(status != null && status.equals("online")){
                friend.estado = InformacionEstado.ONLINE;
                mOnlineFriends.add(friend);
            }else if(status.equals("unApproved")){
                friend.estado = InformacionEstado.UNAPPROVED;
                mUnapprovedFriends.add(friend);
            }else{
                friend.estado = InformacionEstado.OFFLINE;
                mFriends.add(friend);
            }
        }else if(localName == "user"){
            this.userkey = attributes.getValue(InformacionAmigos.user_key);
        }
        super.startElement(url,localName,name,attributes);
    }

    public void startDocument() throws SAXException{
        this.mFriends.clear();
        this.mOnlineFriends.clear();
        this.mUnreadMessages.clear();
        super.startDocument();
    }
}
