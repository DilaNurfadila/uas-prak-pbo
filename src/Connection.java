import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
    String url;
    String username;
    String password;
    String todo;
    String kategori;
    String oldTodo;
    String tanggal_selesai;
    String status;

    public Connection(String newUrl, String newUsername, String newPassword){
        this.url = newUrl;
        this.username = newUsername;
        this.password = newPassword;
    }

    public void displayData(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // register the driver class
            java.sql.Connection connection = DriverManager.getConnection(url, username, password); // create the connection object
            Statement statement = connection.createStatement(); // create the statement object
            String query = "SELECT * FROM todolist"; // show all column with data
            ResultSet resultSet = statement.executeQuery(query); // execute the query

            System.out.println("\n---------------------");
            System.out.println("Todo List");
            System.out.println("=============================");
            while (resultSet.next()) { // show data while data exists in database
                //set the result based on your table atribute
                //E.g : int id = resultSet.getInt("id");
                String todo = resultSet.getString("todo");
                String kategori = resultSet.getString("kategori");
                String tanggal_selesai = resultSet.getString("tanggalSelesai");
                String status = resultSet.getString("status");

                // Display the retrieved data
                System.out.println("Todo: " + todo);
                System.out.println("Kategori: " + kategori);
                System.out.println("Tanggal Selesai: " + tanggal_selesai);
                System.out.println("Status: " + status);
                System.out.println("=====================");
            }
            System.out.println("---------------------\n");
            // close the connection object
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load JDBC driver");
            e.printStackTrace(); // show the error
        } catch (SQLException e) {
            System.out.println("Database connection error");
            e.printStackTrace(); // show the error
        }
    }

    public void createData(){
        try {
            Main.input.nextLine();

            System.out.print("Masukan nama kegiatan : ");
            todo = Main.input.nextLine();
            System.out.print("Masukan nama kategori : ");
            kategori = Main.input.nextLine();
            System.out.print("Masukan tanggal selesai (dd-mm-yyyy) : ");
            tanggal_selesai = Main.input.nextLine();

            java.sql.Connection connection = DriverManager.getConnection(url, username, password); // create the connection object
            Statement statement = connection.createStatement(); // create the statement object
            String query = "SELECT * FROM todolist"; // show all column with data
            ResultSet resultSet = statement.executeQuery(query); // execute the query

            if(resultSet.next()) {
                int rowsInserted = statement.executeUpdate("INSERT INTO todolist VALUES (NULL, '"+todo+"', '"+kategori+"', '"+tanggal_selesai+"', 'Belum selesai');");
                if (rowsInserted > 0) {
                    System.out.println("\nTodo list baru berhasil ditambahkan\n");
                }
            }
            // close the connection object
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Database connection error");
            e.printStackTrace(); // show the error
        }
    }

    public void deleteData(){
        try {
            Main.input.nextLine();

            System.out.print("Masukan nama kegiatan : ");
            todo = Main.input.nextLine();

            java.sql.Connection connection = DriverManager.getConnection(url, username, password); // create the connection object
            Statement statement = connection.createStatement(); // create the statement object
            String query = "SELECT * FROM todolist"; // show all column with data
            ResultSet resultSet = statement.executeQuery(query); // execute the query
            if(resultSet.next()) {
                int rowsInserted = statement.executeUpdate("DELETE FROM todolist WHERE todo = '"+todo+"'");
                if (rowsInserted > 0) {
                    System.out.println("\nKegiatan " + todo + " berhasil dihapus\n");
                } else {
                    System.out.println("\nKegiatan " + todo + " gagal dihapus\n");
                }
            }
            // close the connection object
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Database connection error");
            e.printStackTrace(); // show the error
        }
    }

    public void updateData(){
        try {
            Main.input.nextLine();
            System.out.print("Masukan nama kegiatan : ");
            oldTodo = Main.input.nextLine();

            java.sql.Connection connection = DriverManager.getConnection(url, username, password); // create the connection object
            Statement statement = connection.createStatement(); // create the statement object
            String query = "SELECT * FROM todolist"; // show all column with data
            ResultSet resultSet = statement.executeQuery(query); // execute the query

            if (resultSet.next()){
                System.out.print("Masukan nama kegiatan yang baru : ");
                todo = Main.input.nextLine();
                System.out.print("Masukan nama kategori yang baru : ");
                kategori = Main.input.nextLine();
                System.out.print("Masukan tanggal selesai yang baru : ");
                tanggal_selesai = Main.input.nextLine();

                int rowsUpdated = statement.executeUpdate("UPDATE todolist SET todo='"+todo+"', kategori='"+kategori+"', tanggalSelesai='"+tanggal_selesai+"' WHERE todo='"+oldTodo+"'");
                if (rowsUpdated > 0) {
                    System.out.println("\nKegiatan "+oldTodo+" berhasil diubah!\n");
                }
            } else {
                System.out.println("\nKegiatan "+oldTodo+" tidak ditemukan!\n");
            }
            // close the connection object
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Database connection error");
            e.printStackTrace(); // show the error
        }
    }

    public void changeStatus(){
        try {
            Main.input.nextLine();
            System.out.print("Masukan nama kegiatan yang telah selesai : ");
            oldTodo = Main.input.nextLine();

            java.sql.Connection connection = DriverManager.getConnection(url, username, password); // create the connection object
            Statement statement = connection.createStatement(); // create the statement object
            String query = "SELECT * FROM todolist"; // show all column with data
            ResultSet resultSet = statement.executeQuery(query); // execute the query

            if (resultSet.next()){
                int rowsUpdated = statement.executeUpdate("UPDATE todolist SET status='Selesai' WHERE todo='"+oldTodo+"'");
                if (rowsUpdated > 0) {
                    System.out.println("\nStatus kegiatan "+oldTodo+" berhasil diubah!\n");
                }
            } else {
                System.out.println("\nStatus kegiatan "+oldTodo+" tidak ditemukan!\n");
            }
            // close the connection object
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Database connection error");
            e.printStackTrace(); // show the error
        }
    }
}
