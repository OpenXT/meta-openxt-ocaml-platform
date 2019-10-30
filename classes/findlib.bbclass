DEPENDS_append_class-native = " \
    findlib-native \
"
DEPENDS_append_class-target = " \
    findlib-native \
    virtual/${TARGET_PREFIX}ocamlfind-meta \
"

# location where ocamlfind install puts the packages.
sitelibdir = "${ocamllibdir}/site-lib"
export sitelibdir

FILES_${PN} = " \
    ${sitelibdir}/*/*${SOLIBSDEV} \
    ${bindir}/* \
    ${sbindir}/* \
"
FILES_${PN}-dev = " \
    ${sitelibdir}/*/*.cm* \
    ${sitelibdir}/*/*.mli \
    ${sitelibdir}/*/META \
"
FILES_${PN}-staticdev = " \
    ${sitelibdir}/*/*.a \
"
FILES_${PN}-dbg = " \
    ${sitelibdir}/*/.debug \
    ${bindir}/.debug \
    ${sbindir}/.debug \
    /usr/src/debug \
"

# See http://projects.camlcity.org/projects/dl/findlib-1.4/doc/ref-html/r775.html.
# OCAMLFIND_CONF: overrides the location of the configuration file
# findlib.conf. It must contain the absolute path name of this file.
OCAMLFIND_CONF = "${S}/local-findlib.conf"
export OCAMLFIND_CONF

# Define where to install new META files and where to find existing ones.
# This has to be coherent with findlib{,-native} recipe configuration,
# site-lib wise.
do_local_findlib_conf() {
    cat << EOF > ${OCAMLFIND_CONF}
destdir="${D}${sitelibdir}"
path="${OCAMLLIB}/site-lib:${STAGING_LIBDIR}/ocaml/site-lib"
ldconf="ignore"
ocamlc="ocamlc.opt"
ocamlopt="ocamlopt.opt"
ocamldep="ocamldep.opt"
ocamldoc="ocamldoc.opt"
EOF
}
addtask do_local_findlib_conf before do_configure after do_patch
do_local_findlib_conf[doc] = "Create a volatile local findlib.conf, according to the bitbake staging, for ocamlfind to use."
# See: bitbake.git: 67a7b8b0 build: don't use $B as the default cwd for functions
do_local_findlib_conf[dirs] = "${B}"
do_local_findlib_conf[deptask] = "do_populate_sysroot"

do_install_prepend() {
    install -d ${D}${sitelibdir}
}
