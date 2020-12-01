# Implements some helpers for polynomials over finite fields
# Author: Alexander Zhao Wu
import sympy as sp
from rsa import ModExp

class Polynomial:
    n = None
    verbose = False

    def __init__(self, coeffs, n=None):
        # beginning at 0 degree term to d+1 degree term
        # zero polynomial can be represented as empty list
        self.coeffs = coeffs
        if n:
            self.n = n

    def of(self, x):
        y = 0
        for i in range(self.degree + 1):
            y += (self.coeffs[i] * ModExp.power_mod(x, i, self.n)) % self.n
            y %= self.n
        return y

    # coefficient for the term with given degree
    def coeff(self, degree):
        if degree > self.degree:
            return 0
        return self.coeffs[degree]

    def __add__(self, other):
        if self.n != other.n:
            raise ValueError(
                    "Cannot add polynomials of different finite fields")

        if self.degree > other.degree:
            sum_coeffs = self.coeffs.copy()
            poly_to_add = other
        else:
            sum_coeffs = other.coeffs.copy()
            poly_to_add = self

        for i in range(self.degree + 1):
            sum_coeffs[i] = (sum_coeffs[i] + poly_to_add.coeff(i)) % self.n
        return Polynomial(sum_coeffs, self.n)

    def scale(self, a):
        scaled_coeffs = self.coeffs.copy()
        for i in range(self.degree + 1):
            scaled_coeffs[i] = a * scaled_coeffs[i] % self.n
        return Polynomial(scaled_coeffs, self.n)


    def __mul__(self, other):
        if self.n != other.n:
            raise ValueError(
                    "Cannot multiply polynomials of different finite fields")

        # zero polynomial is empty list
        prod_coeffs = [0] * ((self.degree + 1 + other.degree + 1))

        for i in range(self.degree + 1):
            for j in range(other.degree + 1):
                prod_coeffs[i + j] += (self.coeff(i) * other.coeff(j))\
                                        % self.n
                prod_coeffs[i + j] %= self.n
        return Polynomial(prod_coeffs, self.n)

    def __eq__(self, other):
        return self.n == other.n and self.coeffs == other.coeffs
    
    def __hash__(self):
        return hash(self.n) * 31 + hash(self.coeffs)

    # degree determined by number of points
    # a point is a 2-tuple
    def lagrange_construct(points, n):
        result = Polynomial([0], n)
        for i in range(len(points)):
            p = points[i]
            if p[1] == 0:
                # don't need to compute this delta
                continue
            numerator = Polynomial([1], n) # start with 1
            for pp in points:
                if p[0] == pp[0]:
                    continue # skip current point
                # I'm using negative coefficients here, but I think that's ok
                numerator = numerator.mul(Polynomial([-pp[0], 1], n))
            denominator = numerator.of(p[0])
            delta = numerator.scale(sp.mod_inverse(denominator, n))
            result = result.add(delta.scale(p[1]))
        return result

    @property # also trims leading zeros of polynomial
    def degree(self):
        for i in range(len(self.coeffs) - 1, -1, -1):
            if self.coeffs[i] != 0:
                return i
            self.coeffs.pop()
        # we make the degree of the zero polynomial -1
        return -1

    def __repr__(self):
        return "Polynomial({0}, {1})".format(self.coeffs, self.n)

    def _term_str(degree, coeff):
        if coeff == 0:
            return ""
        if degree == 0:
            return str(coeff)
        if degree == 1:
            string = "x + "
        else:
            string = "x^{1} + "
        if coeff != 1:
            string = "{0}" + string
        return string.format(coeff, degree)

    def __str__(self):
        string = "Polynomial: " if self.verbose else ""
        if not self.coeffs: # zero polynomial
            return string + "0"
        for i in range(self.degree, -1, -1):
            string += Polynomial._term_str(i, self.coeffs[i])
        if string[-3:] == " + ":
            string = string[:-3]
        mod_str = " (mod {0})".format(self.n) if self.verbose else ""
        return string + mod_str
