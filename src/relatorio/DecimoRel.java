package relatorio;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.DecimoRelatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DecimoRel {
private String path; //Caminho base
	
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	
	//Recupera os caminhos para que a classe possa encontrar os relatórios
	public DecimoRel() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path;
		System.out.println(path);
	}
	
	
	//Imprime
	public void imprimir(List<DecimoRelatorio> decimoRelatorio){
		JasperReport report = null;
		try {
			//report = JasperCompileManager.compileReport("reports/jasper/reportDecimo.jrxml");
			report = JasperCompileManager.compileReport(this.getPathToReportPackage()+ "jasper/reportDecimo.jrxml");
			System.out.println("LOCAL ONDE TEM QUE TA O RELATORIO: " + this.getPathToReportPackage()+ "jasper/reportDecimo.jrxml");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			System.out.println("compileReport");
			e.printStackTrace();
		}
		
		JasperPrint print = null;
		try {
			print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(decimoRelatorio));
		} catch (JRException e) {
			// TODO Auto-generated catch block
			System.out.println("fillReport");
			e.printStackTrace();
		}
 
		//JasperExportManager.exportReportToPdfFile(print, "/Users/palomacarreira/Desktop/Relatorio_de_Clientes.pdf");
		try {
			JasperExportManager.exportReportToPdfFile(print, this.getPathToReportPackage()+ decimoRelatorio.get(0).getAno()+decimoRelatorio.get(0).getIdEmpregado());//"pdf/Relatorio_de_Clientes2.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			System.out.println("exportReportToPdfFile");
			e.printStackTrace();
		}		
	}
	
	//Efetua Dowload
	public void download(HttpServletResponse response, List<DecimoRelatorio> decimoRelatorio){
		File arquivo = new File(this.getPathToReportPackage()+ decimoRelatorio.get(0).getAno()+decimoRelatorio.get(0).getIdEmpregado());//"pdf/Relatorio_de_Clientes2.pdf");
		Path path = arquivo.toPath();

		OutputStream output = null;
		try {
			output = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Files.copy(path, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // escreve bytes no fluxo de saída  
	}
	
 
	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}
	
	public String getPath() {
		return this.path;
	}
}
