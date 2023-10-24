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
                String[] elements = linha.split(",", -1);


                for (int i = 0; i < elements.length; i++) {
                    if (elements[i].isEmpty()) {
                        elements[i] = "nao informado";
                    }
                }

                if (pedido.equals(elements[0]) && elements.length == 8) {
                    setId(Integer.parseInt(elements[0]));
                    setNome(elements[1]);
                    setAltura(Integer.parseInt(elements[2]));
                    setPeso(Integer.parseInt(elements[3]));
                    setUniversidade(elements[4]);
                    setAnoNascimento(Integer.parseInt(elements[5]));
                    setCidadeNascimento(elements[6]);
                    setEstadoNascimento(elements[7]);
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

    public void setNome(String nome) {
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

    public String data() {
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

    public static void doQuick(Jogadores jogadores[], int left, int right) {
        int i = left, j = right;
        Jogadores pivot = jogadores[(right + left) / 2];
        Jogadores temp;

        while (i <= j) {
            while (jogadores[i].getEstadoNascimento().compareTo(pivot.getEstadoNascimento()) < 0) {
                i++;
            }

            while (jogadores[j].getEstadoNascimento().compareTo(pivot.getEstadoNascimento()) > 0) {
                j--;
            }

            if (i <= j) {
                temp = jogadores[i];
                jogadores[i] = jogadores[j];
                jogadores[j] = temp;
                i++;
                j--;
            }
        }

        if (left < j) {
            doQuick(jogadores, left, j);
        }

        if (i < right) {
            doQuick(jogadores, i, right);
        }
    }

    public static void QuickSort(Jogadores jogadores[], int start, int end) {
        doQuick(jogadores, start, end - 1);

        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (jogadores[i].getEstadoNascimento().compareTo(jogadores[j].getEstadoNascimento()) == 0) {
                    if (jogadores[i].getNome().compareTo(jogadores[j].getNome()) > 0) {
                        Jogadores temp = jogadores[i];
                        jogadores[i] = jogadores[j];
                        jogadores[j] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File dataFile = new File("/tmp/players.csv");
        Jogadores[] jogadores = new Jogadores[500];
        String request;
        int numPlayers = 0;


        do {
            request = scanner.nextLine();
            if (!request.equalsIgnoreCase("FIM")) {
                Jogadores Jogadores = new Jogadores(request, dataFile);
                jogadores[numPlayers] = Jogadores;
                numPlayers++;
            }
        } while (!request.equalsIgnoreCase("FIM"));
        scanner.close();

        Jogadores[] sortedPlayers = new Jogadores[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            sortedPlayers[i] = jogadores[i];
        }
        QuickSort(sortedPlayers, 0, numPlayers);

        int k = 10;
        Jogadores[] topPlayers = new Jogadores[k];
        for (int i = 0; i < k; i++) {
            topPlayers[i] = sortedPlayers[i];
        }

        for (int i = 0; i < topPlayers.length; i++) {
            System.out.println(topPlayers[i].data());
        }
    }
}
