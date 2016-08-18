package model;

import java.sql.Date;

public class PontoTO {
	public Date data, horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida;
	Boolean falta, faltaJustificada;
	String codigoEmpregado, codigo;
	
	
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

	public Date getHoraSaidaAlmoco() {
		return horaSaidaAlmoco;
	}

	public void setHoraSaidaAlmoco(Date horaSaidaAlmoco) {
		this.horaSaidaAlmoco = horaSaidaAlmoco;
	}

	public Date getHoraVoltaAlmoco() {
		return horaVoltaAlmoco;
	}

	public void setHoraVoltaAlmoco(Date horaVoltaAlmoco) {
		this.horaVoltaAlmoco = horaVoltaAlmoco;
	}

	public Boolean getFalta() {
		return falta;
	}

	public void setFalta(Boolean falta) {
		this.falta = falta;
	}

	public Boolean getFaltaJustificada() {
		return faltaJustificada;
	}

	public void setFaltaJustificada(Boolean faltaJustificada) {
		this.faltaJustificada = faltaJustificada;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}
	
	
}
