from scipy.special import comb
from random import randrange


def num_surjective(n, m):
    """
    :return: the number of surjective functions from a set of size n
             to a set of size m
    """
    return int(sum([(-1)**k * comb(m, k) * (m - k)**n for k in range(0, m + 1)]))

def simulate_dice(num_dice, num_distinct, simulations=100_000, faces=6):
    """
    Calculates with a simulation, not formula. Therefore only gives an upper
    bound and is more accurate with more rounds of simulations.

    :return: number of ways to roll num_dice dice at once with num_distinct
             distinct numbers according to simulation
    """
    trials = set()
    for i in range(simulations):
        rolls = sorted([randrange(1, faces + 1) for _ in range(num_dice)])
        if len(set(rolls)) == num_distinct:
            trials.add(tuple(rolls))
    return len(trials)
