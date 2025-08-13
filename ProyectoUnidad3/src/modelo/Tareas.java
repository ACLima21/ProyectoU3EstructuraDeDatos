package modelo;

import java.time.LocalDateTime;

public class Tareas implements ArbolComparador{
	//ATRIBUTOS
	private String id,titulo,descripcion,fecha,categoria;
	private LocalDateTime date = LocalDateTime.now();
	
	//CONSTRUCTORES
	public Tareas(){
		id="";
		titulo="";
		descripcion="";
		fecha="";
		categoria="";
	}
	public Tareas(String titulo,String descripcion,String fecha,String categoria) {
		this.titulo=titulo;
		this.descripcion=descripcion;
		this.fecha=fecha;
		this.categoria=categoria;
		this.id=date.getSecond()+titulo.substring(0,1)+date.getMinute()+categoria.substring(0,4);
	}
	public Tareas(String id) {
		this.id=id;
		titulo="";
		descripcion="";
		fecha="";
		categoria="";
		
	}
	public Tareas(String id,String titulo,String descripcion,String fecha,String categoria) {
		this.id=id;
		this.titulo=titulo;
		this.descripcion=descripcion;
		this.fecha=fecha;
		this.categoria=categoria;
	}
	
	//GETTERS Y SETTERS
	public String getId() {return id;}
	public String getTitulo() {return titulo;}
	public void setTitulo(String titulo) {this.titulo = titulo;}
	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	public String getFecha() {return fecha;}
	public void setFecha(String fecha) {this.fecha = fecha;}
	public String getCategoria() {return categoria;}
	public void setCategoria(String categoria) {this.categoria = categoria;}
	
	//IMPLEMENTACIÓN DE COMPARADOR
	@Override
    public boolean igualQue(Object q) {
        Tareas p2 = (Tareas) q;
        return this.id.equals(p2.id);
    }

    @Override
    public boolean menorQue(Object q) {
        Tareas p2 = (Tareas) q;
        return this.id.compareTo(p2.id) < 0;
    }

    @Override
    public boolean menorIgualQue(Object q) {
        Tareas p2 = (Tareas) q;
        return this.id.compareTo(p2.id) < 0 || this.id.equals(p2.id);
    }

    @Override
    public boolean mayorQue(Object q) {
        Tareas p2 = (Tareas) q;
        return this.id.compareTo(p2.id) > 0;
    }

    @Override
    public boolean mayorIgualQue(Object q) {
        Tareas p2 = (Tareas) q;
        return this.id.compareTo(p2.id) > 0 || this.id.equals(p2.id);
    }

    //TO STRING
	@Override
	public String toString() {
		String info="";
		info+="ID: "+id+"\n";
		info+="Título: "+titulo+"\n";
		info+="Descripción: "+descripcion+"\n";
		info+="Fecha: "+fecha+"\n";
		info+="Categoría: "+categoria;
		return info;
	}
}
