import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.NumberFormatter;
import java.awt.event.*;
import java.awt.*;
import java.text.DecimalFormat;

class GUI implements ActionListener{
    JFrame frame;
    JPanel panel;
    JTextField textField; //holds numbers that is type and result
    JButton []numButtons = new JButton[10];
    JButton[] otherButtons = new JButton[9];
    JButton delete, equals, clear, decimalpoint;
    JButton add,subtract,divide,multiply, negative;
    Font calculatorFont = new Font("Ink Free", Font.BOLD, 20);
    Font textFieldFont = new Font("Ink Free", Font.BOLD, 30);
    DecimalFormat decimalFormat = new DecimalFormat("#.############");
    double firstNum;
    double secondNum;
    double result;
    char numOperator = ' ';

    GUI() {
        frame = new JFrame("Calculator App ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLayout(null);


        textField = new JTextField();
        textField.setBounds(80, 30, 300, 50);
        textField.setFont(textFieldFont);
        textField.setEditable(false);
//all calculator buttons aside from numbers
        add = new JButton("+");
        subtract = new JButton("-");
        divide = new JButton("÷");

        multiply = new JButton("x");
        decimalpoint = new JButton(".");
        equals = new JButton("=");
        clear = new JButton("C");
        delete = new JButton("Delete");
        negative = new JButton("[-]");

        otherButtons[0] = add;
        otherButtons[1] = subtract;
        otherButtons[2] = multiply;
        otherButtons[3] = divide;
        otherButtons[4] = decimalpoint;
        otherButtons[5] = equals;
        otherButtons[6] = delete;
        otherButtons[7] = clear;
        otherButtons[8] = negative;

        for (int i = 0; i < otherButtons.length; i++) {
            otherButtons[i].addActionListener(this);
            otherButtons[i].setFont(calculatorFont);
            otherButtons[i].setFocusable(false);
        }

        for (int i = 0; i < numButtons.length; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(calculatorFont);
            numButtons[i].setFocusable(false);
        }
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 15, 15)); //default calculator has 4 rows and columns and 15 is pixels of gap
        int panelX = textField.getX(); // Align panel with text field
        int panelY = textField.getY() + textField.getHeight() + 10; // Position below text field with 10 pixel gap
        panel.setBounds(panelX, panelY, textField.getWidth(), 300);
        //   panel.setBackground(Color.BLUE);
        for (int i = 1; i <= 3; i++) {
            panel.add(numButtons[i]);
        }
        panel.add(add);
        for (int i = 4; i <= 6; i++) {
            panel.add(numButtons[i]);
        }
        panel.add(subtract);
        for (int i = 7; i <= 9; i++) {
            panel.add(numButtons[i]);
        }
        panel.add(multiply);
        panel.add(decimalpoint);
        panel.add(numButtons[0]);
        panel.add(equals);
        panel.add(divide);

        negative.setBounds(80,400,100,50);
        delete.setBounds(200, 400, 100, 50);
        clear.setBounds(315, 400, 65, 50);
        frame.add(delete);  //makes the delete button visible on the frame
        frame.add(clear);
        frame.add(negative);
        frame.add(panel);

        frame.add(textField);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i =0; i<numButtons.length; i++){
            if(e.getSource()==numButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource()==decimalpoint){
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource()==add){
            firstNum = Double.parseDouble(textField.getText());
            numOperator = '+';
            textField.setText("");
        }
        if (e.getSource()==subtract){
            firstNum = Double.parseDouble(textField.getText());
            numOperator = '-';
            textField.setText("");
        }
        if (e.getSource()==multiply){
            firstNum = Double.parseDouble(textField.getText());
            numOperator = 'x';
            textField.setText("");
        }
        if (e.getSource()==divide){
            firstNum = Double.parseDouble(textField.getText());
            numOperator = '÷';
            textField.setText("");
        }
        if (e.getSource()==equals){
            secondNum = Double.parseDouble(textField.getText());
            switch (numOperator){
                case '+':
                    result = firstNum+secondNum;
                    break;
                case '-':
                    result = firstNum-secondNum;
                    break;
                case 'x':
                    result = firstNum*secondNum;
                    break;
                case '÷':
                    result = firstNum/secondNum;
            }
            firstNum = result;
            textField.setText(decimalFormat.format(result));
        }
        if(e.getSource() == clear){
            textField.setText("");
            firstNum = 0;
        }
        if(e.getSource()==delete){
            String ogNum = textField.getText();

            textField.setText("");
            for(int i =0; i<ogNum.length()-1; i++){
                textField.setText(textField.getText() + ogNum.charAt(i));  //each time delete is called it sets the num in the textfield to itself-1 in length so 14 would be 1 if deleted
            }
        }
        if(e.getSource()==negative){
            //if it is negative make it look positive if its positive makr it negative
            if(textField.getText().startsWith("-")){
                textField.setText(textField.getText().substring(1));
            }
            else{
                textField.setText("-" + textField.getText());
            }
        }
    }
;
}
