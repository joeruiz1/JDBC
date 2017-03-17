package ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class Ejercicios {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clasejdbc", "root", "root");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        System.out.println("por favor indique la funcion que desea hacer");
        System.out.println("1. Insertar, 2. Actualizar, 3. Listar todo,4. borrar, 5.Finalizar");
        String cadena = br.readLine();
        int numero = 0;
        int num = Integer.parseInt(cadena);

        switch (num) {
            case 1:
                //Insertion 
                // create a sql date object so we can use it in our INSERT statement
                Calendar calendar = Calendar.getInstance();
                java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

                // the mysql insert statement
                String query = " insert into Producto (codigoProducto, precio, proveedor, ciudad)"
                        + " values (?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = null;

                try {

                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setInt(1, 10011);
                    preparedStmt.setInt(2, 500000);
                    preparedStmt.setString(3, "joe");
                    preparedStmt.setString(4, "Suacha");
                    // execute the preparedstatement
                    preparedStmt.execute();

                    System.out.println("You made it, the insertion is ok!");

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Failed to make insertion!");
                    e.printStackTrace();
                }

                break;
            case 2:

                try {
                    //Update
                    // create the java mysql update preparedstatement
                    query = "update Producto set codigoProducto = ? where precio= ?";
                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setInt(1, 10011);
                    preparedStmt.setInt(2, 1000);

                    // execute the java preparedstatement
                    preparedStmt.executeUpdate();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Failed to make update!");
                    e.printStackTrace();
                }
                break;
            case 3:

                break;
            case 4:

                try {
                    query = "delete from producto where codigoProducto  = ?";
                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setInt(1,10011 );
                    preparedStmt.execute();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Failed to make update!");
                    e.printStackTrace();
                }

                break;

        }

    }

}
