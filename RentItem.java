/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hola;


/**
 *
 * @author HP
 */
public abstract class RentItem {
    int codigo;
    String nombreItem;
    double precioRenta;
    int copias;
    
    public RentItem(int code, String name, double precio){
        codigo=code;
        nombreItem=name;
        precioRenta=precio;
        copias=0;
    }
    
    public String toString(){
       String toString;
        return toString="Codigo: "+codigo+" Nombre Item: "+nombreItem+" Precio Renta: "+precioRenta;
    }
    
    public abstract double pagoRenta(int dias);
    
    
    //gets
    public int getCodigo() {
        return codigo;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public double getPrecioRenta() {
        return precioRenta;
    }
    
    
    
    
}
