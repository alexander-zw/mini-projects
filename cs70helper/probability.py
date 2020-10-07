# Simulations to verify probability calculations
import random

def three_geom_prob(p, k):
    """ Pr(X + Y + Z <= k) where X, Y, Z are independent geometric distributions """
    q = 1 - p
    prob = 0
    for i in range(1, k - 1):
        inner_sum = 0
        for j in range(1, k - i - 1):
            inner_sum += p * q**(j - 1) * (1 - q**(k - i - j))
        prob += p * q**(i - 1) * inner_sum
    return prob

def simulate_three_geom_prob(p, k, n=10_000):
    three_success_count = 0
    for _ in range(n):
        success_count = 0
        for i in range(1, k + 1):
            if random.random() < p:
                success_count += 1
                if success_count == 3:
                    three_success_count += 1
                    break
    return three_success_count / n
