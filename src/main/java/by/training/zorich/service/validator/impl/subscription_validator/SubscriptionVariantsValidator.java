/**
 * Validate subscription variant during adding new periodical.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */
package by.training.zorich.service.validator.impl.subscription_validator;

import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubscriptionVariantsValidator implements Validator<SubscriptionVariant> {
    private static final String INDEX_PATTERN = "([0-9]{3,})";
    private Pattern indexPattern;

    public SubscriptionVariantsValidator() {
        indexPattern = Pattern.compile(INDEX_PATTERN);
    }

    @Override
    public boolean validate(SubscriptionVariant objectForValidation) throws ServiceException {
        return (validateIndex(objectForValidation.getIndex()) && validateCost(objectForValidation.getCostForIssue()));
    }

    private boolean validateIndex(String index) {
        Matcher matcher = indexPattern.matcher(index);

        if(matcher.find()) {
            if (matcher.group().equals(index)) {
                return true;
            }
        }

        return false;
    }

    private boolean validateCost(double cost) {
        if(cost <= 0.0) {
            return false;
        }

        return true;
    }
}
