package domparser;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DOMParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DocumentBuilderFactory documentBuilderFactoryObject = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryObject.setValidating(false);
        try{
            DocumentBuilder documentBuilderObject = documentBuilderFactoryObject.newDocumentBuilder();
            String path = "C:\\Users\\Admin\\Desktop\\2014A7PS0035U\\Lab3\\BookStore.xml";
            Document documentObject = documentBuilderObject.parse(path);
            
            // Get root element
            Element rootElement = documentObject.getDocumentElement();
            String rootTagName = rootElement.getTagName();
            String rootNodeName = rootElement.getNodeName();
            String rootValue = rootElement.getNodeValue();
            String rootAttribute = rootElement.getAttribute("sampleAttrib");
            String rootLocalName = rootElement.getLocalName();
            System.out.println("Root Tag Name = "+rootTagName);
            System.out.println("Root Node Name = "+rootNodeName);
            System.out.println("Root Value = "+rootValue);
            System.out.println("Root Attribute (sampleAttribute)= "+rootAttribute);
            System.out.println("Root Local Name = "+rootLocalName);
            System.out.println("");
            
            // Get all the child elements for the root
            NodeList childNodeList = rootElement.getChildNodes();
            System.out.println("Child Nodes Count = "+childNodeList.getLength());
            for (int i=0; i<childNodeList.getLength(); ++i){
                Node eachBookNode = childNodeList.item(i);
                if (eachBookNode.getNodeName() != "#text"){
                    System.out.print("Child "+ (i+1) + " --> Name = "+ eachBookNode.getNodeName() + ", Value = "+ eachBookNode.getNodeValue() + ", Attribute (Category) = ");
                    NamedNodeMap bookAttributeMap = eachBookNode.getAttributes();
                    System.out.print(bookAttributeMap.getNamedItem("Category").getNodeValue());
                    
                    // Children nodes of each book
                    NodeList bookElementValues = eachBookNode.getChildNodes();
                    for (int j=0; j<bookElementValues.getLength(); ++j){
                        Node eachBookAttributeNode = bookElementValues.item(j);
                        if (eachBookAttributeNode.getNodeName() != "#text"){
                            System.out.print(", " + eachBookAttributeNode.getNodeName()+ " = " + eachBookAttributeNode.getTextContent());
                        }
                    }
                    System.out.println("");
                    
                }                    
            }
        }
        catch(Exception e){
            
        }
    }
}
