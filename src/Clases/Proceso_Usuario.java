
package Clases;

import java.util.ArrayList;


public class Proceso_Usuario {
    private ArrayList<Object> U = new ArrayList<Object>();
    String nombre = null;
    String apellido = null;
    String placa = null;
    String marca = null;
    String modelo = null;
    String año = null;
    String color = null;
    

    public Proceso_Usuario() {
    }
    
    public Proceso_Usuario(ArrayList<Object> U){
        this.U= U;
    }
    
    public void AgregarRegistro(Usuario User){
        this.U.add(User);
    }
    
    public void ModificarRegistro(int i, Usuario User){
        this.U.set(i, User);
    }
    
    public void EliminarRegistro(int i){
        this.U.remove(i);
    }
    public Usuario ObtenerRegistro(int i){
        return (Usuario)U.get(i);
    }
    
    public int CantidadRegistro(){
        return this.U.size();
    }
    
    public int BuscarPlaca(String Placa){
        for(int i=0;i<=CantidadRegistro();i++){
            if(Placa.equalsIgnoreCase(ObtenerRegistro(i).getPlaca())){
                nombre = ObtenerRegistro(i).getNombre();
                apellido = ObtenerRegistro(i).getApellido();
                placa = ObtenerRegistro(i).getPlaca();
                marca = ObtenerRegistro(i).getMarca();
                modelo = ObtenerRegistro(i).getModelo();
                año = ObtenerRegistro(i).getAño();
                color = ObtenerRegistro(i).getColor();
               return i;
            }
        }
        return -1;
    }
    
    public String Buscar_Nombre(){
        return nombre;
    }
    
    public String Buscar_Apellido(){
        return apellido;
    }
    
    public String Buscar_Placa(){
        return placa;
    }
    
    public String Buscar_Marca(){
        return marca;
    }

    public String Buscar_Modelo(){
        return modelo;
    }

    public String Buscar_Año(){
        return año;
    }
    public String Buscar_Color(){
        return color;
    }

}
