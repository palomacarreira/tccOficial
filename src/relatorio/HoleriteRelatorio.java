package relatorio;

import java.util.List;

import model.FolhaPagamentoTO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

 
public class HoleriteRelatorio 
{
	private String path; //Caminho base
	
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	
	//Recupera os caminhos para que a classe possa encontrar os relatórios
	public HoleriteRelatorio() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "reports/jasper/";
		System.out.println(path);
	}
	
	
	//Imprime/gera uma lista de Holerite
	public void imprimir(List<FolhaPagamentoTO> holerites) throws Exception	{
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "Holerite.jrxml");
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(holerites));
 
		JasperExportManager.exportReportToPdfFile(print, "User/palomacarreira/Desktop/Relatorio_de_Holerite.pdf");		
	}
 
	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}
	
	public String getPath() {
		return this.path;
	}
}