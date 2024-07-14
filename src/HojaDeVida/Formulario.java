package HojaDeVida;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import clases.Crud;
import POO.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Formulario extends JFrame {

    private JTextField nombresField;
    private JTextField apellidosField;
    private JDateChooser fechaNacimientoChooser; 
    private JComboBox<String> sexoComboBox;
    private JLabel fotoLabel;
    private JButton seleccionarFotoButton;
    private JTextField colegioField;
    private JTextField universidadField;
    private JTextField trabajoField;
    private JTextField datoReferenteField;
    private JButton saveButton, editButton, deleteButton, newButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<User> users;
    private String fotoPath; 

    public Formulario() {
        setTitle("Formulario Hoja de Vida");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        users = new ArrayList<>();

        JPanel formPanel = new JPanel();
        formPanel.setBounds(0, 1, 784, 331);

        nombresField = new JTextField();
        nombresField.setBounds(261, 17, 261, 23);
        apellidosField = new JTextField();
        apellidosField.setBounds(261, 51, 261, 23);
        fechaNacimientoChooser = new JDateChooser(); 
        fechaNacimientoChooser.setBounds(261, 85, 261, 23);
        sexoComboBox = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        sexoComboBox.setBounds(261, 119, 261, 23);
        fotoLabel = new JLabel("Fotografía no seleccionada", JLabel.CENTER);
        fotoLabel.setBounds(588, 62, 172, 108);
        fotoLabel.setPreferredSize(new Dimension(20, 15));
        seleccionarFotoButton = new JButton("Seleccionar Fotografía");
        seleccionarFotoButton.setBounds(588, 181, 172, 23);
        seleccionarFotoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fotoPath = selectedFile.getAbsolutePath(); 
                ImageIcon imageIcon = new ImageIcon(fotoPath);
                Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                fotoLabel.setIcon(new ImageIcon(image));
            }
        });
        colegioField = new JTextField();
        colegioField.setBounds(261, 161, 261, 23);
        universidadField = new JTextField();
        universidadField.setBounds(261, 195, 261, 23);
        trabajoField = new JTextField();
        trabajoField.setBounds(261, 229, 261, 23);
        datoReferenteField = new JTextField();
        datoReferenteField.setBounds(261, 263, 261, 23);

        saveButton = new JButton("Guardar");
        saveButton.setForeground(new Color(0, 0, 0));
        saveButton.setBackground(new Color(0, 255, 64));
        saveButton.setBounds(261, 297, 99, 23);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = getCurrentUser();
                if (user != null) {
                    if (user.getId() == 0) {
                        // Nuevo usuario
                        Crud.insertUser(user);
                        users.add(user);
                        tableModel.addRow(getUserRow(user));
                    } else {
                        // Usuario existente, actualizar
                        Crud.updateUser(user);
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            tableModel.setValueAt(user.getNombres(), selectedRow, 0);
                            tableModel.setValueAt(user.getApellidos(), selectedRow, 1);
                            tableModel.setValueAt(user.getFechaNacimiento(), selectedRow, 2);
                            tableModel.setValueAt(user.getSexo(), selectedRow, 3);
                            tableModel.setValueAt(user.getFoto(), selectedRow, 4);
                            tableModel.setValueAt(user.getColegio(), selectedRow, 5);
                            tableModel.setValueAt(user.getUniversidad(), selectedRow, 6);
                            tableModel.setValueAt(user.getTrabajo(), selectedRow, 7);
                            tableModel.setValueAt(user.getDatoReferente(), selectedRow, 8);
                        }
                    }
                    clearForm();
                }
            }
        });

        editButton = new JButton("Editar");
        editButton.setBackground(new Color(255, 128, 0));
        editButton.setBounds(118, 297, 99, 23);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    User user = users.get(selectedRow);
                    fillForm(user);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.");
                }
            }
        });

        deleteButton = new JButton("Eliminar");
        deleteButton.setBackground(new Color(255, 0, 0));
        deleteButton.setBounds(569, 297, 99, 23);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    User user = users.get(selectedRow);
                    Crud.deleteUser(user.getId());
                    users.remove(selectedRow);
                    tableModel.removeRow(selectedRow);
                    table.revalidate();
                    table.repaint();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.");
                }
            }
        });
        
        newButton = new JButton("Nuevo");
        newButton.setBackground(new Color(0, 0, 255));
        newButton.setBounds(423, 297, 99, 23);
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
                table.clearSelection(); 
            }
        });

        getContentPane().setLayout(null);
        formPanel.setLayout(null);

        JLabel label = new JLabel("Nombres:");
        label.setBounds(0, 17, 261, 23);
        formPanel.add(label);
        formPanel.add(nombresField);
        JLabel label_1 = new JLabel("Apellidos:");
        label_1.setBounds(0, 51, 261, 23);
        formPanel.add(label_1);
        formPanel.add(apellidosField);
        JLabel label_2 = new JLabel("Fecha de Nacimiento:");
        label_2.setBounds(0, 85, 261, 23);
        formPanel.add(label_2);
        formPanel.add(fechaNacimientoChooser); 
        JLabel label_3 = new JLabel("Sexo:");
        label_3.setBounds(0, 119, 261, 23);
        formPanel.add(label_3);
        formPanel.add(sexoComboBox);
        JLabel label_4 = new JLabel("Fotografía:");
        label_4.setBounds(639, 17, 60, 23);
        formPanel.add(label_4);
        formPanel.add(seleccionarFotoButton);
        formPanel.add(fotoLabel);
        JLabel label_5 = new JLabel("");
        label_5.setBounds(522, 69, 261, 23);
        formPanel.add(label_5);
        JLabel label_6 = new JLabel("Colegio:");
        label_6.setBounds(0, 161, 261, 23);
        formPanel.add(label_6);
        formPanel.add(colegioField);
        JLabel label_7 = new JLabel("Universidad:");
        label_7.setBounds(0, 195, 261, 23);
        formPanel.add(label_7);
        formPanel.add(universidadField);
        JLabel label_8 = new JLabel("Trabajo:");
        label_8.setBounds(0, 229, 261, 23);
        formPanel.add(label_8);
        formPanel.add(trabajoField);
        JLabel label_9 = new JLabel("Dato Referente:");
        label_9.setBounds(0, 263, 261, 23);
        formPanel.add(label_9);
        formPanel.add(datoReferenteField);

        formPanel.add(saveButton);
        formPanel.add(editButton);
        formPanel.add(deleteButton);
        formPanel.add(newButton);

        getContentPane().add(formPanel);

        tableModel = new DefaultTableModel(new String[]{
                "Nombres", "Apellidos", "Fecha de Nacimiento", "Sexo", "Fotografía", "Colegio", "Universidad", "Trabajo", "Dato Referente"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 331, 784, 230);

        getContentPane().add(scrollPane);

        loadUsers(); // Cargar usuarios desde la base de datos al iniciar la aplicación
    }

    private void loadUsers() {
        users = Crud.getAllUsers();
        tableModel.setRowCount(0); 
        for (User user : users) {
            tableModel.addRow(getUserRow(user));
        }
    }

    private Object[] getUserRow(User user) {
        return new Object[]{
                user.getNombres(),
                user.getApellidos(),
                user.getFechaNacimiento(),
                user.getSexo(),
                user.getFoto(),
                user.getColegio(),
                user.getUniversidad(),
                user.getTrabajo(),
                user.getDatoReferente()
        };
    }

    private User getCurrentUser() {
        Date fechaNacimiento = fechaNacimientoChooser.getDate();
        int id = 0; 


        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            id = users.get(selectedRow).getId();
        }

        return new User(
                id,
                nombresField.getText(),
                apellidosField.getText(),
                fechaNacimiento,
                (String) sexoComboBox.getSelectedItem(),
                fotoPath, 
                colegioField.getText(),
                universidadField.getText(),
                trabajoField.getText(),
                datoReferenteField.getText()
        );
    }

    private void fillForm(User user) {
        nombresField.setText(user.getNombres());
        apellidosField.setText(user.getApellidos());
        fechaNacimientoChooser.setDate(user.getFechaNacimiento());
        sexoComboBox.setSelectedItem(user.getSexo());
        fotoPath = user.getFoto();
        ImageIcon imageIcon = new ImageIcon(fotoPath);
        Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        fotoLabel.setIcon(new ImageIcon(image));
        colegioField.setText(user.getColegio());
        universidadField.setText(user.getUniversidad());
        trabajoField.setText(user.getTrabajo());
        datoReferenteField.setText(user.getDatoReferente());
    }

    private void clearForm() {
        nombresField.setText("");
        apellidosField.setText("");
        fechaNacimientoChooser.setDate(null);
        sexoComboBox.setSelectedIndex(0);
        fotoLabel.setIcon(null);
        fotoLabel.setText("Fotografía no seleccionada");
        fotoPath = null;
        colegioField.setText("");
        universidadField.setText("");
        trabajoField.setText("");
        datoReferenteField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Formulario().setVisible(true));
    }
}


