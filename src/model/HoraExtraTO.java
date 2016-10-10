package model;

public class HoraExtraTO {
	public String totalDeHorasExtras;
	public String totalDeHorasExtrasNoturno;
	public boolean folgaCompensatoria;
	public String codigoPonto;
	
	
	
	public boolean getFolgaCompensatoria() {
		return folgaCompensatoria;
	}

	public void setFolgaCompensatoria(boolean folgaCompensatoria) {
		this.folgaCompensatoria = folgaCompensatoria;
	}

	public String getCodigoPonto() {
		return codigoPonto;
	}
	
	public void setCodigoPonto(String codigoPonto) {
		this.codigoPonto = codigoPonto;
	}
	
	public String getTotalDeHorasExtrasNoturno() {
		return totalDeHorasExtrasNoturno;
	}

	public String getTotalDeHorasExtras() {
		return totalDeHorasExtras;
	}

	public void setTotalDeHorasExtras(String totalDeHorasExtras) {
		this.totalDeHorasExtras = totalDeHorasExtras;
		
	}
	public void setTotalDeHorasExtrasNoturno(String totalDeHorasExtrasNoturno) {
		this.totalDeHorasExtrasNoturno = totalDeHorasExtrasNoturno;
	}
	
	
	
}
