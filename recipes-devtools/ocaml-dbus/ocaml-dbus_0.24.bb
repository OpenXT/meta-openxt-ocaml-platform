SUMMARY = "DBus ocaml bindings."
DESCRIPTION = "D-Bus is a projects that permis program to communicate with \
each other, using a simple IPC protocol. the DBus ocaml bindings permits \
using all DBus features from ocaml directly, in a safe fashion."
HOMEPAGE = "http://projects.snarc.org/ocaml-dbus"
SECTION = "ocaml/devel"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f89276652d4738435c98d27fee7c6c7f"

DEPENDS += " \
    dbus \
"

SRC_URI = " \
    http://projects.snarc.org/ocaml-dbus/download/ocaml_dbus-${PV}.tar.bz2 \
    file://fix-invalid-characters-in-byte-access.patch \
    file://fix-incorrect-dispatch-statuses.patch \
    file://fix-error-name-lookup.patch \
    file://fix-memleak.patch \
    file://fix-multithread.patch \
"
SRC_URI[md5sum] = "b769af9141a5c073056ed46ef76ba5be"
SRC_URI[sha256sum] = "7c793987668e4236c63857469d2abe4a460e0b0954aa7d3262c6d9bb3c24bfdd"

S = "${WORKDIR}/ocaml_dbus-${PV}"

inherit ocaml findlib pkgconfig

do_compile() {
# TODO: There has to be a better way than this...
# ocamlmklib should be able to figure out where the sysroot staging, apparently
# OCAMLLIB does not cut it, neither does OCAML_TOPLEVEL_PATH, and get the
# LDFLAGS.
    oe_runmake OCAMLMKLIB="ocamlmklib -ldopt '--sysroot=${STAGING_DIR_TARGET} ${LDFLAGS}'"
}

do_install() {
    oe_runmake OCAMLDESTDIR="$(ocamlfind printconf destdir)" install
}
