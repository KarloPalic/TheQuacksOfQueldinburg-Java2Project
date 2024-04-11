package hr.algebra.thequacksofquedlinburg.utils;

import hr.algebra.thequacksofquedlinburg.gameBoard.GameMove;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XmlUtil {
    public static final String FILENAME = "./xml/game_moves.xml";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void saveGameMoveToXml(GameMove gameMove) {
        createXmlFileIfNotExists(); // Check and create XML file if not exists

        List<GameMove> gameMoveList = readGameMovesFromXmlFile();
        gameMoveList.add(gameMove);

        try {
            Document document = createDocument("gameMoves");

            for (GameMove gameMoveXmlNode : gameMoveList) {
                Element gameMoveElement = document.createElement("gameMove");
                document.getDocumentElement().appendChild(gameMoveElement);

                gameMoveElement.appendChild(createElement(document, "playerColor", gameMoveXmlNode.getPlayerColor().name()));
                gameMoveElement.appendChild(createElement(document, "playerPosition", gameMoveXmlNode.getPlayerPosition()));
                gameMoveElement.appendChild(createElement(document, "dateTime", gameMoveXmlNode.getDateTime().format(formatter)));
            }
            saveDocument(document, FILENAME);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createXmlFileIfNotExists() {
        File file = new File(FILENAME);
        if (!file.exists()) {
            try {
                Document document = createDocument("gameMoves");
                saveDocument(document, FILENAME);
            } catch (ParserConfigurationException | TransformerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Document createDocument(String element) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation domImplementation = builder.getDOMImplementation();
        DocumentType documentType = domImplementation.createDocumentType("DOCTYPE", null, "employees.dtd");
        return domImplementation.createDocument(null, element, documentType);
    }

    private static Node createElement(Document document, String tagName, String data) {
        Element element = document.createElement(tagName);
        Text text = document.createTextNode(data);
        element.appendChild(text);
        return element;
    }

    private static void saveDocument(Document document, String fileName) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, document.getDoctype().getSystemId());
        transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));
    }

    public static List<GameMove> readGameMovesFromXmlFile() {
        List<GameMove> gameMovesList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(FILENAME));
            Node node = document.getDocumentElement();

            NodeList childNodes = node.getChildNodes();

            int numberOfNodes = childNodes.getLength();

            for (int n = 0; n < numberOfNodes; n++) {
                Node parentNode = childNodes.item(n);

                if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList gameMoveNodes = parentNode.getChildNodes();

                    Team gameMovePlayerColor = Team.Blue;
                    String playerPosition = "";
                    String localDateTimeString = "";

                    for (int i = 0; i < gameMoveNodes.getLength(); i++) {
                        Node moveNode = gameMoveNodes.item(i);

                        if (moveNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element nodeElement = (Element) moveNode;
                            String nodeName = nodeElement.getNodeName();
                            String nodeText = nodeElement.getTextContent();
                            if (nodeName.equals("playerColor")) {
                                String playerColor = nodeElement.getTextContent();
                                if (playerColor.equals("Red")) {
                                    gameMovePlayerColor = Team.Red;
                                }
                            } else if (nodeName.equals("playerPosition")) {
                                playerPosition = nodeText;
                            } else if (nodeName.equals("dateTime")) {
                                localDateTimeString = nodeText;
                            }
                        }
                    }
                    if (!playerPosition.isEmpty()) {
                        LocalDateTime dateTime = LocalDateTime.parse(localDateTimeString, formatter);
                        GameMove gameMove = new GameMove(gameMovePlayerColor, playerPosition, dateTime);
                        gameMovesList.add(gameMove);
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        return gameMovesList;
    }
}
