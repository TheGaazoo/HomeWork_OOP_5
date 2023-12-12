package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;
    public UserView(UserController userController) {
        this.userController = userController;
    }
    public void run(){
        Commands com;
        showHelp();
        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = prompt("Введите идентификатор пользователя: ");
                    try {
                        User user = userController.findUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case READ_ALL:
                    List<User> allUsers = userController.getAllUsers();
                    System.out.println();
                    for (User user : allUsers) {
                        System.out.println(user);
                        System.out.println("----------------");
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Введите идентификатор пользователя: ");
                    userController.updateUser(userId, createUser());
                    break;
                case DELETE:
                    userId = prompt("Введите идентификатор пользователя: ");
                    userController.deleteUser(userId);
                    break;
                case HELP:
                    System.out.println("--- Информация по командам записной книжки ---");
                    System.out.println("READ - прочитать конкретную запись по идентификационному номеру.");
                    System.out.println("CREATE - создать новую запись.");
                    System.out.println("UPDATE - изменить запись с указанным идентификационным номером.");
                    System.out.println("READ_ALL - вывести все записи на экран.");
                    System.out.println("DELETE - удалить запись с указанным идентификационным номером.");
                    System.out.println("EXIT - выйти из приложения.");
                    System.out.println("HELP - отобразить описание команд.");
                    break;

            }
        }
    }
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
    private void showHelp() {
        System.out.println("Список команд:");
        for(Commands c : Commands.values()) {
            System.out.println(c);
        }
    }
    private User createUser() {
        String firstName = prompt("Фамилия: ");
        String lastName = prompt("Имя: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }
}
