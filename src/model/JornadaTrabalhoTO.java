package model;

public class JornadaTrabalhoTO {
	
	public String horaEntrada, horaSaidaAlmoco, horaVoltaAlmoco, horaSaida;
	public int diaSemana;
	public Boolean diaFolga, diaMeioPeriodo;
	public String codigoContrato,codigo;
	
	
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
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada2) {
		this.horaEntrada = horaEntrada2;
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
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	public int getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Boolean getDiaFolga() {
		return diaFolga;
	}
	public void setDiaFolga(Boolean diaFolga) {
		this.diaFolga = diaFolga;
	}
	public Boolean getDiaMeioPeriodo() {
		return diaMeioPeriodo;
	}
	public void setDiaMeioPeriodo(Boolean diaMeioPeriodo) {
		this.diaMeioPeriodo = diaMeioPeriodo;
	}
	
	
}
