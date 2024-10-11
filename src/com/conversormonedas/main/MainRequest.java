package com.conversormonedas.main;

import com.conversormonedas.modelos.Moneda;
import com.conversormonedas.modelos.MonedaRateExchange;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MainRequest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lecture = new Scanner(System.in);


        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        ArrayList<Moneda> monedas = new ArrayList<>();



        while (true) {

            System.out.println("Elija la moneda que quiere convertir");
            String baseCurrency = lecture.nextLine();

            System.out.println("Escriba a que mondea desea convertirla (o 'Exit' para salir):");
            String targetCurrency = lecture.nextLine().toUpperCase();

            if (targetCurrency.equalsIgnoreCase("Exit")) {
                break;
            }

            System.out.println("Ingrese la cantidad a convertir ("+ baseCurrency.toUpperCase()+" " + "a"+ " " + targetCurrency + "):");
            double amountToConvert = lecture.nextDouble();
            lecture.nextLine();

            try {

                String link = "https://v6.exchangerate-api.com/v6/b09120f995a1e756042aa7d5/latest/" + baseCurrency;


                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(link))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                //System.out.println("JSON Response: " + json);




                MonedaRateExchange miMonedaExchangeRate = gson.fromJson(json, MonedaRateExchange.class);
                Map<String, Double> conversionRates = miMonedaExchangeRate.getConversion_rates();


                if (conversionRates != null && conversionRates.containsKey(targetCurrency)) {
                    double exchangeRate = conversionRates.get(targetCurrency);


                    double convertedAmount = amountToConvert * exchangeRate;

                    System.out.println("Tasa de cambio "+ baseCurrency.toUpperCase()+" a " + targetCurrency + ": " + exchangeRate);
                    System.out.println("El valor de " + amountToConvert + " " + baseCurrency.toUpperCase()+ " en " + targetCurrency + " es: " + convertedAmount);


                    Moneda miMoneda = new Moneda(miMonedaExchangeRate, targetCurrency, exchangeRate);
                    System.out.println(miMoneda);
                    monedas.add(miMoneda);
                } else {
                    System.out.println("La moneda solicitada no está disponible.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al procesar el número. Inténtalo de nuevo.");
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                System.out.println("Ocurrió un error en la solicitud.");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Oucrrio un error inesperado, intentelo nuevamente");;
            }
        }

        System.out.println("\nHistorial de conversiones:");
        for (Moneda moneda : monedas) {
            System.out.println(moneda);
        }
    }
}


