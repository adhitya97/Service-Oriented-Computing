
package calculator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CalculatorWebService", targetNamespace = "http://Calculator/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CalculatorWebService {


    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://Calculator/", className = "calculator.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://Calculator/", className = "calculator.HelloResponse")
    @Action(input = "http://Calculator/CalculatorWebService/helloRequest", output = "http://Calculator/CalculatorWebService/helloResponse")
    public String hello(
        @WebParam(name = "name", targetNamespace = "")
        String name);

    /**
     * 
     * @param number1
     * @param number2
     * @return
     *     returns int
     */
    @WebMethod(operationName = "Add")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "Add", targetNamespace = "http://Calculator/", className = "calculator.Add")
    @ResponseWrapper(localName = "AddResponse", targetNamespace = "http://Calculator/", className = "calculator.AddResponse")
    @Action(input = "http://Calculator/CalculatorWebService/AddRequest", output = "http://Calculator/CalculatorWebService/AddResponse")
    public int add(
        @WebParam(name = "number1", targetNamespace = "")
        int number1,
        @WebParam(name = "number2", targetNamespace = "")
        int number2);

}
