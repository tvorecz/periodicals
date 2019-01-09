package by.training.zorich.bean;

import java.io.Serializable;

public class UserAddress implements Serializable {
    private static final long serialVersionUID = -5413763151048405410L;

    private int idAddress;
    private int IdUser;
    private String address;

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
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

        if (idAddress != that.idAddress) {
            return false;
        }
        if (IdUser != that.IdUser) {
            return false;
        }
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = idAddress;
        result = 31 * result + IdUser;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "idAddress=" + idAddress +
               ", IdUser=" + IdUser +
               ", address='" + address + '\'' +
               '}';
    }
}
