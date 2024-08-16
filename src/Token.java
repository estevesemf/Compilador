public class Token {

    public TipoToken tipoToken;
    public Object valor;

    public Float valorNumerico;

    public Token(TipoToken tipoToken, String valor) {
        this.tipoToken = tipoToken;
        this.valor = valor;
    }

    public TipoToken getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(TipoToken tipoToken) {
        this.tipoToken = tipoToken;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


    public Float getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(Object valor) {
        if(this.tipoToken == TipoToken.TokNumber){
            this.valorNumerico = (Float) valor;
        }
    }

    @Override
    public String toString() {
        if(this.getTipoToken() != TipoToken.TokEof){
            return this.tipoToken.toString() + " " + this.valor;
        }
        return this.tipoToken.toString();
    }

}
