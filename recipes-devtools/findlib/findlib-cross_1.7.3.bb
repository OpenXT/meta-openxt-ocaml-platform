require findlib-${PV}.inc

PROVIDES = " \
    virtual/${TARGET_PREFIX}ocamlfind \
"

PN = "findlib-cross-${TARGET_ARCH}"

# Ignore how TARGET_ARCH is computed.
TARGET_ARCH[vardepvalue] = "${TARGET_ARCH}"

inherit cross
