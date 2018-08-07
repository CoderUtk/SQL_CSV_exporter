package sql_csv_exporter;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

interface components {
    JFrame frame = new JFrame("SQL CSV exporter");
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel radio_connection = new JPanel();
    JPanel delimiterPanel = new JPanel();
    JLabel delimeter = new JLabel("Choose Delimeter:    ");
    BoxLayout box = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
    JRadioButton exisiting_conn = new JRadioButton("Select an existing connection");
    JRadioButton new_conn = new JRadioButton("Add a new connection");
    JComboBox delimeter_combobox = new JComboBox(new String[]{"Comma(,)", "Pipe(|)"});
    JTextField query_textfield = new JTextField();
    ButtonGroup buttonGroup = new ButtonGroup();
}

public class Exporter_UI implements components, ActionListener {

    JFileChooser file_chooser;
    JTextField textfield;

    void set_parameters() {
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(box);
        delimeter_combobox.setMaximumSize(delimeter_combobox.getPreferredSize());
        delimiterPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        delimiterPanel.setVisible(false);
        exisiting_conn.addActionListener(this);
        new_conn.addActionListener(this);
    }

    void display_ui() throws InterruptedException {
        set_parameters();
        buttonGroup.add(exisiting_conn);
        buttonGroup.add(new_conn);
        radio_connection.add(exisiting_conn);
        radio_connection.add(new_conn);
        frame.add(radio_connection);
        frame.add(new JSeparator(),BorderLayout.PAGE_END);
        delimiterPanel.add(delimeter);
        delimiterPanel.add(delimeter_combobox);
        delimiterPanel.setVisible(false);
        frame.add(new JSeparator(),BorderLayout.PAGE_END);
        frame.add(query_textfield);
        //JComboBox db_selector = new JComboBox(names);
        //Object[] message = {"Choose DB:",db_selector,"Delimeter",delimeter_combobox,"Query:",query_textfield};       
        //JOptionPane.showConfirmDialog(frame,message,"Enter Details",JOptionPane.OK_CANCEL_OPTION);
        //query = query_textfield.getText();
        //delimeter = delimeter_combobox.getItemAt(delimeter_combobox.getSelectedIndex()).toString();
        //current_db = db_selector.getItemAt(db_selector.getSelectedIndex()).toString();
        // top to bottom
        frame.setVisible(true);
        Thread.sleep(100000);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (exisiting_conn.isSelected()) {
            System.out.println("dsdsd");
        }
        if (new_conn.isSelected()) {
            System.out.println("qwertyuio");;
        }
    }

}
