package br.com.fullstack.lembretes.configs.excecoes;

public class NaoEncontradoExcecao extends RuntimeException {
    public NaoEncontradoExcecao(String mensagem) {
        super(mensagem);
    }
}
