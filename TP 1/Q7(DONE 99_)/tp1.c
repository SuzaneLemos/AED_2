#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main()
{
    char word[1000];
    char x1[3], x2[3], x3[3], x4[3];

    do
    {
        scanf(" %[^\n]", word);

        if (strcmp(word, "FIM") != 0) // Se entrada for diferente de FIM, a execucao continua
        {

            if (isConsonant(word) == true)
            {
                printf("SIM ");
            }
            else
            {
                printf("NAO ");
            }
            if (isVogal(word) == true)
            {
                printf("SIM ");
            }
            else
            {
                printf("NAO ");
            }
            if (isInteger(word) == true)
            {
                printf("SIM ");
            }
            else
            {
                printf("NAO ");
            }
            if (isFloat(word) == true)
            {
                printf("SIM\n");
            }
            else
            {
                printf("NAO\n");
            }
            // printf("%s %s %s %s", &x1, &x2, &x3, &x4);
        }
    } while (strcmp(word, "FIM") != 0);

    return 0;
}

int isConsonant(char word[])
{
    bool result = true;
    for (int index = 0; index < strlen(word); index++)
    {
        char charat = word[index];
        if (!(charat != 'a' && charat != 'e' && charat != 'i' && charat != 'o' && charat != 'u' && charat != 'A' 
        && charat != 'E' && charat != 'I' && charat != 'O' && charat != 'U' && charat != '0' 
        && charat != '1' && charat != '2' && charat != '3' && charat != '4' && charat != '5' 
        && charat != '6' && charat != '7' && charat != '8' && charat != '9'))
        {
            result = false;
        }
    }
    return result;
}

int isVogal(char word[])
{
    bool result = true;
    for (int index = 0; index < strlen(word); index++)
    {
        char charat = word[index];
        // char charat = charat;
        if (charat != 'a' && charat != 'e' && charat != 'i' && charat != 'o' && charat != 'u' && charat != 'A' && charat != 'E' && charat != 'I' && charat != 'O' && charat != 'U')
        {
            result = false;
        }
    }
    return result;
}

int isInteger(char word[])
{
    bool result = true;
    for (int index = 0; index < strlen(word); index++)
    {
        char charat = word[index];
        if (charat != '0' && charat != '1' && charat != '2' 
        && charat != '3' && charat != '4' && charat != '5' 
        && charat != '6' && charat != '7' && charat != '8' && charat != '9')
        {
            result = false;
        }
    }
    return result;
}

int isFloat(char word[])
{
    bool hasPoint = false;
    bool hasNumber = false;
    bool hasChar = false;
    int length = strlen(word);
    for (int index = 0; index < length; index++)
    {
        char charat = word[index];
        if (!((charat >= '0') && (charat <= '9')))
        {
            if (charat == '.')
            {
                hasPoint = true;
            }
        }
        else if ((charat >= '0') && (charat <= '9'))
        {
            hasNumber = true;
        }
        else if (isConsonant(word) == true || isVogal(word) == true)
        {
            hasChar = true;
        }
    }
    return (hasPoint && hasNumber && !hasChar);
}