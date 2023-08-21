#include "stdio.h"
#include "stdlib.h"

void ExercicioA()
{
    int dummy = 0;

    int num = 0;
    scanf("%d", &num);
    printf("Processing\n");
    for (int i = 0; i < num * 3; i++)
    {
        printf("Loop 1 = %d\n", i);
        dummy--;
    }
    printf("\nFimLoop1\n");

    for (int i = 0; i < num * 2; i++)
    {
        for (int j = 0; j < num; j++)
        {
            printf("Loop 2 = %d\n", j);
            dummy--;
        }
    }
}

void ExercicioB()
{
    int dummy = 0;

    int num = 0, count = 0;
    scanf("%d", &num);
    printf("Processing\n");
    for (int i = 0; i < num * 5; i++)
    {
        printf("Loop 1 = %d\n", i);
        dummy--;
    }
    printf("\nFimLoop1\n");

    for (int i = 0; i < num * 4; i++)
    {
        for (int j = 0; j < num; j++)
        {
            for (int k = 0; k < num; k++)
            {
                count++;
                printf("Loop 2 = %d\n", count);
                dummy--;
            }
        }
    }
}

void ExercicioC()
{
    int dummy = 0;

    int num = 0, count = 0;
    scanf("%d", &num);
    printf("Processing\n");
    for (int i = num; i > 0; i /= 2)
    {
        printf("Loop 1 = %d\n", i);
        dummy--;
    }
    printf("\nFimLoop1\n");

    for (int i = 0; i < num; i++)
    {
        count++;
        printf("Loop 2 = %d\n", count);
        dummy--;
    }
}

void ExercicioD()
{
    int dummy = 0;

    int num = 0, count = 0;
    scanf("%d", &num);
    printf("Processing\n");

    for (int i = 0; i < num * 2; i++)
    {
        for (int j = 0; j < num; j++)
        {
            for (int k = 0; k < num; k++)
            {
                count++;
                printf("Loop 1 = %d\n", count);
                dummy--;
            }
        }
    }
    for (int i = 0; i < 5; i++)
    {
        printf("Loop 2 = %d\n", i);
        dummy--;
    }
    printf("\nFimLoop1\n");
}

void ExercicioE()
{
    int dummy = 0;
    int num = 0, count = 0, count2 = 0;
    scanf("%d", &num);
    printf("Processing\n");

    for (int i = 0; i < num * 9; i++)
    {
        for (int j = 0; j < num; j++)
        {
            for (int k = 0; k < num; k++)
            {
                for (int l = 0; l < num; l++)
                {
                    count++;
                    printf("Loop 1 = %d\n", count);
                    dummy--;
                }
            }
        }
    }
    printf("\nFimLoop1\n");

    for (int i = 0; i < num * 5; i++)
    {
        for (int j = 0; j < num; j++)
        {
            count2++;
            printf("Loop 2 = %d\n", count2);
            dummy--;
        }
    }
    printf("\nFimLoop2\n");

    for (int j = 0; j < num / 2; j++)
    {
        printf("Loop 3 = %d\n", j);
        dummy--;
    }
    printf("\nFimLoop3\n");
}

void ExercicioF()
{
    int dummy = 0;
    int num = 0, count = 0;
    scanf("%d", &num);
    printf("Processing\n");

    for (int i = num; i > 0; i /= 2)
    {
        printf("Loop 1 = %d\n", i);
        dummy--;
    }
    printf("\nFimLoop2\n");

    for (int j = 0; j < 5; j++)
    {
        for (int i = num; i > 0; i /= 2)
        {
            count++;
            printf("Loop 2 = %d\n", count);
            dummy--;
        }
    }
    printf("\nFimLoop2\n");
}

int main()
{
    ExercicioF();

    return 0;
}