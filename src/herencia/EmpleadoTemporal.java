/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herencia;

import java.time.LocalDate;

public class EmpleadoTemporal extends Empleado {
    protected LocalDate finContratacion;
    
    public EmpleadoTemporal(String codigo, String nombreEmpleado, double salarioBase, int horasTrabajadas, LocalDate finContratacion){
        super(codigo, nombreEmpleado, salarioBase, horasTrabajadas);
        this.finContratacion=finContratacion;
    }
    
    public double pagoCondicionado(){
        LocalDate fechaActual=LocalDate.now();
        if(fechaActual.isBefore(finContratacion) || fechaActual.isEqual(finContratacion)){
            return (this.horasTrabajadas/160)*this.salarioBase;
        } else{
            return 0.0;
        } 
    }
    
    public void actualizarContratacion(LocalDate fechaNueva){
        this.finContratacion=fechaNueva;
    }
    
    public String mostrarInformacion(){
        return super.mostrarInformacion()+", Fecha de Fin de Contrato: "+finContratacion;
    }
}
