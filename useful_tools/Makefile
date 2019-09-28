CC?=gcc
CFLAGS?=-Wall -g
BINARIES=every_other

all: default
default: $(BINARIES)

%: %.c
	gcc $(CFLAGS) -o $@ $^

clean:
	rm -f $(BINARIES)