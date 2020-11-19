import java.util.ArrayList;
import java.util.List;

public class UserManager_Manager {

    final private UserManager_User admin;
    private UserManager_User currentLogedInUser = null;
    private List<UserManager_User> userList;
    private static UserManager_Manager managerInstance;

    public UserManager_Manager() {
        admin = new UserManager_User("admin", "admin");
        userList = new ArrayList<>();

        userList.add(admin);
    }

    public static UserManager_Manager getManagerInstance() {

        if (UserManager_Manager.managerInstance == null){
            return UserManager_Manager.managerInstance = new UserManager_Manager();
        }

        return UserManager_Manager.managerInstance;
    }

    public UserManager_User getCurrentLogedInUser() {
        return currentLogedInUser;
    }

    public UserManager_User createUser(String username, String password, String vorname, String nachname) throws UserAlreadyExists {

        for (UserManager_User user: userList) {
            if (user.getBenutzername().equals(username))
                throw new UserAlreadyExists("User" + username +"already in the userList");
        }

        UserManager_User newUser = new UserManager_User(username, password, vorname, nachname);
        userList.add(newUser);

        return newUser;
    }

    public UserManager_User loginUser(String username, String password) throws WrongUserCredentials{
        for (UserManager_User user :
                userList) {
            if (user.getBenutzername().equals(username)){
                if (user.getPasswort().equals(password)){
                    currentLogedInUser = user;
                    System.out.print("User loged in!\n");
                    return user;
                }else{
                    throw new WrongUserCredentials("Wrong Password for user: " + user.getBenutzername());
                }
            }
        }
        throw new WrongUserCredentials("User is not known!");
    }

    public void changePassword(String password1, String password2) throws PasswordIncorrect{

        if (password1.equals(password2) == false){
            throw new PasswordIncorrect("„Kennwörter nicht gleich");
        }
        currentLogedInUser.setPasswort(password1);
    }

    public void logOutCurrentUser(){
        currentLogedInUser = null;
    }

    public void deleteAccount(){

        userList.remove(currentLogedInUser);
        currentLogedInUser = null;

    }
}
