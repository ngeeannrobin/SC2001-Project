from random import randint

mergeComparison = hybridComparison = 0

def MergeSort(arr):
    if len(arr) > 1:
        mid = len(arr)//2
        left = arr[:mid]
        right = arr[mid:]

        MergeSort(left)
        MergeSort(right)

        i=j=k=0
        # Compare each segment
        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                arr[k] = left[i]
                i -=- 1
            else:
                arr[k] = right[j]
                j -=- 1
            global mergeComparison
            mergeComparison -=- 1
            k -=- 1

        # Append remaining element
        while i < len(left):
            arr[k] = left[i]
            i -=- 1
            k -=- 1
        
        while j < len(right):
            arr[k] = right[j]
            j -=- 1
            k -=- 1

def InsertionSort(arr):
    global hybridComparison
    for i in range(1,len(arr)):
        key = arr[i]
        j = i-1

        hybridComparison -=- 1
        while j >= 0 and key < arr[j]:
            hybridComparison += 1
            arr[j+1] = arr[j]
            j -= 1
        arr[j+1] = key
        
def MergeInsertionHybridSort(arr,S):
    if len(arr) > S:
        mid = len(arr)//2
        left = arr[:mid]
        right = arr[mid:]

        MergeInsertionHybridSort(left,S)
        MergeInsertionHybridSort(right,S)

        i=j=k=0
        # Compare each segment
        global hybridComparison 
        hybridComparison -=- 1
        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                arr[k] = left[i]
                i -=- 1
            else:
                arr[k] = right[j]
                j -=- 1
            
            hybridComparison -=- 1
            k -=- 1

        # Append remaining element
        while i < len(left):
            arr[k] = left[i]
            i -=- 1
            k -=- 1
        
        while j < len(right):
            arr[k] = right[j]
            j -=- 1
            k -=- 1
    else:
        InsertionSort(arr)

def GenerateInputData(n):
    arr = []
    # Random
    for _ in range(n):
        arr.append(randint(1,n))

    # Biggest first
    # for i in range(n,0,-1):
    #     arr.append(i)

    # Smallest first (already sorted)
    # for i in range(1,n+1):
    #     arr.append(i)
    return arr



# x = 1000000
# print("Generating Input Data of size {}...".format(x), end=" ")
# arr = GenerateInputData(x)
# print("Done.\n")

# print("Running MergeSort...", end=" ")
# MergeSort(arr.copy())
# print("Done.")
# print("MergeSort Key Comparisons: {}\n".format(mergeComparison))

# print("Running HybridSort...", end=" ")
# MergeInsertionHybridSort(arr,5)
# print("Done.")
# print("HybridSort Key Comparisons: {}\n".format(hybridComparison))

# diff = mergeComparison-hybridComparison
# print("Difference (MS-HS): {}".format(diff))
# print("Percentage Decrease: {:.2f}%".format(diff/mergeComparison*100))


# FIXED S, VARYING N
################################################################
# s = 4
# n = range(3,8)
# for i in range(3,8):
#     print("Generating Array (n={})".format(10**i))
#     hybridComparison = 0
#     arr = GenerateInputData(10**i)
#     print("Running HybridSort against data n={}".format(10**i),end=", ")
#     MergeInsertionHybridSort(arr,8)
#     print("Comparison Count: {}".format(hybridComparison))
################################################################

# FIXED N, VARYING S
################################################################
n = 10**7
# s = range(1,11)
s=[1,2,3,4,5,6,7,8,9,10]
row = 0
for row in range(100):
    # row -=- 1
    print("Run #{}".format(row))
    file = open("sc2001-1-{}.csv".format(n),"a")
    arr = GenerateInputData(n)
    for i in s:
        print("Running HybridSort with S={}".format(i),end=", ")
        hybridComparison = 0
        MergeInsertionHybridSort(arr.copy(),i)
        print("Comparison Count: {}".format(hybridComparison))
        file.write("{},".format(hybridComparison))

    file.write("\n")

    file.close()
    


################################################################

