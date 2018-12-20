package by.training.zorich.service.validator.impl.user_validator;

import by.training.zorich.bean.User;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator<User> {
    private static final String LOGIN_PATTERN = "([a-z\\.\\-0-9]{2,})";
    private Pattern loginPattern;

    public UserValidator() {
        loginPattern = Pattern.compile(LOGIN_PATTERN);
    }

    @Override
    public boolean validate(User objectForValidation) throws ServiceException {
        return validateLoginForm(objectForValidation.getLogin());
    }

    private boolean validateLoginForm(String login) {
        Matcher matcher = loginPattern.matcher(login);
        if(matcher.find()) {
            if(matcher.group().equals(login)) {
                return true;
            }
        }

        return false;
    }
}
