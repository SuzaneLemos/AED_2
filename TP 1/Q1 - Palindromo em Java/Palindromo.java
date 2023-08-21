import java.io.*;
import java.util.*;
import java.text.Normalizer;

/*
 * Aluna: Suzane Lemos de Lima
 * Matricula: 770855
 * Data: 03/08/2023
*/

public class Palindromo 
{
    // Funcao para ler o arquivo e retornar o conteudo
    public static String lerArquivo(String arquivo) throws IOException
    {
        // Para obter o caminho do arquivo
        InputStream inputStream = Palindromo.class.getClassLoader().getResourceAsStream(arquivo);

        // Verificando se foi encontrado
        if (inputStream == null)
        {
            throw new IOException("Arquivo nao encontrado: " + arquivo);
        }

        // Criando o StringBuilder para armazenar o conteudo
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)))
        {
            // Ler linha por linha 
            String linha;

            while ((linha = br.readLine()) != null)
            {
                conteudo.append(linha).append("\n");
            }
        }

        // Retornando o conteudo 
        return conteudo.toString();

    }

    // Funcao para remover nao-letras e retirar acentos
    public static String removerIndesejados (String texto)
    {
        // Normalizar o texto para substituir o que eh acentuado 
        String normalizar = Normalizer.normalize(texto, Normalizer.Form.NFD);

        // Remove os caracteres que nao sao alfabeticos
        return normalizar.replaceAll ("[^\\p{ASCII}]", "");
    }

    // Funcao para verificar se eh um palindromo ou nao
    public static String verificarPalindromo(String texto) 
    {
        String textoFormatado = removerIndesejados(texto);
    
        int i = 0;
        int j = textoFormatado.length() - 1;
    
        // Verificando se eh um palindromo
        while (i < j) 
        {
            if (textoFormatado.charAt(i) != textoFormatado.charAt(j)) 
            {
                return "NAO"; // Retorna "NAO" apenas se NÃO for um palíndromo
            }
    
            i++;
            j--;
        }
    
        return "SIM"; // Retorna "SIM" se for um palíndromo
    }
    

    public static void main (String[] args)
    {
        // Declarando varivel
        String conteudoArq = " ";

        try
        {
            // Chamando a funcao lerArquivo da classe Leitor e armazenando o conteudo retornado
            conteudoArq = lerArquivo("pub.in");
        }

        // Caso ocorra um erro
        catch (IOException erro)
        {
            System.err.println ("Ocorreu um erro: " + erro.getMessage());
        }


        // Comecando o exercicio pedido sobre palindromo
            // Criando um StringBuilder para ler a variavel "conteudo" linha por linha
        StringReader stringReader = new StringReader(conteudoArq);

        try (BufferedReader br = new BufferedReader(stringReader))
        {
            // Lendo as linhas
            String linha;

            while ((linha = br.readLine()) != null)
            {
                // Conferir se eh um palindromo chamando a funcao verificarPalindromo
                String resultado = verificarPalindromo(linha);
                System.out.println (resultado);                
            }
        }

        // Caso ocorra um erro
        catch (IOException error)
        {
            System.err.println ("Ocorreu um erro: " + error.getMessage());
        }

    }
}
