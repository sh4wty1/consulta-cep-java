package org.aplication.service;

import org.aplication.dto.AddressDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {

    AddressDto address = new AddressDto();

    public AddressDto getAddress(String cep) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//            System.out.println(response.body());

            ObjectMapper mapper = new ObjectMapper();
            address = mapper.readValue(response.body(), AddressDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar endereço", e);
        }
        return address;
    }
}
