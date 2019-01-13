/**
 * Handler for adding periodical to data source.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.*;
import by.training.zorich.controller.JspPagePath;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.exception.CommandException;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class AdditionPeriodicalCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(AdditionPeriodicalCommandHandler.class);
    private final static String PERIODICAL_PAGE_SUCCESS = "/periodical/%1$d?message=dbSuccess";
    private final static String ADD_PAGE_ERROR = "/admin/add?message=dbError";
    private final static String TEMP_FILES = "/WEB-INF/temp";
    private final static String FULL_PATH_IMAGE_CATALOG = "%1$s%2$s%3$s";
    private final static String PATH_IMAGE_CATALOG_FOR_DB = "/images/%1$s";
    private final static String EMPTY_STRING = "";
    private final static String UTF8 = "UTF-8";
    private final static String PATH_IMAGE_ATTRIBUTE = "pathToImages";

    private ServiceFactory serviceFactory;
    private DiskFileItemFactory diskFileItemFactory;

    public AdditionPeriodicalCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        diskFileItemFactory = new DiskFileItemFactory(5242880, new File(TEMP_FILES));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        RequestParsingResult requestParsingResult = null;
        Periodical periodical = null;
        List<SubscriptionVariant> subscriptionVariants = null;

        try {
            requestParsingResult = handleMultipartRequest(request);
        } catch (FileUploadException e) {
            LOGGER.error(e);
            response.sendRedirect(ADD_PAGE_ERROR);
        }

        if(requestParsingResult.getParameterMap().get(PeriodicalCharacteristic.IMAGE.getName()) != null && !requestParsingResult.getParameterMap().get(PeriodicalCharacteristic.IMAGE.getName()).equals(EMPTY_STRING)) {
            String fullImagePath = String.format(FULL_PATH_IMAGE_CATALOG,
                                                 request.getAttribute(PATH_IMAGE_ATTRIBUTE),
                                                 File.separator,
                                                 requestParsingResult.getParameterMap().get(PeriodicalCharacteristic.IMAGE.getName()));
            String dbImagePath = String.format(PATH_IMAGE_CATALOG_FOR_DB,
                                               requestParsingResult.getParameterMap().get(PeriodicalCharacteristic.IMAGE.getName()));
            requestParsingResult.getParameterMap().put(PeriodicalCharacteristic.IMAGE.getName(), dbImagePath);

            try {
                periodical = handleMapForPeriodical(requestParsingResult.getParameterMap());
                subscriptionVariants = handleMapForSubscriptionVariants(requestParsingResult.getParameterMap(),
                                                                        requestParsingResult.getSubscriptionTypesIds());
            } catch (NumberFormatException e) {
                LOGGER.error(e);
                request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
            }

            if(subscriptionVariants == null || subscriptionVariants.size() == 0) {
                deleteImageFile(fullImagePath);
                response.sendRedirect(ADD_PAGE_ERROR);
                return;
            }

            ServiceResult serviceResult = new ServiceResult();

            try {
                saveImageFile(requestParsingResult.getFile(), fullImagePath);

                serviceFactory.getPeriodicalService().addNewPeriodical(periodical, serviceResult);

                if (serviceResult.isDone()) {
                    Integer newPeriodicalId = (Integer) serviceResult.getResultObject();

                    serviceResult.clear();
                    subscriptionVariants.get(0).getPeriodical().setId(newPeriodicalId);

                    serviceFactory.getSubscriptionService().addSubscriptionVariants(subscriptionVariants, serviceResult);

                    if (serviceResult.isDone()) {
                        response.sendRedirect(String.format(PERIODICAL_PAGE_SUCCESS, newPeriodicalId));
                    } else {
                        deleteImageFile(fullImagePath);
                        response.sendRedirect(ADD_PAGE_ERROR);
                    }
                } else {
                    deleteImageFile(fullImagePath);
                    response.sendRedirect(ADD_PAGE_ERROR);
                }
            } catch (ServiceException e) {
                LOGGER.error(e);
                deleteImageFile(fullImagePath);
                response.sendRedirect(ADD_PAGE_ERROR);
            }
        } else {
            response.sendRedirect(ADD_PAGE_ERROR);
        }
    }

    private RequestParsingResult handleMultipartRequest(HttpServletRequest request) throws
                                                                                    FileUploadException,
                                                                                    UnsupportedEncodingException {
        RequestParsingResult result = new RequestParsingResult();
        Map<String, String> parameterMap = new HashMap<>();
        List<Integer> subscriptionTypesIds = new ArrayList<>();

        result.setParameterMap(parameterMap);
        result.setSubscriptionTypesIds(subscriptionTypesIds);

        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        List<FileItem> fileItems = servletFileUpload.parseRequest(request);

        Iterator<FileItem> iterator = fileItems.iterator();

        while (iterator.hasNext()) {
            FileItem fileItem = iterator.next();

            if (fileItem.isFormField()) {

                parameterMap.put(fileItem.getFieldName(), fileItem.getString(UTF8));

                if (fileItem.getFieldName().contains(SubscriptionVariantCharacteristic.ID.getName())) {
                    String id = fileItem.getFieldName().replace(SubscriptionVariantCharacteristic.ID.getName(),
                                                                EMPTY_STRING);
                    subscriptionTypesIds.add(Integer.parseInt(id));
                }
            } else {
                parameterMap.put(PeriodicalCharacteristic.IMAGE.getName(), fileItem.getName());
                result.setFile(fileItem);
            }
        }

        return result;
    }

    private Periodical handleMapForPeriodical(Map<String, String> parameterMap) {
        PeriodicalType periodicalType = null;
        PeriodicalTheme periodicalTheme = null;

        periodicalType = new PeriodicalType();
        periodicalType.setId(Integer.parseInt(parameterMap.get(PeriodicalTypeCharacteristic.ID.getName())));
        periodicalTheme = new PeriodicalTheme();
        periodicalTheme.setId(Integer.parseInt(parameterMap.get(PeriodicalThemeCharacteristic.ID.getName())));

        Periodical periodical = new Periodical();

        periodical.setType(periodicalType);
        periodical.setTheme(periodicalTheme);
        periodical.setImagePath(parameterMap.get(PeriodicalCharacteristic.IMAGE.getName()));
        periodical.setName(parameterMap.get(PeriodicalCharacteristic.NAME.getName()));
        periodical.setAnnotation(parameterMap.get(PeriodicalCharacteristic.ANNOTATION.getName()));
        periodical.setPeriodicityInMonth(Integer.parseInt(parameterMap.get(PeriodicalCharacteristic.PERIODICITY.getName())));

        return periodical;
    }

    private List<SubscriptionVariant> handleMapForSubscriptionVariants(Map<String, String> parameterMap,
                                                                       List<Integer> subscriptionTypesIds) {
        List<SubscriptionVariant> subscriptionVariants = new ArrayList<>();
        Periodical periodical = new Periodical();

        for (Integer subscriptionTypesId : subscriptionTypesIds) {
            SubscriptionVariant subscriptionVariant = new SubscriptionVariant();
            subscriptionVariant.setIndex(parameterMap.get(SubscriptionVariantCharacteristic.INDEX.getName() + subscriptionTypesId));
            subscriptionVariant.setCostForIssue(Double.parseDouble((parameterMap.get(SubscriptionVariantCharacteristic.COST.getName() + subscriptionTypesId)).replace(
                    ',',
                    '.')));

            SubscriptionType subscriptionType = new SubscriptionType();
            subscriptionType.setId(subscriptionTypesId);

            subscriptionVariant.setSubscriptionType(subscriptionType);
            subscriptionVariant.setPeriodical(periodical);

            subscriptionVariants.add(subscriptionVariant);
        }

        return subscriptionVariants;
    }

    private void saveImageFile(FileItem file, String filePath) throws IOException {
        byte[] data = file.get();

        FileOutputStream fileOutputStream = null;

        fileOutputStream = new FileOutputStream(new File(filePath));
        fileOutputStream.write(data);
        fileOutputStream.close();
    }

    private boolean deleteImageFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }
}

class RequestParsingResult {
    private Map<String, String> parameterMap;
    private FileItem file;
    private List<Integer> subscriptionTypesIds;

    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public FileItem getFile() {
        return file;
    }

    public void setFile(FileItem file) {
        this.file = file;
    }

    public List<Integer> getSubscriptionTypesIds() {
        return subscriptionTypesIds;
    }

    public void setSubscriptionTypesIds(List<Integer> subscriptionTypesIds) {
        this.subscriptionTypesIds = subscriptionTypesIds;
    }
}
