import java.util.*;
import java.io.*;

class Jogador implements Comparable<Jogador> 
{
    private static int totalComparacoes = 0;

    // Atributos privados
    private int id;
    private int altura;
    private int peso;
    private int anoNascimento;
    private String nome;
    private String universidade;
    private String cidadeNascimento;
    private String estadoNascimento;

    // Construtores get de todos os int
    public int getId ()
    {
        return id;
    }

    public int getAltura ()
    {
        return altura;
    }

    public int getPeso ()
    {
        return peso;
    }

    public int getAnoNascimento ()
    {
        return anoNascimento;
    }

    // Construtores get de todos os String
    public String getNome ()
    {
        return nome;
    }

    public String getUniversidade ()
    {
        return universidade;
    }

    public String getCidadeNascimento ()
    {
        return cidadeNascimento;
    }

    public String getEstadoNascimento ()
    {
        return estadoNascimento;
    }

    // Construtores set de todos os int
    public void setId (int id)
    {
        this.id = id;
    }

    public void setAltura (int altura)
    {
        this.altura = altura;
    }

    public void setPeso (int peso)
    {
        this.peso = peso;
    }

    public void setAnoNascimento (int anoNascimento)
    {
        this.anoNascimento = anoNascimento;
    }

    // Construtores set de todos os String
    public void setNome (String nome)
    {
        this.nome = nome;
    }

    public void setUniversidade (String universidade)
    {
        this.universidade = universidade;
    }

    public void setCidadeNascimento (String cidadeNascimento)
    {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento (String estadoNascimento)
    {
        this.estadoNascimento = estadoNascimento;
    }

    // Construtor clone
    public Jogador clone ()
    {
        Jogador jogadorClone = new Jogador();

        // Clonando todos os int
        jogadorClone.setId(this.id);
        jogadorClone.setAltura(this.altura);
        jogadorClone.setPeso(this.peso);
        jogadorClone.setAnoNascimento(this.anoNascimento);

        // Clonando todos os String
        jogadorClone.setNome(this.nome);
        jogadorClone.setUniversidade(this.universidade);
        jogadorClone.setCidadeNascimento(this.cidadeNascimento);
        jogadorClone.setEstadoNascimento(this.estadoNascimento);

        return jogadorClone;
    }

    // Construtor ler
    public void ler (int id)
    {
        try
        {
            File arquivo = new File("/tmp/players.csv");
            Scanner scanner = new Scanner(arquivo);

            // Ignorar a primeira linha
                if (scanner.hasNextLine()) 
                {
                    scanner.nextLine();
                }

            while (scanner.hasNextLine())
            {
                String linha = scanner.nextLine();
                //System.out.println("Linha do arquivo: " + linha);
                List<String> partesSeparadas = separarLinha(linha);

                int jogadorId = Integer.parseInt(partesSeparadas.get(0));

                if (jogadorId == id)
                {
                    preencherAtributos(partesSeparadas);
                    
                    break;
                }
            }

            scanner.close();
        }catch (FileNotFoundException e) 
        {
            System.err.println("Arquivo players.csv não encontrado.");
        }
    }

    // Construtor para separar a linha em partes
    public static List<String> separarLinha(String linha) 
    {
        String[] partes = linha.split(",");
        List<String> partesSeparadas = new ArrayList<>();

        for (String parte : partes) 
        {
            if (parte.isEmpty()) 
            {
                partesSeparadas.add("nao informado");
            } 
            
            else 
            {
                partesSeparadas.add(parte);
            }
        }

        return partesSeparadas;
    }

    // Construtor para atribuir valores aos atributos de acordo com a linha do arquivo
    public void preencherAtributos(List<String> partesSeparadas) 
    {
        this.id = -1;  // Valor padrão para o ID
        this.nome = "nao informado";  // Valor padrão para o nome
        this.altura = -1;  // Valor padrão para a altura
        this.peso = -1;  // Valor padrão para o peso
        this.universidade = "nao informado";  // Valor padrão para a universidade
        this.anoNascimento = -1;  // Valor padrão para o ano de nascimento
        this.cidadeNascimento = "nao informado";  // Valor padrão para a cidade de nascimento
        this.estadoNascimento = "nao informado";  // Valor padrão para o estado de nascimento

        if (partesSeparadas.size() >= 1)
        {
            this.id = Integer.parseInt(partesSeparadas.get(0));
        }

        if (partesSeparadas.size() >= 2)
        {
            this.nome = partesSeparadas.get(1);
        }

        if (partesSeparadas.size() >= 3)
        {
            this.altura = Integer.parseInt(partesSeparadas.get(2));
        }

        if (partesSeparadas.size() >= 4)
        {
            this.peso = Integer.parseInt(partesSeparadas.get(3));
        }

        if (partesSeparadas.size() >= 5)
        {
            this.universidade = partesSeparadas.get(4);
        }

        if (partesSeparadas.size() >= 6)
        {
            this.anoNascimento = Integer.parseInt(partesSeparadas.get(5));
        }

        if (partesSeparadas.size() >= 7)
        {
            this.cidadeNascimento = partesSeparadas.get(6);
        }

        if (partesSeparadas.size() >= 8)
        {
            this.estadoNascimento = partesSeparadas.get(7);
        }
    }

    @Override
    public int compareTo(Jogador outroJogador) {
        totalComparacoes++; // Incrementa o contador de comparações
        return this.nome.compareTo(outroJogador.nome);
    }

    public static void selectionSort(List<Jogador> jogadores) {
        int n = jogadores.size();
    
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (jogadores.get(j).compareTo(jogadores.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            // Troque a posição dos jogadores
            Jogador temp = jogadores.get(i);
            jogadores.set(i, jogadores.get(minIndex));
            jogadores.set(minIndex, temp);
        }
    }

    public void imprimir() {
        System.out.println("[" + this.id + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso + " ## " +
               this.anoNascimento + " ## " + this.universidade + " ## " + this.cidadeNascimento + " ## " +
               this.estadoNascimento + "]");
    }

    public static void main(String[] args) {
        long inicioExecucao = System.nanoTime();
        Scanner scanner = new Scanner(System.in);
        List<Jogador> jogadoresSelecionados = new ArrayList<>();

        while (true) {
            String entrada = scanner.nextLine();

            if (entrada.equals("FIM")) {
                break;
            }

            try {
                int jogadorId = Integer.parseInt(entrada);
                Jogador jogador = new Jogador();
                jogador.ler(jogadorId);
                jogadoresSelecionados.add(jogador);
            } catch (NumberFormatException e) {
                System.err.println("ID inválido: " + entrada);
            }
        }

        // Utilize o algoritmo de ordenação por seleção para ordenar jogadoresSelecionados em ordem alfabética
        selectionSort(jogadoresSelecionados);

        // Exiba os jogadores em ordem alfabética
        for (Jogador jogador : jogadoresSelecionados) {
            jogador.imprimir();
        }

        scanner.close();

        long tempoExecucao = (System.nanoTime() - inicioExecucao) / 1000000; // Converta para milissegundos
        criarArquivoLog(tempoExecucao);
    }

    public static void criarArquivoLog(long tempoExecucao) 
    {
        try (PrintWriter writer = new PrintWriter("770855_sequencial.txt")) 
        {
            String matricula = "770855";
            writer.printf("%s\t%dms\t%d%n", matricula, tempoExecucao, totalComparacoes);
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}