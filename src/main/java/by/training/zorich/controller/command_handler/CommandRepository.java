package by.training.zorich.controller.command_handler;

import by.training.zorich.controller.HandlerType;
import by.training.zorich.service.factory.ServiceFactory;

public interface CommandRepository {
    void init(ServiceFactory serviceFactory);

    CommandHandler getCommandHandler(HandlerType handlerType);
}
