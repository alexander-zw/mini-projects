import os


filepath = "/Users/AlexanderWu/Downloads/"

letters = ["A", "B", "C", "D", "E", "F"]

letter_to_num = {
    "A": 0, "B": 1, "C": 2, "D": 3, "E": 4, "F": 5
}


# e.g. 06.jpg -> "06 A2.jpg"
def int_to_counter_and_letter_num():
    for filename in os.listdir(filepath):
        try:
            file_num = int(filename[-6:-4])
        except ValueError:
            continue

        letter = letters[file_num % len(letters)]
        new_name = str(file_num).zfill(2) + " " + letter +\
                   str(file_num // len(letters) + 1) + ".jpg"
        os.rename(filepath + filename, filepath + new_name)
        print(filename, "renamed into", new_name)


# e.g. "this is garbage A2.jpg" -> 06.jpg
# HAS NOT BEEN TESTED
def correct_letter_num_to_int():
    for filename in os.listdir(filepath):
        try:
            letter = filename[-6:-5]
            num = int(filename[-5:-4]) - 1

            file_num = letter_to_num[letter] + len(letter_to_num) * num
            new_name = str(file_num).zfill(2) + ".jpg"
            os.rename(filepath + filename, filepath + new_name)
            print(filename, "renamed into", new_name)
        except (ValueError, KeyError):
            print("Skipped:", filename)


# e.g. "this is garbage A3.jpg" -> 02.jpg
def letter_num_to_int():
    for filename in os.listdir(filepath):
        try:
            letter = filename[-6:-5]
            num = int(filename[-5:-4]) - 1

            file_num = letter_to_num[letter] * len(letter_to_num) + num
            new_name = str(file_num).zfill(2) + ".jpg"
            os.rename(filepath + filename, filepath + new_name)
            print(filename, "renamed into", new_name)
        except (ValueError, KeyError):
            print("Skipped:", filename)


# e.g. 1.jpg -> 02.jpg
def change_num():
    for filename in os.listdir(filepath):
        try:
            num = int(filename[-6:-4])
        except ValueError:
            print("Skipped:", filename)
            continue

        new_name = str(num + 1).zfill(2) + ".jpg"
        os.rename(filepath + filename, filepath + new_name)
        print(filename, "renamed into", new_name)


# e.g. "06 06.jpg" -> 06.jpg
def remove_part():
    for filename in os.listdir(filepath):
        if filename != ".DS_Store":
            new_name = filename[:2] + filename[5:]
            os.rename(filepath + filename, filepath + new_name)
            print(filename, "renamed into", new_name)


# e.g. 06g -> 06.jpg
def add_part():
    for filename in os.listdir(filepath):
        if filename != ".DS_Store":
            new_name = filename[:-1] + ".jpg"
            os.rename(filepath + filename, filepath + new_name)
            print(filename, "renamed into", new_name)


# e.g. "30 A2.jpg" -> "06 A2.jpg"
# def change_counter():
#     for filename in os.listdir(filepath):
#         try:
#             count = int(filename[:2])
#             new_name = str(count + 24).zfill(2) + filename[2:]
#             os.rename(filepath + filename, filepath + new_name)
#             print(filename, "renamed into", new_name)
#         except ValueError:
#             print("Skipped:", filename)
#             continue


# e.g. "7 A2.jpg" -> "06.jpg"
def change_counter():
    for filename in os.listdir(filepath):
        try:
            count = int(filename[:2].strip())
            new_name = str(count - 1).zfill(2) + ".jpg"
            os.rename(filepath + filename, filepath + new_name)
            print(filename, "renamed into", new_name)
        except ValueError:
            print("Skipped:", filename)
            continue


change_counter()
