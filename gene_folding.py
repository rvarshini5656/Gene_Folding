def valid(sequence):
    flag = 1
    for ele in sequence:  # TO ALLOW ONLY A G C T IN GENE_SERIES
        if ele not in Accept_list:
            flag = 0
            break
    if flag == 0:
        return -1
    else:
        return goof(sequence)

def checkfold(sequence):
    mid = len(sequence) // 2
    while mid > 0: 
        if sequence[:mid][::-1] == sequence[mid: 2 * mid]: 
            sequence = sequence[mid:]
            mid = len(sequence) // 2 
        else: 
            mid -= 1 
    return sequence[::-1]

def goof(sequence): 
    while True:
        initial_sequence = sequence 
        sequence = checkfold(sequence)
        sequence = checkfold(sequence)  # CHECKING FOLDINGS AGAIN FOR REVERSE OF THE STRING
        if sequence == initial_sequence:         
            break
    return len(sequence)

# Take input from the user
sequence = input("Please enter the gene sequence: ")
Accept_list = ['A', 'G', 'C', 'T']
print(valid(sequence))

