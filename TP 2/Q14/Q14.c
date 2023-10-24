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
void countSort(int size, Jogador jogador[], int place);
int maxo(Jogador Jogador[], int tam);
void Radix(Jogador jogadores[], int tam);
void split(char linha[], char substrings[8][100]);
void ler(Jogador jogadores[], FILE *file);
void imprime(int identificador, Jogador jogadores[]);
void criaLog();

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
            ler(jogadores, file); // Lê informações dos jogadores do arquivo.
            subJogadores[numeroJogador] = jogadores[identificador];
            numeroJogador++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(file); // Fecha o arquivo após a leitura.

    inicio = clock(); // Inicia a contagem do tempo.

    Radix(subJogadores, numeroJogador); // Chama a função de ordenação Radix.

    fim = clock(); // Encerra a contagem do tempo.

    // Loop para imprimir os jogadores ordenados.
    for (int i = 0; i < numeroJogador; i++)
    {
        imprime(i, subJogadores);
    }

    tempoE = (double)(fim - inicio); // Calcula o tempo de execução.

    criaLog(); // Cria um arquivo de log com informações sobre a ordenação.
}

// Função para realizar a ordenação Radix Sort.
void Radix(Jogador jogadores[], int tam)
{
    int place;
    int max = maxo(jogadores, tam);

    for (place = 1; max / place > 0; place *= 10)
    {
        numeroC++;
        countSort(tam, jogadores, place);
    }
}

// Função para realizar o Counting Sort no Radix Sort.
void countSort(int size, Jogador jogador[], int place)
{
    int max = (jogador[0].id / place) % 10;
    Jogador output[size];

    for (int i = 0; i < size; i++)
    {
        numeroC++;
        if ((jogador[i].id / place) % 10 > max)
        {
            max = (jogador[i].id / place) % 10;
            max = jogador[i].id;
            numeroC++;
        }
    }

    int count[max + 1];

    for (int i = 0; i < max + 1; i++)
    {
        count[i] = 0;
        numeroC++;
    }
    for (int i = 0; i < size; i++)
    {
        count[(jogador[i].id / place) % 10]++;
        numeroC++;
    }
    for (int i = 1; i < max + 1; i++)
    {
        count[i] += count[i - 1];
        numeroC++;
    }

    for (int i = size - 1; i >= 0; i--)
    {
        output[count[(jogador[i].id / place) % 10] - 1] = jogador[i];
        count[(jogador[i].id / place) % 10]--;
        numeroC++;
    }

    for (int i = 0; i < size; i++)
    {
        jogador[i] = output[i];
        numeroC++;
    }
}

// Função para encontrar o maior valor na lista de jogadores.
int maxo(Jogador Jogador[], int tam)
{
    int max = Jogador[0].id;
    for (int i = 1; i < tam; i++)
    {
        numeroC++;
        if (Jogador[i].id > max)
        {
            max = Jogador[i].id;
            numeroC++;
        }
    }
    return max;
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
    FILE *arq = fopen("/tmp/770855_radixsort.txt", "w");
    fprintf(arq, "770855\t%d\t%d\t%lf", numeroC + 1, numeroM * 3, tempoE);
    fclose(arq);
}
