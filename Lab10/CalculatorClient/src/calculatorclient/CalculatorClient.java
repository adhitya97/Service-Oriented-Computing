/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorclient;

/**
 *
 * @author Admin
 */
public class CalculatorClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(add(2, 3));
    }

    private static int add(int number1, int number2) {
        calculator.CalculatorWebService_Service service = new calculator.CalculatorWebService_Service();
        calculator.CalculatorWebService port = service.getCalculatorWebServicePort();
        return port.add(number1, number2);
    }
    
}
