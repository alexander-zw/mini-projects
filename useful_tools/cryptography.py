"""
A user-friendly tool for cryptography-related uses.

Signature: Using PKCS#1 v1.5 signature scheme (RSASP1), generates key pairs
and allows users to sign ASCII messages with given key and verify messages
with given key and signature.
"""
from Crypto.PublicKey import RSA
from Crypto.Signature.pkcs1_15 import PKCS115_SigScheme
from Crypto.Hash import SHA256
import binascii

pri_key_file_name = "private_key.pem"
pub_key_file_name = "public_key.pem"

def rsa_key_gen():
    """
    Returns an RSA key pair for signing in addition to printout.
    Saves them to the default file.
    """
    key_pair = RSA.generate(bits=1024)
    print(f"Your public key is\n(n={hex(key_pair.n)}, e={hex(key_pair.e)})\n"
           "Publicly establish this key as belonging to you.\n")
    print(f"Your private key is\n(n={hex(key_pair.n)}, d={hex(key_pair.d)})\n"
           "Do not reveal this key (the d value) to anyone.\n")

    with open(pri_key_file_name, 'wb+') as f:
        f.write(key_pair.export_key('PEM'))
        with open(pub_key_file_name, 'wb+') as f:
            f.write(key_pair.publickey().export_key('PEM'))
            print(f"Your public key has been saved to the file {pub_key_file_name}, "
                  f"and you private key to {pri_key_file_name}. Do not share the private "
                   "key file with anyone.\n")
    return key_pair

def rsa_sign(message, key_pair=None):
    """
    Returns the RSA signature in addition to printout.
    If the key is not given, imports private key from default file.
    """
    if not key_pair:
        with open(pri_key_file_name, 'r') as f:
            key_pair = RSA.import_key(f.read())
    # Sign the message using the PKCS#1 v1.5 signature scheme (RSASP1).
    msg_hash = SHA256.new(message)
    signer = PKCS115_SigScheme(key_pair)
    signature = signer.sign(msg_hash)
    print(f"Your signature is\n{binascii.hexlify(signature).decode('ASCII')}\n"
           "Share this signature along with your message to prove its authenticity.\n")
    return signature

def rsa_verify(message, signature, public_key=None):
    """
    Returns if the RSA signature is valid in addition to printout.
    If the public key is not given, imports public key from default file.
    """
    if not public_key:
        with open(pub_key_file_name, 'r') as f:
            public_key = RSA.import_key(f.read())
    # Verify PKCS#1 v1.5 signature (RSAVP1).
    msg_hash = SHA256.new(message)
    verifier = PKCS115_SigScheme(public_key)
    try:
        verifier.verify(msg_hash, signature)
        print("Signature is VALID ✅\n")
        return True
    except:
        print("Signature is INVALID ❌\n")
        return False

def read_message_from_file(filename):
    """ Reads the file and returns the messaged encoded from ASCII to bytes. """
    with open(filename,'r') as f:
        return bytes(f.read(), "ASCII")
    print(f"Failed to read file {filename}.")

if __name__ == "__main__":
    """ The following demo uses existing keys and signature to verify a message. """
    message = b"I'm glad I'm me, I'm glad I'm me, there's no one else I want to be."
    signature = binascii.unhexlify(
        b"84b7b8cd8942648a0fa200a75f8869900ce6899f766885ded7e17bfd731debb6ef7b0696974028505d447684921638c92c8904c5025487727fdc90b29817c286856323747c441f57457a87c57617ac7c970c77f5d7ae11f74aa49a7ddccfc993b1a1a81a5772f7c69679b9ec0ba5d440d8638701e194f14e0cffb6ec910eda49"
    )

    # key_pair = rsa_key_gen()
    # public_key = key_pair.publickey()
    key_pair, public_key = None, None # If None, reads from file.
    # signature = rsa_sign(message, key_pair)
    assert rsa_verify(message, signature, public_key)
    assert not rsa_verify(b"I hate you.", signature, public_key)
