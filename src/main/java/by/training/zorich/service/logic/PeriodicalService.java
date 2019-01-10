package by.training.zorich.service.logic;

import by.training.zorich.bean.Periodical;
import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.service.exception.ServiceException;
import org.apache.commons.fileupload.FileItem;

import java.util.List;

public interface PeriodicalService {
    void addNewPeriodical(Periodical periodical, ServiceResult serviceResult) throws ServiceException;

    void getPeriodicalById(int idPeriodical, ServiceResult serviceResult) throws ServiceException;

    void getAllPeriodicalTypes(ServiceResult serviceResult) throws ServiceException;

    void getAllPeriodicalThemes(ServiceResult serviceResult) throws ServiceException;

    void search(String keySearch,
                Integer periodicalTypeId,
                Integer periodicalThemeId,
                Integer subscriptionTypeId,
                Integer amountOnPage,
                Integer page,
                ServiceResult serviceResult) throws ServiceException;

    void getCountOfFoundPeriodicals(ServiceResult serviceResult) throws ServiceException;
}
