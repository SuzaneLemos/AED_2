import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
 * Enunciado:
 * 
 * Pesquisa Sequencial em Java: Faça a inserção de alguns registros no final de um vetor e,
 * em seguida, faça algumas pesquisas sequenciais. A chave primária de pesquisa será o atributo
 * nome. A entrada padrão é composta por duas partes onde a primeira é igual a entrada da
 * primeira questão. As demais linhas correspondem a segunda parte. A segunda parte é composta
 * por várias linhas. Cada uma possui um elemento que deve ser pesquisado no vetor. A última
 * linha terá a palavra FIM. A saída padrão será composta por várias linhas contendo as palavras
 * SIM ou NAO para indicar se existe cada um dos elementos pesquisados. Além disso, crie um
 * arquivo de log na pasta corrente com o nome matrícula sequencial.txt com uma única linha
 * contendo sua matrícula, tempo de execução do seu algoritmo e número de comparações. Todas
 * as informações do arquivo de log devem ser separadas por uma tabulação ’\t’.
 * 
 * Inserção e Pesquisa Sequencial em Vetor
 * Este documento descreve um algoritmo para a inserção de registros no final de um vetor e a subsequente realização de pesquisas sequenciais com base na chave primária, que é o atributo nome.
 * 
 * Entrada
 * A entrada padrão consiste em duas partes no mesmo arquivo separadas pela palavra FIM:
 * 
 * A primeira parte é igual à entrada da primeira questão./ que termina com a paravra FIM.
 * 
 * A segunda parte é composta por várias linhas, cada uma contendo um elemento que deve ser pesquisado no vetor. A última linha da segunda parte contém a palavra FIM.
 * 
 * Saída
 * A saída padrão será composta por várias linhas, cada uma contendo as palavras SIM ou NAO para indicar se cada um dos elementos pesquisados foi encontrado no vetor.
 * 
 * Registro de Log
 * Além disso, um arquivo de log será criado na pasta corrente com o nome matrícula_sequencial.txt. Este arquivo conterá uma única linha com as seguintes informações, separadas por uma tabulação ('\t'):
 * 
 * Sua matrícula
 * Tempo de execução do algoritmo
 * Número de comparações realizadas durante a execução
 */

public class Q03 
{
    private static int totalComparacoes = 0;

    public static void main(String[] args) 
    {
        long inicioExecucao = System.nanoTime();

        Scanner scanner = new Scanner(System.in);
        List<Integer> numeros = new ArrayList<>();
        List<String> nomes = new ArrayList<>();

        separarEntradaEmDuasPartes(scanner, numeros, nomes);
        Map<Integer, String> numeroParaNome = buscarNomesCorrespondentes(numeros);
        verificarCorrespondenciaNomes(numeroParaNome, nomes);

        scanner.close();

        long tempoExecucao = (System.nanoTime() - inicioExecucao) / 1000000; // Converta para milissegundos

        criarArquivoLog(tempoExecucao);
    }

    public static void separarEntradaEmDuasPartes(Scanner scanner, List<Integer> numeros, List<String> nomes) 
    {
        // Variável para controlar em qual parte estamos
        boolean primeiraParte = true;

        while (scanner.hasNextLine()) 
        {
            String linha = scanner.nextLine();

            // Verifica se a linha é igual a "FIM" para alternar entre as partes
            if (linha.equals("FIM")) 
            {
                if (primeiraParte) 
                {
                    primeiraParte = false; // Muda para a segunda parte
                } 
                
                else 
                {
                    break; // Finaliza a entrada
                }
            } 
            
            else 
            {
                // Verifica se estamos na primeira parte (números) ou segunda parte (nomes)
                if (primeiraParte) 
                {
                    // Tenta converter a linha para um número e adiciona à lista de números
                    try 
                    {
                        int numero = Integer.parseInt(linha);
                        numeros.add(numero);
                    } 
                    
                    catch (NumberFormatException e) {
                        // Ignora linhas inválidas na primeira parte
                    }
                } 
                
                else 
                {
                    // Segunda parte (nomes) - adiciona à lista de nomes
                    nomes.add(linha);
                }
            }
        }
    }

    public static Map<Integer, String> buscarNomesCorrespondentes(List<Integer> numeros) 
    {
        Map<Integer, String> numeroParaNome = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("/tmp/players.csv"))) 
        {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = reader.readLine()) != null) 
            {
                if (primeiraLinha) 
                {
                    primeiraLinha = false;
                    continue;
                }

                String[] campos = linha.split(",");

                if (campos.length >= 2) 
                {
                    int numero = Integer.parseInt(campos[0]);
                    String nome = campos[1];

                    if (numeros.contains(numero)) 
                    {
                        numeroParaNome.put(numero, nome);
                    }
                }
            }
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return numeroParaNome;
    }

    public static void verificarCorrespondenciaNomes(Map<Integer, String> numeroParaNome, List<String> nomes) 
    {
        for (String nome : nomes) 
        {
            boolean correspondenciaEncontrada = false;

            for (Map.Entry<Integer, String> entry : numeroParaNome.entrySet()) 
            {
                totalComparacoes++; // Incrementa o contador de comparações

                if (entry.getValue().equals(nome)) 
                {
                    correspondenciaEncontrada = true;
                    break;
                }
            }

            if (correspondenciaEncontrada) 
            {
                System.out.println("SIM");
            } 
            
            else 
            {
                System.out.println("NAO");
            }
        }
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


