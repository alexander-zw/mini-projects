"""
Functions that rename files with arbitrary names into counters. Can be used for any files.
Also contains functions that specifically convert between image formats. Has some dependencies.
"""

import pyheif
import PIL
from PIL import Image
import whatimage
import io
import os
import re

# Renames files to batch_name + counter (starting with 0), padded with zeros to get num_digits.
def rename_to_counter(filepath: str, batch_name: str, name_match=".*", start_count=0, num_digits=2):
    for name in os.listdir(filepath):
        if re.fullmatch(name_match, name):
            _, extension = os.path.splitext(name)
            new_name = batch_name + str(start_count).zfill(num_digits) + extension
            os.rename(os.path.join(filepath, name), os.path.join(filepath, new_name))
            print(name, "renamed into", new_name)
            start_count += 1
        else:
            print(name, "skipped")

def convert_all_heic_to_png(path_orig: str, path_dest: str):
    for name in os.listdir(path_orig):
        convert_heic_to_png(os.path.join(path_orig, name), path_dest)

# Preserves the names, changes path to new_path.
def convert_heic_to_png(filename: str, new_path: str):
    if os.path.splitext(filename)[1].lower() == ".heic":
        with open(filename, 'rb') as file:
                i = pyheif.read_heif(file)
                # Convert to other file format.
                pi = Image.frombytes(mode=i.mode, size=i.size, data=i.data)

                _, name = os.path.split(filename)
                new_filename = os.path.join(new_path, os.path.splitext(name)[0] + ".png")
                pi.save(new_filename, format="png")
                print(filename, "converted into", new_filename)
    else:
        print(filename, "is not right format, skipped")

# This code renames heic images_heic in the `images` folder into `phone_calib_xx.heic`
# then copies them then into `images/phone_calib_xx.png`.
if __name__ == "__main__":
    rename_to_counter("pose_heic", "phone_pose_", name_match=".+\.HEIC", start_count=7)
    convert_all_heic_to_png("pose_heic", "pose")
