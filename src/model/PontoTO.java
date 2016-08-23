package model;

import java.sql.Date;

public class PontoTO {
	public Date dataPonto;
	String codigoEmpregado, codigo, horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida;
	String acao;
	
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigoEmpregado() {
		return codigoEmpregado;
	}

	public void setCodigoEmpregado(String codigoEmpregado) {
		this.codigoEmpregado = codigoEmpregado;
	}

	public String getHoraSaidaAlmoco() {
		return horaSaidaAlmoco;
	}

	public void setHoraSaidaAlmoco(String horaSaidaAlmoco) {
		this.horaSaidaAlmoco = horaSaidaAlmoco;
	}

	public String getHoraVoltaAlmoco() {
		return horaVoltaAlmoco;
	}

	public void setHoraVoltaAlmoco(String horaVoltaAlmoco) {
		this.horaVoltaAlmoco = horaVoltaAlmoco;
	}


	public Date getDataPonto() {
		return dataPonto;
	}

	public void setDataPonto(Date data) {
		this.dataPonto = data;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada2) {
		this.horaEntrada = horaEntrada2;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	
	
}
