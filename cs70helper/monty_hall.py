# simulates monty hall problem
import random

NUM_DOORS = 3

def simulate(n, switch):
    """
    Simulates the monty hall problem for n rounds.

    :param n: the number of rounds
    :param switch: whether the player switches when the host reveals a goat
    :return: how many times the player got the car
    """
    success = 0;
    for i in range(n):
        car = random.randrange(NUM_DOORS)
        choice = random.randrange(NUM_DOORS)
        if switch:
            # host opens a door to reveal a goat
            opened = random.choice([x for x in range(NUM_DOORS) if x != car and x != choice])
            # switch to another door that is not the opened one or the original choice
            choice = random.choice([x for x in range(NUM_DOORS) if x != opened and x != choice])
        if choice == car:
            success += 1 # got the car
    return success

def success_percentage(n, switch):
    return simulate(n, switch) / n * 100
