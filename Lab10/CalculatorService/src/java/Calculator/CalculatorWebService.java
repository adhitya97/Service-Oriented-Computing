package Calculator;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "CalculatorWebService")
public class CalculatorWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Add")
    public int Add(@WebParam(name = "number1") int number1, @WebParam(name = "number2") int number2) {
        //TODO write your implementation code here:
        return number1 + number2;
    }
}
