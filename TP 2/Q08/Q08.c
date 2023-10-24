#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>

// Constants for the maximum number of players and the maximum length of strings
#define MAX_PLAYERS 3922
#define MAX_STRING_LENGTH 100

// Structure to represent a player
typedef struct Player
{
    int id;
    char nome[MAX_STRING_LENGTH];
    int altura;
    int peso;
    char universidade[MAX_STRING_LENGTH];
    int anoNascimento;
    char cidadeNascimento[MAX_STRING_LENGTH];
    char estadoNascimento[MAX_STRING_LENGTH];
} Player;

int numberComparisons = 0;   // Counter for comparisons in the sorting algorithm
int numberMovements = 0;     // Counter for data movements in the sorting algorithm
double executionTime = 0.0;  // Store the execution time of the sorting algorithm

// Function to perform the shell sort on an array of players
void shellSort(Player players[], int size)
{
    int gap = 1;

    // Calculate the initial gap value for shell sort
    while (gap < size)
    {
        gap = gap * 3 + 1;
    }

    while (gap > 0)
    {
        gap /= 3;
        for (int i = gap; i < size; i++)
        {
            Player tmp = players[i];
            int j = i - gap;

            // Compare and move elements in the sorting process
            while (j >= 0 && (players[j].peso > tmp.peso ||
                (players[j].peso == tmp.peso && strcmp(players[j].nome, tmp.nome) > 0)))
            {
                players[j + gap] = players[j];
                j -= gap;
                numberComparisons++;  // Increment the comparison counter
                numberMovements++;    // Increment the movement counter
            }

            players[j + gap] = tmp;
        }
    }
}

// Function to split a string into substrings using a comma as the delimiter
void splitString(char line[], char substrings[8][MAX_STRING_LENGTH])
{
    int numSubstrings = 0;
    int currentSubstringPos = 0;
    int currentPosition = 0;

    // Initialize the substrings array
    for (int i = 0; i < 8; i++)
    {
        for (int j = 0; j < MAX_STRING_LENGTH; j++)
        {
            substrings[i][j] = '\0';
        }
    }

    while (line[currentPosition] != '\0')
    {
        if (line[currentPosition] != ',')
        {
            while (line[currentPosition] != ',' && line[currentPosition] != '\0')
            {
                if (line[currentPosition] == '\n')
                {
                    currentPosition++;
                }
                else
                {
                    substrings[numSubstrings][currentSubstringPos] = line[currentPosition];
                    currentPosition++;
                    currentSubstringPos++;
                }
            }
            currentSubstringPos = 0;
            numSubstrings++;
        }
        else
        {
            if (line[currentPosition + 1] == ',' || line[currentPosition + 1] == '\n' || line[currentPosition + 1] == '\0')
            {
                strcpy(substrings[numSubstrings], "nao informado");
                numSubstrings++;
            }
            currentPosition++;
        }
    }
}

// Function to read data from a file and store it in an array of players
void readData(Player players[], FILE *file)
{
    char line[200];
    int numPlayers = -1;

    while (fgets(line, sizeof(line), file) != NULL)
    {
        char substrings[8][MAX_STRING_LENGTH];

        if (numPlayers >= 0)
        {
            // Parse and store player data from the file
            splitString(line, substrings);
            int ID = atoi(substrings[0]);
            int height = atoi(substrings[2]);
            int weight = atoi(substrings[3]);
            int birthYear = atoi(substrings[5]);

            // Populate the player structure
            players[numPlayers].id = ID;
            strcpy(players[numPlayers].nome, substrings[1]);
            players[numPlayers].altura = height;
            players[numPlayers].peso = weight;
            strcpy(players[numPlayers].universidade, substrings[4]);
            players[numPlayers].anoNascimento = birthYear;
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

// Function to print the attributes of a player with the given ID
void printPlayer(int id, Player players[])
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           players[id].id,
           players[id].nome,
           players[id].altura,
           players[id].peso,
           players[id].anoNascimento,
           players[id].universidade,
           players[id].cidadeNascimento,
           players[id].estadoNascimento);
}

// Function to create a log file
void createLog()
{
    FILE *logFile = fopen("/tmp/770855_shellsort.txt", "w");
    fprintf(logFile, "770855\t%d\t%d\t%lf", numberComparisons + 1, numberMovements * 3, executionTime);
    fclose(logFile);
}

int main()
{
    clock_t startTime, endTime;

    char id[50];
    Player players[MAX_PLAYERS];
    Player subPlayers[MAX_PLAYERS];
    int numPlayers = 0;
    FILE *dataFile = fopen("/tmp/players.csv", "r");

    if (dataFile == NULL)
    {
        printf("Erro ao abrir o arquivo. Verifique o caminho e a permissão de acesso.\n");
        return 1; // Encerra o programa com código de erro
    }

    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identifier = atoi(id);

            // Read and store player data from the file
            readData(players, dataFile);
            subPlayers[numPlayers] = players[identifier];
            numPlayers++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(dataFile);

    startTime = clock();
    shellSort(subPlayers, numPlayers);
    endTime = clock();

    // Print sorted player data
    for (int i = 0; i < numPlayers; i++)
    {
        printPlayer(i, subPlayers);
    }

    executionTime = (double)(endTime - startTime) / CLOCKS_PER_SEC;

    // Create a log file
    createLog();

    return 0;
}
