package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.junit.platform.commons.util.StringUtils;

/**
 * abstract class implements methods common to all the frames of the GUI
 * @author Daria Miu
 */
public abstract class AppFrame extends JFrame {

    /**
     * Method to display the errors as messages to the user when the validation of the input fails
     * @param exception message about the error
     */
    public void displayErrorMessage(Exception exception) {
        if (exception != null) {
            String message = exception.getMessage();
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to display information message
     * @param message message to display
     */
    public void displayInformationMessage(String message) {
        if (!StringUtils.isBlank(message)) {
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}