package Team2.BuildWeek3;

import Team2.BuildWeek3.entities.Comune;
import Team2.BuildWeek3.entities.Provincia;
import Team2.BuildWeek3.repositories.ComuneDAO;
import Team2.BuildWeek3.repositories.ProvinciaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class FillDatabaseProvince {
    @Autowired
    private ProvinciaDAO provinciaDAO;
    @Autowired
    ComuneDAO comuneDAO;


    public void fillDatabase() {
        String filePath = new File("src/main/resources/province-italiane.csv").getAbsolutePath();
        boolean isFirstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");
                String provincia = columns[1]; // Assumendo che la provincia sia nella quarta colonna

                // Salva la provincia nel database
                Provincia provinciaEntity = new Provincia();
                provinciaEntity.setNome(provincia);
                provinciaDAO.save(provinciaEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void fillDatabaseComuni() {
        String filePath = new File("src/main/resources/comuni-italiani.csv").getAbsolutePath();
        boolean isFirstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");
                String nome = columns[2]; // Assumendo che la provincia sia nella quarta colonna
                String provincia = columns[3]; // Assumendo che la provincia sia nella quarta colonna
                Comune comune=new Comune(nome,provinciaDAO.findByNomeLikeIgnoreCase(provincia));
                comuneDAO.save(comune);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}