package model;




public class FeriasTO {
	public Double valor;
	public String dataInicio, dataFinal, diaPagamento;
	public String PeriodoAquisitivoInicio, PeriodoAquisitivoFim;
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
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getDiaPagamento() {
		return diaPagamento;
	}
	public void setDiaPagamento(String diaPagamento) {
		this.diaPagamento = diaPagamento;
	}
	public String getPeriodoAquisitivoInicio() {
		return PeriodoAquisitivoInicio;
	}
	public void setPeriodoAquisitivoInicio(String periodoAquisitivoInicio) {
		PeriodoAquisitivoInicio = periodoAquisitivoInicio;
	}
	public String getPeriodoAquisitivoFim() {
		return PeriodoAquisitivoFim;
	}
	public void setPeriodoAquisitivoFim(String periodoAquisitivoFim) {
		PeriodoAquisitivoFim = periodoAquisitivoFim;
	}
}
