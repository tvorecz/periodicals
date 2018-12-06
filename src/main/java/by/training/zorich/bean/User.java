package by.training.zorich.bean;

import by.training.zorich.controller.const_parameter.UserRole;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 8941272441270589087L;

    private int id;
    private String login;
    private String realPassword;
    private long codifiedPassword;
    private String email;
    private UserRole role;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRealPassword() {
        return realPassword;
    }

    public void setRealPassword(String realPassword) {
        this.realPassword = realPassword;
    }

    public long getCodifiedPassword() {
        return codifiedPassword;
    }

    public void setCodifiedPassword(long codifiedPassword) {
        this.codifiedPassword = codifiedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (codifiedPassword != user.codifiedPassword) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (realPassword != null ? realPassword.hashCode() : 0);
        result = 31 * result + (int) (codifiedPassword ^ (codifiedPassword >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "id=" + id +
               ", login='" + login + '\'' +
               ", codifiedPassword='" + codifiedPassword + '\'' +
               ", role=" + role +
               '}';
    }
}
