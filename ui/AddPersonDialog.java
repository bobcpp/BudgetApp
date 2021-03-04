package demo.swing.ui;

import demo.swing.entity.Person;

import javax.swing.*;
import java.awt.*;

public class AddPersonDialog extends JDialog {
    public AddPersonDialog(PersonConsumer personConsumer) {
        setTitle("Add new person");
        JPanel inputsPanel = new JPanel(new GridLayout(2, 2));

        inputsPanel.add(new JLabel("First name:"));
        JTextField firstNameInput = new JTextField(20);
        inputsPanel.add(firstNameInput);
        JTextField lastNameInput = new JTextField(20);
        inputsPanel.add(new JLabel("Last name:"));
        inputsPanel.add(lastNameInput);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JButton addBtn = new JButton("Add");
        mainPanel.add(inputsPanel, BorderLayout.CENTER);
        mainPanel.add(addBtn, BorderLayout.SOUTH);


        addBtn.addActionListener(e -> {
            String firstName = firstNameInput.getText().trim();
            if (firstName.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "First Name shouldn't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String lastName = lastNameInput.getText().trim();

            if (lastName.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Last Name shouldn't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Person person = new Person(firstName, lastName);
            personConsumer.addPerson(person);
            dispose();
        });

        setContentPane(mainPanel);
        pack();
    }
}
