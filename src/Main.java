import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/todolist_uasprakpbo";
        String username = "root";
        String password = "";

        int pilih;
        boolean loop = true;

        Connection connection = new Connection(url, username, password);

        do {
            // show menu
            System.out.println("1. Create Todo\n2. Show Todo List\n3. Delete Todo\n4. Update Todo\n5. Change Status\n6. Exit");
            System.out.print("Masukan pilihan : ");
            pilih = input.nextInt();
            // decision
            // every case, call the method
            switch (pilih){
                case 1:
                    connection.createData();
                    break;
                case 2:
                    connection.displayData();
                    break;
                case 3:
                    connection.deleteData();
                    break;
                case 4:
                    connection.updateData();
                    break;
                case 5:
                    connection.changeStatus();
                    break;
                case 6:
                    // set loop with false for exit from the program
                    loop = false;
                    break;
            }
        } while (loop);
        System.out.println("Program berakhir!");
    }
}