package me.peak;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlParser {
    public static void main(String[] args) {
//        String pom1 = "/Users/gaolei/IdeaProjects/data-di-apollo/apollo-server/pom.xml";
        String pom1 = "/Users/gaolei/IdeaProjects/data-di-apollo/server-authority/pom.xml";
//        String pom2 = "/Users/gaolei/IdeaProjects/data-di-apollo/self-service-bi/apollo-core/pom.xml";
        String pom2 = "/Users/gaolei/IdeaProjects/data-di-apollo/self-service-bi/apollo-query/pom.xml";
        compare(pom1, pom2);
    }

    static void compare(String pathName1, String pathName2) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            File file1 = new File(pathName1);
            Document document1 = builder.parse(file1);
            NodeList nodeList1 = document1.getElementsByTagName("dependency");

            File file2 = new File(pathName2);
            Document document2 = builder.parse(file2);
            NodeList nodeList2 = document2.getElementsByTagName("dependency");

            boolean find;
            for (int i = 0; i < nodeList1.getLength(); i++) {
                find = false;
                Node node1 = nodeList1.item(i);
                String node1Value = dependValue(node1);
                for (int j = 0; j < nodeList2.getLength(); j++) {
                    Node node2 = nodeList2.item(j);
                    String node2Value = dependValue(node2);
                    if (node1Value.equals(node2Value)) {
                        find = true;
                        break;
                    }
                }
                if (find) {
//                    System.out.println(node1Value + " find");
                } else {
                    System.out.println(node1Value + " not find");
                }

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    static String dependValue(Node node) {
        String groupId = "";
        String artifactId = "";
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if ("artifactId".equals(childNode.getNodeName())) {
                artifactId = childNode.getTextContent();
            } else if ("groupId".equals(childNode.getNodeName())) {
                groupId = childNode.getTextContent();
            }
        }
        return groupId + "|" + artifactId;

    }
}
