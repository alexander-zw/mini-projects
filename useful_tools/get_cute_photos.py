# Gets image sources from html
import re

# Only works on formatted html so that img tag is first
def extract_img_src_from_html():
    with open("pexels_newlines.html") as read_file:
        lines = read_file.readlines()
    write_file = open("pexels_urls.txt", "w+")

    tag_matcher = re.compile("\s*<img.+>")
    url_matcher = re.compile('srcset="[^\?]+\.jpeg')

    for line in lines:
        match = tag_matcher.match(line)
        if match:
            match = url_matcher.search(line)
            if match:
                url = match.group()[8:]
                write_file.write(url + "\n")

def add_newlines():
    with open("pexels_cute_animals.html") as read_file:
        lines = read_file.readlines()
    write_file = open("pexels_newlines.html", "w+")

    matcher = re.compile('<img')
    for line in lines:
        new_line = line.replace("<img", "\n<img")
        write_file.write(new_line)

add_newlines()
extract_img_src_from_html()
