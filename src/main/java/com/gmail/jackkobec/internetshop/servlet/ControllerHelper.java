package com.gmail.jackkobec.internetshop.servlet;



import com.gmail.jackkobec.internetshop.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>ControllerHelper class for obtain current command from request.
 * Created by Jack on 28.12.2016.
 */
public class ControllerHelper {

    private static ControllerHelper controllerHelper;

    HashMap<String, ICommand> commands = new HashMap<>();

    /**
     * Init map with all possible commands.
     */
    private ControllerHelper() {
        commands.put("register", new RegisterCommand());
        commands.put("find", new UserInfoCommand());
        commands.put("userregistration", new UserRegistrtionCommand());

    }

    /**
     * Method for get ControllerHelper instance.
     *
     * @return ControllerHelper instance.
     */
    public static ControllerHelper getControllerHelper() {

        if (controllerHelper == null) {
            controllerHelper = new ControllerHelper();
        }

        return controllerHelper;
    }

    /**
     * Method for return current command from request.
     *
     * @param request
     * @return current command for execute.
     */
    public ICommand getCommand(HttpServletRequest request) {

        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new NoCommand();
        }

        return command;
    }
}
 