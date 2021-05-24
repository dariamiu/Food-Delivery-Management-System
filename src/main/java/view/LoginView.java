package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends AppFrame{
    private JButton employee;
    private JButton client;
    private JButton administrator;
    private JButton createAccountButton;
    private JButton loginButton;
    private JTextField userName;
    private JPasswordField passwordTextField;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;
    private ButtonGroup bg;

    public LoginView(){
        this.setTitle("Login");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        panel.setBackground(new Color(199,201,255));
        this.setContentPane(panel);
        this.setVisible(true);
    }
    /**
     * Method to initialize all the components in the panel, set their sizes, positions and color
     * @param panel the panel in the JFrame
     */
    private void initializeForm(JPanel panel){
        JLabel emailLabel = new JLabel("username");
        emailLabel.setBounds(150, 50, 200, 30);

        userName = new JTextField();
        userName.setBounds(150, 80, 200, 30);

        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(150, 110, 200, 30);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(150, 140, 200, 30);
        passwordTextField.setEchoChar('*');

        createAccountButton = new JButton("Create account");
        createAccountButton.setBounds(150, 350, 200, 30);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(200, 250, 100, 30);

        rb1=new JRadioButton("Client");
        rb1.setBounds(100,190,100,30);
        rb2=new JRadioButton("Employee");
        rb2.setBounds(200,190,100,30);
        rb3=new JRadioButton("Administrator");
        rb3.setBounds(300,190,100,30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);

        panel.add(rb1);
        panel.add(rb2);
        panel.add(rb3);
        panel.add(createAccountButton);
        panel.add(emailLabel);
        panel.add(userName);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(loginButton);



    }

    public void loginActionListener(ActionListener actionListener){
        loginButton.addActionListener(actionListener);
    }
    public void createActionListener(ActionListener actionListener){
        createAccountButton.addActionListener(actionListener);
    }

    public JRadioButton getRb1() {
        return rb1;
    }

    public JRadioButton getRb2() {
        return rb2;
    }

    public JRadioButton getRb3() {
        return rb3;
    }

    public char[] getPasswordText(){
        return passwordTextField.getPassword();
    }

    public String getUsername(){
        return userName.getText();
    }
}
