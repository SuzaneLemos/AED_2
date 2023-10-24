#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>

// Variáveis globais para contagem de comparações e movimentações
static int numeroC, numeroM;
double tempoE;

// Definição da estrutura "Player" para armazenar informações de jogadores
typedef struct Player
{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Player;

int main()
{
    clock_t startTime, endTime;

    char id[500];
    Player players[3921];
    Player subPlayers[500];
    int numPlayer = 0;
    FILE *file = fopen("/tmp/players.csv", "r");

    if (file == NULL)
    {
        printf("Erro ao abrir o arquivo /tmp/players.csv. Verifique se o arquivo existe e se o caminho está correto.\n");
        return 1; // Encerra o programa com um código de erro
    }

    // Lê IDs dos jogadores e preenche um array "subPlayers" com os jogadores correspondentes
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identifier = atoi(id);
            readPlayers(players, file);

            subPlayers[numPlayer] = players[identifier];
            numPlayer++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(file);

    startTime = clock();

    // Chama a função de classificação "selectionSort" para ordenar os jogadores em "subPlayers"
    selectionSort(subPlayers, numPlayer);

    endTime = clock();

    // Imprime os jogadores ordenados
    for (int i = 0; i < numPlayer; i++)
    {
        printPlayer(i, subPlayers);
    }

    // Calcula o tempo decorrido e gera um arquivo de log
    tempoE = (double)(endTime - startTime) / CLOCKS_PER_SEC;
    createLog();
}

// Função para ordenar um array de jogadores usando o algoritmo de seleção
void selectionSort(Player players[], int size)
{
    for (int i = 0; i < size - 1; i++)
    {
        for (int j = i + 1; j < size; j++)
        {
            numeroC++;
            // Compara os nomes dos jogadores para ordená-los
            if (strcmp(players[i].nome, players[j].nome) > 0)
            {
                // Troca os jogadores de posição
                Player temp = players[i];
                players[i] = players[j];
                players[j] = temp;
                numeroM++;
            }
        }
    }
}

// Função para dividir uma linha em substrings com base em vírgulas
void splitLine(char line[], char substrings[8][100])
{
    int numSubstrings = 0;
    int currentSubstrIndex = 0;
    int currentIndex = 0;

    // Inicializa a matriz de substrings com valores nulos
    for (int i = 0; i < 8; i++)
    {
        for (int j = 0; j < 100; j++)
        {
            substrings[i][j] = '\0';
        }
    }

    // Percorre a linha e extrai substrings com base nas vírgulas
    while (line[currentIndex] != '\0')
    {
        if (line[currentIndex] != ',')
        {
            while (line[currentIndex] != ',' && line[currentIndex] != '\0')
            {
                if (line[currentIndex] == '\n')
                {
                    currentIndex++;
                }
                else
                {
                    substrings[numSubstrings][currentSubstrIndex] = line[currentIndex];
                    currentIndex++;
                    currentSubstrIndex++;
                }
            }
            currentSubstrIndex = 0;
            numSubstrings++;
        }
        else
        {
            if (line[currentIndex + 1] == ',' || line[currentIndex + 1] == '\n' || line[currentIndex + 1] == '\0')
            {
                // Se não houver dados, define o valor como "nao informado"
                strcpy(substrings[numSubstrings], "nao informado");
                numSubstrings++;
            }
            currentIndex++;
        }
    }
}

// Função para ler informações de jogadores de um arquivo CSV
void readPlayers(Player players[], FILE *file)
{
    char line[3921];
    int numPlayers = -1;

    while (fgets(line, sizeof(line), file) != NULL)
    {
        char substrings[8][100];
        if (numPlayers >= 0)
        {
            splitLine(line, substrings);

            // Converte informações e preenche a estrutura "Player"
            int ID = atoi(substrings[0]);
            int height = atoi(substrings[2]);
            int weight = atoi(substrings[3]);
            int yearOfBirth = atoi(substrings[5]);

            players[numPlayers].id = ID;
            strcpy(players[numPlayers].nome, substrings[1]);
            players[numPlayers].altura = height;
            players[numPlayers].peso = weight;
            strcpy(players[numPlayers].universidade, substrings[4]);
            players[numPlayers].anoNascimento = yearOfBirth;
            strcpy(players[numPlayers].cidadeNascimento, substrings[6]);
            strcpy(players[numPlayers].estadoNascimento, substrings[7]);
            numPlayers++;
        }
        else
        {
            numPlayers++;
        }
    }
}

// Função para imprimir informações de um jogador
void printPlayer(int identifier, Player players[])
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           players[identifier].id,
           players[identifier].nome,
           players[identifier].altura,
           players[identifier].peso,
           players[identifier].anoNascimento,
           players[identifier].universidade,
           players[identifier].cidadeNascimento,
           players[identifier].estadoNascimento);
}

// Função para criar um arquivo de log com informações sobre comparações, movimentações e tempo
void createLog()
{
    FILE *logFile = fopen("/tmp/770855_selecaoRecursiva.txt", "w");
    fprintf(logFile, "770855 \t%d \t%d \t%lf", numeroC + 2, numeroM * 3, tempoE);
    fclose(logFile);
}
