package DAO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesReader {

    public static Properties readProperties(String path) throws IOException {

        Properties props = new Properties();
        Path myPath = Paths.get(path);

        BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
        props.load(bf);

        return props;
    }
}