#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>

// Declaração de variáveis globais (não usadas no código principal)
int numeroC, numeroM;
double tempoE;
clock_t inicio, fim;

// Definição da estrutura para armazenar informações dos jogadores
typedef struct Jogadores
{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogadores;

int main()
{
    char id[500];
    Jogadores jogadores[3922];
    Jogadores subJogadores[500];
    int numeroJogador = 0;

    // Abre o arquivo para leitura
    FILE *file = fopen("/tmp/players.csv", "r");

    // Verifica se o arquivo foi aberto com sucesso
    if (file == NULL)
    {
        printf("Erro ao abrir o arquivo.\n");
        return 1; // Encerra o programa com código de erro.
    }

    do
    {
        scanf("%s", id);

        // Verifica se a entrada é "FIM" ou "fim" para encerrar o loop
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);

            // Chama a função "ler" para ler os dados do arquivo e armazenar em "subJogadores"
            ler(jogadores, file);
            subJogadores[numeroJogador] = jogadores[identificador];
            numeroJogador++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(file); // Fecha o arquivo

    // Chama a função "heapsort" para classificar os jogadores em ordem decrescente de altura
    heapsort(numeroJogador, subJogadores);

    int k = 10;
    Jogadores min[k];

    // Copia os primeiros 10 jogadores classificados para o array "min"
    for (int i = 0; i < k; i++)
    {
        min[i] = subJogadores[i];
    }

    // Exibe os dados dos 10 jogadores mais altos
    for (int i = 0; i < k; i++)
    {
        imprime(i, min);
    }
}

// Função para ajustar a estrutura de heap
void doHeap(Jogadores jogadores[], int i, int fim)
{
    int raiz = i;
    int esquerda = 2 * i + 1;
    int direita = 2 * i + 2;

    // Encontra o índice do nó com a maior altura
    if (esquerda < fim && jogadores[esquerda].altura > jogadores[raiz].altura)
    {
        raiz = esquerda;
    }

    if (direita < fim && jogadores[direita].altura > jogadores[raiz].altura)
    {
        raiz = direita;
    }

    // Se o nó raiz não for o nó atual, faz a troca e chama recursivamente
    if (raiz != i)
    {
        Jogadores aux = jogadores[i];
        jogadores[i] = jogadores[raiz];
        jogadores[raiz] = aux;
        doHeap(jogadores, raiz, fim);
    }
}

// Função para classificar os jogadores usando o algoritmo de heapsort
void heapsort(int fim, Jogadores jogadores[])
{
    // Constrói um heap inicial
    for (int i = (fim / 2) - 1; i >= 0; i--)
    {
        doHeap(jogadores, i, fim);
    }

    // Remove elementos do heap um por um e reconstrói o heap
    for (int i = fim - 1; i >= 0; i--)
    {
        Jogadores aux = jogadores[0];
        jogadores[0] = jogadores[i];
        jogadores[i] = aux;
        doHeap(jogadores, 0, i);
    }

    // Ordena jogadores com a mesma altura em ordem alfabética
    for (int i = 0; i < fim; i++)
    {
        for (int j = i + 1; j < fim; j++)
        {
            if (jogadores[i].altura == jogadores[j].altura)
            {
                if (strcmp(jogadores[i].nome, jogadores[j].nome) > 0)
                {
                    Jogadores aux = jogadores[j];
                    jogadores[j] = jogadores[i];
                    jogadores[i] = aux;
                }
            }
        }
    }
}

// Função para dividir uma linha em substrings usando vírgula como separador
void split(char linha[], char substrings[8][100])
{
    int qtSubstrings = 0;
    int cS = 0;
    int c = 0;

    // Inicializa a matriz de substrings com valores nulos
    for (int i = 0; i < 8; i++)
    {
        for (int j = 0; j < 100; j++)
        {
            substrings[i][j] = '\0';
        }
    }

    // Divide a linha em substrings usando vírgula como separador
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

// Função para ler os dados dos jogadores do arquivo
void ler(Jogadores jogadores[], FILE *file)
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
void imprime(int identificador, Jogadores jogadores[])
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
