import java.util.*;

public class TP1Q14 {
    static class Execute {

        Stack<Character> exp;
        Integer count;
        HashMap<Integer, Stack<Boolean>> acum;
        Integer level;
        boolean resp;

        public Execute(Stack<Character> exp, Integer count, HashMap<Integer, Stack<Boolean>> acum, Integer level,
                boolean resp) {
            this.exp = exp;
            this.count = count;
            this.acum = acum;
            this.level = level;
            this.resp = resp;
        }

        public Execute() {
        }

        public Stack<Character> getExp() {
            return exp;
        }

        public void setExp(Stack<Character> exp) {
            this.exp = exp;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public HashMap<Integer, Stack<Boolean>> getAcum() {
            return acum;
        }

        public void setAcum(HashMap<Integer, Stack<Boolean>> acum) {
            this.acum = acum;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public boolean getResp() {
            return resp;
        }

        public void setResp(boolean resp) {
            this.resp = resp;
        }
    }

    private static String booleanProcessor(String operation) {
        int var = ((int) operation.charAt(0)) - 48;
        boolean[] terms = new boolean[var];
        int nextRead = buildterms(var, operation, terms);
        Stack<Character> exp = recProcesser(operation, nextRead, terms);
        Execute e = new Execute(exp, 0, new HashMap<Integer, Stack<Boolean>>(), 0, false);

        booleanMotor(e);

        return e.getResp() ? "1" : "0";
    }

    private static void booleanMotor(Execute exp) {
        while (!exp.getExp().isEmpty()) {

            char e = exp.getExp().pop();
            switch (e) {
                case ')':

                    exp.setCount(exp.getCount() + 1);
                    exp.setLevel(exp.getLevel() + 1);
                    booleanMotor(exp);
                    break;
                case '(':
                    if (exp.getCount() != 0) {
                        exp.setCount(exp.getCount() - 1);

                        char o = exp.getExp().pop();
                        switch (o) {
                            case 'a':
                                boolean resp1 = true;
                                Stack<Boolean> o1 = exp.getAcum().remove(exp.getLevel());
                                while (!o1.isEmpty()) {

                                    boolean t = o1.pop();

                                    resp1 = resp1 && t;
                                }

                                exp.setLevel(exp.getLevel() - 1);
                                exp.setResp(resp1);
                                if (exp.getAcum().get(exp.getLevel()) == null) {
                                    exp.getAcum().put(exp.getLevel(), new Stack<Boolean>());
                                }

                                exp.getAcum().get(exp.getLevel()).push(resp1);
                                return;
                            case 'o':
                                boolean resp2 = false;
                                Stack<Boolean> o2 = exp.getAcum().remove(exp.getLevel());
                                while (!o2.isEmpty()) {

                                    boolean t = o2.pop();

                                    resp2 = resp2 || t;
                                }

                                exp.setLevel(exp.getLevel() - 1);

                                exp.setResp(resp2);
                                if (exp.getAcum().get(exp.getLevel()) == null) {

                                    exp.getAcum().put(exp.getLevel(), new Stack<Boolean>());
                                }

                                exp.getAcum().get(exp.getLevel()).push(resp2);
                                return;
                            case 'n':

                                boolean resp3 = !exp.getAcum().remove(exp.getLevel()).pop();

                                exp.setLevel(exp.getLevel() - 1);

                                exp.setResp(resp3);
                                if (exp.getAcum().get(exp.getLevel()) == null) {
                                    exp.getAcum().put(exp.getLevel(), new Stack<Boolean>());
                                }
                                exp.getAcum().get(exp.getLevel()).push(resp3);
                                return;
                        }
                    }
                    break;

                case '0':
                    if (exp.getAcum().get(exp.getLevel()) == null) {
                        exp.getAcum().put(exp.getLevel(), new Stack<Boolean>());
                    }
                    exp.getAcum().get(exp.getLevel()).push(false);
                    booleanMotor(exp);
                    break;

                case '1':
                    exp.getAcum().computeIfAbsent(exp.getLevel(), k -> new Stack<Boolean>());
                    exp.getAcum().get(exp.getLevel()).push(true);
                    booleanMotor(exp);
                    break;

            }
        }
        return;
    }

    private static Stack<Character> recProcesser(String st, int nextRead, boolean[] terms) {
        Stack<Character> ex = new Stack<Character>();
        for (int i = nextRead; i < st.length(); i++) {
            if (st.charAt(i) != ' ' && st.charAt(i) != ',') {
                switch (st.charAt(i)) {
                    case 'a':
                    case 'n':
                        // armazendo o valor na Stack
                        ex.push(st.charAt(i));
                        // somando para armazenar apenas uma letra
                        i += 2;
                        break;
                    case 'o':
                        // armazendo o valor na Stack
                        ex.push(st.charAt(i));
                        // somando para armazenar apenas uma letra
                        i++;
                        break;
                    case '(':
                        // armazendo o valor na Stack
                        ex.push(st.charAt(i));
                        break;
                    case ')':
                        ex.push(st.charAt(i));
                        break;
                    default:
                        int element = st.charAt(i) - 65;
                        ex.push(Boolean.TRUE.equals(terms[element]) ? '1' : '0');
                        break;

                }
            }
        }
        return ex;
    }

    private static int buildterms(int var, String input, boolean[] terms) {
        int count = 0;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                if (input.charAt(i) == '0') {
                    terms[count] = false;
                    count++;
                } else if (input.charAt(i) == '1') {
                    terms[count] = true;
                    count++;
                }
            }
            if (count == var) {
                return i += 2;
            }
        }
        return 0;
    }

    private static boolean END(String s) {
        return (s.charAt(0) == '0');
    }

    public static void main(String[] args) {

        String st = "";

        for (int i = 0; i < 104; i++) {
            st = MyIO.readLine();
            String result = booleanProcessor(st);
            System.out.println(result);
        }

    }

}