# Implements RSA encryption and decryption,
# in addition to a modular power function
# Author: Alexander Zhao Wu
import sympy as sp


# Provides a helper function
class ModExp:
    # input x should be < n
    # >>> ModExp.power_mod(2, 6, 7)
    # 1
    def power_mod(x, pow, n):
        if pow == 0:
            return 1
        power_half = ModExp.power_mod(x, pow // 2, n)
        square = (power_half * power_half) % n
        return square if pow % 2 == 0 else (square * x) % n


class RSA:
    prime_min = 1e5
    prime_max = 2e5

    # generates N, e (if needed), and d
    def __init__(self, p=None, q=None, e=None):
        if not p:
            p = sp.randprime(RSA.prime_min, RSA.prime_max)
        if not q:
            q = sp.randprime(RSA.prime_min, RSA.prime_max)
        self._p = p # _p and _q only used for debugging
        self._q = q
        self.N = p * q
        if not e:
            self._find_e_and_d()
        else: # find d from given e
            self.e = e
            self._d = sp.mod_inverse(e, (p - 1) * (q - 1))

    # generates the smallest e >= 3 that has an inverse
    def _find_e_and_d(self):
        self.e = sp.nextprime(2)
        modulus = (self._p - 1) * (self._q - 1)
        while True:
            try:
                self._d = sp.mod_inverse(self.e, modulus)
                break
            except ValueError:
                self.e = sp.nextprime(self.e)

    def encrypt(self, x):
        return ModExp.power_mod(x, self.e, self.N)

    def decrypt(self, y):
        return ModExp.power_mod(y, self._d, self.N)

    def __str__(self):
        return "RSA\np: {0}, q: {1}, N: {2}\ne: {3} d: {4}"\
                .format(self._p, self._q, self.N, self.e, self._d)

    def __repr__(self):
        return "RSA({0}, {1}, {2})".format(self._p, self._q, self.e)
