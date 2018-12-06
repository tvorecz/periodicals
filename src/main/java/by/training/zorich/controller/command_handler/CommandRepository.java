package by.training.zorich.controller.command_handler;

import by.training.zorich.controller.const_parameter.ActionType;

public interface CommandRepository {
    CommandHandler getCommandHandler(ActionType actionType);
}
