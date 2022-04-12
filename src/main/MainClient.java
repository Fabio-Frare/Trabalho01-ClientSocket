
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import utils.SocketSpeaker;
import view.Menu;

/**
 *
 * @author fabio
 */
public class MainClient {
    
//    private static final String ADDRESS = "192.168.0.105";
//    private static final int PORT       = 80;
//    private static Socket socket;
    
    public static void main(String[] args) throws IOException {
        
        Menu menu = new Menu();
        
        while (true) {
            String msg = menu.menuIniciar();
            //enviarDados(msg);
            //receberDados();
            //socket.close();
            // teste 
            
            SocketSpeaker sp = new SocketSpeaker(msg);
            String str = sp.call();
            System.out.println("Servidor: " + str);
        }
    }

//    public static void enviarDados(String msg) throws IOException {
//        socket = new Socket(ADDRESS, PORT);
//        PrintWriter pr = new PrintWriter(socket.getOutputStream());
//        pr.println(msg);
//        pr.flush();
//    }

//    public static void receberDados() throws IOException {
//        InputStreamReader in = new InputStreamReader(socket.getInputStream());
//        BufferedReader bf = new BufferedReader(in);
//        String str = bf.readLine();
//        System.out.println("Servidor: " + str);
//    }
       
}   
    

   
