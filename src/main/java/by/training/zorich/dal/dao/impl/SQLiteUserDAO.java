package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.User;
import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.exception.ExecutorException;
import by.training.zorich.dal.sql_executor.Executor;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

import java.sql.Connection;

public class SQLiteUserDAO implements UserDAO {

    private final static String QUERY_SELECT_USER = "SELECT idUser, login, password, nameRole, email FROM Users, " +
                                                    "UserRoles WHERE Users.idRole = UserRoles.idRole AND Users.login " +
                                                    "= %1 AND Users.password = %2";
    private final static String QUERY_VALIDATE_USER = "SELECT idUser, login, password, nameRole, email FROM Users " +
                                                      "WHERE login = %1 OR email = %2";
    private final static String QUERY_INSERT_USER = "INSERT INTO Users (login, password, idRole, email) values ('%1'," +
                                                    " %2, %3, '%4')";

    private DataSourceConnector connector;
    private Executor executor;
    private ResultHandlerRepository resultHandlerRepository;

    public SQLiteUserDAO(DataSourceConnector connector,
                         Executor executor,
                         ResultHandlerRepository resultHandlerRepository) {
        this.connector = connector;
        this.executor = executor;
        this.resultHandlerRepository = resultHandlerRepository;
    }

    @Override
    public void register(User user) throws DAOException {
        String query = String.format(QUERY_INSERT_USER,
                                     user.getLogin(),
                                     user.getCodifiedPassword(),
                                     user.getRole(),
                                     user.getEmail());
        Connection connection = null;

        try {
            connection = connector.getConnection();
            executor.update(connection, query);
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Getting of connection from the pool is failed!", e);
        } catch (ExecutorException e) {
            throw new DAOException("Registration is failed!", e);
        } finally {
            if (connection != null) {
                try {
                    connector.giveBackConnection(connection);
                } catch (DataSourceConnectorException e) {
                    throw new DAOException("Giving back connection to the pool is failed!", e);
                }
            }
        }
    }

    @Override
    public User authenticate(User user) throws DAOException {
        String query = String.format(QUERY_SELECT_USER, user.getLogin(), user.getCodifiedPassword());
        Connection connection = null;

        try {
            connection = connector.getConnection();
            return (User) executor.select(connection, query,
                                          resultHandlerRepository.getResultHandler(
                                                  HandlerType.USER_HANDLER));
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Getting of connection from the pool is failed!", e);
        } catch (ExecutorException e) {
            throw new DAOException("Getting of id role id failed!", e);
        } finally {
            if (connection != null) {
                try {
                    connector.giveBackConnection(connection);
                } catch (DataSourceConnectorException e) {
                    throw new DAOException("Giving back connection to the pool is failed!", e);
                }
            }
        }
    }

    @Override
    public boolean validate(User user) throws DAOException {
        String query = String.format(QUERY_VALIDATE_USER, user.getLogin(), user.getEmail());
        Connection connection = null;

        try {
            connection = connector.getConnection();
            return (Boolean) executor.select(connection,
                                             query,
                                             resultHandlerRepository.getResultHandler(HandlerType.VALIDATE_USER_HANDLER));
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Getting of connection from the pool is failed!", e);
        } catch (ExecutorException e) {
            throw new DAOException("Getting of id role id failed!", e);
        } finally {
            if (connection != null) {
                try {
                    connector.giveBackConnection(connection);
                } catch (DataSourceConnectorException e) {
                    throw new DAOException("Giving back connection to the pool is failed!", e);
                }
            }
        }
    }
}
