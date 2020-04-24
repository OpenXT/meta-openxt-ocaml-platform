require findlib-${PV}.inc

PROVIDES = " \
    virtual/${TARGET_PREFIX}ocamlfind-meta \
"

PN = "findlib-cross-${TARGET_ARCH}"

do_install_append() {
    rm -rf ${D}${bindir}
    rm -rf ${D}${mandir}
    rm -rf ${D}${sysconfdir}
}

# Ignore how TARGET_ARCH is computed.
TARGET_ARCH[vardepvalue] = "${TARGET_ARCH}"

inherit cross
