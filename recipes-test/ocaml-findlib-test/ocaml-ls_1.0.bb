SUMMARY = "OCaml test for findlib."
DESCRIPTION = "Trivial ls in CAML to test the OCaml basic toolchain."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SECTION = "devel/ocaml"

SRC_URI = " \
    file://ocaml-ls.ml \
"

S = "${WORKDIR}"

inherit ocaml findlib

do_configure[noexec] = "1"

do_compile() {
    ocamlfind opt -linkpkg -package unix ocaml-ls.ml -o ocaml-ls
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/ocaml-ls ${D}${bindir}/ocaml-ls
}
