package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.*;
import by.training.zorich.controller.JspPagePath;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.exception.CommandException;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AdditionPeriodicalCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(AdditionPeriodicalCommandHandler.class);
    private final static String PERIODICAL_PAGE = "/periodical/%1$d?message=dbSuccess";
    private final static String TEMP_FILES = "/WEB-INF/temp";
    private final static String IMAGE_FILES = "/image%1$s%2$s";
    private ServiceFactory serviceFactory;
    private DiskFileItemFactory diskFileItemFactory;

    public AdditionPeriodicalCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        diskFileItemFactory = new DiskFileItemFactory(1048576, new File(TEMP_FILES));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {




    }

    private Map<String, String> handleRequestForFile(HttpServletRequest request) throws FileUploadException {
        Map<String, String> parameterMap = new HashMap<>();

        if(ServletFileUpload.isMultipartContent(request)) {
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

            List<FileItem> fileItems = servletFileUpload.parseRequest(request);

            Iterator<FileItem> iterator = fileItems.iterator();

            while (iterator.hasNext()) {
                FileItem fileItem = iterator.next();

                if(fileItem.isFormField()) {
                    parameterMap.put(fileItem.getFieldName(), fileItem.getString());
                } else {
                    String fileName = fileItem.getName();
                    long length = fileItem.getSize();
                    String type = fileItem.getContentType();

                    byte[] data = fileItem.get();

                    //файлу надо присваивать айди периодического издания
                    String filePath = String.format(IMAGE_FILES, File.separator, fileName);

                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(new File(filePath));
                        fileOutputStream.write(data);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }

        }

        return parameterMap;
    }

    private Periodical handleRequestForPeriodical(HttpServletRequest request, HttpServletResponse response, String imagePath) throws
                                                                                                                              ServletException,
                                                                                                                              IOException {
        PeriodicalType periodicalType = null;
        PeriodicalTheme periodicalTheme = null;

        try {
            periodicalType = new PeriodicalType();
            periodicalType.setId(Integer.parseInt(request.getParameter(PeriodicalTypeCharacteristic.ID.getName())));
            periodicalTheme = new PeriodicalTheme();
            periodicalTheme.setId(Integer.parseInt(request.getParameter(PeriodicalThemeCharacteristic.ID.getName())));

        } catch (NumberFormatException e) {
            LOGGER.error(e);
            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
        }

        Periodical periodical = new Periodical();

        periodical.setImagePath(imagePath);
        periodical.setType(periodicalType);
        periodical.setTheme(periodicalTheme);
        periodical.setName(request.getParameter(PeriodicalCharacteristic.NAME.getName()));
        periodical.setAnnotation(request.getParameter(PeriodicalCharacteristic.ANNOTATION.getName()));

        return periodical;
    }

    private List<SubscriptionVariant> handleRequestForSubscriptionVariants(HttpServletRequest request) {
        return null;
    }
}
