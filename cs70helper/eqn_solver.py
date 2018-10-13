# Uses Gaussian elimination to solve linear equations
# Author: Alexander Zhao Wu

from fields import *

class Solver:

    def row_swap(A, row1, row2):
        for c in range(len(A[row1])):
            temp = A[row1][c]
            A[row1][c] = A[row2][c]
            A[row2][c] = temp

    def row_mul(A, row, scalar):
        for c in range(len(A[row])):
            A[row][c] *= scalar

    def row_add_scaled(A, row_use, row_replace, scalar):
        for c in range(len(A[row_use])):
            A[row_replace][c] += A[row_use][c] * scalar

    # A is an augmented matrix over field
    # uses Gaussian elimination, only solves invertible matrices
    # destructive: A is changed to reduced row echelon form
    # returns a vector of solutions (assuning A has one more column than row)
    def solve(A):
        field = A[0][0] # used for accessing zero and id
        for r in range(len(A)):
            for next_row in range(r + 1, len(A)):
                if A[r][r] == field.zero:
                    Solver.row_swap(A, r, next_row)
            if A[r][r] == field.zero: # all zeros in the column
                raise ValueError("Singular matrix, cannot solve")
            if A[r][r] != field.id:
                Solver.row_mul(A, r, A[r][r].inv())
            for lower_row in range(r + 1, len(A)):
                Solver.row_add_scaled(A, r, lower_row, -A[lower_row][r])

        for r in range(len(A) - 1, 0, -1): # eliminating upper triangle
            for upper_row in range(r - 1, -1, -1):
                Solver.row_add_scaled(A, r, upper_row, -A[upper_row][r])

        solution = []
        for r in range(len(A)):
            solution.append(A[r][len(A)])
        return solution
