package mainPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class MainFrame extends JFrame {
    // Константы с исходным размером окна приложения
    private static final int WIDTH = 300;
    private static final int HEIGHT = 350;


    public MainFrame() {

        // Обязательный вызов конструктора предка
        super("Вычисление значения функции");
        // Установить размеры окна
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,  (kit.getScreenSize().height - HEIGHT)/2);
        // Создать контейнер типа "коробка с горизонтальной укладкой"
        Box hboxPValue = Box.createHorizontalBox();
        // Добавить "клей"
        hboxPValue.add(Box.createHorizontalGlue());
        // Добавить подпись "P:"
        hboxPValue.add(new JLabel("P:"));

        // Добавить "распорку"
        hboxPValue.add(Box.createHorizontalStrut(10));

        JTextField textFieldParam = new JTextField ("0.0", 5);
        //textFieldParam.setInputVerifier(new NumberVerifier());
        textFieldParam.setMaximumSize(
                new Dimension(2*textFieldParam.getPreferredSize().width,
                        textFieldParam.getPreferredSize().height));
        // Добавить поле ввода начального значения X
        hboxPValue.add(textFieldParam);
        hboxPValue.add(Box.createHorizontalStrut(10));

        JButton Plus = new JButton("+");
        hboxPValue.add(Plus);
        hboxPValue.add(Box.createHorizontalStrut(10));
        //hboxPValue.add(Box.createHorizontalGlue());

        JButton Minus = new JButton("-");
        hboxPValue.add(Minus);
        hboxPValue.add(Box.createHorizontalGlue());

        JLabel roleP = new JLabel("Роль Р: ");
        JComboBox P_select = new JComboBox();
        P_select.setMaximumSize(new Dimension(3*P_select.getPreferredSize().width, P_select.getPreferredSize().height));
        P_select.addItem("Слагаемое");
        P_select.addItem("Вычитаемое");
        Box PhorizontalBox = Box.createHorizontalBox();
        PhorizontalBox.add(Box.createHorizontalStrut(10));
        PhorizontalBox.add(roleP);
        PhorizontalBox.add(P_select);
        PhorizontalBox.add(Box.createHorizontalStrut(10));

        JTextField textFieldX = new JTextField("0.0", 5);

        // Установить максимальный размер равный предпочтительному, чтобы
        // предотвратить увеличение размера поля ввода
        textFieldX.setMaximumSize(
                new Dimension(2*textFieldX.getPreferredSize().width,
                        textFieldX.getPreferredSize().height));

        // Создать метку для вывода значения функции
        JLabel labelY = new JLabel("");
        labelY.setMinimumSize(textFieldX.getMaximumSize());  // т е минимальной строкой для у будет являться максимальная для х
        labelY.setPreferredSize(textFieldX.getPreferredSize()); // те предпочтительно для н является предпочтительное для х

        textFieldX.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(KeyEvent.getKeyText(e.getExtendedKeyCode()).equals("Enter")){
                    textFieldX.transferFocus();
                }
            }
        });

        textFieldX.setInputVerifier(new NumberVerifier());

        textFieldX.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }
            @Override
            public void focusLost(FocusEvent e) {
                NumberVerifier XfieldVerify = (NumberVerifier) textFieldX.getInputVerifier();
                if(XfieldVerify.isV()) {
                    Double X = Double.parseDouble(textFieldX.getText());
                    Double P = Double.parseDouble(textFieldParam.getText());
                    if(P_select.getSelectedIndex() == 0) labelY.setText(Double.toString(X+P));
                    if(P_select.getSelectedIndex() == 1) labelY.setText(Double.toString(X-P));
                }
            }
        });

        textFieldParam.setInputVerifier(new NumberVerifier());

        textFieldParam.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }
            @Override
            public void focusLost(FocusEvent e) {
                NumberVerifier PfieldVerify = (NumberVerifier) textFieldParam.getInputVerifier();
                if(PfieldVerify.isV()) {

                    Double X = Double.parseDouble(textFieldX.getText());
                    Double P = Double.parseDouble(textFieldParam.getText());
                    if(P_select.getSelectedIndex() == 0) labelY.setText(Double.toString(X+P));
                    if(P_select.getSelectedIndex() == 1) labelY.setText(Double.toString(X-P));
                }
            }
        });

        textFieldParam.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(KeyEvent.getKeyText(e.getExtendedKeyCode()).equals("Enter")){
                    textFieldParam.transferFocus();
                }
            }
        });

        Plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                    Double P = Double.parseDouble(textFieldParam.getText());
                    P++;
                    textFieldParam.setText(Double.toString(P));
                    Double X = Double.parseDouble(textFieldX.getText());
                    if(P_select.getSelectedIndex() == 0) labelY.setText(Double.toString(X+P));
                    if(P_select.getSelectedIndex() == 1) labelY.setText(Double.toString(X -P));

            }
        });

        Minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                    Double P = Double.parseDouble(textFieldParam.getText());
                    P--;
                    textFieldParam.setText(Double.toString(P));
                    Double X = Double.parseDouble(textFieldX.getText());
                    if(P_select.getSelectedIndex() == 0) labelY.setText(Double.toString(X+P));
                    if(P_select.getSelectedIndex() == 1) labelY.setText(Double.toString(X -P));
            }
        });

        // Создать контейнер c горизонт укл х: и поле ввода х
        Box hboxXValue = Box.createHorizontalBox();
        hboxXValue.add(Box.createHorizontalGlue());
        hboxXValue.add(new JLabel("X:"));
        hboxXValue.add(Box.createHorizontalStrut(10));
        hboxXValue.add(textFieldX);
        hboxXValue.add(Box.createHorizontalGlue());
        // Установить максимальный размер равный предпочтительному, чтобы
        // предотвратить увеличение размера поля ввода
        hboxXValue.setMaximumSize(
                new Dimension(hboxXValue.getMaximumSize().width,
                        hboxXValue.getPreferredSize().height));

        // Создать контейнер типа "коробка с горизонтальной укладкой" для Y и его значения
        Box hboxYValue = Box.createHorizontalBox();
        hboxYValue.add(Box.createHorizontalGlue());
        hboxYValue.add(new JLabel("Y:"));
        hboxYValue.add(Box.createHorizontalStrut(10));
        // Добавить метку для вывода значения Y
        hboxYValue.add(labelY);
        // Добавить "клей"
        hboxYValue.add(Box.createHorizontalGlue());
        hboxYValue.setMaximumSize(
                new Dimension(hboxYValue.getMaximumSize().width,
                        hboxYValue.getPreferredSize().height));

        JLabel formula = new JLabel();
        //formula.setMaximumSize(new Dimension(formula.getMinimumSize().width, formula.getMinimumSize().height));
        formula.setIcon(new ImageIcon(kit.getImage("Lab3icon.png")));
        
        P_select.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(P_select.getSelectedIndex() == 0) {
                    formula.setIcon(new ImageIcon(kit.getImage("Lab3icon.png")));
                }
                if(P_select.getSelectedIndex() == 1) {
                    formula.setIcon(new ImageIcon(kit.getImage("Lab3icon-.png")));
                }
                NumberVerifier XfieldVerify = (NumberVerifier) textFieldX.getInputVerifier();
                NumberVerifier PfieldVerify = (NumberVerifier) textFieldParam.getInputVerifier();
                if(XfieldVerify.isV() && PfieldVerify.isV()){
                    Double X = Double.parseDouble(textFieldX.getText());
                    Double P = Double.parseDouble(textFieldParam.getText());
                    if(P_select.getSelectedIndex() == 0) {
                        labelY.setText(Double.toString(X+P));
                    }
                    if(P_select.getSelectedIndex() == 1) {
                        labelY.setText(Double.toString(X-P));
                    }
                }
            }
        });
        Box Func = Box.createHorizontalBox();
        // Добавить "клей"
        Func.add(Box.createHorizontalGlue());
        Func.add(Box.createHorizontalStrut(20));
        Func.add(formula);
        // Добавить "распорку"
        Func.add(Box.createHorizontalGlue());
        Func.add(Box.createHorizontalStrut(20));
        // Добавить "клей"


        Box Parameters = Box.createVerticalBox();
        // Задать для контейнера тип рамки c заголовком
        Parameters.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Параметры функции:"));
        //вертикальный клей
        Parameters.add(Box.createVerticalGlue());
        Parameters.add(hboxPValue);
        // распорка вертикальная
        //Parameters.add(Box.createVerticalStrut(10));
        Parameters.add(Box.createVerticalStrut(10));
        Parameters.add(PhorizontalBox);
        //Parameters.add(Box.createVerticalGlue());
        Parameters.add(Box.createVerticalStrut(10));
        Parameters.add(Func);
        // распорка вертикальная
        Parameters.add(Box.createVerticalGlue());
        Parameters.setMaximumSize(new Dimension(Parameters.getMaximumSize().width,175));
        Parameters.setMinimumSize(new Dimension(Parameters.getMinimumSize().width,175));
        Parameters.setPreferredSize(new Dimension(Parameters.getPreferredSize().width,175));
        // добавляем вертикальный клей

        getContentPane().add(Parameters);
        Box vboxCalculator = Box.createVerticalBox();
        // Задать для контейнера тип рамки c заголовком
        vboxCalculator.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Расчет значения функции:"));
        // добавляем вертикальный клей
        vboxCalculator.add(Box.createVerticalGlue());
        vboxCalculator.add(hboxXValue); // добавление в список
        // распорка вертикальная
        vboxCalculator.add(Box.createVerticalStrut(30));
        vboxCalculator.add(hboxYValue); // в эл список vboxCalculator добавляет hboxYValue
        vboxCalculator.add(Box.createVerticalStrut(10));
        // добавляем вертикальный клей
        vboxCalculator.add(Box.createVerticalGlue());

        // Установить коробку в панель содержимого
        getContentPane().add(vboxCalculator);
        //getContentPane().setFocusable(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    }
    public static void main(String[] args) {
        // Создать экземпляр главного окна
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        // Задать действие, выполняемое при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Показать главное окно приложения
        frame.setVisible(true);
        }
}

