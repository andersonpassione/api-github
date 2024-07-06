package br.com.alura.screenmatch.exercicioApiGitHub;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class TesteAPIGitHub {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe um usuário do git hub: ");
        String usuario = scanner.nextLine();

        String endereco = "https://api.github.com/users/" + usuario;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .header("Accept", "application/vnd.github.v3+json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();

            if (statusCode == 404){
                throw new ErroConsultaGitHubException("O usuário " + usuario + " não foi encontrado no GitHub.");
            }

            String json = response.body();
            System.out.println(json);

        } catch (IOException | InterruptedException e){
            System.out.println("Houve um erro durante a consulta a API do GitHub.");
            e.printStackTrace();
        } catch (ErroConsultaGitHubException e){
            System.out.println(e.getMessage());
        }
    }
}
