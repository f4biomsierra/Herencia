/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herencia;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Fabio Sierra
 */

public class Empresa {
    private ArrayList<Empleado> empleados;

    public Empresa(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
    
    public boolean agregarEmpleado(Empleado nuevoEmpleado){
        if(buscarEmpleado(nuevoEmpleado.codigo) == null){
            empleados.add(nuevoEmpleado);
            return true;
        }
        System.out.println("Error. El codigo: "+nuevoEmpleado.codigo+" ya esta registrado.");
        return false;
    }
    
    public Empleado buscarEmpleado(String codigo){
        for(Empleado emp:empleados){
            if(emp.codigo.equalsIgnoreCase(codigo)){
                return emp;
            }
        }
        return null;
    }
    
    public void registrarHoras(String codigo, int horas){
        Empleado datos = buscarEmpleado(codigo);
        
        if(datos != null){
            datos.registrarHorasTrabajadas(horas);
        } else {
            System.out.println("No se encontro empleado con esa ID.");
        }
    }
    
    public void registrarEmpleadoVentas(String codigo, double monto){
        Empleado datos = buscarEmpleado(codigo);
        
        if(datos instanceof EmpleadoVentas){
            ((EmpleadoVentas) datos).registroVentas(monto);
        } else {
            System.out.println("Este empleado no es de ventas o no existe.");
        }
    }
    
    public void renovarContrato(String codigo, LocalDate nuevaFecha){
        Empleado datos = buscarEmpleado(codigo);
        
        if(datos instanceof EmpleadoTemporal){
            ((EmpleadoTemporal) datos).actualizarContratacion(nuevaFecha);
        } else {
            System.out.println("Este empleado no es temporal o no existe.");
        }
    }
    
    public double obtenerPago(String codigo){
        Empleado datos = buscarEmpleado(codigo);
        
        if(datos != null){
            if(datos instanceof EmpleadoTemporal){
                return ((EmpleadoTemporal) datos).pagoCondicionado();
            }
            return datos.calcularPago();
        }
        return -1;
    }
    
    public void generarReportes(){
        int empleadosEstandar=0;
        int empleadosTemporales=0;
        int empleadosDeVentas=0;
        
        System.out.println("=== REPORTES DE EMPLEADOS ====");
        
        for (Empleado e : empleados) {
            System.out.println(e.mostrarInformacion());
            System.out.println("Pago calculado: L. " + String.format("%.2f", obtenerPago(e.codigo)));
            
            if (e instanceof EmpleadoVentas) empleadosDeVentas++;
            else if (e instanceof EmpleadoTemporal) empleadosTemporales++;
            else empleadosEstandar++;
            
            System.out.println("---------------------------------------");
        }
        
        System.out.println("-- RESUMEN PERSONAL --");
        System.out.println("Estándar: " + empleadosEstandar);
        System.out.println("Temporales: " + empleadosTemporales);
        System.out.println("Ventas: " + empleadosDeVentas);
    }
}
