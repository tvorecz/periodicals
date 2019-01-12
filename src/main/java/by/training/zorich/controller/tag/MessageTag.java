package by.training.zorich.controller.tag;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageTag extends BodyTagSupport {
    private String messageType;

    private static final Map<String, String> MESSAGE_MAP = new HashMap<>();
    private static final Map<String, String> ALERT_MAP = new HashMap<>();

    public static void initMessageMap() {
        MESSAGE_MAP.put("nothingFound", "message.nothing");
        ALERT_MAP.put("nothingFound", "alert-info");

        MESSAGE_MAP.put("dbSuccess", "form.message.addToDB");
        ALERT_MAP.put("dbSuccess", "alert-success");
        MESSAGE_MAP.put("cartSuccess", "form.message.addToCart");
        ALERT_MAP.put("cartSuccess", "alert-success");
        MESSAGE_MAP.put("cartError", "form.message.cartError");
        ALERT_MAP.put("cartError", "alert-danger");

        MESSAGE_MAP.put("subscribeError", "form.message.subscribeError");
        ALERT_MAP.put("subscribeError", "alert-danger");
        MESSAGE_MAP.put("addAddressSuccess", "form.message.addAddressSuccess");
        ALERT_MAP.put("addAddressSuccess", "alert-success");
        MESSAGE_MAP.put("addAddressError", "form.message.addAddressError");
        ALERT_MAP.put("addAddressError", "alert-danger");

        MESSAGE_MAP.put("success", "form.message.finish");
        ALERT_MAP.put("success", "alert-success");
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if(messageType != null && MESSAGE_MAP.containsKey(messageType)) {
                JspWriter out = pageContext.getOut();
                out.write(formOpenOutputString());

                ServletRequest servletRequest = pageContext.getRequest();
                servletRequest.setAttribute("messageForUser", MESSAGE_MAP.get(messageType));

                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }

        } catch (IOException e) {
            throw new JspException("Recording info to jsp-page is failed!", e);
        }
    }


    @Override
    public int doAfterBody() throws JspException {
        try {
            if(messageType != null && MESSAGE_MAP.containsKey(messageType)) {
                JspWriter out = pageContext.getOut();
                out.write(formCloseOutputString());
            }

            return EVAL_PAGE;
        } catch (IOException e) {
            throw new JspException("Recording info to jsp-page is failed!", e);
        }
    }

    private String formOpenOutputString() {
        StringBuffer buffer = new StringBuffer("<div class=\"alert ");
        buffer.append(ALERT_MAP.get(messageType));
        buffer.append(" role=\"alert\">\n<strong>");

        return buffer.toString();
    }

    private String formCloseOutputString() {
        StringBuffer buffer = new StringBuffer("</strong><br />\n</div>");
        return buffer.toString();
    }
}
