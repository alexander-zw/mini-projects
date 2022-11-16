""" Run with -h to see help text. """

import argparse
from os import PathLike
import os.path as path

from PIL import Image


def join_paths(path1: PathLike, path2: PathLike):
    """Returns the normalized concatenation of the paths."""
    return path.normpath(path.join(path1, path2))


def gen_frame(im):
    """Creates a GIF frame from an image, preserving transparency."""
    try:
        # I have no idea why, but for some images you need to do this, and for others you don't.
        alpha = im.getchannel("A")
    except:
        return im

    # Convert the image into P mode but only use 255 colors in the palette out of 256
    im = im.convert("RGB").convert("P", palette=Image.ADAPTIVE, colors=255)

    # Set all pixel values below 128 to 255, and the rest to 0
    mask = Image.eval(alpha, lambda a: 255 if a <= 128 else 0)

    # Paste the color of index 255 and use alpha as a mask
    im.paste(255, mask)

    # The transparency index is 255
    im.info["transparency"] = 255

    return im


def get_explode_frame_filename(index: int):
    return join_paths(path.dirname(__file__), f"data/explode_frames/{index:02}.png")


def get_explode_frames():
    return [(Image.open(get_explode_frame_filename(i)), 100) for i in range(22)]


def crop_square_and_resample_25x25(im):
    width, height = im.size
    if width > height:
        left = (width - height) / 2
        right = (width + height) / 2
        square = im.crop((left, 0, right, height))
    else:
        top = (height - width) / 2
        bottom = (height + width) / 2
        square = im.crop((0, top, width, bottom))
    square.thumbnail(IMAGE_SIZE)
    return square


def save_gif(path: str, frames: list[tuple[any, int]]):
    """Saves the frames with individual delays as a GIF to the path given, infinite loop."""
    images, delays = zip(*frames)
    images[0].save(
        path,
        save_all=True,
        append_images=images[1:],
        loop=0,
        duration=delays,
        disposal=2,
    )


parser = argparse.ArgumentParser(
    prog="GIF Explodifier",
    description="""
Script that creates an explodified GIF. Takes in a static image, and creates a GIF where the input
image flashes and explodes.

The explodified GIF will have transparent background for the explosion and preserve the transparent
background of the input.""",
    epilog="""
I have no idea why, but for some images you'll get the error

"TypeError: color must be int or tuple"

The fix I found was to open the image in Apple Preview, then run cmd-A, cmd-K, cmd-S (crop all and
save). Then the error will go away. I think it has something to do with the color mode the image is
stored in?

The explode source data is located in data/explode_frames/. If you download this script you need to
copy that folder also for it to work.

This script was tested with pillow-9.3.0. It may not work as well with other versions.
""",
)
parser.add_argument(
    "input_image",
    type=str,
    help="The input image to explodify; a transparent background PNG is preferred",
)
parser.add_argument(
    "output_gif",
    type=str,
    help="The path to output the explodified GIF",
)

IMAGE_SIZE = (25, 25)
# fmt: off
GIF_FLASH_DELAYS = [
    1100, 400, 600, 300, 400, 250, 250, 180, 160, 150, 130, 100, 90, 90, 80, 70, 60, 50, 50, 50, 50,
    40, 40, 30, 30, 30, 30, 30, 30, 30, 30,
]
# fmt: on

if __name__ == "__main__":
    args = parser.parse_args()
    # input_frame = gen_frame(Image.open(args.input_image))
    input_frame = crop_square_and_resample_25x25(Image.open(args.input_image))
    input_frame = gen_frame(input_frame)
    explode_frames = get_explode_frames()
    # For some reason creating a blank image with Image.new doesn't work, so using a frame from the explode GIF.
    blank_frame = explode_frames[-1][0]

    frames = []
    for i, delay in enumerate(GIF_FLASH_DELAYS):
        frames.append((input_frame if i % 2 == 0 else blank_frame, delay))
    frames.extend(explode_frames)

    save_gif(args.output_gif, frames)
