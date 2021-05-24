package view;

import javax.swing.*;

/**
 * class that represents the frame that is displayed with de detailed list of products of a menu
 * @author Daria Miu
 */
public class ComponentsItemFrame extends JFrame {

    public ComponentsItemFrame(String components){

        this.setSize(500,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea();
        String components1 = components.replaceAll(",","\n");
        textArea.setText(components1);
        textArea.setBounds(10,10,450,450);
        textArea.setEditable(false);
        panel.add(textArea);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}
