SUMMARY = "Simple helloworld! in CAML."
DESCRIPTION = "Simple helloworld! in CAML to test the OCaml basic toolchain."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://helloworld.ml;md5=4ef7127acc7f38322c009092a0050f59"
SECTION = "devel/ocaml"

SRC_URI = " \
    file://helloworld.ml \
"

S = "${WORKDIR}"

inherit ocaml

do_configure[noexec] = "1"

do_compile() {
    ocamlc -o helloworld helloworld.ml
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/helloworld ${D}${bindir}/helloworld
}
