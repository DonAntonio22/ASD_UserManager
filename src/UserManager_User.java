public class UserManager_User {

    private String benutzername;
    private String passwort;
    private String vorname;
    private String nachname;

    public UserManager_User(String benutzername, String passwort) {
        this.benutzername = benutzername;
        this.passwort = passwort;
    }

    public UserManager_User(String benutzername, String passwort, String vorname, String nachname) {
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
