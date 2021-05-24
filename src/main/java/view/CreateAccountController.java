package view;

import bll.LoginService;
import validator.InputValidator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * class that controls the CreateAccountView
 * @author Daria Miu
 */
public class CreateAccountController {
    private CreateAccountView createView;
    private LoginService loginService;
    private InputValidator inputValidator;
    public CreateAccountController(){
        createView = new CreateAccountView();
        loginService = new LoginService();
        inputValidator = new InputValidator();
        actions();
    }
    /**
     * method to initialize all the action listeners for the buttons from the CreateAccountView.
     * method retrieves the data from the text fields in the interface and
     * sends the data to the BLL to be processed after validating them with the methods from InputValidator class
     */
    private void actions(){
        createView.createButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    inputValidator.validateCredentials(createView.getUsername(), String.valueOf(createView.getPasswordText()));
                }catch (RuntimeException e1){
                    createView.displayErrorMessage(e1);
                }

                if(createView.getRb1().isSelected()){

                    try{
                        loginService.createAccount(createView.getUsername(), String.valueOf(createView.getPasswordText()),
                                "client", createView.getSecret());
                        createView.displayInformationMessage("Account created");
                    }catch (RuntimeException exception){
                        createView.displayErrorMessage(exception);
                    }


                }
                if(createView.getRb2().isSelected()){

                    try{
                        loginService.createAccount(createView.getUsername(),String.valueOf(createView.getPasswordText()),
                                "employee", createView.getSecret());
                        createView.displayInformationMessage("Account created");
                    }catch (RuntimeException exception){
                        createView.displayErrorMessage(exception);
                    }

                }

                if(createView.getRb3().isSelected()){

                    try{
                        loginService.createAccount(createView.getUsername(),String.valueOf(createView.getPasswordText()),"admin", createView.getSecret());
                        createView.displayInformationMessage("Account created");
                    }catch (RuntimeException exception){
                        createView.displayErrorMessage(exception);
                    }

                }


            }
        });
    }
}
