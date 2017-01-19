package com.gmail.jackkobec.internetshop.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Jack on 19.01.2017.
 */
public class AlertTag extends TagSupport {

    private static final String ALERT_SUCCESS_CLASS = "alert alert-success";
    private static final String ALERT_DANGER_CLASS = "alert alert-danger";

    private String alertClass;
    private String alertMessage;

    public void setAlertClass(String alertClass) {
        this.alertClass = alertClass;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter jspWriter = pageContext.getOut();
        try {

            if (ALERT_SUCCESS_CLASS.equals(alertClass)) {

                jspWriter.print("<div class=" + "\"" + ALERT_SUCCESS_CLASS + "\"" + "role =\"alert\">\n" +
                        "<h1>" + alertMessage + "</h1>\n" +
                        "</div>");

            } else if (ALERT_DANGER_CLASS.equals(alertClass)) {

                jspWriter.print("<div class=" + "\"" + ALERT_DANGER_CLASS + "\"" + "role =\"alert\">\n" +
                        "<h1>" + alertMessage + "</h1>\n" +
                        "</div>");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return SKIP_BODY;
    }
}
