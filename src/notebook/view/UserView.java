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
        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
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
            }
        }
    }
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
    private User createUser() {
        String firstName = prompt("Фамилия: ");
        String lastName = prompt("Имя: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }
}
