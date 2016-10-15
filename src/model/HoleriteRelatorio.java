package model;

import java.sql.Date;

public class HoleriteRelatorio {

	public String nomeEmpregador;
	public String idEmpregado;
	public String nomeEmpregado;
	public String cargoEmpregado;
	public double salarioBase;
	public double descontos;
	public double valeTransporte;
	public double horaExtra;
	public double descontoBeneficios;
	public double valorDescontoInss;
	public double descontoIrrf;
	public double fgts;
	public double salarioLiquido;
	public Date dataDoHolerite;
	
	public String getNomeEmpregador() {
		return nomeEmpregador;
	}
	public void setNomeEmpregador(String nomeEmpregador) {
		this.nomeEmpregador = nomeEmpregador;
	}
	public String getIdEmpregado() {
		return idEmpregado;
	}
	public void setIdEmpregado(String idEmpregado) {
		this.idEmpregado = idEmpregado;
	}
	public String getNomeEmpregado() {
		return nomeEmpregado;
	}
	public void setNomeEmpregado(String nomeEmpregado) {
		this.nomeEmpregado = nomeEmpregado;
	}
	public String getCargoEmpregado() {
		return cargoEmpregado;
	}
	public void setCargoEmpregado(String cargoEmpregado) {
		this.cargoEmpregado = cargoEmpregado;
	}
	public double getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}
	public double getDescontos() {
		return descontos;
	}
	public void setDescontos(double descontos) {
		this.descontos = descontos;
	}
	public double getValorDescontoInss() {
		return valorDescontoInss;
	}
	public void setValorDescontoInss(double valorDescontoInss) {
		this.valorDescontoInss = valorDescontoInss;
	}
	public double getSalarioLiquido() {
		return salarioLiquido;
	}
	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}
	public Date getDataDoHolerite() {
		return dataDoHolerite;
	}
	public void setDataDoHolerite(Date dataDoHolerite) {
		this.dataDoHolerite = dataDoHolerite;
	}
	public double getHoraExtra() {
		return horaExtra;
	}
	public void setHoraExtra(double horaExtra) {
		this.horaExtra = horaExtra;
	}
	public double getValeTransporte() {
		return valeTransporte;
	}
	public void setValeTransporte(double valeTransporte) {
		this.valeTransporte = valeTransporte;
	}
	public double getDescontoBeneficios() {
		return descontoBeneficios;
	}
	public void setDescontoBeneficios(double descontoBeneficios) {
		this.descontoBeneficios = descontoBeneficios;
	}
	public double getDescontoIrrf() {
		return descontoIrrf;
	}
	public void setDescontoIrrf(double descontoIrrf) {
		this.descontoIrrf = descontoIrrf;
	}
	public double getFgts() {
		return fgts;
	}
	public void setFgts(double fgts) {
		this.fgts = fgts;
	}
}
