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


    public Jogadores(String pedido, File tabela) {
        try {
            Scanner scan = new Scanner(tabela);

            while (scan.hasNextLine()) {
                String linha = scan.nextLine(); // Read a line
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
            System.out.println("Arquivo não encontrado");
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
        return (
            "[" +
            getId() + " ## " +
            getNome() + " ## " +
            getAltura() + " ## " +
            getPeso() + " ## " +
            getAnoNascimento() + " ## " +
            getUniversidade() + " ## " +
            getCidadeNascimento() + " ## " +
            getEstadoNascimento() + "]"
        );
    }
}

public class Jogador {
    static int contador = 0, moves = 0;
    static long duration;

    public static void doMerge(Jogadores[] jogadores, Jogadores[] esquerda, Jogadores[] direita) {
        int Tesquerda = esquerda.length;
        int Tdireita = direita.length;
        int i = 0, j = 0, k = 0;

        while (i < Tesquerda && j < Tdireita) {
            contador++;
            if ((esquerda[i].getUniversidade()).compareTo(direita[j].getUniversidade()) < 0) {
                jogadores[k] = esquerda[i];
                i++;
            } else if ((esquerda[i].getUniversidade()).compareTo(direita[j].getUniversidade()) > 0) {
                jogadores[k] = direita[j];
                j++;
            } else {
                if ((esquerda[i].getNome()).compareTo(direita[j].getNome()) < 0) {
                    jogadores[k] = esquerda[i];
                    i++;
                } else {
                    jogadores[k] = direita[j];
                    j++;
                }
            }
            k++;
        }

        while (i < Tesquerda) {
            jogadores[k] = esquerda[i];
            i++;
            k++;
        }
        while (j < Tdireita) {
            jogadores[k] = direita[j];
            j++;
            k++;
        }
    }

    public static void MergeSort(Jogadores[] jogadores) {
        long startTime = System.nanoTime();
        int fim = jogadores.length;

        contador++;
        if (fim <= 1) {
            return;
        }

        int mid = fim / 2;
        Jogadores[] esquerda = new Jogadores[mid];
        Jogadores[] direita = new Jogadores[fim - mid];

        for (int i = 0; i < mid; i++) {
            contador++;
            esquerda[i] = jogadores[i];
        }
        for (int i = mid; i < fim; i++) {
            contador++;
            direita[i - mid] = jogadores[i];
        }

        MergeSort(esquerda);
        MergeSort(direita);

        doMerge(jogadores, esquerda, direita);

        long endTime = System.nanoTime();
        duration = (endTime - startTime);
    }

        public static void criarLog() {
        String fileName = "/tmp/770855_mergesort.txt";

        try {
            File logFile = new File(fileName);
            FileWriter writer = new FileWriter(logFile);
            writer.write("770855" + "\t" + (contador + 2) + "\t" + moves + "\t" + duration);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");

        Jogadores[] jocker = new Jogadores[1000];
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

        Jogadores[] newJogador = new Jogadores[numeroJogadores];

        for (int i = 0; i < numeroJogadores; i++) {
            newJogador[i] = jocker[i];
        }

        MergeSort(newJogador);

        for (int i = 0; i < numeroJogadores; i++) {
            System.out.println(newJogador[i].dados());
        }

        criarLog();
    }
}
