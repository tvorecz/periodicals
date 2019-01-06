package by.training.zorich.service.validator.impl.periodical_validator;

import by.training.zorich.bean.Periodical;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.validator.Validator;

public class PeriodicalValidator implements Validator<Periodical> {

    @Override
    public boolean validate(Periodical objectForValidation) throws ServiceException {
        return false;
    }
}
