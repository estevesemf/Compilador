import java.util.Scanner;

public class Lexer {
    public Lexer() {
    }

    public Token next(String token, Scanner scanner){
        if (isHexa(token)) {
            return new Token(TipoToken.TokHexa, token);
        } else if(isComentario(token)){
            if (scanner.hasNextLine()) {
                //String comentario = token + scanner.nextLine();
                //resultados.add(new Resultado(Token.TokComentario, comentario));
            } else {
                //resultados.add(new Resultado(Token.TokComentario, token));
            }
        } else if(isOperador(token)){
            return new Token(TipoToken.TokOp, geraOperador(token));
        } else if (isComentarioComplexo(token)) {

        } else if(isNumeric(token)){
            return new Token(TipoToken.TokNumber, token);
        }
        System.out.println("Operador inválido! " + token);
        throw new RuntimeException("Operador inválido!");
    }

    public static String geraOperador (String operador){
        String operadorRetorno = null;
        switch (operador) {
            case "+" -> operadorRetorno  = "OpSum";
            case "*" -> operadorRetorno = "OpMult";
            case "-" -> operadorRetorno = "OpSub";
            case "/" -> operadorRetorno = "OpDiv";
            case "%" -> operadorRetorno = "OpMod";
            case "^" -> operadorRetorno = "OpPow";
        }
        return operadorRetorno;
    }


    public static boolean isNumeric(String str) {
        return str.matches("[0-9]+");
    }

    public static boolean isOperador(String str) {
        //return str.matches("\\+|-|\\*|/|%|/|\\");
        return str.matches("[+\\-*/%^]+");
    }

    public static boolean isComentario(String str){
        //lógica para comentário 1 -> ("//".*)
        return str.startsWith("//");
    }

    public static boolean isComentarioComplexo(String str){
        //lógica para comentário 2 -> ("/*"(.|{WS})*"*/")
        return str.matches("\"/\\*\"(.|\\s)*\"\\*/\"");
    }

    public static boolean isHexa(String str){
        return str.matches("0x[A-F0-9]+");
    }

}
