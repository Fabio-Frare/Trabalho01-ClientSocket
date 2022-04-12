package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Responsavel por realizar o socket
 *
 * @author Lucas Nogueira
 */
public class SocketSpeaker {
    
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT       = 9876;
    private static Socket socket;
    private String msg;

    public SocketSpeaker(String msg) throws IOException {
        socket = new Socket(ADDRESS, PORT);
        this.msg = msg;
    }
    
    public String call() throws IOException{
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