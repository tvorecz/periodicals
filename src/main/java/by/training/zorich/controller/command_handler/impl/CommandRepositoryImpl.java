package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.CommandRepository;
import by.training.zorich.controller.CommandType;
import by.training.zorich.service.factory.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandRepositoryImpl implements CommandRepository {
    private Map<CommandType, CommandHandler> actionRepository;

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
        actionRepository.put(CommandType.LOGIN, new SignInCommandHandler(serviceFactory));
        actionRepository.put(CommandType.REGISTER, new SignUpCommandHandler(serviceFactory));
        actionRepository.put(CommandType.LOCALE, new ChangeLocaleHandler(serviceFactory, JspRepositoryImpl.getInstance()));
    }

    @Override
    public CommandHandler getCommandHandler(CommandType commandType) {
        return actionRepository.get(commandType);
    }
}
