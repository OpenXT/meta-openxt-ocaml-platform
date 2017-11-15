SUMMARY = "Camomile is a Unicode library for ocaml."
DESCRIPTION = "Camomile provides Unicode character type, UTF-8, UTF-16, \
UTF-32 strings, conversion to/from about 200 encodings, collation and \
locale-sensitive case mappings, and more."
HOMEPAGE = "https://github.com/yoriyuki/Camomile"
SECTION = "ocaml/devel"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=d8045f3b8f929c1cb29a1e3fd737b499"

DEPENDS = " \
    ocaml-native \
    camlp4-native \
"

SRC_URI = " \
    http://github.com/yoriyuki/Camomile/releases/download/rel-${PV}/camomile-${PV}.tar.bz2 \
    file://ocaml-camomile-destdir.patch \
"
SRC_URI[md5sum] = "1e25b6cd4efd26ab38a667db18d83f02"
SRC_URI[sha256sum] = "85806b051cf059b93676a10a3f66051f7f322cad6e3248172c3e5275f79d7100"

S = "${WORKDIR}/camomile-${PV}"

inherit ocaml findlib

FILES_${PN} = " \
    ${ocamllibdir}/camomile/*${SOLIBS} \
    ${datadir}/camomile/charmaps/* \
    ${datadir}/camomile/database/* \
    ${datadir}/camomile/locales/* \
    ${datadir}/camomile/mappings/* \
"
FILES_${PN}-dev = " \
    ${ocamllibdir}/camomile/*${SOLIBSDEV} \
    ${ocamllibdir}/camomile/*.cm* \
    ${ocamllibdir}/camomile/META \
"
FILES_${PN}-staticdev = " \
    ${ocamllibdir}/camomile \
"
FILES_${PN}-dbg = " \
    ${ocamllibdir}/camomile/.debug/* \
"

do_configure() {
    ./configure \
        --prefix=${prefix} \
        --bindir=${bindir} \
        --libdir=${libdir} \
        --datadir=${datadir} \
        --host=${TARGET_SYS}
}

# Apparently the .cmi files are not available when they need to be.
#  Error: Unbound module <X>
# This does not happen using camlp4 byte-code instead of native compiled.
PARALLEL_MAKE = ""
do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake DESTDIR="${D}" install
}
