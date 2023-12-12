package notebook.view;

import notebook.Colors;
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
            String command = prompt(Colors.blue + Colors.bold +"Введите команду: " + Colors.reset);
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) {
                System.out.println(Colors.green + Colors.bold + Colors.italic + "Спасибо за использование нашего приложения! До свидания!" + Colors.reset);
                return;
            }
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
                    System.out.println(Colors.green + Colors.bold + "--- Информация по командам записной книжки ---" + Colors.reset);
                    System.out.println(Colors.cyan + Colors.bold + "READ " + Colors.reset + "- прочитать конкретную запись по идентификационному номеру.");
                    System.out.println(Colors.cyan + Colors.bold + "CREATE " + Colors.reset + "- создать новую запись.");
                    System.out.println(Colors.cyan + Colors.bold + "UPDATE " + Colors.reset + "- изменить запись с указанным идентификационным номером.");
                    System.out.println(Colors.cyan + Colors.bold + "READ_ALL " + Colors.reset + "- вывести все записи на экран.");
                    System.out.println(Colors.cyan + Colors.bold + "DELETE " + Colors.reset + "- удалить запись с указанным идентификационным номером.");
                    System.out.println(Colors.cyan + Colors.bold + "EXIT " + Colors.reset + "- выйти из приложения.");
                    System.out.println(Colors.cyan + Colors.bold + "HELP " + Colors.reset + "- отобразить описание команд.");
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
