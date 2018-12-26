package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.UserAddress;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.dao.UserAddressDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.util.List;

public class MySqlUserAddressDAO extends CommonDAO<List<UserAddress>> implements UserAddressDAO {
    private final static String QUERY_ADD_ADDRESS = "INSERT INTO user_addresses (address, idUser) values ('%1$s', " +
                                                    "%2$d)";
    private final static String QUERY_EDIT_ADDRESS = "UPDATE user_addresses SET address = '%1$s' WHERE idUser = %2$d";
    private final static String QUERY_DELETE_ADDRESS = "DELETE FROM user_addresses WHERE idAddress = %1$d";
    private final static String QUERY_GET_ADDRESS = "SELECT * FROM user_addresses WHERE idUser = %1$d";


    public MySqlUserAddressDAO(DataSourceConnector connector,
                               SQLExecutor sqlExecutor,
                               ResultHandlerRepository resultHandlerRepository) {
        super(connector, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public void add(UserAddress address) throws DAOException {
        String query = String.format(QUERY_ADD_ADDRESS, address.getAddress(), address.getIdUser());

        super.executeSimpleUpdate(query, TransactionStatus.OFF);
    }

    @Override
    public void edit(UserAddress address) throws DAOException {
        String query = String.format(QUERY_EDIT_ADDRESS, address.getAddress(), address.getIdUser());

        super.executeSimpleUpdate(query, TransactionStatus.OFF);
    }

    @Override
    public void delete(int idAddress) throws DAOException {
        String query = String.format(QUERY_DELETE_ADDRESS, idAddress);

        super.executeSimpleUpdate(query, TransactionStatus.OFF);
    }

    @Override
    public List<UserAddress> getAllUserAddresses(int idUser) throws DAOException {
        String query = String.format(QUERY_GET_ADDRESS, idUser);

        return super.executeSimpleSelect(query, HandlerType.USER_ADDRESS_HANDLER, TransactionStatus.OFF);
    }
}
