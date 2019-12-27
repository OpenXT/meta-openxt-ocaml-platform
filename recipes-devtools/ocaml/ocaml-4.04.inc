require ocaml-common.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=4f72f33f302a53dc329f4d3819fe14f9"

SRC_URI = " \
    http://caml.inria.fr/pub/distrib/ocaml-4.04/ocaml-${PV}.tar.xz \
    file://oe-linux-configure.patch \
    file://shebang-use-env.patch \
"
SRC_URI[md5sum] = "a230f5f352318575870abb326db7355b"
SRC_URI[sha256sum] = "d158ed3e9446b300554baeaaa8cca2e9491420b505a9878940205074e2970f2e"

S = "${WORKDIR}/ocaml-${PV}"

do_configure() {
    ./configure -no-curses \
                -no-graph \
                -prefix ${prefix} \
                -libdir ${libdir}/ocaml \
                -mandir ${datadir}/man \
                -fPIC \
                ${EXTRA_CONF}
}
do_configure_append() {
    # Make sure to turn off GCC stack-protector.
    sed -i'' -re 's/NATIVECCCOMPOPTS=(.*)/NATIVECCCOMPOPTS=\1 -fno-stack-protector/' config/Makefile
    sed -i'' -re 's/BYTECCCOMPOPTS=(.*)/BYTECCCOMPOPTS=\1 -fno-stack-protector/' config/Makefile
}

do_compile() {
    # Build the native compiler and libraries as well (Xen needs ocamlopt...)
    oe_runmake world.opt
}

do_install() {
    oe_runmake DESTDIR="${D}" install
}
