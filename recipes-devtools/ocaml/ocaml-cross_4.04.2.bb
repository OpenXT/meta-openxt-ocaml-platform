require ocaml-4.04.inc

SRC_URI += " \
    file://0001-cross-Add-cross-compilation-rules.patch \
"

DEPENDS += " \
    virtual/${TARGET_PREFIX}binutils \
    virtual/${TARGET_PREFIX}gcc \
    virtual/libc \
    libgcc \
    ocaml-native \
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
COMPATIBLE_MACHINE_x86-64 = "(.*)"

# Speed things up since we assume host is of the same architecture.
do_configure_x86() {
    ./configure -no-curses \
                -no-graph \
                -no-shared-libs \
                -no-debugger \
                -no-ocamldoc \
                -prefix ${prefix} \
                -bindir ${bindir} \
                -libdir ${libdir}/ocaml \
                -mandir ${datadir}/man \
                -fPIC \
                -cc "${TARGET_PREFIX}gcc -m32 --sysroot=${STAGING_DIR_TARGET}" \
                -as "${TARGET_PREFIX}as --32" \
                -aspp "${TARGET_PREFIX}gcc -m32 -c" \
                -libs "-Wl,--sysroot=${STAGING_DIR_TARGET}" \
                -host ${TARGET_SYS} \
                -partialld "ld -r -melf_i386" \
                ${EXTRA_CONF}
}

do_configure_x86-64() {
    ./configure -no-curses \
                -no-graph \
                -no-shared-libs \
                -no-debugger \
                -no-ocamldoc \
                -prefix ${prefix} \
                -bindir ${bindir} \
                -libdir ${libdir}/ocaml \
                -mandir ${datadir}/man \
                -fPIC \
                -cc "${TARGET_PREFIX}gcc --sysroot=${STAGING_DIR_TARGET}" \
                -as "${TARGET_PREFIX}as" \
                -aspp "${TARGET_PREFIX}gcc -c" \
                -libs "-Wl,--sysroot=${STAGING_DIR_TARGET}" \
                -host ${TARGET_SYS} \
                -partialld "${TARGET_PREFIX}ld -r" \
                ${EXTRA_CONF}
}

do_compile() {
    oe_runmake OCAMLLIB="${STAGING_LIBDIR_NATIVE}/ocaml" cross-opt
}

do_install_append() {
    rm "${D}${bindir}/ocamlrun"
}

# Ignore how TARGET_ARCH is computed.
TARGET_ARCH[vardepvalue] = "${TARGET_ARCH}"

# The native compiler will be installed in the sysroot to serve as the cross
# compiler with the rest of the cross build environment. It is already stripped
# and debugging is not a concern here.
INSANE_SKIP_${PN} += "already-stripped"

inherit cross
