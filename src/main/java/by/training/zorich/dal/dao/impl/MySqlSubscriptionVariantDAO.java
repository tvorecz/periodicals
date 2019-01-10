package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.SubscriptionVariantDAO;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MySqlSubscriptionVariantDAO extends CommonDAO<Object> implements SubscriptionVariantDAO {
    private final static String QUERY_SELECT_SUBSCRIPTION_VARIANT_BY_ID = "SELECT \tsubscription_variants" +
                                                                          ".idSubscriptionVariant,\n" +
                                                                          "\t\tsubscription_variants" +
                                                                          ".indexSubscription,\n" +
                                                                          "\t\tsubscription_variants.costForIssue,\n" +
                                                                          "\t\tperiodicals.idPeriodical,\n" +
                                                                          "\t\tperiodicals.namePeriodical,\n" +
                                                                          "\t\tperiodicals.periodicityInMonth,\n" +
                                                                          "\t\tperiodicals.annotation,\n" +
                                                                          "\t\tperiodicals.imagePath,\n" +
                                                                          "\t\tperiodical_type.idType,\n" +
                                                                          "\t\tperiodical_type.typeName,\n" +
                                                                          "\t\tperiodical_theme.idTheme,\n" +
                                                                          "\t\tperiodical_theme.nameTheme,\t\t\n" +
                                                                          "\t\tsubscription_types.idSubscriptionType," +
                                                                          "\n" +
                                                                          "\t\tsubscription_types" +
                                                                          ".nameSubscriptionType,\n" +
                                                                          "\t\tsubscription_types.monthAmount\n" +
                                                                          "\t\t\n" +
                                                                          "\t\tFROM subscription_variants\n" +
                                                                          "\t\t\tJOIN subscription_types ON " +
                                                                          "subscription_variants.idSubscriptionType =" +
                                                                          " subscription_types.idSubscriptionType\n" +
                                                                          "\t\t\tJOIN periodicals ON " +
                                                                          "subscription_variants.idPeriodical = " +
                                                                          "periodicals.idPeriodical\n" +
                                                                          "\t\t\tJOIN periodical_type ON periodicals" +
                                                                          ".idType = periodical_type.idType\n" +
                                                                          "\t\t\tJOIN periodical_theme ON periodicals" +
                                                                          ".idTheme = periodical_theme.idTheme\n" +
                                                                          "\t\t\tWHERE subscription_variants" +
                                                                          ".idSubscriptionVariant = %1$d";

    private final static String QUERY_PATTERN_SELECT_SUBSCRIPTION_VARIANT_BY_ID = "SELECT \tsubscription_variants" +
                                                                          ".idSubscriptionVariant,\n" +
                                                                          "\t\tsubscription_variants" +
                                                                          ".indexSubscription,\n" +
                                                                          "\t\tsubscription_variants.costForIssue,\n" +
                                                                          "\t\tperiodicals.idPeriodical,\n" +
                                                                          "\t\tperiodicals.namePeriodical,\n" +
                                                                          "\t\tperiodicals.periodicityInMonth,\n" +
                                                                          "\t\tperiodicals.annotation,\n" +
                                                                          "\t\tperiodicals.imagePath,\n" +
                                                                          "\t\tperiodical_type.idType,\n" +
                                                                          "\t\tperiodical_type.typeName,\n" +
                                                                          "\t\tperiodical_theme.idTheme,\n" +
                                                                          "\t\tperiodical_theme.nameTheme,\t\t\n" +
                                                                          "\t\tsubscription_types.idSubscriptionType," +
                                                                          "\n" +
                                                                          "\t\tsubscription_types" +
                                                                          ".nameSubscriptionType,\n" +
                                                                          "\t\tsubscription_types.monthAmount\n" +
                                                                          "\t\t\n" +
                                                                          "\t\tFROM subscription_variants\n" +
                                                                          "\t\t\tJOIN subscription_types ON " +
                                                                          "subscription_variants.idSubscriptionType =" +
                                                                          " subscription_types.idSubscriptionType\n" +
                                                                          "\t\t\tJOIN periodicals ON " +
                                                                          "subscription_variants.idPeriodical = " +
                                                                          "periodicals.idPeriodical\n" +
                                                                          "\t\t\tJOIN periodical_type ON periodicals" +
                                                                          ".idType = periodical_type.idType\n" +
                                                                          "\t\t\tJOIN periodical_theme ON periodicals" +
                                                                          ".idTheme = periodical_theme.idTheme\n" +
                                                                          "\t\t\tWHERE subscription_variants" +
                                                                          ".idSubscriptionVariant = ?";

    private final static String QUERY_PATTERN_INSERT_SUBSCRIPTION_VARIANT = "INSERT INTO subscription_variants (indexSubscription, idPeriodical, idSubscriptionType, costForIssue) values(?, ?, ?, ?)";
    private final static String QUERY_SELECT_SUBSCRIPTION_VARIANT_FOR_PERIODICAL = "SELECT \tsubscription_variants" +
                                                                                   ".idSubscriptionVariant,\n" +
                                                                                   "\t\tsubscription_variants" +
                                                                                   ".indexSubscription,\n" +
                                                                                   "\t\tsubscription_variants" +
                                                                                   ".costForIssue,\n" +
                                                                                   "\t\tsubscription_types" +
                                                                                   ".idSubscriptionType,\n" +
                                                                                   "\t\tsubscription_types" +
                                                                                   ".nameSubscriptionType,\n" +
                                                                                   "\t\tsubscription_types" +
                                                                                   ".monthAmount\n" +
                                                                                   "\t\t\n" +
                                                                                   "\t\tFROM subscription_variants\n" +
                                                                                   "\t\t\tJOIN subscription_types ON " +
                                                                                   "subscription_variants" +
                                                                                   ".idSubscriptionType = " +
                                                                                   "subscription_types" +
                                                                                   ".idSubscriptionType\n" +
                                                                                   "\t\t\tWHERE subscription_variants" +
                                                                                   ".idPeriodical = %1$d";


    private final static String QUERY_INSERT_SUBSCRIPTION_VARIANT = "";
    private final static String QUERY_DELETE_SUBSCRIPTION_VARIANT = "";



    public MySqlSubscriptionVariantDAO(DataSourceConnector connector,
                                       TransactionManager transactionManager,
                                       SQLExecutor sqlExecutor,
                                       ResultHandlerRepository resultHandlerRepository) {
        super(connector, transactionManager, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public void addSubscriptionVariantsTransactionaly(List<SubscriptionVariant> subscriptionVariants) throws DAOException {
        Iterator<SubscriptionVariant> subscriptionVariantIterator = subscriptionVariants.iterator();

        PreparedStatement preparedStatement = super.getPreparedStatement(QUERY_PATTERN_INSERT_SUBSCRIPTION_VARIANT ,TransactionStatus.ON);

        while (subscriptionVariantIterator.hasNext()) {
            try {
                SubscriptionVariant subscriptionVariant = subscriptionVariantIterator.next();
                preparedStatement.setString(1, subscriptionVariant.getIndex());
                preparedStatement.setInt(2, subscriptionVariant.getPeriodical().getId());
                preparedStatement.setInt(3, subscriptionVariant.getSubscriptionType().getId());
                preparedStatement.setDouble(4, subscriptionVariant.getCostForIssue());
            } catch (SQLException e) {
                throw new DAOException("Setting prepared statement parameters is failed!", e);
            }

            super.executeUpdateDataSource(preparedStatement, TransactionStatus.ON);
        }

        super.finalizeExecution(preparedStatement, TransactionStatus.END);
    }

    @Override
    public List<SubscriptionVariant> getAllSubscriptionVariantsForPeriodical(int idPeriodical) throws DAOException {
        String query = String.format(QUERY_SELECT_SUBSCRIPTION_VARIANT_FOR_PERIODICAL, idPeriodical);

        return (List<SubscriptionVariant>) super.executeSelectFromDataSource(query, HandlerType.SIMPLE_SUBSCRIPTION_VARIANT, TransactionStatus.OFF);
    }

    @Override
    public SubscriptionVariant getSubscriptionVariantById(int idSubscriptionVariant) throws DAOException {
        String query = String.format(QUERY_SELECT_SUBSCRIPTION_VARIANT_BY_ID, idSubscriptionVariant);

        return (SubscriptionVariant) super.executeSelectFromDataSource(query, HandlerType.SUBSCRIPTION_VARIANT_BY_ID, TransactionStatus.OFF);
    }

    @Override
    public List<SubscriptionVariant> getSubscriptionVariantsByIdsTransactionaly(List<Integer> subscriptionVariantsIds) throws
                                                                                                         DAOException {
        List<SubscriptionVariant> subscriptionVariants = new ArrayList<>();

        Iterator<Integer> integerIterator = subscriptionVariantsIds.iterator();

        PreparedStatement preparedStatement = super.getPreparedStatement(QUERY_PATTERN_SELECT_SUBSCRIPTION_VARIANT_BY_ID ,TransactionStatus.ON);

        while (integerIterator.hasNext()) {
            try {
                Integer subscriptionVariantId = integerIterator.next();

                preparedStatement.setInt(1, subscriptionVariantId);

                SubscriptionVariant subscriptionVariant = (SubscriptionVariant) super.executeSelectFromDataSource(preparedStatement, HandlerType.SUBSCRIPTION_VARIANT_BY_ID, TransactionStatus.ON);

                subscriptionVariants.add(subscriptionVariant);
            } catch (SQLException e) {
                throw new DAOException("Setting prepared statement parameters is failed!", e);
            }
        }

        super.finalizeExecution(preparedStatement, TransactionStatus.END);

        return subscriptionVariants;
    }

    @Override
    public void deleteSubscriptionVariants(List<SubscriptionVariant> subscriptionVariants) throws DAOException {

    }

    @Override
    public void addSubscriptionVariant(SubscriptionVariant subscriptionVariant) throws DAOException {

    }

    @Override
    public void deleteSubscriptionVariant(SubscriptionVariant subscriptionVariant) throws DAOException {

    }
}
