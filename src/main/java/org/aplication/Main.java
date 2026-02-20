package org.aplication;

import org.aplication.dto.AddressDto;
import org.aplication.service.ApiService;

import java.io.IOException;
import java.rmi.ServerRuntimeException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        ApiService apiService = new ApiService();

        while (running) {
            System.out.println("Bem vindo ao consulta CEP!");
            System.out.println("Digite seu CEP (digite 'sair' para fechar o programa): ");
            String cep = scanner.next();
            scanner.nextLine();

            if (cep.equalsIgnoreCase("sair")) {
                System.out.println("Tchau!");
                running = false;
            } else {
                try {
                    AddressDto addressDto = apiService.getAddress(cep);
                    System.out.println("CEP: " + addressDto.getCep());
                    System.out.println(addressDto.getLogradouro());
                    System.out.println("Bairro: " + addressDto.getBairro());
                    System.out.println(addressDto.getLocalidade() + " - " + addressDto.getUf());
                } catch (RuntimeException e) {
                    System.out.println("Erro ao buscar endereço");
                }
            }
        }
    }
}
