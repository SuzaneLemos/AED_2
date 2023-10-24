#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>

int numeroC, numeroM;
double tempoE;
clock_t inicio, fim;

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
void bubbleSort(Jogador jogadores[], int tam);
void split(char linha[], char substrings[8][100]);
void readPlayers(Jogador jogadores[], FILE *file);
void printPlayer(int identificador, Jogador jogadores[]);
void criarLog();

int main()
{
    char id[500];
    Jogador jogadores[3922];
    Jogador subJogadores[500];
    int numeroJogador = 0;
    FILE *file = fopen("/tmp/players.csv", "r"); // Abre o arquivo para leitura.

    // Verifica se o arquivo foi aberto com sucesso.
    if (file == NULL)
    {
        printf("Erro ao abrir o arquivo. Verifique o caminho e a permissão de acesso.\n");
        return 1; // Encerra o programa com código de erro.
    }

    // Loop para ler IDs e selecionar jogadores com base nesses IDs.
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            readPlayers(jogadores, file); // Lê informações dos jogadores do arquivo.
            subJogadores[numeroJogador] = jogadores[identificador];
            numeroJogador++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(file); // Fecha o arquivo após a leitura.

    inicio = clock(); // Inicia a contagem do tempo.

    bubbleSort(subJogadores, numeroJogador); // Chama a função de ordenação.

    fim = clock(); // Encerra a contagem do tempo.

    // Loop para imprimir os jogadores ordenados.
    for (int i = 0; i < numeroJogador; i++)
    {
        printPlayer(i, subJogadores);
    }

    tempoE = (double)(fim - inicio) / CLOCKS_PER_SEC; // Calcula o tempo de execução.

    criarLog(); // Cria um arquivo de log com informações sobre a ordenação.
}

// Função para realizar ordenação com o algoritmo Bubble Sort.
void bubbleSort(Jogador jogadores[], int tam)
{
    for (int i = tam - 1; i > 0; i--)
    {
        numeroC++; // Contabiliza uma comparação.
        for (int j = 0; j < tam - 1; j++)
        {
            numeroC += 2; // Contabiliza duas comparações.

            if (jogadores[j].anoNascimento > jogadores[j + 1].anoNascimento)
            {
                Jogador aux = jogadores[j];
                jogadores[j] = jogadores[j + 1];
                jogadores[j + 1] = aux;
                numeroM++; // Contabiliza um movimento.
            }
            else if (jogadores[j].anoNascimento == jogadores[j + 1].anoNascimento)
            {
                if (strcmp(jogadores[j + 1].nome, jogadores[j].nome) < 0)
                {
                    Jogador aux = jogadores[j + 1];
                    jogadores[j + 1] = jogadores[j];
                    jogadores[j] = aux;
                    numeroM++; // Contabiliza um movimento.
                }
            }
        }
    }
}

// Função para dividir uma linha em substrings com base em vírgulas.
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
                    c++;
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

// Função para ler informações de jogadores a partir de um arquivo.
void readPlayers(Jogador jogadores[], FILE *file)
{
    char linha[200];
    int qtJogadores = -1; // Inicializado como -1 para pular a primeira linha do arquivo.

    while (fgets(linha, sizeof(linha), file) != NULL)
    {
        char substrings[8][100];

        if (qtJogadores >= 0)
        {
            split(linha, substrings); // Divide a linha em substrings.
            int ID = atoi(substrings[0]);
            int h = atoi(substrings[2]);
            int p = atoi(substrings[3]);
            int ano = atoi(substrings[5]);

            // Preenche a estrutura Jogador com informações lidas.
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

// Função para imprimir as informações de um jogador.
void printPlayer(int identificador, Jogador jogadores[])
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

// Função para criar um arquivo de log com informações sobre a ordenação.
void criarLog()
{
    FILE *arq = fopen("/tmp/770855_bubble.txt", "w");
    fprintf(arq, "770855\t%d\t%d\t%lf", numeroC + 1, numeroM * 3, tempoE);
    fclose(arq);
}
