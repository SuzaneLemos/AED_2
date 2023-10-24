import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Jogadores {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogadores(String pedido, File tabela) {
        try {
            Scanner scan = new Scanner(tabela);

            while (scan.hasNextLine()) {
                String linha = scan.nextLine(); 
                String[] elementos = linha.split(",", -1); 

                for (int i = 0; i < elementos.length; i++) {
                    if (elementos[i].isEmpty()) {
                        elementos[i] = "nao informado";
                    }
                }

                if (pedido.equals(elementos[0]) && elementos.length == 8) {
                    setId(Integer.parseInt(elementos[0]));
                    setNome(elementos[1]);
                    setAltura(Integer.parseInt(elementos[2]));
                    setPeso(Integer.parseInt(elementos[3]));
                    setUniversidade(elementos[4]);
                    setAnoNascimento(Integer.parseInt(elementos[5]));
                    setCidadeNascimento(elementos[6]);
                    setEstadoNascimento(elementos[7]);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("arquivo não encontrado");
        }
    }

    public Jogadores() {
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public int getId() {
        return this.id;
    }

    public int getAltura() {
        return this.altura;
    }

    public String getNome() {
        return this.nome;
    }

    public int getPeso() {
        return this.peso;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    public String dados() {
        return ("[" +
                getId() +
                " ## " +
                getNome() +
                " ## " +
                getAltura() +
                " ## " +
                getPeso() +
                " ## " +
                getAnoNascimento() +
                " ## " +
                getUniversidade() +
                " ## " +
                getCidadeNascimento() +
                " ## " +
                getEstadoNascimento() +
                "]");
    }
}

public class Jogador {
    static int contador = 0;

    public static void ordenar(int fim, Jogadores[] joker, Jogadores[] min, int k) {
        for (int i = 0; i < k; i++) {
            min[i] = joker[i];
            contador++;
        }

        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < fim; j++) {
                if (joker[j].getNome().compareTo(min[i].getNome()) < 0) {
                    Jogadores aux = min[i];
                    min[i] = joker[j];
                    joker[j] = aux;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");

        Jogadores[] joker = new Jogadores[500];
        String pedido;
        int numeroJogador = 0;

        do {
            pedido = scan.nextLine();

            if (!pedido.equalsIgnoreCase("FIM")) {
                Jogadores player = new Jogadores(pedido, tabela);
                joker[numeroJogador] = player;
                numeroJogador++;
            }
        } while (!pedido.equalsIgnoreCase("FIM"));

        scan.close();

        int k = 10;
        Jogadores[] min = new Jogadores[k];

        ordenar(numeroJogador, joker, min, k);

        for (int i = 0; i < min.length; i++) {
            System.out.println(min[i].dados());
        }
    }
}
