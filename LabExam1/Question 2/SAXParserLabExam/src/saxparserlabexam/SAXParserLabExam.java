package saxparserlabexam;

import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


class SAXHandler extends DefaultHandler {

    int emailIndicator = 0;
    @Override
    public void startDocument() throws SAXException {       
    }

    @Override
    public void endDocument() throws SAXException {        
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {   
        if (qName == "Email"){
            emailIndicator = 1;
        }
        else{
            emailIndicator = 0;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (emailIndicator == 1){
            System.out.println(new String(ch, start, length));
        }        
    }
}

public class SAXParserLabExam {
    public static void main(String[] args) {
        SAXParserFactory saxParserFactoryObject = SAXParserFactory.newInstance();
        saxParserFactoryObject.setValidating(false);
        try{
            SAXParser saxParserObject = saxParserFactoryObject.newSAXParser();
            String path = "C:\\Users\\Admin\\Desktop\\2014A7PS0035U\\LabExam\\Question 2\\CompanyDetails.xml";
            SAXHandler saxHandlerObject = new SAXHandler();
            saxParserObject.parse(path, saxHandlerObject);
        }
        catch(Exception ex){}
    }    
}
