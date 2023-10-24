#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Definição da estrutura Jogador
struct Jogador {
    int id;
    int altura;
    int peso;
    int anoNascimento;
    char nome[100];
    char universidade[100];
    char cidadeNascimento[100];
    char estadoNascimento[100];
};

// Protótipos das funções para a estrutura Jogador
void setId(struct Jogador *jogador, int id);
int getId(struct Jogador jogador);
void setAltura(struct Jogador *jogador, int altura);
int getAltura(struct Jogador jogador);
void setPeso(struct Jogador *jogador, int peso);
int getPeso(struct Jogador jogador);
void setAnoNascimento(struct Jogador *jogador, int anoNascimento);
int getAnoNascimento(struct Jogador jogador);
void setNome(struct Jogador *jogador, const char *nome);
const char* getNome(struct Jogador jogador);
void setUniversidade(struct Jogador *jogador, const char *universidade);
const char* getUniversidade(struct Jogador jogador);
void setCidadeNascimento(struct Jogador *jogador, const char *cidadeNascimento);
const char* getCidadeNascimento(struct Jogador jogador);
void setEstadoNascimento(struct Jogador *jogador, const char *estadoNascimento);
const char* getEstadoNascimento(struct Jogador jogador);
struct Jogador clone(struct Jogador jogador);
void imprimir(struct Jogador jogador);
void ler(struct Jogador *jogador, int id);
void separarLinha(char *linha, char partes[8][100]);
void preencherAtributos(struct Jogador *jogador, char partes[8][100]);

// Implementação dos métodos para a estrutura Jogador
void setId(struct Jogador *jogador, int id) {
    jogador->id = id;
}

int getId(struct Jogador jogador) {
    return jogador.id;
}

void setAltura(struct Jogador *jogador, int altura) {
    jogador->altura = altura;
}

int getAltura(struct Jogador jogador) {
    return jogador.altura;
}

void setPeso(struct Jogador *jogador, int peso) {
    jogador->peso = peso;
}

int getPeso(struct Jogador jogador) {
    return jogador.peso;
}

void setAnoNascimento(struct Jogador *jogador, int anoNascimento) {
    jogador->anoNascimento = anoNascimento;
}

int getAnoNascimento(struct Jogador jogador) {
    return jogador.anoNascimento;
}

void setNome(struct Jogador *jogador, const char *nome) {
    strncpy(jogador->nome, nome, sizeof(jogador->nome));
    jogador->nome[sizeof(jogador->nome) - 1] = '\0';
}

const char* getNome(struct Jogador jogador) {
    return jogador.nome;
}

void setUniversidade(struct Jogador *jogador, const char *universidade) {
    strncpy(jogador->universidade, universidade, sizeof(jogador->universidade));
    jogador->universidade[sizeof(jogador->universidade) - 1] = '\0';
}

const char* getUniversidade(struct Jogador jogador) {
    return jogador.universidade;
}

void setCidadeNascimento(struct Jogador *jogador, const char *cidadeNascimento) {
    strncpy(jogador->cidadeNascimento, cidadeNascimento, sizeof(jogador->cidadeNascimento));
    jogador->cidadeNascimento[sizeof(jogador->cidadeNascimento) - 1] = '\0';
}

const char* getCidadeNascimento(struct Jogador jogador) {
    return jogador.cidadeNascimento;
}

void setEstadoNascimento(struct Jogador *jogador, const char *estadoNascimento) {
    strncpy(jogador->estadoNascimento, estadoNascimento, sizeof(jogador->estadoNascimento));
    jogador->estadoNascimento[sizeof(jogador->estadoNascimento) - 1] = '\0';
}

const char* getEstadoNascimento(struct Jogador jogador) {
    return jogador.estadoNascimento;
}

struct Jogador clone(struct Jogador jogador) {
    struct Jogador jogadorClone;
    jogadorClone.id = jogador.id;
    jogadorClone.altura = jogador.altura;
    jogadorClone.peso = jogador.peso;
    jogadorClone.anoNascimento = jogador.anoNascimento;
    setNome(&jogadorClone, getNome(jogador));
    setUniversidade(&jogadorClone, getUniversidade(jogador));
    setCidadeNascimento(&jogadorClone, getCidadeNascimento(jogador));
    setEstadoNascimento(&jogadorClone, getEstadoNascimento(jogador));
    return jogadorClone;
}

void imprimir(struct Jogador jogador) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento, jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
}

void ler(struct Jogador *jogador, int id) {
    FILE *arquivo = fopen("/tmp/players.csv", "r");

    if (arquivo == NULL) {
        printf("Arquivo players.csv não encontrado.\n");
        return;
    }

    char linha[256];
    fgets(linha, sizeof(linha), arquivo);

    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        int jogadorId;
        char nome[100], universidade[100], cidadeNascimento[100], estadoNascimento[100];
        int altura, peso, anoNascimento;

        int numCampos = sscanf(linha, "%d,%99[^,],%d,%d,%99[^,],%d,%99[^,],%99[^\n]", &jogadorId, nome, &altura, &peso, universidade, &anoNascimento, cidadeNascimento, estadoNascimento);

        // Verifique se há campos vazios e substitua por "nao informado"
        if (numCampos < 8) {
            if (strchr(nome, ',') == NULL) {
                strcpy(nome, "nao informado");
            }
            if (strchr(universidade, ',') == NULL) {
                strcpy(universidade, "nao informado");
            }
            if (strchr(cidadeNascimento, ',') == NULL) {
                strcpy(cidadeNascimento, "nao informado");
            }
            if (strchr(estadoNascimento, ',') == NULL) {
                strcpy(estadoNascimento, "nao informado");
            }
        }

        // Remova vírgula final, se existir
        int len = strlen(estadoNascimento);
        if (estadoNascimento[len - 1] == ',') {
            estadoNascimento[len - 1] = '\0';
        }

        if (jogadorId == id) {
            setId(jogador, jogadorId);
            setAltura(jogador, altura);
            setPeso(jogador, peso);
            setAnoNascimento(jogador, anoNascimento);
            setNome(jogador, nome);
            setUniversidade(jogador, universidade);
            setCidadeNascimento(jogador, cidadeNascimento);
            setEstadoNascimento(jogador, estadoNascimento);

            imprimir(*jogador);
            break;
        }
    }

    fclose(arquivo);
}

void separarLinha(char *linha, char partes[8][100]) {
    char *token = strtok(linha, ",");
    int i = 0;
    while (token != NULL) {
        strncpy(partes[i], token, 100);
        token = strtok(NULL, ",");
        i++;
    }
}

void preencherAtributos(struct Jogador *jogador, char partes[8][100]) {
    setId(jogador, atoi(partes[0]));
    setNome(jogador, partes[1]);
    setAltura(jogador, atoi(partes[2]));
    setPeso(jogador, atoi(partes[3]));
    setUniversidade(jogador, partes[4]);
    setAnoNascimento(jogador, atoi(partes[5]));
    setCidadeNascimento(jogador, partes[6]);
    setEstadoNascimento(jogador, partes[7]);
}

int main() {
    struct Jogador jogador;

    while (1) {
        char input[10];
        scanf("%s", input);

        if (strcmp(input, "FIM") == 0) {
            break;
        }

        int id = atoi(input);
        ler(&jogador, id);
    }

    return 0;
}