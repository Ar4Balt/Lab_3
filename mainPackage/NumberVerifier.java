package mainPackage;

import javax.swing.*;


public class NumberVerifier extends InputVerifier {

    public boolean v = true;

    public boolean isV() {
        return v;
    }

    @Override
    public boolean verify(JComponent input) {
        JTextField tf =  (JTextField) input;
        this.v = false;
        try{
            Double.parseDouble(tf.getText());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Значение не является числом",
            "Неверные данные", JOptionPane.ERROR_MESSAGE);
            JOptionPane r = new JOptionPane();
            return false;
        }

        this.v = true;
        return true;
    }
}
