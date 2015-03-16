package com.example.joedhernandez.connection;

import android.util.Log;

import com.example.joedhernandez.interfaces.SocketInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by joedhernandez on 3/15/15.
 */
public class Socketer implements SocketInterface {


    private static final String AUTHENTICATION_SERVER_ADDRESS = "http://127.0.0.1/androidchatter";
    private int listeningPort = 0;
    private static final String HTTP_REQUEST_FAILED = null;
    private HashMap<InetAddress,Socket> sockets = new HashMap<InetAddress,Socket>();
    private ServerSocket serverSocket = null;
    private boolean listening;

    private class RecieveConnection extends Thread{
        Socket clientSocket = null;

        public RecieveConnection(Socket socket){
            this.clientSocket = socket;
            Socketer.this.sockets.put(socket.getInetAddress(),socket);
        }

        public void run(){
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                while((inputLine = in.readLine())!=null){
                    if(inputLine.equals("exit") == false){

                    }else{
                        clientSocket.shutdownInput();
                        clientSocket.shutdownOutput();
                        clientSocket.close();
                        Socketer.this.sockets.remove(clientSocket.getInetAddress());
                    }
                }
            }catch(IOException e){
                Log.e("ReceiveConnection.run: when receiving connection","");
            }
        }
    }

    public String enviarRequestHTTP(String Params){
        URL url;
        String result = new String();
        try{
            url = new URL(AUTHENTICATION_SERVER_ADDRESS);
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);

            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.println(Params);
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine())!=null){
                result = result.concat(inputLine);
            }
            in.close();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        if(result.length() == 0){
            result = HTTP_REQUEST_FAILED;
        }
        return result;
    }

    public int startListeningPort(int port){

        listening = true;
        try{
            serverSocket = new ServerSocket(port);
            this.listeningPort = port;
        }catch(IOException e){
            return 0;
        }
        while (listening){
            try{
                new RecieveConnection((Socket)serverSocket.accept()).start();
            }catch (IOException e){
                return 2;
            }
        }
        try{
            serverSocket.close();
        }catch (IOException e){
            Log.e("Exception server socket","Exception when closing server socket");
            return 3;
        }

        return 1;
    }

    public void exit(){
        for(Iterator<Socket> iterator = sockets.values().iterator(); iterator.hasNext()){
            Socket socket = iterator.next();
            try{
                socket.shutdownInput();
                socket.shutdownInput();
                socket.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
