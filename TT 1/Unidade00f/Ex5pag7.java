public class Ex5pag7 {
    public static void main(String[] args) {
        
    }
    boolean isConsoante(String s, int n){
        boolean resp = false;
        if(n < s.length()){
            if(s.charAt(n) == 'a' || s.charAt(n) == 'e' || s.charAt(n) == 'i' || s.charAt(n) == 'o' || s.charAt(n) == 'u'){
                resp = false;
            }else{
                resp = true;
            }
        }
        return resp;
    }
}
