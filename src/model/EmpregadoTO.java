package model;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.sql.Date;

public class EmpregadoTO {
	public String nome, sobrenome, estadoCivil, sexo, cpf;
	Date dataNasc;
	public String rg, ufRg, numCarteira, serieCarteira, ufCarteira, senha;
	public Boolean ativo;
	public byte[] foto;
	public int qtdDependentes;
	public String codigoEmpregado;
	private String email;

	public String getCodigoEmpregado() {
		return codigoEmpregado;
	}
	public void setCodigo(String codigo) {
		this.codigoEmpregado = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc2) {
		this.dataNasc = dataNasc2;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getUfRg() {
		return ufRg;
	}
	public void setUfRg(String ufRg) {
		this.ufRg = ufRg;
	}
	public String getNumCarteira() {
		return numCarteira;
	}
	public void setNumCarteira(String numCarteira) {
		this.numCarteira = numCarteira;
	}
	public String getSerieCarteira() {
		return serieCarteira;
	}
	public void setSerieCarteira(String serieCarteira) {
		this.serieCarteira = serieCarteira;
	}
	public String getUfCarteira() {
		return ufCarteira;
	}
	public void setUfCarteira(String ufCarteira) {
		this.ufCarteira = ufCarteira;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] bs) {
		this.foto = bs;
	}
	public int getQtdDependentes() {
		return qtdDependentes;
	}
	public void setQtdDependentes(int qtdDependentes) {
		this.qtdDependentes = qtdDependentes;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
