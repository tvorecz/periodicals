package by.training.zorich.dal.dao.SearchCriteria.impl;

import by.training.zorich.dal.dao.SearchCriteria.SearchCriteria;

public class PeriodicalSearchCriteria implements SearchCriteria {
    private final static String QUERY_BASE = "SELECT \tperiodicals.idPeriodical,\n" +
                                             "\t\tperiodicals.namePeriodical,\n" +
                                             "\t\tperiodicals.periodicityInMonth,\n" +
                                             "\t\tperiodicals.annotation,\n" +
                                             "\t\tperiodicals.imagePath,\n" +
                                             "\t\tperiodical_theme.nameTheme,\n" +
                                             "\t\tperiodical_type.typeName\n" +
                                             "\t\t\n" +
                                             "\t\tFROM periodicals\n" +
                                             "\t\t\tJOIN periodical_type ON periodicals.idType = periodical_type" +
                                             ".idType\n" +
                                             "\t\t\tJOIN periodical_theme ON periodicals.idTheme = periodical_theme" +
                                             ".idTheme";

    private final static String QUERY_WHERE = " WHERE  ";
    private final static String QUERY_AND = " AND ";
    private final static String QUERY_PERIODICAL_CONTENT = "(periodicals.namePeriodical LIKE '\\%%1$s\\%' OR " +
                                                           "periodicals.annotation LIKE '\\%%2$s\\%')";
    private final static String QUERY_PERIODICAL_TYPE = "periodical_type.typeName LIKE '\\%%1$s\\%'";
    private final static String QUERY_PERIODICAL_THEME = "periodical_theme.nameTheme LIKE '\\%%1$s\\%'";
    private final static String QUERY_LIMIT_RECORDS = " LIMIT %1$d, %2$d";


    private String searchKey;
    private String typeName;
    private String themeName;
    private Integer beginOfRange;
    private Integer countOfRecords;
    private boolean additionalCriteria;

    public PeriodicalSearchCriteria() {
        beginOfRange = 1;
        countOfRecords = 10;
    }

    public void setSearchKey(String searchKey) {
        additionalCriteria = true;
        this.searchKey = searchKey;
    }

    public void setTypeName(String typeName) {
        additionalCriteria = true;
        this.typeName = typeName;
    }

    public void setThemeName(String themeName) {
        additionalCriteria = true;
        this.themeName = themeName;
    }

    public void setBeginOfRange(Integer beginOfRange) {
        this.beginOfRange = beginOfRange;
    }

    public void setCountOfRecords(Integer countOfRecords) {
        this.countOfRecords = countOfRecords;
    }

    @Override
    public String createQuery() {
        StringBuffer query = new StringBuffer(QUERY_BASE);

        if(additionalCriteria) {
            query.append(QUERY_WHERE);

            boolean hasFirstElement = false;

            if(typeName != null) {
                query.append(String.format(QUERY_PERIODICAL_TYPE, typeName));
                hasFirstElement = true;
            }

            if(themeName != null) {
                if(hasFirstElement) {
                    query.append(QUERY_AND);
                }

                query.append(String.format(QUERY_PERIODICAL_THEME, themeName));
                hasFirstElement = true;
            }

            if(searchKey != null) {
                if(hasFirstElement) {
                    query.append(QUERY_AND);
                }

                query.append(String.format(QUERY_PERIODICAL_CONTENT, searchKey, searchKey));
            }
        }

        query.append(String.format(QUERY_LIMIT_RECORDS, beginOfRange, countOfRecords));

        return query.toString();
    }
}
