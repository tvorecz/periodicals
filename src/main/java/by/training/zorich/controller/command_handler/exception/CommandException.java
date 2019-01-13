package by.training.zorich.controller.command_handler.exception;

public class CommandException extends Exception {
    private static final long serialVersionUID = -3127265711923199012L;

    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Exception exeption) {
        super(message, exeption);
    }

    public CommandException(Exception exeption) {
        super(exeption);
    }
}
