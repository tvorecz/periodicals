package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.User;
import by.training.zorich.bean.UserLocale;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

public class MySqlUserDAO extends CommonDAO<Object> implements UserDAO {
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


    public MySqlUserDAO(DataSourceConnector connector,
                        TransactionManager transactionManager,
                        SQLExecutor sqlExecutor,
                        ResultHandlerRepository resultHandlerRepository) {
        super(connector, transactionManager, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public void register(User user) throws DAOException {
        String query = String.format(QUERY_INSERT_USER,
                                     user.getLogin(),
                                     user.getCodifiedPassword(),
                                     user.getRole().getIdRole(),
                                     user.getEmail(),
                                     user.getCurrentLocale().getIdLocale());

        super.executeUpdateDataSource(query, TransactionStatus.OFF);
    }

    @Override
    public User authenticate(User user) throws DAOException {
        String query = String.format(QUERY_LOGINATION_USER, user.getLogin(), user.getCodifiedPassword());

        return (User) super.executeSelectFromDataSource(query, HandlerType.USER_HANDLER, TransactionStatus.OFF);
    }

    @Override
    public User getUserInfo(int userId) throws DAOException {
        String query = String.format(QUERY_SELECT_USER_BY_ID, userId);

        return (User) super.executeSelectFromDataSource(query, HandlerType.USER_HANDLER, TransactionStatus.OFF);
    }

    @Override
    public void changeLocale(int idUser, UserLocale newUserLocale) throws DAOException {
        String query = String.format(QUERY_CHANGE_LOCALE, newUserLocale.getIdLocale(), idUser);

        super.executeUpdateDataSource(query, TransactionStatus.OFF);
    }

    @Override
    public boolean validate(User user) throws DAOException {
        String query = String.format(QUERY_VALIDATE_USER, user.getLogin(), user.getEmail());

        return (Boolean) super.executeSelectFromDataSource(query, HandlerType.VALIDATE_HANDLER, TransactionStatus.OFF);
    }
}
