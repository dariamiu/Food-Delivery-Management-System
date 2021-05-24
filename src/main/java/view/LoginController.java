package view;

import bll.DeliveryService;
import bll.LoginService;
import validator.InputValidator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class that controls the LoginView
 * @author Daria Miu
 */
public class LoginController {

    private LoginView loginView;
    private LoginService loginService;
    private InputValidator inputValidator;
    private DeliveryService deliveryService;
    private AdministratorController administratorController;
    private EmployeeView employeeView;
    private ClientController clientController;

    public LoginController(){
        deliveryService = new DeliveryService();
        employeeView = new EmployeeView();
        deliveryService.addObserver(employeeView);
        loginView = new LoginView();
        loginService = new LoginService();
        inputValidator = new InputValidator();
        deliveryService.importData();
        actions();
    }
    /**
     * method to initialize all the action listeners for the buttons from the LoginView.
     * method retrieves the data from the text fields in the interface and
     * sends the data to the BLL to be processed after validating them with the methods from InputValidator class
     */
    private void actions(){
        loginView.createActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAccountController();
            }
        });
        loginView.loginActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    inputValidator.validateCredentials(loginView.getUsername(), String.valueOf(loginView.getPasswordText()));
                }catch(RuntimeException exception){
                    loginView.displayErrorMessage(exception);
                }
                if(loginView.getRb1().isSelected()){

                    try{
                        int id = loginService.findAccount(loginView.getUsername(), String.valueOf(loginView.getPasswordText()),
                                "client");
                        clientController = new ClientController(deliveryService, id);
                    }catch (RuntimeException exception){
                        loginView.displayErrorMessage(exception);
                    }
                }
                if(loginView.getRb2().isSelected()){
                    try{
                        loginService.findAccount(loginView.getUsername(),String.valueOf(loginView.getPasswordText()),"employee");
                        employeeView.start();
                    }catch (RuntimeException exception){
                        loginView.displayErrorMessage(exception);
                    }

                }
                if(loginView.getRb3().isSelected()){
                    try{
                        loginService.findAccount(loginView.getUsername(),String.valueOf(loginView.getPasswordText()),"admin");
                        deliveryService.importData();
                        administratorController = new AdministratorController(deliveryService);


                    }catch (RuntimeException exception){
                        loginView.displayErrorMessage(exception);
                    }


                }
            }
        });
    }

}
