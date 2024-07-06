package br.com.alura.screenmatch.exercicioApiGitHub;

public class ErroConsultaGitHubException extends RuntimeException{
    private String mensagem;

    public ErroConsultaGitHubException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
