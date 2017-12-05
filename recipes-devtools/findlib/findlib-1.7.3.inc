require findlib-common.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=a30ace4f9508a47d2c25c45c48af6492"

SRC_URI = " \
    http://download.camlcity.org/download/findlib-${PV}.tar.gz \
"
SRC_URI[md5sum] = "7d57451218359f7b7dfc969e3684a6da"
SRC_URI[sha256sum] = "d196608fa23c36c2aace27d5ef124a815132a5fcea668d41fa7d6c1ca246bd8b"

# Sadness:
# | File "topfind.ml", line 223, characters 6-37:
# | Error: Unbound module Fl_package_base
# | make[1]: *** [Makefile:168: topfind.cmo] Error 2
PARALLEL_MAKE = ""
