"""
Generates PNGs that can be uploaded to Slack as custom emojis, which signify that a floor is hot or cold.
Also allows adding a building number. See hot_cold_emojis/output/ for example output.

Requires two source images for fire and snowman and a font file. Requires Pillow to be installed.
"""

from PIL import Image, ImageDraw, ImageFont

BUILDING_NUMBER = 2
FLOOR_NUMBER = 1
FONT_PATH = "hot_cold_emojis/input/Arial-Bold.ttf"
FONT_SIZE = 35

def create_emoji(base_image_path, floor_number, font_path, fill_color, outline_color, output_path, font_size, building_number=None):
    """
    floor_number: the floor number to display on the emoji, displayed as orange outline yellow fill for hot, and blue outline and fill for cold.
    building_number: If provided, the building number will be displayed as black outline white fill left of the floor number.
    """
    # Load the base emoji image
    base_image = Image.open(base_image_path).convert("RGBA")
    width, height = base_image.size
    
    draw = ImageDraw.Draw(base_image)
    font = ImageFont.truetype(font_path, font_size)
    
    # Calculate text size and position
    text = str(floor_number)
    if building_number is not None:
        text = f"{building_number}-{text}"
    text_width = draw.textlength(text, font=font)
    text_height = font_size
    margin = 3
    position = (margin, height - text_height - margin)

    # Draw the text with an outline
    outline_thickness = 3
    
    if building_number is not None:
        building_text = f"{building_number}-"
        building_width = draw.textlength(building_text, font=font)
        
        # Draw building number outline (black)
        for dx in range(-outline_thickness, outline_thickness+1):
            for dy in range(-outline_thickness, outline_thickness+1):
                if dx != 0 or dy != 0:
                    draw.text((position[0] + dx, position[1] + dy), building_text, font=font, fill="#222222")
        
        # Draw building number (white)
        draw.text(position, building_text, font=font, fill="#DDFFDD") # Yeah that's white, why do you ask?
        
        # Adjust position for floor number
        position = (position[0] + building_width, position[1])
        text = str(floor_number)

    # Draw the floor number outline
    for dx in range(-outline_thickness, outline_thickness+1):
        for dy in range(-outline_thickness, outline_thickness+1):
            if dx != 0 or dy != 0:
                draw.text((position[0] + dx, position[1] + dy), text, font=font, fill=outline_color)

    # Main text
    draw.text(position, text, font=font, fill=fill_color)
    
    base_image.save(output_path)


if BUILDING_NUMBER is not None:
    create_emoji("hot_cold_emojis/input/fire.png", FLOOR_NUMBER, FONT_PATH, "#F5C342", "#EA3323", f"hot_cold_emojis/output/its-hot-on-mtv0{BUILDING_NUMBER}-{FLOOR_NUMBER}st.png", FONT_SIZE, BUILDING_NUMBER)
    create_emoji("hot_cold_emojis/input/snowman.png", FLOOR_NUMBER, FONT_PATH, "#5FCFFA", "#0000F5", f"hot_cold_emojis/output/its-cold-on-mtv0{BUILDING_NUMBER}-{FLOOR_NUMBER}st.png", FONT_SIZE, BUILDING_NUMBER)
else:
    create_emoji("hot_cold_emojis/input/fire.png", FLOOR_NUMBER, FONT_PATH, "#F5C342", "#EA3323", f"hot_cold_emojis/output/its-hot-on-{FLOOR_NUMBER}th.png", FONT_SIZE)
    create_emoji("hot_cold_emojis/input/snowman.png", FLOOR_NUMBER, FONT_PATH, "#5FCFFA", "#0000F5", f"hot_cold_emojis/output/its-cold-on-{FLOOR_NUMBER}th.png", FONT_SIZE)
print("Custom emojis created successfully.")
