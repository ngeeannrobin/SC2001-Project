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


int knapSack2(int C, int n, int wt[], int val[], int profit[]){
    if (C<0){
        return 0;
    } 
    // P(C) has already been calculated before, just return the calculated value.
    if (profit[C] != -1){
        return profit[C];
    }
    else {
        int maxProfit = 0;
        // for every item,
        for (int i=0; i<n;i++)
            // if item fits in knapsack,
            if (C-wt[i] >0)
                // update maxProfit if this item has a higher profit than the previous.
                maxProfit = max(maxProfit, knapSack2(C-wt[i],n,wt,val,profit) + val[i]);
        profit[C] = maxProfit;
    }
    return profit[C];
}

int main() {

    // Initialise weights, values, number of items, and knapsack capacity (used in both approach).
    int wt1[] = {4, 6, 8};
    int val1[] = {7, 6, 9};
    int n1 = sizeof(wt1) / sizeof(wt1[0]);
    int C1 = 14;

    // Initialise profit array that will store profits for all values of C (used in P()).
    int profit1[C1+1];
    profit1[0] = 0;
    for (int i=1; i<C1+1; i++){
        profit1[i] = -1;
    }

    // Get results using both approach
    int result1 = knapSack(C1, wt1, val1, n1);
    int resultP1 = knapSack2(C1, n1, wt1, val1, profit1);

    printf("Maximum profit for the first set of weights and profits is: %d (approach 1) / %d (approach 2)\n", result1,resultP1);

    // Initialise weights, values, number of items, and knapsack capacity (used in both approach).
    int wt2[] = {5, 6, 8};
    int val2[] = {7, 6, 9};
    int n2 = sizeof(wt2) / sizeof(wt2[0]);
    int C2 = 14;
    // Initialise profit array that will store profits for all values of C (used in P()).
    int profit2[C2+1];
    profit2[0] = 0;
    for (int i=1; i<C2+1; i++){
        profit2[i] = -1;
    }

    // Get results using both approach
    int result2 = knapSack(C2, wt2, val2, n2);
    int resultP2 = knapSack2(C2, n2, wt2, val2, profit2);

    printf("Maximum profit for the second set of weights and profits is: %d (approach 1) / %d (approach 2)\n", result2,resultP2);

    return 0;
}