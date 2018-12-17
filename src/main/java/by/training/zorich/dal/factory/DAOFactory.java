package by.training.zorich.dal.factory;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.dao.UserRoleDAO;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

public interface DAOFactory {
    void init(DataSourceConnector connector,
              SQLExecutor SQLExecutor,
              ResultHandlerRepository resultHandlerRepository);

    UserDAO getUserDAO();

    UserRoleDAO getUserRoleDAO();
}
