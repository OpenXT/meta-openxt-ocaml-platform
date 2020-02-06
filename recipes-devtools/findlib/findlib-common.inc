SUMMARY = "OCaml library manager."
DESCRIPTION = "Findlib is a library manager for Objective Caml. It provides a \
convention how to store libraries, and a file format (\x22META\x22) to \
describe the properties of libraries. There is also a tool (ocamlfind) for \
interpreting the META files, so that it is very easy to use libraries in \
programs and scripts."
HOMEPAGE = "http://projects.camlcity.org/projects/findlib.html"
SECTION = "ocaml/devel"
LICENSE = "MIT"

S = "${WORKDIR}/findlib-${PV}"

inherit ocaml

do_configure() {
    ./configure \
        -bindir ${bindir} \
        -mandir ${datadir}/man \
        -sitelib ${ocamllibdir}/site-lib \
        -config ${sysconfdir}/findlib.conf
}

do_compile() {
    # findlib.cma, findlib_mt.cma (the thread-safe version), and ocamlfind.
    oe_runmake all
    # findlib.cmxa, findlib_mt.cmxa, and ocamlfind_opt.
    oe_runmake opt
}

EXTRA_OEMAKE_append += "prefix='${D}'"
do_install() {
    oe_runmake install
}
