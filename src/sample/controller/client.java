package sample.controller;
//import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.Scanner;

public class client  {

    public static void main(String args[]) throws IOException{
    Scanner s = new Scanner(System.in);
    System.out.println("Note: Enter 127.0.0.1 to connect to same machine 'localhost'");
    System.out.print("Please Enter IP: ");
    String IP = s.next();
    Socket socket = new Socket(IP,52349);
    int num=0;
    DataInputStream reader = new DataInputStream(socket.getInputStream());
    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
    System.out.println("\n*****Welcome CLIENT ID="+reader.readInt()+"*****\n");
    while(true){
        System.out.println("[1] get number of Task for a spscific user");
        System.out.println("[2] Exit");
        System.out.print("Your Choice: ");
        int choice = s.nextInt();
        writer.writeInt(choice);
        if(choice == 1){
            System.out.print("\nPlease Enter UserId: ");
            int userId = s.nextInt();
            writer.writeInt(userId);
            num = reader.readInt();
            System.out.println("Received number of tasks = "+num+"\n");
        }
        else {
            System.out.println("\nGood Bye :)");
            System.exit(0);
        }
    }

}
}
