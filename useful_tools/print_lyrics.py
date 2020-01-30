import inflect

infl = inflect.engine()

for i in range(1, 100):
    num = infl.number_to_words(i)
    num_plus_one = infl.number_to_words(i + 1)
    print("{} little bugs in the code, {} bugs in the code".format(num.capitalize(), num))
    print("Fix one bug, compile again, {} little bugs in the code".format(num_plus_one))