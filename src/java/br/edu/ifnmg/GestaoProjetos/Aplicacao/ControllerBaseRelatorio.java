/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Aplicacao;


import br.edu.ifnmg.DomainModel.Entidade;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author petronio
 * @param <T>
 */
public abstract class ControllerBaseRelatorio<T extends Entidade> extends ControllerBase {


    
    private String relatorio;
    private String arquivoSaida;


    private List<T> dados;

    protected abstract Map<String, Object> carregaParametros();

    public abstract List<T> getDados();

    public Map<String, Object> getParametrosComuns() throws MalformedURLException {
        HashMap<String, Object> par = new HashMap<>();
        //String tmp = getConfiguracao("DIRETORIO_ARQUIVOS") + evento.getBanner().getUri();
        //par.put("banner", tmp);
        par.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));  
        return par;
    }

    public void executaRelatorioPDF() throws JRException, IOException {

        InputStream reportStream = null;
        try {
            reportStream = getClass().getResourceAsStream(relatorio);
            //JasperReport report = JasperCompileManager.compileReport(reportStream);

            JRDataSource ds = new JRBeanCollectionDataSource(getDados(), true);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(report, carregaParametros(), ds);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, carregaParametros(), ds);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + getArquivoSaida() + ".pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException ex) {
            Logger.getLogger(ControllerBaseRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    public String getArquivoSaida() {
        return arquivoSaida;
    }

    public void setArquivoSaida(String arquivoSaida) {
        this.arquivoSaida = arquivoSaida;
    }

}
