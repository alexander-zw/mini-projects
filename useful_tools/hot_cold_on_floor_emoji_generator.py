"""
Generates PNGs that can be uploaded to Slack as custom emojis, which signify that a floor is hot or cold.
See hot_cold_emojis/output/ for example output.

Requires two source images for fire and snowman and a font file. Requires Pillow to be installed.
"""

from PIL import Image, ImageDraw, ImageFont

FLOOR_NUMBER = 11
FONT_PATH = "hot_cold_emojis/input/Arial-Bold.ttf"
FONT_SIZE = 41

def create_emoji(base_image_path, number, font_path, fill_color, outline_color, output_path, font_size):
    # Load the base emoji image
    base_image = Image.open(base_image_path).convert("RGBA")
    width, height = base_image.size
    
    draw = ImageDraw.Draw(base_image)
    font = ImageFont.truetype(font_path, font_size)
    
    # Calculate text size and position
    text_width = draw.textlength(str(number), font=font)
    text_height = font_size
    margin = 3
    position = (margin, height - text_height - margin) 

    # Draw the text with an outline
    outline_thickness = 4
    for dx in range(-outline_thickness, outline_thickness+1):
        for dy in range(-outline_thickness, outline_thickness+1):
            if dx != 0 or dy != 0:
                draw.text((position[0] + dx, position[1] + dy), str(number), font=font, fill=outline_color)

    # Main text
    draw.text(position, str(number), font=font, fill=fill_color)
    
    base_image.save(output_path)


# Example usage
create_emoji("hot_cold_emojis/input/fire.png", FLOOR_NUMBER, FONT_PATH, "#F5C342", "#EA3323", f"hot_cold_emojis/output/its-hot-on-{FLOOR_NUMBER}th.png", FONT_SIZE)
create_emoji("hot_cold_emojis/input/snowman.png", FLOOR_NUMBER, FONT_PATH, "#5FCFFA", "#0000F5", f"hot_cold_emojis/output/its-cold-on-{FLOOR_NUMBER}th.png", FONT_SIZE)
print("Custom emojis created successfully.")
