package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.CommandRepository;
import by.training.zorich.controller.const_parameter.ActionType;
import by.training.zorich.service.factory.ServiceFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandRepositoryImpl implements CommandRepository {
    private Map<ActionType, CommandHandler> actionRepository;

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
        actionRepository.put(ActionType.SIGN_IN, new SignInCommandHandler(serviceFactory));
        actionRepository.put(ActionType.SIGN_UP, new SignUpCommandHandler(serviceFactory));
    }

    @Override
    public CommandHandler getCommandHandler(ActionType actionType) {
        return actionRepository.get(actionType);
    }
}
