import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ExchangeRateResponse rates = CurrencyConverter.getExchangeRates();

        if (rates == null) {
            System.out.println("Erro ao obter as taxas de câmbio. Tente novamente mais tarde.");
            return;
        }

        System.out.println("===== Conversor de Moedas: USD para Outras =====");
        System.out.print("Digite o valor em USD para converter: ");
        double amount = scanner.nextDouble();

        // Lista das moedas que queremos converter
        String[] moedas = {"BRL", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY", "ARS", "MXN"};

        for (String moeda : moedas) {
            if (rates.getConversion_rates().containsKey(moeda)) {
                double taxa = rates.getConversion_rates().get(moeda);
                double valorConvertido = amount * taxa;
                System.out.printf("USD %.2f -> %s %.2f%n", amount, moeda, valorConvertido);
            } else {
                System.out.println("Moeda " + moeda + " não encontrada na API.");
            }
        }

        scanner.close();
    }
}
