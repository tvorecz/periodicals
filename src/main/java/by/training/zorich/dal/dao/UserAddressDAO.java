package by.training.zorich.dal.dao;

import by.training.zorich.bean.UserAddress;
import by.training.zorich.dal.exception.DAOException;

import java.util.List;

public interface UserAddressDAO {
    void add(UserAddress address) throws DAOException;

    void edit(UserAddress address) throws DAOException;

    void delete(int idAddress) throws DAOException;

    List<UserAddress> getAllUserAddresses(int idUser, int beginOfRange, int countOfRecords) throws DAOException;
}
