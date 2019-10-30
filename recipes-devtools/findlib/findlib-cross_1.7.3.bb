require findlib-${PV}.inc

PROVIDES = " \
    virtual/${TARGET_PREFIX}ocamlfind-meta \
"

PN = "findlib-cross-${TARGET_ARCH}"

do_install_append() {
    rm -r ${D}${bindir}
    rm -r ${D}${mandir}
}

# Ignore how TARGET_ARCH is computed.
TARGET_ARCH[vardepvalue] = "${TARGET_ARCH}"

inherit cross
