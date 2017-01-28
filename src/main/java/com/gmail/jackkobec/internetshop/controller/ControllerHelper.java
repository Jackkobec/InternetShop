package com.gmail.jackkobec.internetshop.controller;



import com.gmail.jackkobec.internetshop.commands.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>ControllerHelper class for obtain current command from request.
 * Created by Jack on 28.12.2016.
 */
public class ControllerHelper {
    public static final Logger LOGGER = LogManager.getLogger(ControllerHelper.class);

    private static ControllerHelper controllerHelper;
    private HashMap<String, ICommand> commands = new HashMap<>();

    /**
     * Init map with all possible commands.
     */
    private ControllerHelper() {
        commands.put("userlogin", new UserLoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("find", new UserInfoCommand());
        commands.put("gotocategory", new GoCategoryCommand());
        commands.put("gotomainpage", new GoToMainPageCommand());
        commands.put("showitem", new ShowItemCommand());
        commands.put("addtocart", new AddToCartCommand());
        commands.put("removeitemfromcart", new RemoveItemFromCartCommand());

        commands.put("makeorder", new MakeOrderCommand());
        commands.put("removeitemfromorder", new RemoveItemFromOrderCommand());
        commands.put("cancelorder", new CancelOrderCommand());
        commands.put("payorder", new PayOrderCommand());
        commands.put("editorder", new EditOrderCommand());
        commands.put("confirmpayment", new ConfirmPaymentCommand());
        commands.put("removeallnotpaidorders", new RemoveAllNotPaidOrders());

        commands.put("gotousermanagementpage", new GoToUserManagementPageCommand());
        commands.put("gotoitemmanagementpage", new GoToItemManagementPage());

        commands.put("edititem", new EditItemCommand());
        commands.put("updateitem", new UpdateItemCommand());
        commands.put("additem", new AddNewItemCommand());
        commands.put("deleteitem", new DeleteItemCommand());

        commands.put("addusertoblocklist", new AddUserToBlockList());
        commands.put("removeuserfromblocklist", new RemoveUserFromBlockList());
        commands.put("userregistration", new UserRegistrationCommand());
        commands.put("userlogout", new UserLogOutCommand());
        commands.put("userupdate", new UserUpdateCommand());
        commands.put("changeuserpassword", new ChangeUserPassword());

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

    @Override
    protected Object clone() throws CloneNotSupportedException {

        throw new CloneNotSupportedException("Singleton cloning not supported.");
    }
}
 