package DAO;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DataSourceReader {

    public static PGSimpleDataSource getDataSource(String path) throws IOException {
        Properties props = new Properties();
        Path myPath = Paths.get(path);

        BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
        props.load(bf);

        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setURL(props.getProperty("db.url"));
        ds.setUser(props.getProperty("db.user"));
        ds.setPassword(props.getProperty("db.passwd"));
        return ds;
    }
}
