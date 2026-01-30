/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herencia;

import java.time.LocalDate;

public class EmpleadoTemporal extends Empleado {
    protected LocalDate finContratacion;
    
    public EmpleadoTemporal(LocalDate finContratacion){
        this.finContratacion=finContratacion;
    }
    
    public double pagoCondicionado(){
        LocalDate fechaActual=LocalDate.now();
        if(fechaActual.isBefore(finContratacion) || fechaActual.isEqual(finContratacion)){
            
        }
    }
    
    public void actualizarContratacion(LocalDate fechaNueva){
        this.finContratacion=fechaNueva;
    }
    
    public String mostrarInformacion(){
        
    }
}
