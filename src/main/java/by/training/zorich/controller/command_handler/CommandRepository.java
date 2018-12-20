package by.training.zorich.controller.command_handler;

import by.training.zorich.controller.ActionType;
import by.training.zorich.service.factory.ServiceFactory;

public interface CommandRepository {
    public void init(ServiceFactory serviceFactory);

    CommandHandler getCommandHandler(ActionType actionType);
}
