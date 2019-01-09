package by.training.zorich.service.validator.impl.user_validator;

import by.training.zorich.bean.UserAddress;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAddressValidator implements Validator<UserAddress> {
    private static final String ADDRESS_PATTERN = "((\\d+,)([\\D]+,[\\D]+,)([\\D]+)([\\d|\\D]+))";
    private Pattern loginPattern;

    public UserAddressValidator() {
        loginPattern = Pattern.compile(ADDRESS_PATTERN);
    }

    @Override
    public boolean validate(UserAddress objectForValidation) throws ServiceException {
        Matcher matcher = loginPattern.matcher(objectForValidation.getAddress());

        if(matcher.find() && matcher.group().equals(objectForValidation.getAddress())) {
            return true;
        }

        return false;
    }
}
