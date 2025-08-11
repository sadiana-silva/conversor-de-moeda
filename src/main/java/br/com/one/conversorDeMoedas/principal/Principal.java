package br.com.one.conversorDeMoedas.principal;

import br.com.one.conversorDeMoedas.model.ExchangeResponse;
import br.com.one.conversorDeMoedas.service.ConsumoApi;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {
        String apiKey = System.getenv("API_KEY_EXRATE");
        String endereco = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
        ConsumoApi consumoApi = new ConsumoApi();
        String json = consumoApi.obterDados(endereco);
        Gson gson = new Gson();
        ExchangeResponse resposta = gson.fromJson(json, ExchangeResponse.class);

//        double taxaUSDparaBRL = resposta.getConversionRates().get("BRL");
//        System.out.println("Taxa de conversão de USD para BRL: " + taxaUSDparaBRL);

        List<String> moedasSelecionadas = Arrays.asList("ARS", "BOB", "BRL", "CLP", "COP", "USD");

        Map<String, Double> todasAsTaxas = resposta.getConversionRates();

        Map<String, Double> taxasFiltradas = new HashMap<>();
        for (String codigo : moedasSelecionadas) {
            Double taxa = todasAsTaxas.get(codigo);
            if (taxa != null) {
                taxasFiltradas.put(codigo, taxa);
            }
        }

        System.out.println("Moedas Filtradas: ");
        for (Map.Entry<String, Double> entry : taxasFiltradas.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
