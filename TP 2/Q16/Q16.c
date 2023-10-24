#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>

// Definição da estrutura Jogador
typedef struct Jogador
{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogador;

// Protótipos de funções
void ordenar(Jogador jocker[], int fim, Jogador min[], int k);
void split(char linha[], char substrings[8][100]);
void ler(Jogador jogadores[], FILE *file);
void imprime(int identificador, Jogador jogadores[]);

int main()
{
    char id[500];
    Jogador jogadores[3922];   // Estrutura para armazenar todos os jogadores
    Jogador subJogadores[500]; // Estrutura temporária para armazenar jogadores selecionados
    int numeroJogador = 0;
    FILE *file = fopen("/tmp/players.csv", "r");

    // Verifica se o arquivo foi aberto com sucesso.
    if (file == NULL)
    {
        printf("Erro ao abrir o arquivo. Verifique o caminho e a permissão de acesso.\n");
        return 1; // Encerra o programa com código de erro.
    }

    // Leitura dos jogadores a partir da entrada padrão (stdin)
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            ler(jogadores, file);
            subJogadores[numeroJogador] = jogadores[identificador];
            numeroJogador++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(file);

    int k = 10;
    Jogador min[k];

    for (int i = 0; i < k; i++)
    {
        min[i] = subJogadores[i];
    }

    // Função para ordenar os jogadores com base no ano de nascimento e nome
    ordenar(subJogadores, numeroJogador, min, k);

    // Impressão dos 10 primeiros jogadores após a classificação
    for (int i = 0; i < k; i++)
    {
        imprime(i, min);
    }
}

// Função para ordenar os jogadores
void ordenar(Jogador jocker[], int fim, Jogador min[], int k)
{
    for (int i = 0; i < k; i++)
    {
        for (int j = i + 1; j < fim; j++)
        {
            if (jocker[j].anoNascimento < min[i].anoNascimento)
            {
                Jogador aux = min[i];
                min[i] = jocker[j];
                jocker[j] = aux;
            }

            if (jocker[j].anoNascimento == min[i].anoNascimento)
            {
                if (strcmp(jocker[j].nome, min[i].nome) < 0)
                {
                    Jogador aux = min[i];
                    min[i] = jocker[j];
                    jocker[j] = aux;
                }
            }
        }
    }
}

// Função para dividir uma linha em substrings com base em vírgulas
void split(char linha[], char substrings[8][100])
{
    int qtSubstrings = 0;
    int cS = 0;
    int c = 0;

    for (int i = 0; i < 8; i++)
    {
        for (int j = 0; j < 100; j++)
        {
            substrings[i][j] = '\0';
        }
    }

    while (linha[c] != '\0')
    {
        if (linha[c] != ',')
        {
            while (linha[c] != ',' && linha[c] != '\0')
            {
                if (linha[c] == '\n')
                {
                    c++;
                }
                else
                {
                    substrings[qtSubstrings][cS] = linha[c];
                    c++;
                    cS++;
                }
            }
            cS = 0;
            qtSubstrings++;
        }
        else
        {
            if (linha[c + 1] == ',' || linha[c + 1] == '\n' || linha[c + 1] == '\0')
            {
                strcpy(substrings[qtSubstrings], "nao informado");
                qtSubstrings++;
            }
            c++;
        }
    }
}

// Função para ler os jogadores do arquivo CSV
void ler(Jogador jogadores[], FILE *file)
{
    char linha[200];
    int qtJogadores = -1;

    while (fgets(linha, sizeof(linha), file) != NULL)
    {
        char substrings[8][100];
        if (qtJogadores >= 0)
        {
            split(linha, substrings);
            int ID = atoi(substrings[0]);
            int h = atoi(substrings[2]);
            int p = atoi(substrings[3]);
            int ano = atoi(substrings[5]);

            jogadores[qtJogadores].id = ID;
            strcpy(jogadores[qtJogadores].nome, substrings[1]);
            jogadores[qtJogadores].altura = h;
            jogadores[qtJogadores].peso = p;
            strcpy(jogadores[qtJogadores].universidade, substrings[4]);
            jogadores[qtJogadores].anoNascimento = ano;
            strcpy(jogadores[qtJogadores].cidadeNascimento, substrings[6]);
            strcpy(jogadores[qtJogadores].estadoNascimento, substrings[7]);
            qtJogadores++;
        }
        else
        {
            qtJogadores++;
        }
    }
}

// Função para imprimir os dados de um jogador
void imprime(int identificador, Jogador jogadores[])
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogadores[identificador].id,
           jogadores[identificador].nome,
           jogadores[identificador].altura,
           jogadores[identificador].peso,
           jogadores[identificador].anoNascimento,
           jogadores[identificador].universidade,
           jogadores[identificador].cidadeNascimento,
           jogadores[identificador].estadoNascimento);
}
