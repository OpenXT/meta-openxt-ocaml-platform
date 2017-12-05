SUMMARY = "OCamlbuild generic build tool."
DESCRIPTION = "OCamlbuild is a generic build tool, that has built-in rules for \
building OCaml library and programs."
HOMEPAGE = "https://github.com/ocaml/ocamlbuild"
SECTION = "devel/ocaml"
LICENSE = "LGPLv2.1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5123b1988300c0d24c79e04f09d86dc0"

SRC_URI = " \
    https://github.com/ocaml/ocamlbuild/archive/${PV}.tar.gz \
    file://shebang-length-overflow.patch \
"
SRC_URI[md5sum] = "000d2ebad1333f9afcccdcd68c19f14d"
SRC_URI[sha256sum] = "2603be3709634b6191dd00627213cff56f15200f2d0a24e0af58a18a0580b71e"

S = "${WORKDIR}/ocamlbuild-${PV}"

inherit native ocaml

do_configure() {
    oe_runmake configure \
        OCAMLBUILD_PREFIX=${D}${prefix} \
        OCAMLBUILD_BINDIR=${D}${bindir} \
        OCAMLBUILD_LIBDIR=${D}${libdir}/ocaml \
        OCAMLBUILD_MANDIR=${D}${datadir}/man \
        OCAML_NATIVE=false \
        OCAML_NATIVE_TOOLS=false
}

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake install CHECK_IF_PREINSTALLED=false
}
