# some functions that use bitwise operations


def not_power_two(x):
    return x & (x - 1)


def power_16(x):
    # check sign bit and divisible by 16 (or is 1)
    if x >> 31 != 0 or (x & 0xf != 0 and x != 1) or x == 0:
        return 0
    return x == 1 or power_16(x >> 4)
