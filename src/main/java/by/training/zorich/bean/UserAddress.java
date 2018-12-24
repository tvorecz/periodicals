package by.training.zorich.bean;

import java.io.Serializable;

public class UserAddress implements Serializable {
    private static final long serialVersionUID = -5413763151048405410L;

    private int idAdress;
    private int IdUser;
    private String address;

    public int getIdAdress() {
        return idAdress;
    }

    public void setIdAdress(int idAdress) {
        this.idAdress = idAdress;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserAddress that = (UserAddress) o;

        if (idAdress != that.idAdress) {
            return false;
        }
        if (IdUser != that.IdUser) {
            return false;
        }
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = idAdress;
        result = 31 * result + IdUser;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "idAdress=" + idAdress +
               ", IdUser=" + IdUser +
               ", address='" + address + '\'' +
               '}';
    }
}
