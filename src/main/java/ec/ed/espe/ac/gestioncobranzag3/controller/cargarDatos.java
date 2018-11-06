package ec.ed.espe.ac.gestioncobranzag3.controller;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import sun.applet.Main;
/**
 *
 * @author jhona
 */
@Named
@ViewScoped
public class cargarDatos implements Serializable{

    public void cargar() {
        int cont = 1;
        System.out.println("Entro a cargar...");
            File fileMora = new File("c:\\tmp\\mora.txt");
            List<String> readLines = null;
            try {
                readLines = verificateFiles(fileMora);
            } catch (IOException ex) {
                Logger.getLogger(cargarDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (readLines != null) {
                transferData(readLines);
            }
            System.out.println("Dia " + cont);
            cont++;       
    }
    private List<String> verificateFiles(File file) throws IOException {
        List<String> readLines;
        if (file.exists()) {
            readLines = FileUtils.readLines(file, "UTF-8");
        } else {

            readLines = null;
        }
        return readLines;
    }

    private void transferData(List<String> readSource) {
        Iterator iter = readSource.iterator();
        String[] values;
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            values = iter.next().toString().split(",");
            System.out.println("Hola : "+values[0]+" "+values[1]);
        }
    }
    
}
