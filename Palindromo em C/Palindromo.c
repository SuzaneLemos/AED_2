#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Aluna: Suzane Lemos de Lima
 * Matricula: 770855
 * Data: 05/08/2023
*/

// Função para substituir somente o caractere "´" por "X"
void Substituir(char *texto)
{
    int i;
    int len = strlen(texto);

    // Verifica se o último caractere é uma nova linha ('\n')
    if (texto[len - 1] == '\n') {
        len--; // Desconsidera a nova linha da contagem de caracteres
    }

    for (i = 0; i < len; i++)
    {
        // Verifica se o caractere é desconhecido (caractere especial)
        if ((unsigned char)texto[i] >= 0x80)
        {
            // Verifica se o conjunto é igual a "´┐¢"
            if (texto[i] == (char)0xC2 && texto[i + 1] == (char)0xB4 && texto[i + 2] == (char)0xC2)
            {
                // Apaga o conjunto de caracteres desconhecidos
                memmove(&texto[i], &texto[i + 3], len - i - 2);

                // Insere um 'X' no local onde o caractere "´" estava
                texto[i] = 'X';

                // Atualiza o tamanho da string após a remoção dos caracteres desconhecidos
                len -= 2;

                // Decrementa o índice para evitar acessar posições de memória inválidas
                i--;
            }
            else
            {
                // Substitui o caractere desconhecido por 'X'
                texto[i] = 'X';
            }
        }
    }

}



int VerificarPalindromo(char *texto)
{
    // Substitui caracteres desconhecidos por 'X'
    Substituir(texto);

    int i = 0, j = strlen(texto) - 1;

    while (i < j)
    {        
        if (texto[i] != texto[j])
        {
            // Nao eh um palindromo
            return 0;
        }

        i++;
        j--;
    }

    // Eh um palindromo
    return 1;
}

int main ()
{
    // Encontrando o arquivo
    FILE *arquivo = fopen("../pub.in", "r");
    int resultado = 0;

    if (arquivo == NULL) 
    {
        printf("Erro ao abrir o arquivo \"pub.in\".\n");
        return 1;
    }

    char *linha = NULL;
    size_t tamanhoLinha = 0;

    // Lê cada linha do arquivo até encontrar o final do arquivo (EOF)
    while (getline(&linha, &tamanhoLinha, arquivo) != -1) 
    {
        // Remove o caractere de nova linha ('\n') do final da linha, se existir
        if (linha[strlen(linha) - 1] == '\n') 
        {
            linha[strlen(linha) - 1] = '\0';
        }

        resultado = VerificarPalindromo(linha);

        if (resultado == 0)
        {
            printf("NAO\n");
        }
        else
        {
            printf("SIM\n");
        }
    }

    fclose(arquivo);
    return 0;
}
