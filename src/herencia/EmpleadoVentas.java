/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herencia;

import java.time.LocalDate;

public class EmpleadoVentas extends Empleado {
    protected double []ventasMensuales;
    protected double tasaComision;
    
    public EmpleadoVentas(String codigo, String nombreEmpleado, double salarioBase, int horasTrabajadas, double []ventasMensuales, double tasaComision){
        super(codigo, nombreEmpleado, salarioBase, horasTrabajadas);
        this.ventasMensuales=new double[12];
        this.tasaComision=tasaComision;
    }
    
    public void registroVentas(double monto){
        int mesActual=LocalDate.now().getMonthValue()-1;
        this.ventasMensuales[mesActual]=this.ventasMensuales[mesActual]+monto;
    }
    
    public double calculoComision(){
        int mesActual=LocalDate.now().getMonthValue()-1;
        return this.ventasMensuales[mesActual]*this.tasaComision;
    }
    
    public double calcularPago(){
        double proporcional=(this.horasTrabajadas/160)*this.salarioBase;
        double comision=calculoComision();
        return proporcional+comision;
    }
    
    public double ventasAnuales(){
        double total=0;
        for(int contador=0;contador<ventasMensuales.length;contador++){
            total=total+ventasMensuales[contador];
        }
        return total;
    }
    
    public String mostrarInformacion(){
        return super.mostrarInformacion()+", Ventas Anuales: "+String.format("%.2f", ventasAnuales());
    }
}
