package rest_assured.payload.response.error;

import java.util.List;

public class ErrorEntity {
    private List<String> username;
    private List<String> firstName;
    private List<String> lastName;
    private List<String> email;
    private List<String> password;
    private List<String> phone;
    private List<String> userStatus;
    public ErrorEntity() {
    }

    public ErrorEntity(List<String> username, List<String> firstName, List<String> lastName, List<String> email, List<String> password, List<String> phone, List<String> userStatus) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public List<String> getFirstName() {
        return firstName;
    }

    public void setFirstName(List<String> firstName) {
        this.firstName = firstName;
    }

    public List<String> getLastName() {
        return lastName;
    }

    public void setLastName(List<String> lastName) {
        this.lastName = lastName;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(List<String> userStatus) {
        this.userStatus = userStatus;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }
}
