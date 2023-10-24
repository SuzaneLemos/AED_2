import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    // Construtor da classe Jogadores que lê dados do arquivo
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

                // Se o pedido corresponder à primeira coluna e os elementos têm o tamanho correto, define os atributos da classe
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
            System.out.println("Arquivo não encontrado");
        }
    }

    public Jogadores() {
    }

    // Getters e Setters dos atributos da classe
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

    // Método para retornar os dados do jogador formatados em uma string
    public String dados() {
        return "[" +
                getId() + " ## " +
                getNome() + " ## " +
                getAltura() + " ## " +
                getPeso() + " ## " +
                getAnoNascimento() + " ## " +
                getUniversidade() + " ## " +
                getCidadeNascimento() + " ## " +
                getEstadoNascimento() + "]";
    }
}

public class Jogador {
    static int contador = 0, moves = 0;
    static long duration;

    // Método para aplicar o algoritmo de ordenação HeapSort
    public static void doHeap(Jogadores jogadores[], int i, int fim) {
        int raiz = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < fim && jogadores[esquerda].getAltura() > jogadores[raiz].getAltura()) {
            contador++;
            raiz = esquerda;
        }

        if (direita < fim && jogadores[direita].getAltura() > jogadores[raiz].getAltura()) {
            contador++;
            raiz = direita;
        }

        if (raiz != i) {
            contador++;
            Jogadores aux = jogadores[i];
            jogadores[i] = jogadores[raiz];
            jogadores[raiz] = aux;
            moves++;
            doHeap(jogadores, raiz, fim);
        }
    }

    // Método para ordenar um array de jogadores usando o algoritmo HeapSort
    public static void heapsort(int fim, Jogadores jogadores[]) {
        long startTime = System.nanoTime();

        for (int i = (fim / 2) - 1; i >= 0; i--) {
            contador++;
            doHeap(jogadores, i, fim);
        }

        for (int i = fim - 1; i >= 0; i--) {
            contador++;
            Jogadores aux = jogadores[0];
            jogadores[0] = jogadores[i];
            jogadores[i] = aux;
            moves++;
            doHeap(jogadores, 0, i);
        }

        for (int i = 0; i < fim; i++) {
            contador++;
            for (int j = i + 1; j < fim; j++) {
                contador++;
                if (jogadores[i].getAltura() == jogadores[j].getAltura()) {
                    contador++;
                    if (jogadores[i].getNome().compareToIgnoreCase(jogadores[j].getNome()) > 0) {
                        contador++;
                        Jogadores aux = jogadores[j];
                        jogadores[j] = jogadores[i];
                        jogadores[i] = aux;
                        moves++;
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        duration = (endTime - startTime);
    }

    // Método para criar um arquivo de log com informações sobre a execução do programa
    public static void criarLog() {
        String fileName = "/tmp/770855_heapsort.txt";

        try {
            File logFile = new File(fileName);
            FileWriter writer = new FileWriter(logFile);
            writer.write("770855" + "\t" + duration + "\t" + (contador + 1) + "\t" + moves);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");

        // Verifica se o arquivo players.csv existe antes de continuar
        if (!tabela.exists() || !tabela.isFile()) {
            System.out.println("Arquivo players.csv não encontrado.");
            return;
        }

        Jogadores[] jocker = new Jogadores[3000];
        String pedido;
        int numeroJogadores = 0;

        do {
            pedido = scan.nextLine();

            if (!pedido.equalsIgnoreCase("FIM")) {
                Jogadores player = new Jogadores(pedido, tabela);
                jocker[numeroJogadores] = player;
                numeroJogadores++;
            }
        } while (!pedido.equalsIgnoreCase("FIM"));
        scan.close();

        heapsort(numeroJogadores, jocker);

        for (int i = 0; i < numeroJogadores; i++) {
            System.out.println(jocker[i].dados());
        }

        criarLog();
    }
}
