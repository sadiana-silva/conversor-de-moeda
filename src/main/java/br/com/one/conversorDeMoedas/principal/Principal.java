package br.com.one.conversorDeMoedas.principal;

import br.com.one.conversorDeMoedas.model.ExchangeResponse;
import br.com.one.conversorDeMoedas.service.ConsumoApi;
import com.google.gson.Gson;

public class Principal {

    public static void main(String[] args) {
        String apiKey = System.getenv("API_KEY_EXRATE");
        String endereco = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
        ConsumoApi consumoApi = new ConsumoApi();
        String json = consumoApi.obterDados(endereco);
        Gson gson = new Gson();
        ExchangeResponse resposta = gson.fromJson(json, ExchangeResponse.class);

        double taxaUSDparaBRL = resposta.getConversionRates().get("BRL");
        System.out.println("Taxa de conversão de USD para BRL: " + taxaUSDparaBRL);
    }
}
