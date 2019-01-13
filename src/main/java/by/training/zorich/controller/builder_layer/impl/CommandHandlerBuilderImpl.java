/**
 * Class for building and initialization all program layers by chain. Using in MainController.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.controller.builder_layer.impl;

import by.training.zorich.controller.builder_layer.CommandHandlerBuilder;
import by.training.zorich.controller.command_handler.CommandRepository;
import by.training.zorich.controller.command_handler.exception.CommandException;
import by.training.zorich.controller.command_handler.impl.CommandRepositoryImpl;
import by.training.zorich.controller.command_handler.impl.JspRepositoryImpl;
import by.training.zorich.controller.tag.MessageTag;
import by.training.zorich.service.builder_layer.ServiceLayerBuilder;
import by.training.zorich.service.builder_layer.impl.ServiceLayerBuilderImpl;
import by.training.zorich.service.exception.ServiceException;

public class CommandHandlerBuilderImpl implements CommandHandlerBuilder {


    /**
     * Method starts chain of initialisation all program layers.
     */
    @Override
    public CommandRepository build() throws CommandException {
        CommandRepository commandRepository = CommandRepositoryImpl.getInstance();

        ServiceLayerBuilder serviceLayerBuilder = new ServiceLayerBuilderImpl();

        try {
            commandRepository.init(serviceLayerBuilder.build());
        } catch (ServiceException e) {
            throw new CommandException("Error during building command repository.", e);
        }

        JspRepositoryImpl.getInstance().init(commandRepository);

        MessageTag.initMessageMap();

        return commandRepository;
    }
}
