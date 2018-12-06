package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandler;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.impl.result_handler.user_handler.UserResultHandler;
import by.training.zorich.dal.sql_executor.impl.result_handler.user_handler.UserValidateResultHandler;
import by.training.zorich.dal.sql_executor.impl.result_handler.user_roles_handler.IdUserRoleResultHandler;

import java.util.HashMap;
import java.util.Map;

public class ResultHandlerRepositoryImpl implements ResultHandlerRepository {
    private final Map<HandlerType, ResultHandler> repository = new HashMap<>();

    private ResultHandlerRepositoryImpl() {
        repository.put(HandlerType.ID_USER_ROLE_HANDLER, new IdUserRoleResultHandler());
        repository.put(HandlerType.USER_HANDLER, new UserResultHandler());
        repository.put(HandlerType.VALIDATE_USER_HANDLER, new UserValidateResultHandler());
    }

    private static class ResultHandlerRepositoryHelper {
        private final static ResultHandlerRepositoryImpl REPOSITORY = new ResultHandlerRepositoryImpl();
    }

    public static ResultHandlerRepositoryImpl getInstance() {
        return ResultHandlerRepositoryHelper.REPOSITORY;
    }

    @Override
    public ResultHandler getResultHandler(HandlerType handlerType) {
        return repository.get(handlerType);
    }
}
