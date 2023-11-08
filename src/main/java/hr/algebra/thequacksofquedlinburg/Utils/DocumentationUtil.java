package hr.algebra.thequacksofquedlinburg.Utils;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import javafx.scene.Scene;


public class DocumentationUtil {
    public static final String THE_QUACKS_PATH = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\target\\classes\\hr\\algebra\\thequacksofquedlinburg";
    public static final String CONTROLLER_PATH = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\target\\classes\\hr\\algebra\\thequacksofquedlinburg\\Controllers";
    public static final String GAME_BOARD_PATH  = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\target\\classes\\hr\\algebra\\thequacksofquedlinburg\\GameBoard";
    public static final String ENUM_PATH  = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\target\\classes\\hr\\algebra\\thequacksofquedlinburg\\GameBoard\\enums";
    public static final String UTILS_PATH  = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\target\\classes\\hr\\algebra\\thequacksofquedlinburg\\Utils";


    public static final String HTML_QUACKS  = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\files\\documentation.html";
    public static final String HTML_CONTROLLERS  = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\files\\Controllers\\controllerDoc.html";
    public static final String HTML_ENUMS  = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\files\\Enums\\enumDoc.html";
    public static final String HTML_GAME_BOARD  = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\files\\GameBoard\\gameBoardDoc.html";
    public static final String HTML_UTILS  = "C:\\Users\\wExzEk\\Desktop\\Year 3\\Java 2\\The Quacks of Quedlinburg\\files\\Utils\\utilsDoc.html";



    public void generateDocumentation(String location, String outputPath){
            try (Stream<Path> stream = Files.walk(Paths.get(location))) {
                List<String> filePathsList = stream.filter(Files::isRegularFile)
                        .map(Path::toString)
                        .filter(string -> string.endsWith(".class"))
                        .filter(string -> !string.endsWith("module-info.class"))
                        .toList();

                StringBuilder documentationContent = new StringBuilder();
                String existingHtmlContent = Files.exists(Paths.get(outputPath)) ? Files.readString(Paths.get(outputPath)) : "";

                for (String path : filePathsList) {
                    String[] pathTokens = path.split("classes");
                    String secondToken = pathTokens[1].substring(1, pathTokens[1].lastIndexOf('.'));
                    String fullyQualifiedName = secondToken.replace('\\', '.');

                    Class<?> clazz = Class.forName(fullyQualifiedName);

                    if (existingHtmlContent.contains(fullyQualifiedName)) {
                        continue;
                    }

                    Field[] classFields = clazz.getDeclaredFields();

                    for (Field field : classFields) {
                        int modifiers = field.getModifiers();

                        // Append documentation data to the StringBuilder
                        documentationContent.append("Field: " + field + "<br>");
                        documentationContent.append("Modifiers: " + modifiers + "<br>");
                        documentationContent.append("Type: " + field.getType().getTypeName() + "<br>");
                        documentationContent.append("Name: " + field.getName() + "<br><br>");
                    } // Replace with your actual content

                }
                existingHtmlContent += documentationContent.toString();

                // Write the updated HTML content back to the HTML file
                Files.write(Paths.get(outputPath), existingHtmlContent.getBytes());

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

}
