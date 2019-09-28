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
