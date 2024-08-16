import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Compilador {

    public static void main(String[] args) {
       /* Tentativa de implementação para entrada por arquivo
        try{
            File arquivoDeEntrada = new File("resource/entrada.txt");
            Scanner myReader = new Scanner(arquivoDeEntrada);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            e.printStackTrace();
        }


        // System.out.println("Teste");

        Lexer lexer = new Lexer();

        while (scanner.hasNext()) {
            String token = scanner.next().trim();
            listaTokens.add(lexer.next(token, scanner));
        }
        listaTokens.add(new Token(TipoToken.TokEof, null));
        listaTokens.stream().forEach(r -> System.out.println(r.toString()));
        */
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Token> listaTokens = new ArrayList<>();
        scanner = new Scanner(input);
        boolean dentroBlocoComentario2 = false;

        while (scanner.hasNext()) {
            String token = scanner.next().trim();
            if (isHexa(token)) {
                listaTokens.add(new Token(TipoToken.TokHexa, token));
            } else if(isComentario(token)) {
                if (scanner.hasNextLine()) {
                    //String comentario = token + scanner.nextLine();
                    //resultados.add(new Resultado(Token.TokComentario, comentario));
                } else {
                    //resultados.add(new Resultado(Token.TokComentario, token));
                }
            } else if (isComentario2(token, dentroBlocoComentario2)){
                dentroBlocoComentario2 = true;
                while (scanner.hasNext()) {
                    String comentario = scanner.next().trim();
                    if (isComentario2(comentario, dentroBlocoComentario2)) {
                        // Continua dentro do bloco
                    } else {
                        dentroBlocoComentario2 = false;
                        break;
                    }
                }
            } else if(isOperador(token)){
                listaTokens.add(new Token(TipoToken.TokOp, geraOperador(token)));
            }else if(isNumeric(token)){
                listaTokens.add(new Token(TipoToken.TokNumber, token));
            }
            else{
                System.out.println("Operador inválido! " + token);
            }
        }
        listaTokens.add(new Token(TipoToken.TokEof, null));
        listaTokens.stream().forEach(r -> System.out.println(r.toString()));

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


    //public static boolean isComentarioComplexo(String str){
        //lógica para comentário 2 -> ("/*"(.|{WS})*"*/")
        //return str.matches("\"/\\*\"(.|\\s)*\"\\*/\"");
    //}



    public static boolean isHexa(String str){
        return str.matches("0x[A-F0-9]+");
    }

    public static boolean isComentario2(String str, boolean dentroBloco) {
        //lógica para comentário 2 -> ("/*"(.|{WS})*"*/")
        if (str.startsWith("/*")) {
            dentroBloco = true;
        } else if (str.endsWith("*/")) {
            dentroBloco = false;
        }
        return dentroBloco;
    }

}
