package by.training.zorich.dal.factory;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.dao.UserRoleDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.Executor;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

public interface DAOFactory {
    void init(DataSourceConnector connector,
              Executor executor,
              ResultHandlerRepository resultHandlerRepository);

    UserDAO getUserDAO();

    UserRoleDAO getUserRoleDAO();
}
