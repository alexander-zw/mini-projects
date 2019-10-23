# Support for traditional stable marriage algorithm execution
import random
import copy


class SMA:
    def __init__(self, n=0, men_pref=None, women_pref=None):
        """
        Initialize an instance of a stable marriage matching.

        :param n: the number of men, which is equal to the number of women;
        ignored if men_pref or women_pref is provided
        :param men_pref: a list of lists, where each nested list is a list of
        preferences for the man at that index
        :param women_pref: a list of lists, where each nested list is a list of
        preferences for the man at that index
        """
        self.men_pref = men_pref if men_pref else SMA.generate_random_pref(n)
        self.women_pref = women_pref if women_pref else SMA.generate_random_pref(n)
        self.pairing = []  # map from women to their paired men
        self.iterations = None;

    @property
    def n(self):
        return len(self.men_pref)

    def generate_random_pref(n):
        pref_lists = []
        for i in range(n):
            pref_list = list(range(n))
            random.shuffle(pref_list)
            pref_lists.append(pref_list)
        return pref_lists

    def run(self):
        remaining_men_pref = copy.deepcopy(self.men_pref)
        self.pairing = [-1] * self.n
        rejected = [True] * self.n
        self.iterations = 0;
        while any(rejected):
            for man in range(self.n):
                if rejected[man]:
                    self.pairing[man] = remaining_men_pref[man].pop(0)

            for woman in range(self.n):
                preferred_man = None
                best_ranking = self.n
                for man in range(self.n):
                    ranking = self.women_pref[woman].index(man)
                    if self.pairing[man] == woman:
                        if ranking < best_ranking:
                            if preferred_man is not None:
                                rejected[preferred_man] = True
                            preferred_man = man
                            best_ranking = ranking
                            rejected[man] = False
                        else:
                            rejected[man] = True
            self.iterations += 1

    def pretty_pairing(self):
        if not self.pairing:
            return "[not calculated]"
        return " ".join(["({}, {})".format(SMA.pretty_person(man, False),
                                           SMA.pretty_person(self.pairing[man], self.n <= 26))
                         for man in range(self.n)])

    def __repr__(self):
        return "SMA({})".format(len(self.men_pref))

    def __str__(self):
        """
        Represents men with numbers 1-n and women with letters A-Z.
        If we would run out of letters, however, just use numbers for women as well.

        Displays men's preferences first, then women's.
        """
        sma_str = "SMA Instance:\n"
        for i in range(len(self.men_pref)):
            sma_str += "{}: {}\n".format(SMA.pretty_person(i, False),
                                         SMA.pretty_pref_list(self.men_pref[i], True))
        sma_str += "\n"
        for i in range(len(self.women_pref)):
            sma_str += "{}: {}\n".format(SMA.pretty_person(i, self.n <= 26),
                                         SMA.pretty_pref_list(self.women_pref[i], False))
        return sma_str[:-1] # remove last newline

    def pretty_pref_list(pref_list, use_letters):
        if use_letters and len(pref_list) > 26:
            return SMA.pretty_pref_list(pref_list, False)
        else:
            return ", ".join([SMA.pretty_person(x, use_letters) for x in pref_list])

    def pretty_person(person, use_letters):
        return chr(person + 65) if use_letters else str(person + 1)

