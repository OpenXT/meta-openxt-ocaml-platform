# OCAMLLIB
# The directory containing the OCaml standard library. (If OCAMLLIB is not set,
# CAMLLIB will be used instead.) Used to locate the ld.conf configuration file
# for dynamic loading
OCAMLLIB_class-native = "${STAGING_LIBDIR_NATIVE}/ocaml"
OCAMLLIB_class-cross = "${STAGING_LIBDIR_NATIVE}/ocaml"
OCAMLLIB = "${STAGING_LIBDIR_NATIVE}/${TARGET_SYS}/ocaml"
export OCAMLLIB

# OCAML_TOPLEVEL_PATH
# Path to the top-level (path to libraries & such).
OCAML_TOPLEVEL_PATH_class-native = "${STAGING_LIBDIR_NATIVE}"
OCAML_TOPLEVEL_PATH_class-cross = "${STAGING_LIBDIR_NATIVE}"
OCAML_TOPLEVEL_PATH = "${STAGING_LIBDIR_NATIVE}/${TARGET_SYS}"
export OCAML_TOPLEVEL_PATH

DEPENDS_append_class-native = " \
    ocaml-native \
"
DEPENDS_append_class-cross = " \
    ocaml-native \
"
DEPENDS_append_class-target = " \
    virtual/${TARGET_PREFIX}ocamlc \
    virtual/${TARGET_PREFIX}ocamlopt \
"

ocamllibdir = "${libdir}/ocaml"
export ocamllibdir

OPN ?= "${@d.getVar('BPN').replace('ocaml-','')}"
