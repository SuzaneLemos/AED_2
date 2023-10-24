import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Jogadores 
{
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    // Construtor que lê informações dos jogadores a partir de um arquivo
    public Jogadores(String pedido, File tabela) 
    {
        try 
        {
            Scanner scan = new Scanner(tabela);

            while (scan.hasNextLine()) 
            {
                String linha = scan.nextLine();
                String[] elementos = linha.split(",", -1);

                // Verifica se algum campo está vazio e preenche com "nao informado"
                for (int i = 0; i < elementos.length; i++) 
                {
                    if (elementos[i].isEmpty()) 
                    {
                        elementos[i] = "nao informado";
                    }
                }

                // Verifica se o pedido corresponde a um jogador e se há 8 elementos na linha
                if (pedido.equals(elementos[0]) && elementos.length == 8) 
                {
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
        } 
        
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
            System.out.println("arquivo não encontrado");
        }
    }

    public Jogadores() 
    {
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    private void setNome(String nome) 
    {
        this.nome = nome;
    }

    public void setAltura(int altura) 
    {
        this.altura = altura;
    }

    public void setPeso(int peso) 
    {
        this.peso = peso;
    }

    public void setUniversidade(String universidade) 
    {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) 
    {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) 
    {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) 
    {
        this.estadoNascimento = estadoNascimento;
    }

    public int getId() 
    {
        return this.id;
    }

    public int getAltura() 
    {
        return this.altura;
    }

    public String getNome() 
    {
        return this.nome;
    }

    public int getPeso() 
    {
        return this.peso;
    }

    public String getUniversidade() 
    {
        return this.universidade;
    }

    public int getAnoNascimento() 
    {
        return this.anoNascimento;
    }

    public String getCidadeNascimento() 
    {
        return this.cidadeNascimento;
    }

    public String getEstadoNascimento() 
    {
        return this.estadoNascimento;
    }

    public String dados() 
    {
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

public class Jogador 
{
    static int contador = 0;

    // Cria um arquivo de log com informações sobre o processo de ordenação
    public static void criarLog(Long duration) 
    {
        String fileName = "/tmp/770855_insercao.txt";

        try 
        {
            File logFile = new File(fileName);
            FileWriter writer = new FileWriter(logFile);
            writer.write("770855" + "\t" + duration + "\t" + contador);
            writer.close();
        } 
        
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Ordena o array de jogadores com base no ano de nascimento e nome
    public static void ordenar(int fim, Jogadores[] jogadores) 
    {
        for (int i = 0; i < fim; i++) 
        {
            int menor = i;
            contador++;

            for (int j = i + 1; j < fim; j++) 
            {
                if (jogadores[j].getAnoNascimento() < jogadores[menor].getAnoNascimento()) {
                    menor = j;
                    contador++;
                }

                if (jogadores[j].getAnoNascimento() == jogadores[menor].getAnoNascimento()) {
                    contador++;
                    if (jogadores[j].getNome().compareTo(jogadores[menor].getNome()) < 0) {
                        menor = j;
                        contador++;
                    }
                }
            }

            Jogadores aux = jogadores[i];
            jogadores[i] = jogadores[menor];
            jogadores[menor] = aux;
        }

        contador++;
    }

    public static void main(String[] args) 
    {
        long startTime = System.nanoTime();

        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");

        // Verifica se o arquivo de jogadores existe
        if (!tabela.exists() || !tabela.isFile()) 
        {
            System.out.println("Arquivo players.csv não encontrado.");
            return;
        }

        Jogadores[] jogadores = new Jogadores[500];
        String pedido;
        int numeroJogadores = 0;

        do {
            pedido = scan.nextLine();

            if (!pedido.equalsIgnoreCase("FIM")) 
            {
                Jogadores player = new Jogadores(pedido, tabela);
                jogadores[numeroJogadores] = player;
                numeroJogadores++;
            }
        } 
        
        while (!pedido.equalsIgnoreCase("FIM"));
        scan.close();

        ordenar(numeroJogadores, jogadores);

        for (int i = 0; i < numeroJogadores; i++) 
        {
            System.out.println(jogadores[i].dados());
        }

        long endTime = System.nanoTime();
        criarLog(endTime - startTime);
    }
}
