package demo.swing.ui;

import demo.swing.entity.Person;
import demo.swing.entity.Sex;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Scanner;

public class MainFrame extends JFrame {

    private DefaultListModel<Person> listModel;

    public MainFrame() {

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                    onClose();
                    System.exit(0);
            }
        });

        setTitle("Demo Persons App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        listModel = new DefaultListModel<>();
        JList<Person> list = new JList<>(listModel);
        JButton addPersonBtn = new JButton("Add Person");
        JButton removePersonBtn = new JButton("Remove Person");

        addPersonBtn.setForeground(Color.BLUE);
        addPersonBtn.setOpaque(true);
        removePersonBtn.setForeground(Color.RED);
        removePersonBtn.setOpaque(true);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(addPersonBtn);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(removePersonBtn);

        removePersonBtn.addActionListener(e -> {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        });

        addPersonBtn.addActionListener(e -> {
            JDialog addPersonDialog = new AddPersonDialogWithBuilder(newPerson -> listModel.addElement(newPerson));
            addPersonDialog.setModal(true);
            addPersonDialog.setLocationRelativeTo(this);
            addPersonDialog.setVisible(true);
        });

        list.addListSelectionListener(e -> {
            removePersonBtn.setEnabled(list.getSelectedIndex() != -1);
        });
        removePersonBtn.setEnabled(false);

        mainPanel.add(list, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.EAST);

        listModel.addElement(new Person("Ivan", "Ivanov"));
        listModel.addElement(new Person("Petrov", "Petrov"));

        Scanner fileReader = null;

        try {
            fileReader = new Scanner(new FileReader("records.txt"));
        } catch (IOException e) {

        }

            if (fileReader != null) {

                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] words = line.split(" ");
                    Person person = null;
                    switch (words.length) {
                        case 2: {
                            person = new Person(words[0], words[1]);
                            break;
                        }
                        case 3: {
                            if (words[2].equals("Male")) {
                                person = new Person(words[0], words[1], null, Sex.Male);
                            } else {
                                person = new Person(words[0], words[1], null, Sex.Female);
                            }
                            break;
                        }
                        default: {
                            if (words[3].equals("Male")) {
                                person = new Person(words[0], words[1], Integer.parseInt(words[2]), Sex.Male);
                            } else {
                                person = new Person(words[0], words[1], Integer.parseInt(words[2]), Sex.Female);
                            }
                        }
                    }
                    listModel.addElement(person);
                }
            }

        setContentPane(mainPanel);
    }

    private void onClose(){

        BufferedWriter fileWriter = null;
        File file = new File("records.txt");
        try {

            fileWriter = new BufferedWriter(
                    new FileWriter("records.txt", true));
        } catch (IOException e) {
        }

        try {

            if (listModel != null) {
                for (int i = 0; i < listModel.getSize(); i++) {
                    Person person = listModel.get(i);
                    StringBuilder lineToWrite = new StringBuilder(person.getFirstName() + " " + person.getLastName());
                    if (person.getAge() != null) {
                        lineToWrite.append(" " + person.getAge());
                    }
                    if (person.getSex() != null) {
                        lineToWrite.append(" " + person.getSex());
                    }
                    lineToWrite.append("\n");
                    fileWriter.write(lineToWrite.toString());
                    fileWriter.flush();
                }
            }

        } catch (IOException e) {
        }
    }
}
