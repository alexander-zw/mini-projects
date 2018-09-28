# Implements some helpers for polynomials over finite fields
# Author: Alexander Zhao Wu
import sympy as sp
from rsa import ModExp

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

class Polynomial:
    def __init__(self, coeffs, n):
        # beginning at 0 degree term to d+1 degree term
        self.coeffs = coeffs
        self.n = n

    def of(self, x):
        y = 0
        for i in range(len(self.coeffs)):
            y += (self.coeffs[i] * ModExp.power_mod(x, i, self.n)) % self.n
            y %= self.n
        return y

    # coefficient for the term with given degree
    def coeff(self, degree):
        if degree > self.degree:
            return 0
        return self.coeffs[degree]

    def add(self, other):
        if self.n != other.n:
            raise ValueError(
                    "Cannot add polynomials of different finite fields")

        if self.degree > other.degree:
            sum_coeffs = self.coeffs.copy()
            poly_to_add = other
        else:
            sum_coeffs = other.coeffs.copy()
            poly_to_add = self

        for i in range(len(self.coeffs)):
            sum_coeffs[i] = (sum_coeffs[i] + poly_to_add.coeff(i)) % self.n
        return Polynomial(sum_coeffs, self.n)

    def scale(self, a):
        scaled_coeffs = self.coeffs.copy()
        for i in range(len(self.coeffs)):
            scaled_coeffs[i] = a * scaled_coeffs[i] % self.n
        return Polynomial(scaled_coeffs, self.n)


    def mul(self, other):
        if self.n != other.n:
            raise ValueError(
                    "Cannot multiply polynomials of different finite fields")

        prod_coeffs = [0] * (len(self.coeffs) * len(other.coeffs))

        for i in range(len(self.coeffs)):
            for j in range(len(other.coeffs)):
                prod_coeffs[i + j] += (self.coeff(i) * other.coeff(j))\
                                        % self.n
                prod_coeffs[i + j] %= self.n
        return Polynomial(prod_coeffs, self.n)

    # degree determined by number of points
    def lagrange_construct(points, n):
        result = Polynomial([0], n)
        for i in range(len(points)):
            p = points[i]
            if p.y == 0:
                # don't need to compute this delta
                continue
            numerator = Polynomial([1], n) # start with 1
            for pp in points:
                if p.x == pp.x:
                    continue # skip current point
                # I'm using negative coefficients here, but I think that's ok
                numerator = numerator.mul(Polynomial([-pp.x, 1], n))
            denominator = numerator.of(p.x)
            delta = numerator.scale(sp.mod_inverse(denominator, n))
            result = result.add(delta.scale(p.y))
        return result

    @property
    def degree(self):
        return len(self.coeffs) - 1

    def __repr__(self):
        return "Polynomial({0}, {1})".format(self.coeffs, self.n)

    def _term_str(degree, coeff):
        if degree == 0:
            return str(coeff)
        if coeff == 0:
            return ""
        if degree == 1:
            string = "x + "
        else:
            string = "x^{1} + "
        if coeff != 1:
            string = "{0}" + string
        return string.format(coeff, degree)

    def __str__(self):
        string = "Polynomial: "
        for i in range(len(self.coeffs) - 1, -1, -1):
            string += Polynomial._term_str(i, self.coeffs[i])
        return string + " (mod {0})".format(self.n)
