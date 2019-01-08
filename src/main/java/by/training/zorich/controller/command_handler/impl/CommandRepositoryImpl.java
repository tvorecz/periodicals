package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.CommandRepository;
import by.training.zorich.controller.HandlerType;
import by.training.zorich.service.factory.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandRepositoryImpl implements CommandRepository {
    private Map<HandlerType, CommandHandler> actionRepository;

    private CommandRepositoryImpl() {
        actionRepository = new HashMap<>();
    }

    private static class CommandRepositoryHelper{
        private static final CommandRepositoryImpl COMMAND_REPOSITORY = new CommandRepositoryImpl();
    }

    public static CommandRepositoryImpl getInstance() {
        return CommandRepositoryHelper.COMMAND_REPOSITORY;
    }

    public void init(ServiceFactory serviceFactory) {
        actionRepository.put(HandlerType.LOGIN, new SignInCommandHandler(serviceFactory));
        actionRepository.put(HandlerType.REGISTER, new SignUpCommandHandler(serviceFactory));
        actionRepository.put(HandlerType.LOCALE, new ChangeLocaleCommandHandler(serviceFactory, JspRepositoryImpl.getInstance()));
        actionRepository.put(HandlerType.PREPROCESS_ADD, new PeriodicalAdditionPageHandler(serviceFactory));
        actionRepository.put(HandlerType.URI_ADD_PERIODICAL, new AdditionPeriodicalCommandHandler(serviceFactory));
        actionRepository.put(HandlerType.PREPROCESS_CARD, new PeriodicalCardPageHandler(serviceFactory));
        actionRepository.put(HandlerType.ADD_TO_CART, new AddingItemToCartCommandHandler(serviceFactory));
        actionRepository.put(HandlerType.PREPROCESS_CART, new SubscriberCartPageHandler(serviceFactory));
        actionRepository.put(HandlerType.DELETE_FROM_CART, new DeleteCartItemCommandHandler(serviceFactory));
        actionRepository.put(HandlerType.SUBSCRIBE, new SubscriptionCommandHandler(serviceFactory));

    }

    @Override
    public CommandHandler getCommandHandler(HandlerType handlerType) {
        return actionRepository.get(handlerType);
    }
}
