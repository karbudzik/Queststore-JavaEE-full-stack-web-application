import DAO.CodecoolerClassDAO;
import DAO.CodecoolerClassJDBCDAO;
import DAO.CodecoolerDAO;
import DAO.DataSourceReader;
import exception.ReadException;
import model.CodecoolerClass;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PGSimpleDataSource dataSource = null;

        try {
            dataSource = DataSourceReader.getDataSource("src/main/resources/database.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CodecoolerClassDAO dao = new CodecoolerClassJDBCDAO(dataSource);

        try {
            dao.addCodecoolerClass(new CodecoolerClass.Builder()
                    .withName("SuperKlasa")
                    .withCity("Mielec")
                    .withStartDate(new java.sql.Date(213423653423L))
                    .withEndDate(new java.sql.Date(3456754345654L))
                    .build());
//            dao.deleteCodecoolerClass(3);
            List<CodecoolerClass> classes = dao.getAllCodecoolerClasss();
            classes.stream().forEach(x -> System.out.println(x));
//            System.out.println(dao.getCodecoolerClassById());
        } catch (ReadException e) {
            e.printStackTrace();
        }
    }
}
