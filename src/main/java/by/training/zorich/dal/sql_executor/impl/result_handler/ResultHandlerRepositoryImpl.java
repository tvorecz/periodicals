package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandler;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

import java.util.HashMap;
import java.util.Map;

public class ResultHandlerRepositoryImpl implements ResultHandlerRepository {
    private final Map<HandlerType, ResultHandler> repository = new HashMap<>();

    private ResultHandlerRepositoryImpl() {
        repository.put(HandlerType.ID_USER_ROLE_HANDLER, new IdUserRoleResultHandler());
        repository.put(HandlerType.USER_HANDLER, new UserResultHandler());
        repository.put(HandlerType.VALIDATE_USER_HANDLER, new UserValidateResultHandler());
        repository.put(HandlerType.USER_ADDRESS_HANDLER, new UserAddressResultHandler());
        repository.put(HandlerType.USER_SUBSCRIPTION_HANDLER, new UserSubscriptionHandler());
        repository.put(HandlerType.LAST_INSERTED_PAYMENT_ID, new LastIdResultHandler());
        repository.put(HandlerType.SELECT_PAYMENT_BY_ID, new PaymentResultHandler());

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
