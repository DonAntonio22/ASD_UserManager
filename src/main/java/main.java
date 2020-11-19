import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    public static void main(String[] args) {

        UserManager_Manager manager = UserManager_Manager.getManagerInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maxLoginTries = 0;


        while (true) {
            String command = "";
            try {
                System.out.print("*******Enter command*******\nCommand: ");
                command = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (command.trim()) {
                case "login":
                    if (manager.getCurrentLogedInUser() == null){
                        while (manager.getCurrentLogedInUser() == null)  {
                            try {
                                System.out.print("*******Login*******\nUser: ");
                                String username = reader.readLine();
                                System.out.print("Password: ");
                                String password = reader.readLine();
                                manager.loginUser(username, password);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (WrongUserCredentials wrongUserCredentials) {
                                if (maxLoginTries >= 2) break;
                                ++maxLoginTries;
                                System.out.print("username oder password nicht korrekt\n");
                            }

                        }
                    }else{
                        System.out.print("Please log out first");
                    }
                    maxLoginTries = 0;
                    break;
                case "changePWD":
                    if (manager.getCurrentLogedInUser() != null){
                        System.out.print("*******Change Password*******\nEnter password: ");
                        try {
                            String newPassword = reader.readLine();
                            System.out.print("Reenter password: ");
                            String reenteredPassword = reader.readLine();
                            manager.changePassword(newPassword, reenteredPassword);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (PasswordIncorrect passwordIncorrect) {
                            System.out.print(passwordIncorrect);
                        }
                    }else {
                        System.out.print("No user loged in\n");
                    }
                    break;
                case "deleteACC":
                    if (manager.getCurrentLogedInUser() != null){
                        System.out.print("*******Delete Account*******\nWollen Sie den Account wirklich löschen[yes]? ");
                        try {
                            if ("yes".equals(reader.readLine().toLowerCase().trim()))
                            manager.deleteAccount();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.print("No user loged in\n");
                    }
                    break;
                case "createACC":
                    if (manager.getCurrentLogedInUser() != null){
                        System.out.print("*******Create Account*******\nVorname: ");
                        try {
                            String vorname = reader.readLine();
                            System.out.print("Nachname: ");
                            String nachname = reader.readLine();
                            System.out.print("Username: ");
                            String newUser = reader.readLine();
                            System.out.print("Password: ");
                            String newPassword = reader.readLine();
                            manager.createUser(newUser, newPassword,vorname, nachname);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (UserAlreadyExists userAlreadyExists) {
                            userAlreadyExists.printStackTrace();
                        }
                    }else {
                        System.out.print("No user loged in\n");
                    }
                    break;
                case "logout":
                    manager.logOutCurrentUser();
                    System.out.println("User loged out");
                    break;
                default:
                    System.out.println("no match");
            }
        }
    }
}
