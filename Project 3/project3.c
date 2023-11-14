#include <stdio.h>

int max(int a, int b) {
    return (a > b) ? a : b;
}

int knapSack(int C, int wt[], int val[], int n) {
    int i, j;
    int profit[n + 1][C + 1];

    for (i = 0; i <= n; i++) {
        for (j = 0; j <= C; j++) {
            if (i == 0 || j == 0)
                profit[i][j] = 0;
            else if (wt[i - 1] <= j)
                profit[i][j] = max(val[i - 1] + profit[i][j - wt[i - 1]], profit[i - 1][j]);
            else
                profit[i][j] = profit[i - 1][j];
        }
    }

    return profit[n][C];
}

int main() {
    int wt1[] = {4, 6, 8};
    int val1[] = {7, 6, 9};
    int n1 = sizeof(wt1) / sizeof(wt1[0]);
    int C1 = 14;
    int result1 = knapSack(C1, wt1, val1, n1);

    printf("Maximum profit for the first set of weights and profits is: %d\n", result1);

    int wt2[] = {5, 6, 8};
    int val2[] = {7, 6, 9};
    int n2 = sizeof(wt2) / sizeof(wt2[0]);
    int C2 = 14;
    int result2 = knapSack(C2, wt2, val2, n2);

    printf("Maximum profit for the second set of weights and profits is: %d\n", result2);

    return 0;
}