package Modelo;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author emanu
 */

public class Docente extends Persona {
    private String codigo_docente, fecha_ingreso_laborar,fecha_ingreso_registro;
    private int salario,id;
    private Conexion cn;
    
    public Docente(){}
    public Docente(int id, String codigo_docente, String fecha_ingreso_laborar, String fecha_ingreso_registro, int salario, String nit, String nombre, String apellido, String direccion, String telefono, String fecha_nacimiento) {
        super(nit, nombre, apellido, direccion, telefono, fecha_nacimiento);
        this.codigo_docente = codigo_docente;
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
        this.fecha_ingreso_registro = fecha_ingreso_registro;
        this.salario = salario;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
    }

    public String getFecha_ingreso_laborar() {
        return fecha_ingreso_laborar;
    }

    public void setFecha_ingreso_laborar(String fecha_ingreso_laborar) {
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
    }

    public String getFecha_ingreso_registro() {
        return fecha_ingreso_registro;
    }

    public void setFecha_ingreso_registro(String fecha_ingreso_registro) {
        this.fecha_ingreso_registro = fecha_ingreso_registro;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
    
    
    
    @Override
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn= new Conexion();
            cn.abrir_conexion();
            String query;
            query = "Select id_docente as id_docente,codigo_docente,nit,nombre,apellido,direccion,telefono,fecha_nacimiento,salario,fil,fir from docente1;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"id_docente","codigo_docente","nit","nombre","apellido","direccion","telefono","fecha_nacimiento","salario","fil","fir"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[]=new String[11];
            
            while(consulta.next()){
            datos[0] = consulta.getString("id_docente");
            datos[1] = consulta.getString("codigo_docente");
            datos[2] = consulta.getString("nit");
            datos[3] = consulta.getString("nombre");
            datos[4] = consulta.getString("apellido");
            datos[5] = consulta.getString("direccion");
            datos[6] = consulta.getString("telefono");
            datos[7] = consulta.getString("fecha_nacimiento");
            datos[8] = consulta.getString("salario");
            datos[9] = consulta.getString("fil");
            datos[10] = consulta.getString("fir");
            tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return tabla;
    }

     @Override
    public void crear (){
    try{
    PreparedStatement parametro;
    cn = new Conexion();
    cn.abrir_conexion();
    
        String query = "INSERT INTO docente1 (`codigo_docente`,`nit`,`nombres`,`apellidos`,`direccion`,`telefono`,`fecha_nacimiento`,`salario`,`fil`,`fir`) VALUES(?,?,?,?,?,??,?,?,?);";    
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getCodigo_docente());
        parametro.setString(2, getNit());
        parametro.setString(3, getNombre());
        parametro.setString(4, getApellido());
        parametro.setString(5, getDireccion());
        parametro.setString(6, getTelefono());
        parametro.setString(7, getFecha_nacimiento());
        parametro.setInt(8, getSalario());
        parametro.setString(9, getFecha_ingreso_laborar());
        parametro.setString(10, getFecha_ingreso_registro());
        int executar = parametro.executeUpdate();
        System.out.println("Ingreso Exitoso..." + Integer.toString(executar));
        
        cn.cerrar_conexion();
    } catch(SQLException ex) {
    System.out.println("error" + ex.getMessage());
    }
    
    }
           
    @Override
    public void actualizar(){
    try{
    PreparedStatement parametro;
    cn = new Conexion();
    cn.abrir_conexion();
    
        
        String query = "UPDATE docente1 SET codigo_docente = ?,nit = ?, nombre = ?, apellido = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, salario = ? , fil = ?, fir = ? WHERE id_docente = ?;";    
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getCodigo_docente());
        parametro.setString(2, getNit());
        parametro.setString(3, getNombre());
        parametro.setString(4, getApellido());
        parametro.setString(5, getDireccion());
        parametro.setString(6, getTelefono());
        parametro.setString(7, getFecha_nacimiento());
        parametro.setInt(8, getSalario());
        parametro.setString(9, getFecha_ingreso_laborar());
        parametro.setString(10, getFecha_ingreso_registro());
        parametro.setInt(11, getId());
        int executar = parametro.executeUpdate();
        System.out.println("Modificacinn exitosa..." + Integer.toString(executar));
        
        cn.cerrar_conexion();
    } catch(SQLException ex) {
    System.out.println("error en modificar" + ex.getMessage());
    }
    }
    
 public void eliminar(){
        try{
           PreparedStatement parametro;
           cn = new Conexion();
           cn.abrir_conexion();
           String query = "Delete from docente1 WHERE id_docente = ?;";
           parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
           parametro.setInt(1, this.getId());
           int executer = parametro.executeUpdate();
           System.out.println("Eliminacion exitosa");
           cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error al eliminar" + ex.getMessage());
        }
 }

    
    
    
}
