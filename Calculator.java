import java.awt.*;
import java.awt.event.*;

public class AWTCalculator extends Frame implements ActionListener {

    TextField tfInput;
    Panel panel;
    String btnString[] = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "C", "0", "=", "+"};
    Button btn[] = new Button[16];
    double num1, num2, result;
    char op;

    public AWTCalculator() {
        setTitle("Simple AWT Calculator");
        setSize(250, 300);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        tfInput = new TextField(10);
        tfInput.setEditable(false);
        tfInput.setFont(new Font("Arial", Font.BOLD, 18));
        add(tfInput, BorderLayout.NORTH);

        panel = new Panel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        for (int i = 0; i < 16; i++) {
            btn[i] = new Button(btnString[i]);
            btn[i].setFont(new Font("Arial", Font.BOLD, 18));
            btn[i].addActionListener(this);
            panel.add(btn[i]);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.charAt(0) == '.') {
            tfInput.setText(tfInput.getText() + command);
        } else if (command.charAt(0) == 'C') {
            tfInput.setText("");
            num1 = num2 = result = 0;
            op = ' ';
        } else if (command.charAt(0) == '=') {
            if (tfInput.getText().isEmpty()) return;
            try {
                num2 = Double.parseDouble(tfInput.getText());
                switch (op) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            tfInput.setText("Error: Div by zero");
                            return;
                        }
                        break;
                }
                tfInput.setText(String.valueOf(result));
                num1 = result;
            } catch (NumberFormatException ex) {
                tfInput.setText("Invalid Input");
            }
        } else {
            if (!tfInput.getText().isEmpty()) {
                num1 = Double.parseDouble(tfInput.getText());
                op = command.charAt(0);
                tfInput.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new AWTCalculator();
    }
}
