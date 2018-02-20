package saxparserproject;

import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


class SAXHandler extends DefaultHandler {

    // To keep track of the number of occurences
    Hashtable<String, Integer> occurenceHashtable = new Hashtable<>();
    
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start of document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End of document");
        System.out.print("Occurence Count - ");
        System.out.println(occurenceHashtable.toString());
        
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {   
        System.out.print("Start element    : " + qName);
        if (occurenceHashtable.containsKey(qName)){
            occurenceHashtable.replace(qName, occurenceHashtable.get(qName), occurenceHashtable.get(qName)+1);
        }
        else{
            occurenceHashtable.put(qName, 1);
        }
        if (attributes.getLength() > 0){
            System.out.print(" (Attributes of the element: ");
            for (int i=0; i< attributes.getLength(); ++i){
                System.out.print(" "+attributes.getQName(i)+ "=" + attributes.getValue(i));
            }
            System.out.print(")");
        }
        System.out.println();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End element      : " + qName);
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        System.out.println("Start characters : " + new String(ch, start, length));
    }
}

public class SAXParserProject {
    public static void main(String[] args) {
        SAXParserFactory saxParserFactoryObject = SAXParserFactory.newInstance();
        saxParserFactoryObject.setValidating(false);
        try{
            SAXParser saxParserObject = saxParserFactoryObject.newSAXParser();
            String path = "C:\\Users\\Admin\\Desktop\\2014A7PS0035U\\Lab5\\BookStore.xml";
            SAXHandler saxHandlerObject = new SAXHandler();
            saxParserObject.parse(path, saxHandlerObject);
        }
        catch(Exception ex){}
    }   
}
