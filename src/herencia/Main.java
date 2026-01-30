/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herencia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private Empresa empresa;
    private JTextField textoCodigo, textoNombre, textoSalario, textoHoras, textoMonto, textoTasa, textoFechaFinC;
    private JComboBox<String> TipoEmpleado;
    private JButton btnRegistrar, btnHoras, btnVentas, btnPago, btnReporte;

    public Main() {
        empresa=new Empresa(new ArrayList<>());
        iniciarUI();
    }

    private void iniciarUI() {
        setTitle("Gestión de Empleados");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 5, 5));

        panelFormulario.add(new JLabel("Tipo:"));
        TipoEmpleado = new JComboBox<>(new String[]{"Estándar", "Temporal", "Ventas"});
        panelFormulario.add(TipoEmpleado);

        panelFormulario.add(new JLabel("Código:"));
        textoCodigo = new JTextField();
        panelFormulario.add(textoCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        textoNombre = new JTextField();
        panelFormulario.add(textoNombre);

        panelFormulario.add(new JLabel("Salario Base:"));
        textoSalario = new JTextField();
        panelFormulario.add(textoSalario);

        panelFormulario.add(new JLabel("Fecha Fin Contrato (Año-Mes-Dia):"));
        textoFechaFinC = new JTextField();
        panelFormulario.add(textoFechaFinC);

        panelFormulario.add(new JLabel("Tasa Comisión (% ventas):"));
        textoTasa = new JTextField();
        panelFormulario.add(textoTasa);

        panelFormulario.add(new JLabel("Horas a Registrar:"));
        textoHoras = new JTextField();
        panelFormulario.add(textoHoras);

        panelFormulario.add(new JLabel("Monto Venta:"));
        textoMonto = new JTextField();
        panelFormulario.add(textoMonto);
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 5, 5));

        btnRegistrar = new JButton("Registrar Empleado");
        btnRegistrar.addActionListener(e -> registrarEmpleado());

        btnHoras = new JButton("Registrar Horas");
        btnHoras.addActionListener(e -> registrarHoras());

        btnVentas = new JButton("Registrar Ventas");
        btnVentas.addActionListener(e -> registrarVentas());

        btnPago = new JButton("Calcular Pago");
        btnPago.addActionListener(e -> calcularPago());

        btnReporte = new JButton("Generar Reporte");
        btnReporte.addActionListener(e -> generarReporte());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnHoras);
        panelBotones.add(btnVentas);
        panelBotones.add(btnPago);
        panelBotones.add(btnReporte);

        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }


    private void registrarEmpleado() {
        try {
            String tipo = (String) TipoEmpleado.getSelectedItem();
            String codigo=textoCodigo.getText();
            String nombre=textoNombre.getText();
            double salario=Double.parseDouble(textoSalario.getText());
            Empleado emp=null;

            if (tipo.equals("estandar")) {
                emp=new Empleado(codigo, nombre, salario, 0);
            } else if (tipo.equals("temporal")) {
                String fechaTexto = textoFechaFinC.getText();
                if (fechaTexto.isEmpty()==false) {
                    LocalDate fechaFin = LocalDate.parse(fechaTexto);
                    emp=new EmpleadoTemporal(codigo, nombre, salario, 0, fechaFin);
                } else {
                    throw new Exception("Fecha de fin de contrato requerida para empleados temporales.");
                }
            } else if (tipo.equals("ventas")) {
                double tasa=Double.parseDouble(textoTasa.getText()) / 100;
                emp=new EmpleadoVentas(codigo, nombre, salario, 0, new double[12], tasa);
            }

            if(emp==null){
                throw new Exception("No se pudo crear el empleado. Verifique los datos.");
            }
            
            if (!empresa.agregarEmpleado(emp)) {
                throw new Exception("Código ya registrado.");
            }
            JOptionPane.showMessageDialog(this, "Empleado registrado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void registrarHoras() {
        try {
            String codigo = textoCodigo.getText();
            int horas = Integer.parseInt(textoHoras.getText());
            empresa.registrarHoras(codigo, horas);
            JOptionPane.showMessageDialog(this, "Horas registradas.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void registrarVentas() {
        try {
            String codigo=textoCodigo.getText();
            double monto = Double.parseDouble(textoMonto.getText());
            empresa.registrarEmpleadoVentas(codigo, monto);
            JOptionPane.showMessageDialog(this, "Ventas registradas.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void calcularPago() {
        try {
            String codigo=textoCodigo.getText();
            double pago=empresa.obtenerPago(codigo);
            if (pago==-1){
                throw new Exception("Empleado no encontrado.");
            }
            JOptionPane.showMessageDialog(this, "Pago: "+String.format("%.2f", pago));
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error: "+e.getMessage());
        }
    }

    private void generarReporte() {
        empresa.generarReportes();
        JOptionPane.showMessageDialog(this, "Reporte generado en consola.");
    }

    public static void main(String[] args) {
        new Main();
    }
}
