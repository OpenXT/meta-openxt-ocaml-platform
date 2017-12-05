require ocaml-4.04.inc

DEPENDS += " \
    virtual/${TARGET_PREFIX}binutils \
    virtual/${TARGET_PREFIX}gcc \
    virtual/${TARGET_PREFIX}libc-for-gcc \
    libgcc \
"

PROVIDES = " \
    virtual/${TARGET_PREFIX}ocamlc \
    virtual/${TARGET_PREFIX}ocamlopt \
"

PN = "ocaml-cross-${TARGET_ARCH}"

# No need to get a headache yet:
# OpenXT builds on x86(_64?) and targets x86, hopefuly x86_64 soon (this is
# meant to help)
# Restrict support from i.86|x86_64.*-linux -> i.86[|x86_64]
COMPATIBLE_HOST = "(i.86|x86_64).*-linux"
COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE_x86 = "(.*)"
#COMPATIBLE_MACHINE_x86-64 = "(.*)"

# Speed things up since we assume host is of the same architecture.
# Current OpenXT target are linux 32 bit.
do_configure_x86() {
    ./configure -no-curses \
                -no-graph \
                -prefix ${prefix} \
                -bindir ${bindir} \
                -libdir ${libdir}/ocaml \
                -mandir ${datadir}/man \
                -cc "${TARGET_PREFIX}gcc -m32 -fPIC --sysroot=${STAGING_DIR_TARGET}" \
                -as "${TARGET_PREFIX}as --32" \
                -aspp "${TARGET_PREFIX}gcc -m32 -c -fPIC" \
                -host ${TARGET_SYS} \
                -partialld "ld -r -melf_i386" \
                ${EXTRA_CONF}
}

# Ignore how TARGET_ARCH is computed.
TARGET_ARCH[vardepvalue] = "${TARGET_ARCH}"

inherit cross
