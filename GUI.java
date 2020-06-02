package ilya.ignatov;

import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;
import ru.vsu.cs.util.ArrayUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

public class GUI extends JFrame {
    private JTable jTable = new JTable();
    private JPanel jPanel = new JPanel();
    private JLabel countElements = new JLabel("Количество элементов списка: ");
    private JLabel maxElement = new JLabel("Максимальное число: ");
    private JLabel countMax = new JLabel("Количество максимальных элементов: ");
    private JLabel addElementLable = new JLabel("Добавить элемент");
    private JButton loadList = new JButton("Загрузить из файла");
    private JButton addElement = new JButton("Добавить элемент");
    private JButton deleteElement = new JButton("Удалить элемент");
    private JButton randomList = new JButton("Рандомный список");
    private JButton execute = new JButton("Выполнить программу");
    private TextField addElementField = new TextField();
    private TextField size = new TextField("0");
    private TextField max = new TextField();
    private TextField count = new TextField();
    private JFileChooser fileChooser = new JFileChooser();


    public GUI () {
        super("Нахождение количества максимальных элементов");
        LinkList linkList = new LinkList();
        fileChooser.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
        fileChooser.addChoosableFileFilter(filter);

        loadList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    linkList.clear();
                    if (fileChooser.showOpenDialog(jPanel) == JFileChooser.APPROVE_OPTION) {
                        linkList.clear();
                        int[] array = ArrayUtils.readIntArrayFromFile(fileChooser.getSelectedFile().getPath());
                        for (int value : array) {
                            linkList.addElement(value);
                        }
                        JTableUtils.writeArrayToJTable(jTable, array);
                        size.setText(String.valueOf(linkList.getSize()));
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        addElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                linkList.addElement(Integer.parseInt(addElementField.getText()));
                JTableUtils.writeArrayToJTable(jTable, linkList.toArray());
                size.setText(String.valueOf(linkList.getSize()));
                addElementField.setText("");
            }
        });

        deleteElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                linkList.deleteElement();
                JTableUtils.writeArrayToJTable(jTable, linkList.toArray());
                size.setText(String.valueOf(linkList.getSize()));
            }
        });

        randomList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                linkList.clear();
                try {
                    int count = Integer.parseInt(size.getText());
                    Random random = new Random();
                    for (int i = 0; i <count; i++) {
                        linkList.addElement(random.nextInt(20));
                    }
                    int[] array = linkList.toArray();
                    JTableUtils.writeArrayToJTable(jTable, array);

                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                max.setText(String.valueOf(linkList.findMax()));
                count.setText(String.valueOf(linkList.findCount()));
            }
        });

        jPanel.setLayout(null);
        countElements.setBounds(5,5, 250, 20);
        size.setBounds(260, 5, 50, 20);
        addElementLable.setBounds(5, 30, 150, 20);
        addElementField.setBounds(160, 30, 50, 20 );
        jTable.setBounds(5, 55, 200, 20);
        loadList.setBounds(5, 80, 200, 30);
        randomList.setBounds(5,115, 200, 30);
        addElement.setBounds(5, 150, 200, 30 );
        deleteElement.setBounds(5, 185, 200, 30);
        execute.setBounds(5,220, 200, 30);
        maxElement.setBounds(5, 255, 180, 30);
        max.setBounds(190, 255, 50, 20);
        countMax.setBounds(5, 280, 280, 20);
        count.setBounds(290, 280, 50, 20);

        jPanel.add(countElements);
        jPanel.add(size);
        jPanel.add(addElementLable);
        jPanel.add(addElementField);
        jPanel.add(jTable);
        jPanel.add(loadList);
        jPanel.add(randomList);
        jPanel.add(addElement);
        jPanel.add(deleteElement);
        jPanel.add(execute);
        jPanel.add(maxElement);
        jPanel.add(max);
        jPanel.add(countMax);
        jPanel.add(count);

        getContentPane().add(jPanel);
    }

    public static void main (String [] args) {
        GUI app = new GUI();
        app.setBounds(100, 100, 600, 400);
        app.setVisible(true);
    }
}