import re
import numpy as np
# generate latex stuff

def table(rows):
    print("\\begin{tabular}{ %s}" % ("c " * len(rows[0])))
    for row in rows:
        row_str = ""
        for item in row:
            row_str += str(item) + " & "
        print(row_str[:-2] + "\\\\")
    print("\\end{tabular}")

def extract_values_from_svm_str(data_str):
    p = re.compile("\d\S* ")
    Cs = [s[:-1] for s in p.findall(data_str)]

    p = re.compile("\d\S+\n")
    accuracies = [s[:-1] for s in p.findall(data_str)]

    Cs = ["$C$"] + Cs
    accuracies = ["Accuracy"] + accuracies
    table(list(zip(Cs, accuracies)))

if __name__ == "__main__":
    data_str = """
model with C value 8 has accuracy 0.809477756286267
model with C value 9 has accuracy 0.8083172147001934
model with C value 10 has accuracy 0.811411992263056
model with C value 12 has accuracy 0.811798839458414
model with C value 13 has accuracy 0.8119922630560928
model with C value 14 has accuracy 0.8139264990328821
model with C value 15 has accuracy 0.8102514506769826
model with C value 16 has accuracy 0.8077369439071566
model with C value 17 has accuracy 0.8061895551257253
model with C value 18 has accuracy 0.8135396518375242
model with C value 20 has accuracy 0.8125725338491296
"""
    extract_values_from_svm_str(data_str)
