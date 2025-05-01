import javax.swing.*;

public class TodoList {
    public static void main(String[] args) {
        JFrame frame = new JFrame("To-Do List");
        JTextField taskField = new JTextField();
        taskField.setBounds(30, 30, 200, 30);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(240, 30, 80, 30);

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> taskList = new JList<>(model);
        JScrollPane scroll = new JScrollPane(taskList);
        scroll.setBounds(30, 70, 200, 150);

        JButton removeBtn = new JButton("Remove");
        removeBtn.setBounds(240, 70, 80, 30);

        addBtn.addActionListener(e -> {
            String task = taskField.getText();
            if (!task.isEmpty()) {
                model.addElement(task);
                taskField.setText("");
            }
        });

        removeBtn.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                model.remove(selected);
            }
        });

        frame.add(taskField);
        frame.add(addBtn);
        frame.add(scroll);
        frame.add(removeBtn);

        frame.setSize(370, 280);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
