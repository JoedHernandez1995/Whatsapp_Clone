package com.example.joedhernandez.interfaces;

import java.io.UnsupportedEncodingException;

/**
 * Created by joedhernandez on 3/15/15.
 */
public interface Manejador {

    public String getUsername();
    public String sendMessage(String sender,String receiver,String message) throws UnsupportedEncodingException;
    public String authenticateUser(String usernameText,String passwordText) throws UnsupportedEncodingException;
    public void messageReceived(String username,String message);
    public boolean isNetworkConnected();
    public boolean isUserAuthenticated();
    public String getLastRawFriendList();
    public void exit();
    public String signUpUser(String usernameText,String passwordText,String email);
    public String addNewFriendRequest(String friendUsername);
    public String sendFriendRequestResponse(String approvedFriendNames,String discardedFriendNames);
}
