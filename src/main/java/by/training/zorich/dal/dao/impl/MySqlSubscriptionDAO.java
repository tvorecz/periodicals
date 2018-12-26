package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.SubsciptionVariant;
import by.training.zorich.bean.UserAddress;
import by.training.zorich.bean.UserSubscription;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.SubscriptionDAO;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.PreparedStatementFillerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MySqlSubscriptionDAO extends CommonDAO<Object> implements SubscriptionDAO {
    private final static String QUERY_INSERT_SUBSCRIPTION = "INSERT INTO user_subscriptions (idAddress, idSubscriptionVariant, dateBegin, dateEnd, idPayment) values(%1$d, %2$d, '%3$s', '%4$s', %5$d)";
    private final static String QUERY_PATTERN_INSERT_SUBSCRIPTION = "INSERT INTO user_subscriptions (idAddress, idSubscriptionVariant, dateBegin, dateEnd, idPayment) values(?, ?, ?, ?, ?)";
    private final static String QUERY_SELECT_SUBSCRIPTION = "SELECT \tuser_subscriptions.idUserSubscription, \n" +
                                                            "\t\tuser_addresses.idAddress,\n" +
                                                            "\t\tuser_addresses.address,\n" +
                                                            "\t\tuser_addresses.idUser,\n" +
                                                            "\t\tsubscription_variants.idSubscriptionVariant,\n" +
                                                            "\t\tsubscription_variants.indexSubscription,\n" +
                                                            "\t\tperiodicals.idPeriodical,\n" +
                                                            "\t\tperiodical_type.typeName,\n" +
                                                            "\t\tperiodical_theme.nameTheme,\n" +
                                                            "\t\tperiodicals.namePeriodical,\n" +
                                                            "\t\tperiodicals.periodicityInMonth,\n" +
                                                            "\t\tperiodicals.annotation,\n" +
                                                            "\t\tperiodicals.imagePath,\n" +
                                                            "\t\tsubscription_types.idSubscriptionType,\n" +
                                                            "\t\tsubscription_types.nameSubscriptionType,\n" +
                                                            "\t\tsubscription_types.monthAmount,\n" +
                                                            "\t\tsubscription_variants.cost,\n" +
                                                            "\t\tuser_subscriptions.dateBegin,\n" +
                                                            "\t\tuser_subscriptions.dateEnd,\n" +
                                                            "\t\tpayments.idPayment,\n" +
                                                            "\t\tpayments.amount,\n" +
                                                            "\t\tpayments.payStatus\n" +
                                                            "\t\t\n" +
                                                            "\t\tFROM user_subscriptions\n" +
                                                            "\t\t\tJOIN user_addresses \n" +
                                                            "\t\t\t\t\tON user_addresses.idUser = %1$d\n" +
                                                            "\t\t\t\t\tAND user_addresses.idAddress = " +
                                                            "user_subscriptions.idAddress\n" +
                                                            "\t\t\tJOIN payments ON user_subscriptions.idPayment = " +
                                                            "payments.idPayment\n" +
                                                            "\t\t\tJOIN subscription_variants ON user_subscriptions" +
                                                            ".idSubscriptionVariant = subscription_variants" +
                                                            ".idSubscriptionVariant\n" +
                                                            "\t\t\tJOIN subscription_types ON subscription_variants" +
                                                            ".idSubscriptionType = subscription_types" +
                                                            ".idSubscriptionType\n" +
                                                            "\t\t\tJOIN periodicals ON subscription_variants" +
                                                            ".idPeriodical = periodicals.idPeriodical\n" +
                                                            "\t\t\tJOIN periodical_type ON periodicals.idPeriodical =" +
                                                            " periodical_type.idType\n" +
                                                            "\t\t\tJOIN periodical_theme ON periodicals.idTheme = " +
                                                            "periodical_theme.idTheme";


    public MySqlSubscriptionDAO(DataSourceConnector connector,
                                SQLExecutor sqlExecutor,
                                ResultHandlerRepository resultHandlerRepository) {
        super(connector, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public void subscribeTransactionaly(int idPayment, SubsciptionVariant subscriptionVariant,
                                        UserAddress address,
                                        LocalDate begin, LocalDate end) throws DAOException {
        String query = String.format(QUERY_INSERT_SUBSCRIPTION, address.getIdAdress(), subscriptionVariant.getId(), Date.valueOf(begin), Date.valueOf(end), idPayment);
        super.executeUpdate(query, TransactionStatus.ON);
    }

    @Override
    public void subscribeTransactionaly(int idPayment, List<SubsciptionVariant> subscriptionVariants,
                                        UserAddress address,
                                        List<LocalDate> begins, List<LocalDate> ends) throws DAOException {

        Iterator<SubsciptionVariant> subsciptionVariantIterator = subscriptionVariants.iterator();
        Iterator<LocalDate> beginDateIterator = begins.iterator();
        Iterator<LocalDate> endDateIterator = ends.iterator();

        PreparedStatement preparedStatement = super.createPreparedStatement(QUERY_PATTERN_INSERT_SUBSCRIPTION, TransactionStatus.ON);

        while (subsciptionVariantIterator.hasNext() && beginDateIterator.hasNext() && endDateIterator.hasNext()) {
            List<Object> valuesForFillingStatement =  new ArrayList<>();
            List<PreparedStatementFillerType > valuesTypes = new ArrayList<>();

            valuesForFillingStatement.add(address.getIdAdress());
            valuesTypes.add(PreparedStatementFillerType.INTEGER);

            valuesForFillingStatement.add(subsciptionVariantIterator.next().getId());
            valuesTypes.add(PreparedStatementFillerType.INTEGER);

            valuesForFillingStatement.add(Date.valueOf(beginDateIterator.next()));
            valuesTypes.add(PreparedStatementFillerType.DATE);

            valuesForFillingStatement.add(Date.valueOf(endDateIterator.next()));
            valuesTypes.add(PreparedStatementFillerType.DATE);

            valuesForFillingStatement.add(idPayment);
            valuesTypes.add(PreparedStatementFillerType.INTEGER);

            super.fillPreparedStatement(preparedStatement, valuesForFillingStatement, valuesTypes, TransactionStatus.ON);

            super.executeUpdate(preparedStatement, TransactionStatus.ON);
        }
    }

    @Override
    public List<UserSubscription> getAllSubscriptions(int idUser) throws DAOException {
        String query = String.format(QUERY_SELECT_SUBSCRIPTION, idUser);

        super.executeSelect(query, HandlerType.USER_SUBSCRIPTION_HANDLER, TransactionStatus.OFF);

        return null;
    }
}
