package model;

import java.sql.Date;

public class FeriasTO {
	public Double valor;
	public Date dataInicio, dataFinal, diaPagamento;
	public Date PeriodoAquisitivoInicio, PeriodoAquisitivoFim;
	public String situacao;
	public Boolean vendaFerias;
	public int qtdDiasFerias;
	public String codigoContrato, codigo;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	public int getQtdDiasFerias() {
		return qtdDiasFerias;
	}
	public void setQtdDiasFerias(int qtdDiasFerias) {
		this.qtdDiasFerias = qtdDiasFerias;
	}
	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Boolean getVendaFerias() {
		return vendaFerias;
	}
	public void setVendaFerias(Boolean vendaFerias) {
		this.vendaFerias = vendaFerias;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Date getDiaPagamento() {
		return diaPagamento;
	}
	public void setDiaPagamento(Date diaPagamento) {
		this.diaPagamento = diaPagamento;
	}

	public Date getPeriodoAquisitivoInicio() {
		return PeriodoAquisitivoInicio;
	}
	public void setPeriodoAquisitivoInicio(Date periodoAquisitivoInicio) {
		PeriodoAquisitivoInicio = periodoAquisitivoInicio;
	}
	public Date getPeriodoAquisitivoFim() {
		return PeriodoAquisitivoFim;
	}
	public void setPeriodoAquisitivoFim(Date periodoAquisitivoFim) {
		PeriodoAquisitivoFim = periodoAquisitivoFim;
	}


}
