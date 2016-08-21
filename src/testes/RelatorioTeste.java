package testes;

import java.util.ArrayList;
import java.util.List;

import relatorio.HoleriteRelatorio;
import model.FolhaPagamentoTO;

 
public class RelatorioTeste{
	public static void main(String[] args){
		try{
			List<FolhaPagamentoTO> holerites = new ArrayList<FolhaPagamentoTO>();
			System.out.println("Inicio extraindo PDF");
			
			FolhaPagamentoTO folhaPagamentoTO1 = new FolhaPagamentoTO();
			//folhaPagamentoTO1.setDecimoTerceiro(2.0);
			folhaPagamentoTO1.setDecTercPrimeiro(2.0);
			folhaPagamentoTO1.setDecTercSegunda(2.0);
			//folhaPagamentoTO1.setIR_retido(2.0);
			folhaPagamentoTO1.setSalarioLiquido(2.0);
			folhaPagamentoTO1.setFgts(2.0);
			folhaPagamentoTO1.setInss(2.0);
			folhaPagamentoTO1.setTotalPagarHoraExtra(2.0);
			
			/*FolhaPagamentoTO folhaPagamentoTO2 = new FolhaPagamentoTO();
			folhaPagamentoTO2.setDecimoTerceiro(2.0);
			folhaPagamentoTO2.setDecTercPrimeiro(2.0);
			folhaPagamentoTO2.setDecTercSegunda(2.0);
			folhaPagamentoTO2.setIR_retido(2.0);
			folhaPagamentoTO2.setSalarioLiquido(2.0);
			folhaPagamentoTO2.setFgts(2.0);
			folhaPagamentoTO2.setInss(2.0);
			folhaPagamentoTO2.setTotalPagarHoraExtra(2.0);
			
			FolhaPagamentoTO folhaPagamentoTO3 = new FolhaPagamentoTO();
			folhaPagamentoTO3.setDecimoTerceiro(2.0);
			folhaPagamentoTO3.setDecTercPrimeiro(2.0);
			folhaPagamentoTO3.setDecTercSegunda(2.0);
			folhaPagamentoTO3.setIR_retido(2.0);
			folhaPagamentoTO3.setSalarioLiquido(2.0);
			folhaPagamentoTO3.setFgts(2.0);
			folhaPagamentoTO3.setInss(2.0);
			folhaPagamentoTO3.setTotalPagarHoraExtra(2.0);*/
			
			
			holerites.add(folhaPagamentoTO1);
		
			
			
			HoleriteRelatorio relatorio = new HoleriteRelatorio();
			relatorio.imprimir(holerites);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}