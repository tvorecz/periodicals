package by.training.zorich.dal.sql_executor.impl.statement_filler;

import by.training.zorich.dal.sql_executor.PreparedStatementFillerType;
import by.training.zorich.dal.sql_executor.PreparedStatementFiller;
import by.training.zorich.dal.sql_executor.PreparedStatementFillerRepository;

import java.util.HashMap;
import java.util.Map;

public class PreparedStatementFillerRepositoryImpl implements PreparedStatementFillerRepository {
    private final Map<PreparedStatementFillerType, PreparedStatementFiller> repository = new HashMap<>();

    private PreparedStatementFillerRepositoryImpl() {

    }

    private static class PreparedStatementFillerRepositoryHelper {
        private final static PreparedStatementFillerRepositoryImpl REPOSITORY = new PreparedStatementFillerRepositoryImpl();
    }

    public static PreparedStatementFillerRepository getInstance() {
        return PreparedStatementFillerRepositoryHelper.REPOSITORY;
    }

    @Override
    public PreparedStatementFiller getFiller(PreparedStatementFillerType type) {
        return repository.get(type);
    }
}
