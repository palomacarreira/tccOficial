package model;

import java.sql.Date;

public class ContratoTO {

	public String cargo, codigo, duracaoSemanal;
	String diaPagamento;
	public Date dataDemissao, dataAdmissao;
	public Boolean descontoINSS;
	public Double valeTransporte, valorPagarDemissao, salarioBase, descontoBeneficios;
	public String compensacaoDias;
	public String regimeDeTrabalho, descricao, tipoDemissao;
	public String conta, agencia, banco, tipoConta;
	public String codigoUsuario, codigoEmpregado;
	
	
	public Double getDescontoBeneficios() {
		return descontoBeneficios;
	}
	public void setDescontoBeneficios(Double descontoBeneficios) {
		this.descontoBeneficios = descontoBeneficios;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getCodigoEmpregado() {
		return codigoEmpregado;
	}
	public void setCodigoEmpregado(String codigoEmpregado) {
		this.codigoEmpregado = codigoEmpregado;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getDiaPagamento() {
		return diaPagamento;
	}
	public void setDiaPagamento(String diaPagamento) {
		this.diaPagamento = diaPagamento;
	}
	public Date getDataDemissao() {
		return dataDemissao;
	}
	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Boolean getDescontoINSS() {
		return descontoINSS;
	}
	public void setDescontoINSS(Boolean descontoINSS) {
		this.descontoINSS = descontoINSS;
	}
	public Double getValeTransporte() {
		return valeTransporte;
	}
	public void setValeTransporte(Double valeTransporte) {
		this.valeTransporte = valeTransporte;
	}
	public Double getValorPagarDemissao() {
		return valorPagarDemissao;
	}
	public void setValorPagarDemissao(Double valorPagarDemissao) {
		this.valorPagarDemissao = valorPagarDemissao;
	}
	public Double getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}
	public String getCompensacaoDias() {
		return compensacaoDias;
	}
	public void setCompensacaoDias(String compensacaoDias2) {
		this.compensacaoDias = compensacaoDias2;
	}

	public String getRegimeDeTrabalho() {
		return regimeDeTrabalho;
	}
	public void setRegimeDeTrabalho(String regimeDeTrabalho) {
		this.regimeDeTrabalho = regimeDeTrabalho;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipoDemissao() {
		return tipoDemissao;
	}
	public void setTipoDemissao(String tipoDemissao) {
		this.tipoDemissao = tipoDemissao;
	}
	
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public String getDuracaoSemanal() {
		return duracaoSemanal;
	}
	public void setDuracaoSemanal(String duracaoSemanal) {
		this.duracaoSemanal = duracaoSemanal;
	}
	
}
