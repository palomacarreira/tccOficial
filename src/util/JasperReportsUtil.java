package util;

import java.io.File;
import java.util.List;
import java.util.Map;

import model.JasperPDF;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperReportsUtil {
	 public String geraRelatorio(List<JasperPDF> jasperPDF, Map<String, Object> parametros, String jasper, String nomeDestino, String pastaRaiz, String realPath) throws JRException, Exception{
		 try{
			 parametros.put("SUBREPORT_DIR", realPath+"/jasper/"); 
			
			 File file = new File(realPath+"/jasper/"+jasper);
			
			 JasperReport report =  (JasperReport) JRLoader.loadObject(file);
			 
			 JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(jasperPDF);
			 
			 JasperFillManager.fillReportToFile(report, pastaRaiz+"/"+nomeDestino+".jrprint", parametros, ds);
			 
			 JasperExportManager.exportReportToPdfFile(pastaRaiz+"/"+nomeDestino+".jrprint", pastaRaiz+"/"+nomeDestino+".pdf");
			 
			 return pastaRaiz+"/"+nomeDestino+".pdf";

		 }
		 catch(JRException jre){
			 jre.printStackTrace();
			 throw new JRException("");
		 }
	 }
}
