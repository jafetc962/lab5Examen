
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Main extends JFrame {
    
    private ArrayList<RentItem> items;
    private JTextArea displayArea;
    
    public Main() {
        items = new ArrayList<>();
        setupGUI();
    }
    
    private void setupGUI() {
        setTitle("Rent Item ");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        JButton addItemButton = new JButton("Agregar Item");
        JButton consultItemButton = new JButton("Consultar Item");
        JButton submenuButton = new JButton("Ejecutar Sub Menu  ");
        JButton printAllButton = new JButton("Imprimir Todo");
        JButton salir = new JButton("Salir");
        
        addItemButton.addActionListener(e -> agregarItem());
        consultItemButton.addActionListener(e -> consultarItem());
        submenuButton.addActionListener(e -> ejecutarSubmenu());
        printAllButton.addActionListener(e -> imprimirTodo());
        salir.addActionListener(e -> salir());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addItemButton);
        buttonPanel.add(consultItemButton);
        buttonPanel.add(submenuButton);
        buttonPanel.add(printAllButton);
        buttonPanel.add(salir);
        
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void agregarItem() {
        String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo de item (MOVIE o GAME):");
        if (tipo == null) {
            return;
        }
        
        tipo = tipo.trim().toUpperCase();
        if (!tipo.equals("MOVIE") && !tipo.equals("GAME")) {
            JOptionPane.showMessageDialog(this, "TIPO INCORRECTO");
            return;
        }
        
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el codigo del item:"));
            if (codigo < 0) {
                JOptionPane.showMessageDialog(null, "TIPO INCORRECTO");
                return;
            }
            for (RentItem item : items) {
                if (item.getCodigo() == codigo) {
                    JOptionPane.showMessageDialog(this, "Codigo ya existe. Intente con otro codigo.");
                    return;
                }
            }
            
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del item:");
            double precio = Double.parseDouble(JOptionPane.showInputDialog(this, "Ingrese el precio de renta:"));
            if (precio <= 0) {
                JOptionPane.showMessageDialog(null, "TIPO INCORRECTO");
                return;
            }
            
            if (tipo.equals("MOVIE")) {
                items.add(new Movie(codigo, nombre, precio));
                displayArea.append("Movie agregada: " + nombre + "\n");
            } else if (tipo.equals("GAME")) {
                items.add(new Game(codigo, nombre, precio));
                displayArea.append("Game agregado: " + nombre + "\n");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada no valida.");
        }
    }
    
    private void consultarItem() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el codigo del item:"));
            RentItem item = null;
            for (RentItem i : items) {
                if (i.getCodigo() == codigo) {
                    item = i;
                    break;
                }
            }
            if (item == null) {
                JOptionPane.showMessageDialog(this, "Item No Existe");
                return;
            }
            
            int dias = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese los dias a rentar:"));
            if (dias <= 0) {
                JOptionPane.showMessageDialog(null, "TIPO INCORRECTO");
                return;
            }
            double montoPagar = item.pagoRenta(dias);
            displayArea.append(item.toString() + "\nMonto a Pagar: " + montoPagar + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada no valida.");
        }
    }
    
    private void ejecutarSubmenu() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el codigo del item:"));
            RentItem item = null;
            for (RentItem i : items) {
                if (i.getCodigo() == codigo) {
                    item = i;
                    break;
                }
            }
            if (item == null) {
                JOptionPane.showMessageDialog(this, "Item No Existe");
                return;
            }
            
            if (item instanceof MenuActions) {
                ((MenuActions) item).submenu();
                int opcion = Integer.parseInt(JOptionPane.showInputDialog(this, "Escoja Opcion:    \n1. Actualizar Fecha de Publicacion "
                        + "                                                                         \n2. Agregar Especificacion "
                        + "                                                                         \n3. Ver Especificaciones"));
                ((MenuActions) item).ejecutarOpcion(opcion);
            } else {
                JOptionPane.showMessageDialog(this, "El item no implementa MenuActions");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada no valida.");
        }
    }
    
    private void imprimirTodo() {
        if (items.isEmpty()) {
            displayArea.append("No hay items para mostrar.\n");
            return;
        }
        for (RentItem item : items) {
            displayArea.append(item.toString() + "\n");
        }
    }
    
    private void salir() {
        System.exit(0);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}