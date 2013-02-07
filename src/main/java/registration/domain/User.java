package registration.domain;

public class User {
    private String id;
    private String password;

    private String firstName;
    private String lastName;

    private User() {
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class Builder {
        private User user;

        public Builder() {
        }

        public Builder newUser(String id) {
            user = new User();
            user.id = id;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
