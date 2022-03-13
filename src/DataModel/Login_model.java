package DataModel;

import javafx.beans.property.SimpleStringProperty;

public class Login_model {
    private SimpleStringProperty userName = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleStringProperty role = new SimpleStringProperty();

    public Login_model(String userName, String password, String role) {
        this.userName.set(userName);
    }

    public Login_model() {

    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }
}
