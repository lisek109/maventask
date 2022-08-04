package files.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TextReader2 {

    private static final FileValidator<String> validator = new Validator();
    private static final Logger LOGGER2 = LoggerFactory.getLogger(TextReader2.class);

    protected String getString() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid;
        String name;
        do {
            LOGGER2.warn("Please enter a file name.");
            name = scanner.nextLine();
            isValid = validator.validate(name);
            if (!isValid) {
                LOGGER2.warn("Name of file is incorrect. Try again!");
            }
        } while (!isValid);
        scanner.close();
        return name;
    }


    public String parseInput(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        StringBuilder output = new StringBuilder();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line.replace(" ", ""));
            }
        } catch (NullPointerException e) {
            LOGGER2.error("File could not be read! Check if resources folder contains file with given name.");
        } catch (IOException e) {
            LOGGER2.error("File could not be read!");
            e.printStackTrace();
        }
        return output.toString();
    }


    public void showFileContent()  {
        LOGGER2.info(parseInput(getString()));
    }
}
