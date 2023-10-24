import java.io.*;
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
        return "[" + getId() + " ## " + getNome() + " ## " + getAltura() + " ## " + getPeso() +
               " ## " + getAnoNascimento() + " ## " + getUniversidade() + " ## " +
               getCidadeNascimento() + " ## " + getEstadoNascimento() + "]";
    }
}

public class Jogador {
    static int contador = 0, moves = 0;
    static long duration;

    public static void alfabetica(int fim, Jogadores Jogadoreses[]) {
        for (int i = 0; i < fim; i++) {
            contador++;
            for (int j = i + 1; j < fim; j++) {
                contador++;
                if (Jogadoreses[i].getAltura() == Jogadoreses[j].getAltura()) {
                    contador++;
                    if (Jogadoreses[i].getNome().compareToIgnoreCase(Jogadoreses[j].getNome()) > 0) {
                        contador++;
                        Jogadores aux = Jogadoreses[j];
                        Jogadoreses[j] = Jogadoreses[i];
                        Jogadoreses[i] = aux;
                        moves++;
                    }
                }
            }
        }
    }

    public static void countSort(int fim, Jogadores Jogadoreses[]) {
        long startTime = System.nanoTime();

        int maior = Jogadoreses[0].getAltura();
        Jogadores[] JogadoresesC = new Jogadores[fim];

        for (int i = 0; i < fim; i++) {
            contador++;
            if (Jogadoreses[i].getAltura() > maior) {
                maior = Jogadoreses[i].getAltura();
            }
        }

        int[] aux = new int[maior + 1];

        for (int i = 0; i <= maior; i++) {
            contador++;
            aux[i] = 0;
        }

        for (int i = 0; i < fim; i++) {
            contador++;
            aux[Jogadoreses[i].getAltura()]++;
        }

        for (int i = 1; i <= maior; i++) {
            contador++;
            aux[i] += aux[i - 1];
        }

        for (int i = 0; i < fim; i++) {
            contador++;
            JogadoresesC[aux[Jogadoreses[i].getAltura()] - 1] = Jogadoreses[i];
            aux[Jogadoreses[i].getAltura()]--;
            moves++;
        }

        for (int i = 0; i < fim; i++) {
            contador++;
            Jogadoreses[i] = JogadoresesC[i];
            moves++;
        }

        alfabetica(fim, Jogadoreses);

        long endTime = System.nanoTime();
        duration = (endTime - startTime);
    }

    public static void criarLog() {
        String fileName = "/tmp/770855_countingsort.txt";

        try {
            File logFile = new File(fileName);
            FileWriter writer = new FileWriter(logFile);
            writer.write("770855\t" + contador + "\t" + moves + "\t" + duration);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");

        Jogadores[] Jogadoreses = new Jogadores[3000];
        String pedido;
        int numeroJogadoreses = 0;

        do {
            pedido = scan.nextLine();

            if (!pedido.equalsIgnoreCase("FIM")) {
                Jogadores player = new Jogadores(pedido, tabela);
                Jogadoreses[numeroJogadoreses] = player;
                numeroJogadoreses++;
            }
        } while (!pedido.equalsIgnoreCase("FIM"));
        scan.close();

        countSort(numeroJogadoreses, Jogadoreses);

        for (int i = 0; i < numeroJogadoreses; i++) {
            System.out.println(Jogadoreses[i].dados());
        }

        criarLog();
    }
}
