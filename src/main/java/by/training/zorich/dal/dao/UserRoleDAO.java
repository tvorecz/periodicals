package by.training.zorich.dal.dao;

import by.training.zorich.dal.exception.DAOException;

public interface UserRoleDAO {
    int getIdRoleByName(String roleName) throws DAOException;
}
