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

public class GUI extends JFrame {
    private JTable jTable = new JTable();
    private JPanel jPanel = new JPanel();
    private JLabel countElements = new JLabel("Количество элементов списка: ");
    private JLabel maxElement = new JLabel("Максимальное число: ");
    private JLabel countMax = new JLabel("Количество максимальных элементов");
    private JButton loadList = new JButton("Загрузить из файла");
    private JButton randomList = new JButton("Рандомный список");
    private JButton findMax = new JButton("Выполнить программу");
    private TextField size = new TextField("0");
    private TextField max = new TextField();
    private TextField count = new TextField();
    private JScrollPane scrollPane = new JScrollPane(jTable);
    private JFileChooser fileChooser = new JFileChooser();


    public GUI () {
        super("Нахождение количества максимальных элементов");
        LinkList linkList = new LinkList();
        fileChooser.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
        fileChooser.addChoosableFileFilter(filter);

        randomList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                linkList.clear();
                try {
                    int[] array = linkList.randomList(Integer.parseInt(size.getText()));
                    JTableUtils.writeArrayToJTable(jTable, array);
                    linkList.listFromArray(JTableUtils.readIntArrayFromJTable(jTable));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        loadList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    linkList.clear();
                    if (fileChooser.showOpenDialog(jPanel) == JFileChooser.APPROVE_OPTION) {
                        int[] array = ArrayUtils.readIntArrayFromFile(fileChooser.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(jTable, array);
                        linkList.listFromArray(JTableUtils.readIntArrayFromJTable(jTable));
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        findMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                max.setText(String.valueOf(linkList.findMax()));
                count.setText(String.valueOf(linkList.findCount()));
            }
        });

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.add(countElements);
        container.add(size);
        container.add(jTable);
        container.add(maxElement);
        container.add(max);
        container.add(countMax);
        container.add(count);
        container.add(randomList);
        container.add(loadList);
        container.add(findMax);
    }

    public static void main (String [] args) {
        GUI app = new GUI();
        app.setBounds(100, 100, 1000, 200);
        app.setVisible(true);
    }
}