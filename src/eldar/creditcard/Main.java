package eldar.creditcard;

import eldar.creditcard.models.CardIssuer;
import eldar.creditcard.models.CreditCard;
import eldar.creditcard.models.Operation;
import eldar.creditcard.models.Person;
import eldar.creditcard.service.Processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class Main {

    final static String API_URL = "https://gixjlcaka3.execute-api.us-east-1.amazonaws.com/dev/rate";
    final static String jsonInputString = "{\"amount\": \"500.0\"," +
                                          " \"brand\": \"VISA\"," +
                                          " \"year\": \"2025\"," +
                                          " \"month\": \"6\"," +
                                          " \"day\": \"15\"}";

    final static long ccNumber1 = 5469875213659875L;
    final static long ccNumber2 = 154896547236587L;

    final static double opAmount = 500.0;
    final static LocalDate opDate = LocalDate.of(2022, 10, 15);

    final static CreditCard cc1 = new CreditCard(CardIssuer.VISA, ccNumber1,
            new Person("Ignacio", "Flores", 1),
            LocalDate.of(2025, 6, 15));

    final static CreditCard cc2 = new CreditCard(CardIssuer.AMEX, ccNumber2,
            new Person("Javier", "Gramajo", 2),
            LocalDate.of(2024, 8, 23));

    final static Operation op = new Operation(cc1, opAmount, opDate);

    public static void main(String[] args) {

        Processor processor = Processor.getInstance();

        try {
            processor.addCard(cc1);
            processor.addCard(cc2);

            processor.doOperation(op);

            // Invocar un metodo que devuelva toda la informacion de la tarjeta
            System.out.println("\n" + processor.getCard(ccNumber1));

            // Informar si una operacion es valida
            if(processor.isOperationValid(op)) {
                System.out.println("\nValid operation");
            } else {
                System.out.println("\nInvalid operation");
            }

            // Informar si una tarjeta es valida para operar
            if(processor.canCCOperate(ccNumber1)) {
                System.out.println("\nCredit card can operate");
            } else {
                System.out.println("\nCredit card can't operate");
            }

            // Identificar si una tarjeta es distinta a otra
            if(processor.areCCEqual(ccNumber1, ccNumber2)) {
                System.out.println("\nCredit cards are the same");
            } else {
                System.out.println("\nCredit card are not the same");
            }

            // Obtener por medio de un metodo la tasa de una operacion informando marca e importe"
            System.out.println("\nCost added from rate: " + processor.getCostAddedFromRate(op));

        } catch(Exception e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }

        // Recibe la tasa de una operacion en un JSON en el body de la response
        getCostAddedFromRateAPI();
    }

    public static void getCostAddedFromRateAPI() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try(OutputStream output = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                output.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response from server: \n" + response);
            }

        } catch(Exception e) {
            System.out.println("An exception Occurred");
        }
    }

}
