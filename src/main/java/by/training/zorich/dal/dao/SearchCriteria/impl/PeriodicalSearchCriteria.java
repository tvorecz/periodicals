/**
 * PeriodicalSearchCriteria gather the info about periodical and form string-query for searching periodicals in
 * database.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.dal.dao.SearchCriteria.impl;

import by.training.zorich.dal.dao.SearchCriteria.SearchCriteria;

public class PeriodicalSearchCriteria implements SearchCriteria {
    private final static String QUERY_BASE = "SELECT \tSQL_CALC_FOUND_ROWS DISTINCT\n" +
                                             "\t\tperiodicals.idPeriodical,\n" +
                                             "\t\tperiodicals.namePeriodical,\n" +
                                             "\t\tperiodicals.periodicityInMonth,\n" +
                                             "\t\tperiodicals.annotation,\n" +
                                             "\t\tperiodicals.imagePath,\n" +
                                             "\t\tperiodical_theme.idTheme,\n" +
                                             "\t\tperiodical_theme.nameTheme,\n" +
                                             "\t\tperiodical_type.idType,\n" +
                                             "\t\tperiodical_type.typeName\n" +
                                             "\t\t\n" +
                                             "\t\tFROM periodicals\n" +
                                             "\t\t\tJOIN periodical_type ON periodicals.idType = periodical_type" +
                                             ".idType\n" +
                                             "\t\t\tJOIN periodical_theme ON periodicals.idTheme = periodical_theme" +
                                             ".idTheme\n" +
                                             "\t\t\tJOIN subscription_variants ON subscription_variants.idPeriodical " +
                                             "= periodicals.idPeriodical\n" +
                                             "\t\t\tJOIN subscription_types ON subscription_variants" +
                                             ".idSubscriptionType = subscription_types.idSubscriptionType";

    private final static String QUERY_WHERE = " WHERE  ";
    private final static String QUERY_AND = " AND ";
    private final static String QUERY_PERIODICAL_CONTENT = "(periodicals.namePeriodical LIKE '\\%%1$s\\%' OR " +
                                                           "periodicals.annotation LIKE '\\%%2$s\\%')";
    private final static String QUERY_PERIODICAL_TYPE = "periodical_type.idType = %1$s";
    private final static String QUERY_PERIODICAL_THEME = "periodical_theme.idTheme = %1$s";
    private final static String QUERY_SUBSCRIPTION_TYPE = "subscription_types.idSubscriptionType = %1$s";

    private final static String QUERY_LIMIT_RECORDS = " ORDER BY periodicals.namePeriodical LIMIT %1$s, %2$s";


    private String searchKey;
    private Integer periodicalTypeId;
    private Integer periodicalThemeId;
    private Integer subscriptionTypeId;
    private Integer beginOfRange;
    private Integer countOfRecords;

    public PeriodicalSearchCriteria() {
        beginOfRange = 1;
        countOfRecords = 10;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public void setPeriodicalTypeId(Integer periodicalTypeId) {
        this.periodicalTypeId = periodicalTypeId;
    }

    public void setPeriodicalThemeId(Integer periodicalThemeId) {
        this.periodicalThemeId = periodicalThemeId;
    }

    public void setSubscriptionTypeId(Integer subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public void setBeginOfRange(Integer beginOfRange) {
        if (beginOfRange != null) {
            this.beginOfRange = beginOfRange - 1;
        }
    }

    public void setCountOfRecords(Integer countOfRecords) {
        this.countOfRecords = countOfRecords;
    }

    @Override
    public String createQuery() {
        StringBuffer query = new StringBuffer(QUERY_BASE);

        if (periodicalTypeId != null || periodicalThemeId != null || subscriptionTypeId != null || searchKey != null) {
            query.append(QUERY_WHERE);

            boolean hasFirstElement = false;

            if (searchKey != null) {
//                query.append(String.format(QUERY_PERIODICAL_CONTENT, searchKey, searchKey));

                query.append("(periodicals.namePeriodical LIKE '%");
                query.append(searchKey);
                query.append("%' OR periodicals.annotation LIKE '%");
                query.append(searchKey);
                query.append("%')");

                hasFirstElement = true;
            }

            if (appendIntegerToQuery(periodicalTypeId, query, hasFirstElement, QUERY_PERIODICAL_TYPE)) {
                hasFirstElement = true;
            }

            if (appendIntegerToQuery(periodicalThemeId, query, hasFirstElement, QUERY_PERIODICAL_THEME)) {
                hasFirstElement = true;
            }

            appendIntegerToQuery(subscriptionTypeId, query, hasFirstElement, QUERY_SUBSCRIPTION_TYPE);
        }

        query.append(String.format(QUERY_LIMIT_RECORDS, beginOfRange.toString(), countOfRecords.toString()));

        return query.toString();
    }

    private boolean appendIntegerToQuery(Integer integerForAppend,
                                         StringBuffer query,
                                         boolean hasFirstElement,
                                         String patternQuery) {
        if (integerForAppend != null) {
            if (hasFirstElement) {
                query.append(QUERY_AND);
            }

            query.append(String.format(patternQuery, integerForAppend.toString()));
            return true;
        }

        return false;
    }
}
