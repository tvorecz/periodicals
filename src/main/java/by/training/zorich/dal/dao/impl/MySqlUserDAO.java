package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.User;
import by.training.zorich.bean.UserLocale;
import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.exception.ExecutorException;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

import java.sql.Connection;

public class MySqlUserDAO implements UserDAO {
    private final static String QUERY_INSERT_USER = "INSERT INTO users (login, password, idRole, email, " +
                                                    "idDefaultLocal) values ('%1$s'," +
                                                    " '%2$s', %3$d, '%4$s', %5$d)";

    private final static String QUERY_LOGINATION_USER = "SELECT idUser, login, user_roles.nameRole, email, " +
                                                        "internationalization.nameLocal FROM users, " +
                                                        "user_roles, internationalization WHERE users.idRole = " +
                                                        "user_roles.idRole AND users.idDefaultLocal = " +
                                                        "internationalization.idLocal AND users.login " +
                                                        "= '%1$s' AND users.password = '%2$s'";

    private final static String QUERY_SELECT_USER_BY_ID = "SELECT idUser, login, user_roles.nameRole, email, " +
                                                          "internationalization.nameLocal FROM users, " +
                                                          "user_roles, internationalization WHERE users.idRole = " +
                                                          "user_roles.idRole AND users.idDefaultLocal = " +
                                                          "internationalization.idLocal AND users.idUser " +
                                                          "= %1$d";

    private final static String QUERY_VALIDATE_USER = "SELECT idUser, login, password, idRole, email FROM users " +
                                                      "WHERE login = '%1$s' OR email = '%2$s'";

    private final static String QUERY_CHANGE_LOCALE = "UPDATE users SET idDefaultLocal = %1$d WHERE idUser = %2$d";


    private DataSourceConnector connector;
    private SQLExecutor sqlExecutor;
    private ResultHandlerRepository resultHandlerRepository;

    public MySqlUserDAO(DataSourceConnector connector,
                        SQLExecutor sqlExecutor,
                        ResultHandlerRepository resultHandlerRepository) {
        this.connector = connector;
        this.sqlExecutor = sqlExecutor;
        this.resultHandlerRepository = resultHandlerRepository;
    }

    @Override
    public void register(User user) throws DAOException {
        String query = String.format(QUERY_INSERT_USER,
                                     user.getLogin(),
                                     user.getCodifiedPassword(),
                                     user.getRole().getIdRole(),
                                     user.getEmail(),
                                     user.getCurrentLocale().getIdLocale());

        updateUser(query);
    }

    @Override
    public User authenticate(User user) throws DAOException {
        String query = String.format(QUERY_LOGINATION_USER, user.getLogin(), user.getCodifiedPassword());

        return selectUserFromDB(query);
    }

    @Override
    public User getUserInfo(int userId) throws DAOException {
        String query = String.format(QUERY_SELECT_USER_BY_ID, userId);

        return selectUserFromDB(query);
    }

    @Override
    public void changeLocale(int idUser, UserLocale newUserLocale) throws DAOException {
        String query = String.format(QUERY_CHANGE_LOCALE, newUserLocale.getIdLocale(), idUser);

        updateUser(query);
    }

    @Override
    public boolean validate(User user) throws DAOException {
        String query = String.format(QUERY_VALIDATE_USER, user.getLogin(), user.getEmail());
        Connection connection = null;

        try {
            connection = connector.getConnection();
            return (Boolean) sqlExecutor.select(connection,
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

    private User selectUserFromDB(String query) throws DAOException{
        Connection connection = null;

        try {
            connection = connector.getConnection();
            return (User) sqlExecutor.select(connection, query,
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

    private void updateUser(String query) throws DAOException {
        Connection connection = null;

        try {
            connection = connector.getConnection();
            sqlExecutor.update(connection, query);
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
}
