package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateAccountView extends AppFrame{
    private JButton createButton;
    private JTextField userName;
    private JPasswordField passwordTextField;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;
    private JTextField secretCode;
    private ButtonGroup bg;

    public CreateAccountView(){
        this.setTitle("Create account");
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


        JLabel secret = new JLabel("introduce code for admin/employee account");
        secret.setBounds(120,253,312,21);
        panel.add(secret);

        secretCode = new JTextField();
        secretCode.setBounds(210,284,80,21);
        panel.add(secretCode);

        createButton = new JButton("CREATE");
        createButton.setBounds(200, 350, 105, 27);

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
        panel.add(emailLabel);
        panel.add(userName);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(createButton);

    }

    public void createButtonActionListener(ActionListener actionListener){
        createButton.addActionListener(actionListener);
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

    public String getSecret(){
        return secretCode.getText();
    }
}
