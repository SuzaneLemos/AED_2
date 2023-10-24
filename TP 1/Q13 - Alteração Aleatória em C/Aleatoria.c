#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

char substituteIn(char *word, char target, char toChar) {
    int index;
    char charat;
    char *finalw = (char *)malloc(strlen(word) + 1);

    for (index = 0; index < strlen(word); index++) {
        if (word[index] == target) {
            charat = toChar;
        } else {
            charat = word[index];
        }
        finalw[index] = charat;
    }
    finalw[index] = '\0';

    return finalw;
}

char rndChar() {
    int rnd = rand() % 52;
    char base = (rnd < 26) ? 'a' : 'A';

    return base + rnd % 26;
}

int main() {
    char frase[100];
    srand(time(NULL));

    while (1) {
        printf("Digite uma frase (ou 'FIM' para sair): ");
        fgets(frase, sizeof(frase), stdin);
        frase[strlen(frase) - 1] = '\0';  // Remove o caractere de nova linha

        if (strcmp(frase, "FIM") == 0) {
            break;
        }

        // Limpar o buffer após a leitura da linha
        while (getchar() != '\n');

        char target = rndChar();
        char toChar = rndChar();

        char *result = substituteIn(frase, target, toChar);
        printf("%s\n", result);
        free(result);
    }

    return 0;
}
