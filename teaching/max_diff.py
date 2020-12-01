"""
Given a list of elements, find the maximum sum of consecutive absolute differences
out of all the orderings.
"""
from itertools import permutations
from math import factorial
import random

def sum_of_diffs(a):
    sum = 0
    # for i in range(0, len(a) - 1, 2):
    for i in range(len(a) - 1):
        sum += abs(a[i] - a[i + 1])
    return sum

def greedy_perm(a):
    a_sorted = sorted(a)
    solution = [0] * len(a)
    for i in range(len(a) // 2):
        solution[2 * i] = a_sorted[i]
        solution[2 * i + 1] = a_sorted[len(a) - i - 1]
    if len(a) % 2 == 1:
        solution[len(a) - 1] = a_sorted[len(a) // 2]
    return solution

# a = random.sample(range(0, 100), 8)
a = list(range(4))
num_perm = factorial(len(a))
print(f"For {len(a)} items, there are a total of {num_perm} permuations")
max_sum = 0
max_perm = a
num_max = 0
counter = 0
sample_i = random.randrange(0, len(a))
sample = a
for perm in permutations(a):
    perm_sum = sum_of_diffs(perm)
    if perm_sum > max_sum:
        max_sum = perm_sum
        max_perm = perm
        num_max = 1
    elif perm_sum == max_sum:
        num_max += 1
    counter += 1
    if counter == sample_i:
        sample = perm

greedy = greedy_perm(a)

print("Max")
print(max_perm)
print(max_sum)
print("Greedy")
print(greedy)
print(sum_of_diffs(greedy))
print("Random")
print(sample)
print(sum_of_diffs(sample))
print(f"There are {num_max} orderings of the max value, {num_max / num_perm * 100}%")
