package model;

import java.sql.Date;

public class AtividadesTO {
	private int id;
	private Date data;
	private String titulo, descricao;
	private Boolean realizado;
	private int FkEmpregado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getRealizado() {
		return realizado;
	}
	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}
	public int getFkEmpregado() {
		return FkEmpregado;
	}
	public void setFkEmpregado(int fkEmpregado) {
		FkEmpregado = fkEmpregado;
	}
	
}
