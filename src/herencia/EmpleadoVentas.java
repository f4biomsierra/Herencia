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
    
    public EmpleadoVentas(double []ventasMensuales, double tasaComision){
        this.ventasMensuales=new double[12];
        this.tasaComision=tasaComision;
    }
    
    public void registroVentas(double monto){
        int mesActual=LocalDate.now().getMonthValue()-1;
        this.ventasMensuales[mesActual]=this.ventasMensuales[mesActual]+monto;
    }
    
    public double calculoComision(){
        int mesActual=LocalDate.now().getMonthValue()-1;
    }
    
    public double pagoMensual(){
        
    }
    
    public double ventasAnuales(){
        
    }
    
    public String mostrarInformacion(){
        
    }
}
