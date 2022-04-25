package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsavel por realizar o socket
 *
 * @author Fabio e Lucas Nogueira
 */
public class SocketSpeaker {
    
    private static String ADDRESS;
    private static int PORT;
    private static Socket socket;
    private String msg;
    
    private static SocketSpeaker ss;

    private SocketSpeaker() throws IOException {}
    
    public static SocketSpeaker getInstance(){
        if(ss == null){
            try {
                ss = new SocketSpeaker();
            } catch (IOException ex) {
                Logger.getLogger(SocketSpeaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return ss;
    }
    
    public void init(String address, int port){
        ADDRESS = address;
        PORT    = port;
    }
    
    public void setMensagem(String msg){
        this.msg = msg;
    }
    
    public String call() throws IOException{
        socket = new Socket(ADDRESS, PORT);
        
        enviarDados();
        String res = receberDados();
        socket.close();
        
        return res;
    }

    private void enviarDados() throws IOException {
        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println(msg);
        pr.flush();
    }

    private String receberDados() throws IOException {
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        return str;
    }
}