package rest_assured.payload.request;

import rest_assured.utils.LogUtils;

public class RegisterUserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public RegisterUserRequest() {
    }

    public RegisterUserRequest(String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setPhone(phone);
        setUserStatus(userStatus);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            LogUtils.error("Validation ERROR: Username cannot be empty");
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (username.length() < 3 || username.length() > 20) {
            LogUtils.error("Validation ERROR: Username must be between 3 and 20");
            throw new IllegalArgumentException("Username must be between 3 and 20");
        }
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            LogUtils.error("Validation ERROR: First name cannot be empty");
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (firstName.length() > 50) {
            LogUtils.error("Validation ERROR: First name must not exceed 50 characters");
            throw new IllegalArgumentException("First name must not exceed 50 characters");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            LogUtils.error("Validation ERROR: Last name cannot be empty");
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (lastName.length() > 50) {
            LogUtils.error("Validation ERROR: Last name must not exceed 50 characters");
            throw new IllegalArgumentException("Last name must not exceed 50 characters");
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            LogUtils.error("Validation ERROR: Email cannot be empty");
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            LogUtils.error("Validation ERROR: Invalid email format");
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            LogUtils.error("Validation ERROR: Password cannot be empty");
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (password.length() < 6 || password.length() > 50) {
            LogUtils.error("Validation ERROR: must be between 8 and 50 characters");
            throw new IllegalArgumentException("Password must be between 8 and 50 characters");
        }
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            LogUtils.error("Validation ERROR: Phone number cannot be empty");
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (!phone.matches("\\d{10}")) {
            LogUtils.error("Validation ERROR: Invalid phone format");
            throw new IllegalArgumentException("Invalid phone format");
        }
        this.phone = phone;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        if (userStatus != 0 && userStatus != 1) { // Reject anything other than 0 or 1
            LogUtils.error("Validation ERROR: User status must be either 0 or 1");
            throw new IllegalArgumentException("User status must be either 0 or 1");
        }
        this.userStatus = userStatus;
    }
}
