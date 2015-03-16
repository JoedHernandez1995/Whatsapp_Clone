package com.example.joedhernandez.toolbox;

import com.example.joedhernandez.typo.InformacionAmigos;

/**
 * Created by joedhernandez on 3/15/15.
 */
public class ControladorDeAmigos {

    public static InformacionAmigos[] friendsInfo = null;
    public static InformacionAmigos[] unapprovedFriends = null;
    public String activeFriends;

    public static void setFriendsInfo(InformacionAmigos[] info){
            ControladorDeAmigos.friendsInfo = info;
    }

    public static InformacionAmigos checkFriends(String username, String userkey){
        InformacionAmigos result = null;
        if(friendsInfo != null){
            for(int i = 0; i < friendsInfo.length; i++){
                if(friendsInfo[i].usuario.equals(username) && friendsInfo[i].user_key.equals(userkey)){
                    result = friendsInfo[i];
                    break;
                }
            }
        }
        return result;
    }

    public static InformacionAmigos getFriendsInfo(String username){
        InformacionAmigos result = null;
        if(friendsInfo != null){
            for(int i = 0; i < friendsInfo.length; i++){
                if(friendsInfo[i].usuario.equals(username)){
                    result = friendsInfo[i];
                    break;
                }
            }
        }
        return result;
    }
}
