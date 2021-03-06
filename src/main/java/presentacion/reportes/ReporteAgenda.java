package presentacion.reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import dto.PersonaDTO;

public class ReporteAgenda {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	private Logger log = Logger.getLogger(ReporteAgenda.class);
	private ComparadorCodigoPostal comparador = new ComparadorCodigoPostal ();
	
	public ReporteAgenda(List<PersonaDTO> personas) {
		ordenarPorCodigoPostal(personas);
		
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		try {
			String  ruta = new File("reportes"+File.separator+"ReporteAgenda.jasper").getAbsoluteFile().toString();
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,new JRBeanCollectionDataSource(personas));
			log.info("Se cargó correctamente el reporte");
		} catch (JRException ex) {
			log.error("Ocurrio un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
	}

	private void ordenarPorCodigoPostal (List<PersonaDTO> personas) {
		Collections.sort(personas, this.comparador);
	}
	
	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}
	
}