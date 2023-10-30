package med.voll.api.domain;

public class ValidacaoExeption extends RuntimeException {
    public ValidacaoExeption(String menssagem) {
        super(menssagem);
    }
}
