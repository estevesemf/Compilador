public class LexerService {

    public LexerService() {
    }

    public static boolean isNumeric(String str) {
        return str.matches("[0-9]+");
    }

    public static boolean isOperador(String str) {
        //return str.matches("\\+|-|\\*|/|%|/|\\");
        return str.matches("[+\\-*/%^]+");
    }

    public static boolean isComentario(String str){
        return str.startsWith("//");
    }

    public static boolean isHexa(String str){
        return str.matches("0x[A-F0-9]+");
    }

    /*

     */

}
