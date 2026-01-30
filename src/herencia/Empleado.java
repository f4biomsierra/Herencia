/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herencia;

import java.time.LocalDate;
        

public class Empleado {
    protected String codigo;
    protected String nombreEmpleado;
    protected LocalDate fechaContratacion;
    protected double salarioBase;
    protected int horasTrabajadas;

    public Empleado(String codigo, String nombreEmpleado, double salarioBase, int horasTrabajadas) {
        this.codigo = codigo;
        this.nombreEmpleado = nombreEmpleado;
        this.salarioBase = salarioBase;
        this.horasTrabajadas = horasTrabajadas;
        this.fechaContratacion = LocalDate.now();
        this.horasTrabajadas=0;
    }
    
    public void registrarHorasTrabajadas(int horas){
        if(horas>0){
            this.horasTrabajadas += horas;
        }
    }
    
    public double calcularPago(){
        double pagoPrincipal = (salarioBase/160)*horasTrabajadas;
        double deduccion = pagoPrincipal*0.035;
        return pagoPrincipal-deduccion;
    }
    
    public String mostrarInformacion(){
         return String.format("ID: "+codigo+ " | Nombre: "+nombreEmpleado+" | Fecha de Contratacion: "+fechaContratacion.toString());
    }
}
