# contains implementation of several fields
# Author: Alexander Zhao Wu

from abc import ABC, abstractmethod
import sympy as sp

class Field(ABC):
    @property
    @abstractmethod
    def zero(self):
        pass

    @property
    @abstractmethod
    def id(self):
        pass

    @abstractmethod
    def __add__(self, other):
        pass

    def __sub__(self, other):
        return self + (-other)

    @abstractmethod
    def __mul__(self, other):
        pass

    def __truediv__(self, other):
        return self * other.inv()

    @abstractmethod
    def __neg__(self):
        pass

    @abstractmethod
    def inv(self):
        pass

    # exponent is a nonegative integer, not a member of the field
    def __pow__(self, exponent):
        if exponent == 0:
            return self.id
        power_half = self**(exponent // 2)
        square = power_half * power_half
        return square if exponent % 2 == 0 else square * self

    def factorial(self):
        if self == self.id or self == self.zero:
            return self.id
        return self * (self - self.id).factorial()

class Real(Field):
    def __init__(self, r):
        self.val = r

    @property
    def zero(self):
        return Real(0)

    @property
    def id(self):
        return Real(1)

    def __add__(self, other):
        return Real(self.val + other.val)

    def __mul__(self, other):
        return Real(self.val * other.val)

    def __neg__(self):
        return Real(-self.val)

    def inv(self):
        return Real(1 / self.val)

    def __eq__(self, other):
        return self.val == other.val

    def __repr__(self):
        return repr(self.val)

    def __str__(self):
        return str(self.val)

class PrimeMod(Field):
    def __init__(self, value, modulus):
        self.val = value
        self.mod = modulus

    @property
    def zero(self):
        return PrimeMod(0, self.mod)

    @property
    def id(self):
        return PrimeMod(1, self.mod)

    def __add__(self, other):
        return PrimeMod((self.val + other.val) % self.mod, self.mod)

    def __mul__(self, other):
        return PrimeMod((self.val * other.val) % self.mod, self.mod)

    # just keeps it negative to save time
    def __neg__(self):
        return PrimeMod(-self.val, self.mod)

    def inv(self):
        return PrimeMod(sp.mod_inverse(self.val, self.mod), self.mod)

    # should only be used to compare same modulus numbers
    def __eq__(self, other):
        return (self.val % self.mod) == (other.val % self.mod)

    def __repr__(self):
        return repr(self.val)

    def __str__(self):
        return str(self.val)

class MontPrimeMod(PrimeMod):
    """ Uses Montgomery multiplication """
    def __init__(self, mont_val, modulus, pow_of_two, R):
        self.mod = modulus
        self.mont_val = mont_val
        self.pow_of_two = pow_of_two
        self.R = R

    @classmethod
    def from_value(cls, value, modulus):
        n = sp.ceiling(sp.log(modulus) / sp.log(2))
        pow_of_two = 2**n
        R = modulus - sp.mod_inverse(modulus, pow_of_two % modulus)
        mont_val = (value * pow_of_two) % modulus
        return cls(mont_val, modulus, pow_of_two, R)

    @property
    def value(self):
        return (self * MontPrimeMod(1, self.mod, self.pow_of_two, self.R)).mont_val

    @property
    def zero(self):
        return MontPrimeMod.from_value(0, self.mod)

    @property
    def id(self):
        return MontPrimeMod.from_value(1, self.mod)

    def __add__(self, other):
        return MontPrimeMod((self.mont_val + other.mont_val) % self.mod, self.mod, self.pow_of_two, self.R)

    # WARNING: currently buggy :<
    def __mul__(self, other):
        """ Montgomery multiplication """
        x = self.mont_val * other.mont_val
        s = ((x % self.pow_of_two) * self.R) % self.pow_of_two
        t = (x + s * self.mod) // self.pow_of_two
        new_mont = t if t < self.mod else t - self.mod
        return MontPrimeMod(new_mont, self.mod, self.pow_of_two, self.R)

    # just keeps it negative to save time
    def __neg__(self):
        return self.zero - self

    def inv(self):
        return MontPrimeMod.from_value(sp.mod_inverse(self.value, self.mod), self.mod)

    # should only be used to compare same modulus numbers
    def __eq__(self, other):
        return self.mont_val == other.mont_val

    def __repr__(self):
        return "MontPrimeMod(%d, %d, %d, %d)" % (self.mont_val, self.mod, self.pow_of_two, self.R)

    def __str__(self):
        return "%d mod %d, mont_val=%d" % (self.value, self.mod, self.mont_val)


# an algebraic rational expression
# contains variables and constants combined with +-*/
# Unfortunately NOT implemented yet
class Expr(Field):
    def __init__(self, r):
        self.val = r

    @property
    def zero(self):
        return Real(0)

    @property
    def id(self):
        return Real(1)

    def __add__(self, other):
        return Real(self.val + other.val)

    def __mul__(self, other):
        return Real(self.val * other.val)

    def __neg__(self):
        return Real(-self.val)

    def inv(self):
        return Real(1 / self.val)

    def __eq__(self, other):
        return self.val == other.val

    def __repr__(self):
        return repr(self.val)

    def __str__(self):
        return str(self.val)
