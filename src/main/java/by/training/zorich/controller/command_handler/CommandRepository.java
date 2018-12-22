package by.training.zorich.controller.command_handler;

import by.training.zorich.controller.CommandType;
import by.training.zorich.service.factory.ServiceFactory;

public interface CommandRepository {
    public void init(ServiceFactory serviceFactory);

    CommandHandler getCommandHandler(CommandType commandType);
}
