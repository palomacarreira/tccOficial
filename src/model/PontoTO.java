package model;

import java.sql.Date;

public class PontoTO {
	public Date data;
	Boolean falta, faltaJustificada;
	String codigoEmpregado, codigo, horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida;
	
	
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
