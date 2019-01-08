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
        repository.put(HandlerType.VALIDATE_HANDLER, new ValidateResultHandler());
        repository.put(HandlerType.USER_ADDRESS_HANDLER, new UserAddressResultHandler());
        repository.put(HandlerType.USER_SUBSCRIPTION_HANDLER, new UserSubscriptionHandler());
        repository.put(HandlerType.LAST_INSERTED_ITEM_ID, new LastIdResultHandler());
        repository.put(HandlerType.SELECT_PAYMENT_BY_ID, new PaymentResultHandler());
        repository.put(HandlerType.SUBSCRIPTION_VARIANT_BY_ID, new SubscriptionVariantHandler());
        repository.put(HandlerType.ALL_PERIODICAL_TYPES, new PeriodicalTypeResultHandler());
        repository.put(HandlerType.ALL_PERIODICAL_THEMES, new PeriodicalThemeResultHandler());
        repository.put(HandlerType.ALL_SUBSCRIPTION_TYPES, new SubscriptionTypeResultHandler());
        repository.put(HandlerType.PERIODICAL_BY_ID, new PeriodicalResultHandler());
        repository.put(HandlerType.SIMPLE_SUBSCRIPTION_VARIANT, new SimpleSubscriptionVariantHandler());

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
