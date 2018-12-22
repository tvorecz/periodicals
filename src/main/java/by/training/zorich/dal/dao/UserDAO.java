package by.training.zorich.dal.dao;

import by.training.zorich.bean.User;
import by.training.zorich.bean.UserLocale;
import by.training.zorich.dal.exception.DAOException;

import java.sql.SQLException;

public interface UserDAO {
    void register(User user) throws DAOException;

    void changeLocale(int idUser, UserLocale newUserLocale) throws DAOException;

    User authenticate(User user) throws DAOException;

    User getUserInfo(int userId) throws DAOException;

    boolean validate(User user) throws DAOException;
}
