package stubs;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtils {

    public static String getJsonFromFile(final String fileName) {
        return getJson(fileName, FileUtils.class);
    }

    private static String getJson(String fileName,final Class<?> callerClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = null;
        try (InputStream inputStream = callerClass.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found!");
            }

            content = new Scanner(inputStream, StandardCharsets.UTF_8)
                    .useDelimiter("\\A")
                    .next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
