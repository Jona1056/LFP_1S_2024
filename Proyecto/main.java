package com.mycompany.ejemplo1;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Ejemplo1 extends JFrame {

    private JTextArea editorCodigo;
    private JTextArea salidaSQL;
    private JLabel imagenDiagrama;
    private JButton btnAnalizar, btnCargar, btnGuardarSQL, btnGuardarImagen;
    private JTextArea lineas; // Área para los números de línea

    public Ejemplo1() {
        setTitle("Analizador Léxico de Bases de Datos");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de Editor con Números de Línea
        JPanel panelEditor = new JPanel(new BorderLayout());
        panelEditor.setBorder(BorderFactory.createTitledBorder("Editor de Código"));

        editorCodigo = new JTextArea(15, 40);
        JScrollPane scrollEditor = new JScrollPane(editorCodigo);
        
        // Área de texto para mostrar los números de línea
        lineas = new JTextArea("1\n");
        lineas.setBackground(Color.LIGHT_GRAY);
        lineas.setEditable(false);
        lineas.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Hacer que los números de línea se actualicen cuando el usuario escribe
        editorCodigo.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarNumerosLinea(); }
            public void removeUpdate(DocumentEvent e) { actualizarNumerosLinea(); }
            public void changedUpdate(DocumentEvent e) { actualizarNumerosLinea(); }
        });

        // Agregamos los números de línea al ScrollPane
        scrollEditor.setRowHeaderView(lineas);

        panelEditor.add(scrollEditor, BorderLayout.CENTER);

        // Panel de Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAnalizar = new JButton("Analizar");
        btnCargar = new JButton("Cargar Archivo");
        btnGuardarSQL = new JButton("Guardar SQL");
        btnGuardarImagen = new JButton("Ver Imagen");
        panelBotones.add(btnCargar);
        panelBotones.add(btnAnalizar);
        panelBotones.add(btnGuardarSQL);
        panelBotones.add(btnGuardarImagen);

        // Panel de Salida SQL
        JPanel panelSQL = new JPanel(new BorderLayout());
        panelSQL.setBorder(BorderFactory.createTitledBorder("Salida SQL Generado"));
        salidaSQL = new JTextArea(10, 40);
        salidaSQL.setEditable(false);
        JScrollPane scrollSQL = new JScrollPane(salidaSQL);
        panelSQL.add(scrollSQL, BorderLayout.CENTER);

        // Panel de Imagen del Diagrama ER
        JPanel panelDiagrama = new JPanel(new BorderLayout());
        imagenDiagrama = new JLabel();
        imagenDiagrama.setHorizontalAlignment(SwingConstants.CENTER);
        panelDiagrama.add(imagenDiagrama, BorderLayout.CENTER);

        // Agregar los paneles a la ventana principal
        add(panelEditor, BorderLayout.WEST);
        add(panelSQL, BorderLayout.EAST);
        add(panelDiagrama, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción de Cargar Archivo
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    cargarArchivo(file);
                }
            }
        });

        // Acción de Analizar Código
        btnAnalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analizarCodigo();
            }
        });

        // Acción de Guardar SQL
        btnGuardarSQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarSQL();
            }
        });

        // Acción de Guardar Imagen
        btnGuardarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarImagen();
            }
        });

        setVisible(true);
    }

    private void actualizarNumerosLinea() {
        int totalLineas = editorCodigo.getLineCount();
        StringBuilder numeros = new StringBuilder();
        for (int i = 1; i <= totalLineas; i++) {
            numeros.append(i).append("\n");
        }
        lineas.setText(numeros.toString());
    }

    private void cargarArchivo(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            editorCodigo.setText(contenido.toString());
            actualizarNumerosLinea();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void analizarCodigo() {
        String codigo = editorCodigo.getText();
        // Simulación de análisis léxico: Generar SQL
        String sqlGenerado = "CREATE TABLE Cliente (\n    ID INT PRIMARY KEY,\n    Nombre VARCHAR(100)\n);\n";
        salidaSQL.setText(sqlGenerado);

        // Simulación de imagen de Graphviz
        ImageIcon icon = new ImageIcon("diagrama.png"); 
        imagenDiagrama.setIcon(icon);
    }

    private void guardarSQL() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(salidaSQL.getText());
                JOptionPane.showMessageDialog(this, "Archivo SQL guardado exitosamente");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Aquí se guardaría la imagen generada con Graphviz
            JOptionPane.showMessageDialog(this, "Imagen del diagrama guardada exitosamente");
        }
    }

    public static void main(String[] args) {
        new Ejemplo1();
    }
}
