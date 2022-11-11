package sample.controller;

import sample.model.Task;
import sample.model.User;

import java.sql.*;
import javax.swing.JOptionPane;
public class DatabaseHandler extends config  {

    Connection dbconnection;
    public Connection getdbconnection()throws ClassNotFoundException, SQLException {
        String connectionString="jdbc:mysql://localhost:3306/todo"+
         "?useUnicode=true&characterEncoding=UTF-8";
        Class.forName("com.mysql.jdbc.Driver");

        dbconnection= DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbconnection;

    }

    //Delete from the database

    public void deletTask(int userId, int taskId) throws SQLException, ClassNotFoundException {

        String query= " DELETE FROM "+ Const.Task_table+ " WHERE "+
                Const.User_ID+"=?" +" AND "+ Const.TASK_ID+"=?";
        PreparedStatement preparedStatement= getdbconnection().prepareStatement(query);
        preparedStatement.setInt(1,userId);
        preparedStatement.setInt(2,taskId);
        preparedStatement.execute();
        preparedStatement.close();
        /*
       PreparedStatement-> use this when you plan to use the SQL statements many times.
        The PreparedStatement interface accepts input parameters at runtime.
         you should always explicitly close the Statement object to ensure proper cleanup
         */

    }
    // assigne values to the database
    public void signUpUser(User user){
        String insert = " INSERT INTO "+ Const.User_table +
                "(" + Const.User_FIRST_NAME+"," +
                Const.User_LAST_NAME+"," + Const.User_username + 
                ","+ Const.User_PASSWORD +"," +Const.User_LOCATION+","
                + Const.User_GENDER+")" + "VALUES(?,?,?,?,?,?)";

        try{

            PreparedStatement prepareStatement = getdbconnection().prepareStatement(insert);
            prepareStatement.setString(1,user.getFirstName());
            prepareStatement.setString(2,user.getLastname());
            prepareStatement.setString(3,user.getUserName());
            prepareStatement.setString(4,user.getPassword());
            prepareStatement.setString(5,user.getLocation());
            prepareStatement.setString(6,user.getGender());
         /*
               executeUpdate. Executes the SQL statement in this PreparedStatement object,
               which must be an SQL Data Manipulation Language (DML) statement,
               such as INSERT , UPDATE or DELETE ; or an SQL statement
               that returns nothing, such as a DDL statement
         */
            prepareStatement.executeUpdate();
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);

        }

    }

    public ResultSet getTasksByUser(int userId){

        ResultSet resulTask=null;
        String query= "SELECT * FROM " + Const.Task_table +" WHERE " +
                Const.User_ID+ "=?";
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = getdbconnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            // it returns the ResultSet values in the database table that i'm asking about
            resulTask= preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return resulTask;

    }

    public ResultSet getUser(User user){
        ResultSet resultSet=null;

        if (!user.getUserName().equals("") || !user.getPassword().equals("")){

            String query= "SELECT * FROM " + Const.User_table +" WHERE " +
                    Const.User_username+ "=?" + " AND " + Const.User_PASSWORD+"=?";
            /*
             this query means that select all from users table where as example
             uersname="Ahmed" and password="agudw7dt"
             */
            try {
                PreparedStatement preparedStatement= getdbconnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                // it returns the ResultSet values in the database table that i'm asking about
                resultSet= preparedStatement.executeQuery();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null,e);
            }

        }else {
            System.out.println("please enter your credential ");

        }

        return resultSet;
    }

    public int getAllTasks(int userid) throws SQLException, ClassNotFoundException {
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
// insert tasks to the database
    public void insertTask(Task task) {

        String insert = " INSERT INTO "+ Const.Task_table +"(" 
                + Const.User_ID+ ","+Const.TASK_DATE+","+
                Const.TASK_DISCRIPTION+"," + Const.TASK_task +")" + "VALUES(?,?,?,?)";
        System.out.println(insert);
        try{

            PreparedStatement prepareStatement = getdbconnection().prepareStatement(insert);
            prepareStatement.setInt(1,task.getUserId());
            prepareStatement.setTimestamp(2,task.getDatecreated());
            prepareStatement.setString(3,task.getDescription());
            prepareStatement.setString(4,task.getTask());
            prepareStatement.executeUpdate();
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);

        }


    }


}
