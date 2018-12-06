package by.training.zorich.controller.builder_layer;

import by.training.zorich.controller.command_handler.CommandRepository;
import by.training.zorich.controller.command_handler.exception.CommandException;

public interface CommandHandlerBuilder {
    public CommandRepository build() throws CommandException;
}
