package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings( {"rawtypes", "unchecked"} )
public class ReportUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	public byte[] geraRelatorioPDF( List listaDados, String nomeRelatorio, ServletContext ServletContext) throws Exception{
		
		/*Cria a lisda de dados que vem do nossso sql da consulta feita*/
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = ServletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashMap(), jrbcds);
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
	}

}
