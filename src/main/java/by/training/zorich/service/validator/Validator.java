package by.training.zorich.service.validator;

import by.training.zorich.service.exception.ServiceException;

public interface Validator<T> {
    boolean validate(T objectForValidation) throws ServiceException;
}
