package sample.controller;

import javafx.collections.ObservableList;
import sample.model.Task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;

public class clientThread extends Thread implements getTasks {
    private Socket socket;
    private DataInputStream reader;
    private DataOutputStream writer;
    private int clientNumber;
    private ObservableList<Task> tasks;
    int num=0;

    public clientThread(Socket socket, DataInputStream reader, DataOutputStream writer, int clientNumber) {
        this.socket = socket;
        this.reader = reader;
        this.writer = writer;
        this.clientNumber = clientNumber;
    }

    Connection dbconnection;

    public Connection getdbconnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/todoapp";

        Class.forName("com.mysql.jdbc.Driver");

        dbconnection = DriverManager.getConnection(connectionString, "root", "aaa101098");
        return dbconnection;

    }

    @Override
    public void run() {
        while(true){
            try {
                writer.writeInt(clientNumber);
                int choice = reader.readInt();
                if(choice == 1){
                    int userid = reader.readInt();
                    System.out.println("\nReceived From Client #"+clientNumber+" userid= "+userid);

                     num =  getTasksByUser(userid);
                    System.out.println("Sent number of tasks to Client #"+clientNumber+"= "+num+"\n");
                    writer.write(num);
                }
                else {
                    socket.close();
                    System.out.println("Client #"+clientNumber+" Disconnected");
                }
            } catch (IOException | SQLException | ClassNotFoundException ex) {
                ex.getMessage();
            }
        }
    }

    @Override
    public int getTasksByUser(int userid) throws SQLException, ClassNotFoundException {
        String query= "SELECT COUNT(*) FROM " + Const.Task_table +" WHERE " +
                Const.User_ID+ "=?";

        PreparedStatement preparedStatement= getdbconnection().prepareStatement(query);
        preparedStatement.setInt(1,userid);
        ResultSet resultSet= preparedStatement.executeQuery();
        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return resultSet.getInt(1);
    }

}