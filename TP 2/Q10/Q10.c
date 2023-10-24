#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>

// Variáveis globais para contagem de comparações, movimentos e tempo de execução.
int numeroC, numeroM;
float tempoE;

// Definição da estrutura Jogador para armazenar informações sobre jogadores.
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

// Protótipos de funções.
void doQuick(Jogador jogadores[], int esq, int dir);
void Quik(Jogador jogadores[], int inicio, int fim);
void split(char linha[], char substrings[8][100]);
void ler(Jogador jogadores[], FILE *file);
void imprime(int identificador, Jogador jogadores[]);
void criaLog();

int main()
{
    clock_t inicio, fim;
    char id[50];
    Jogador jogadores[3922];                     // Array principal para armazenar todos os jogadores.
    Jogador subJogadores[500];                   // Array para armazenar jogadores selecionados.
    int numeroJogador = 0;                       // Contador de jogadores selecionados.
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
            ler(jogadores, file); // Lê informações dos jogadores do arquivo.
            subJogadores[numeroJogador] = jogadores[identificador];
            numeroJogador++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(file); // Fecha o arquivo após a leitura.

    inicio = clock(); // Inicia a contagem do tempo.

    Quik(subJogadores, 0, numeroJogador); // Chama a função de ordenação.

    fim = clock(); // Encerra a contagem do tempo.

    // Loop para imprimir os jogadores ordenados.
    for (int i = 0; i < numeroJogador; i++)
    {
        imprime(i, subJogadores);
    }

    tempoE = (float)(fim - inicio) / CLOCKS_PER_SEC; // Calcula o tempo de execução.

    criaLog(); // Cria um arquivo de log com informações sobre a ordenação.
}

// Função para realizar a ordenação rápida (Quick Sort) dos jogadores.
void doQuick(Jogador jogadores[], int esq, int dir)
{
    int i = esq, j = dir;
    Jogador pivo = jogadores[(dir + esq) / 2]; // Seleciona um pivô.
    Jogador aux;

    numeroC++; // Contabiliza uma comparação.

    while (i <= j)
    {
        while (strcmp(jogadores[i].estadoNascimento, pivo.estadoNascimento) < 0)
        {
            i++;
            numeroC++; // Contabiliza uma comparação.
        }

        while (strcmp(jogadores[j].estadoNascimento, pivo.estadoNascimento) > 0)
        {
            j--;
            numeroC++; // Contabiliza uma comparação.
        }

        if (i <= j)
        {
            numeroC++; // Contabiliza uma comparação.
            aux = jogadores[i];
            jogadores[i] = jogadores[j];
            jogadores[j] = aux;
            i++;
            j--;
            numeroM++; // Contabiliza um movimento.
        }
        numeroC += 4; // Contabiliza 4 comparações.
    }

    numeroC++; // Contabiliza uma comparação.

    if (esq < j)
    {
        doQuick(jogadores, esq, j); // Recursivamente ordena a partição esquerda.
    }

    numeroC++; // Contabiliza uma comparação.

    if (i < dir)
    {
        doQuick(jogadores, i, dir); // Recursivamente ordena a partição direita.
    }
}

// Função para ordenar jogadores usando o Quick Sort.
void Quik(Jogador jogadores[], int inicio, int fim)
{
    doQuick(jogadores, inicio, fim - 1); // Chama a função de ordenação rápida.

    // Loop para ordenar jogadores com base no nome, em caso de estados de nascimento iguais.
    for (int i = 0; i < fim; i++)
    {
        for (int j = i + 1; j < fim; j++)
        {
            if (strcmp(jogadores[i].estadoNascimento, jogadores[j].estadoNascimento) == 0)
            {
                if (strcmp(jogadores[i].nome, jogadores[j].nome) > 0)
                {
                    Jogador aux = jogadores[i];
                    jogadores[i] = jogadores[j];
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

    // Inicializa todas as substrings como vazias.
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

// Função para ler informações de jogadores a partir de um arquivo.
void ler(Jogador jogadores[], FILE *file)
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

// Função para criar um arquivo de log com informações sobre a ordenação.
void criaLog()
{
    FILE *arq = fopen("/tmp/770855_quicksort.txt", "w");
    fprintf(arq, "770855\t%d\t%d \t%f", numeroC + 2, numeroM * 3, tempoE);
    fclose(arq);
}
