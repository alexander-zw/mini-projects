# deep sum nested list
from functools import reduce

def deep_sum(lst):
    if not isinstance(lst, list):
        return lst
    else:
        sum = 0
        for e in lst:
            sum += deep_sum(e)
        return sum
        # return reduce(lambda x, y: deep_sum(x) + deep_sum(y), lst, 0)
