package Team2.BuildWeek3;
import Team2.BuildWeek3.entities.Provincia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;


public class FillDatabaseComuni {
    public static void main(String[] args) {
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
                String nun = columns[0];
//                String nun2 = columns[1];
                String nome = columns[2];
//                String provincia = columns[3];
                Provincia provincia1 = new Provincia(nome);

//                Comune comune = new Comune(nome,provincia1);

//                System.out.println(nome+ " " + provincia);
                System.out.println(provincia1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}