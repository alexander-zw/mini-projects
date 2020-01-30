string = "pintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/args-none -a args-none -- -q  -f run " \
         "args-none < /dev/null 2> tests/userprog/args-none.errors > tests/userprog/args-none.output\nperl -I../.. " \
         "../../tests/userprog/args-none.ck tests/userprog/args-none tests/userprog/args-none.result\npass " \
         "tests/userprog/args-none\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/args-single -a " \
         "args-single -- -q  -f run 'args-single onearg' < /dev/null 2> tests/userprog/args-single.errors > " \
         "tests/userprog/args-single.output\nperl -I../.. ../../tests/userprog/args-single.ck " \
         "tests/userprog/args-single tests/userprog/args-single.result\npass tests/userprog/args-single\npintos -v -k " \
         "-T 60 --qemu  --filesys-size=2 -p tests/userprog/args-multiple -a args-multiple -- -q  -f run " \
         "'args-multiple some arguments for you!' < /dev/null 2> tests/userprog/args-multiple.errors > " \
         "tests/userprog/args-multiple.output\nperl -I../.. ../../tests/userprog/args-multiple.ck " \
         "tests/userprog/args-multiple tests/userprog/args-multiple.result\npass tests/userprog/args-multiple\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/args-many -a args-many -- -q  -f run 'args-many a b " \
         "c d e f g h i j k l m n o p q r s t u v' < /dev/null 2> tests/userprog/args-many.errors > " \
         "tests/userprog/args-many.output\nperl -I../.. ../../tests/userprog/args-many.ck tests/userprog/args-many " \
         "tests/userprog/args-many.result\npass tests/userprog/args-many\npintos -v -k -T 60 --qemu  --filesys-size=2 " \
         "-p tests/userprog/args-dbl-space -a args-dbl-space -- -q  -f run 'args-dbl-space two  spaces!' < /dev/null " \
         "2> tests/userprog/args-dbl-space.errors > tests/userprog/args-dbl-space.output\nperl -I../.. " \
         "../../tests/userprog/args-dbl-space.ck tests/userprog/args-dbl-space " \
         "tests/userprog/args-dbl-space.result\npass tests/userprog/args-dbl-space\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/sc-bad-sp -a sc-bad-sp -- -q  -f run sc-bad-sp < /dev/null 2> " \
         "tests/userprog/sc-bad-sp.errors > tests/userprog/sc-bad-sp.output\nperl -I../.. " \
         "../../tests/userprog/sc-bad-sp.ck tests/userprog/sc-bad-sp tests/userprog/sc-bad-sp.result\npass " \
         "tests/userprog/sc-bad-sp\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/sc-bad-arg -a " \
         "sc-bad-arg -- -q  -f run sc-bad-arg < /dev/null 2> tests/userprog/sc-bad-arg.errors > " \
         "tests/userprog/sc-bad-arg.output\nperl -I../.. ../../tests/userprog/sc-bad-arg.ck tests/userprog/sc-bad-arg " \
         "tests/userprog/sc-bad-arg.result\npass tests/userprog/sc-bad-arg\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/sc-boundary -a sc-boundary -- -q  -f run sc-boundary < /dev/null 2> " \
         "tests/userprog/sc-boundary.errors > tests/userprog/sc-boundary.output\nperl -I../.. " \
         "../../tests/userprog/sc-boundary.ck tests/userprog/sc-boundary tests/userprog/sc-boundary.result\npass " \
         "tests/userprog/sc-boundary\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/sc-boundary-2 -a " \
         "sc-boundary-2 -- -q  -f run sc-boundary-2 < /dev/null 2> tests/userprog/sc-boundary-2.errors > " \
         "tests/userprog/sc-boundary-2.output\nperl -I../.. ../../tests/userprog/sc-boundary-2.ck " \
         "tests/userprog/sc-boundary-2 tests/userprog/sc-boundary-2.result\npass tests/userprog/sc-boundary-2\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/sc-boundary-3 -a sc-boundary-3 -- -q  -f run " \
         "sc-boundary-3 < /dev/null 2> tests/userprog/sc-boundary-3.errors > " \
         "tests/userprog/sc-boundary-3.output\nperl -I../.. ../../tests/userprog/sc-boundary-3.ck " \
         "tests/userprog/sc-boundary-3 tests/userprog/sc-boundary-3.result\npass tests/userprog/sc-boundary-3\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/halt -a halt -- -q  -f run halt < /dev/null 2> " \
         "tests/userprog/halt.errors > tests/userprog/halt.output\nperl -I../.. ../../tests/userprog/halt.ck " \
         "tests/userprog/halt tests/userprog/halt.result\npass tests/userprog/halt\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/exit -a exit -- -q  -f run exit < /dev/null 2> " \
         "tests/userprog/exit.errors > tests/userprog/exit.output\nperl -I../.. ../../tests/userprog/exit.ck " \
         "tests/userprog/exit tests/userprog/exit.result\npass tests/userprog/exit\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/create-normal -a create-normal -- -q  -f run create-normal < /dev/null " \
         "2> tests/userprog/create-normal.errors > tests/userprog/create-normal.output\nperl -I../.. " \
         "../../tests/userprog/create-normal.ck tests/userprog/create-normal " \
         "tests/userprog/create-normal.result\npass tests/userprog/create-normal\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/create-empty -a create-empty -- -q  -f run create-empty < /dev/null 2> " \
         "tests/userprog/create-empty.errors > tests/userprog/create-empty.output\nperl -I../.. " \
         "../../tests/userprog/create-empty.ck tests/userprog/create-empty tests/userprog/create-empty.result\npass " \
         "tests/userprog/create-empty\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/create-null -a " \
         "create-null -- -q  -f run create-null < /dev/null 2> tests/userprog/create-null.errors > " \
         "tests/userprog/create-null.output\nperl -I../.. ../../tests/userprog/create-null.ck " \
         "tests/userprog/create-null tests/userprog/create-null.result\npass tests/userprog/create-null\npintos -v -k " \
         "-T 60 --qemu  --filesys-size=2 -p tests/userprog/create-bad-ptr -a create-bad-ptr -- -q  -f run " \
         "create-bad-ptr < /dev/null 2> tests/userprog/create-bad-ptr.errors > " \
         "tests/userprog/create-bad-ptr.output\nperl -I../.. ../../tests/userprog/create-bad-ptr.ck " \
         "tests/userprog/create-bad-ptr tests/userprog/create-bad-ptr.result\npass " \
         "tests/userprog/create-bad-ptr\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/create-long -a " \
         "create-long -- -q  -f run create-long < /dev/null 2> tests/userprog/create-long.errors > " \
         "tests/userprog/create-long.output\nperl -I../.. ../../tests/userprog/create-long.ck " \
         "tests/userprog/create-long tests/userprog/create-long.result\npass tests/userprog/create-long\npintos -v -k " \
         "-T 60 --qemu  --filesys-size=2 -p tests/userprog/create-exists -a create-exists -- -q  -f run create-exists " \
         "< /dev/null 2> tests/userprog/create-exists.errors > tests/userprog/create-exists.output\nperl -I../.. " \
         "../../tests/userprog/create-exists.ck tests/userprog/create-exists " \
         "tests/userprog/create-exists.result\npass tests/userprog/create-exists\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/create-bound -a create-bound -- -q  -f run create-bound < /dev/null 2> " \
         "tests/userprog/create-bound.errors > tests/userprog/create-bound.output\nperl -I../.. " \
         "../../tests/userprog/create-bound.ck tests/userprog/create-bound tests/userprog/create-bound.result\npass " \
         "tests/userprog/create-bound\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/open-normal -a " \
         "open-normal -p ../../tests/userprog/sample.txt -a sample.txt -- -q  -f run open-normal < /dev/null 2> " \
         "tests/userprog/open-normal.errors > tests/userprog/open-normal.output\nperl -I../.. " \
         "../../tests/userprog/open-normal.ck tests/userprog/open-normal tests/userprog/open-normal.result\npass " \
         "tests/userprog/open-normal\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/open-missing -a " \
         "open-missing -- -q  -f run open-missing < /dev/null 2> tests/userprog/open-missing.errors > " \
         "tests/userprog/open-missing.output\nperl -I../.. ../../tests/userprog/open-missing.ck " \
         "tests/userprog/open-missing tests/userprog/open-missing.result\npass tests/userprog/open-missing\npintos -v " \
         "-k -T 60 --qemu  --filesys-size=2 -p tests/userprog/open-boundary -a open-boundary -p " \
         "../../tests/userprog/sample.txt -a sample.txt -- -q  -f run open-boundary < /dev/null 2> " \
         "tests/userprog/open-boundary.errors > tests/userprog/open-boundary.output\nperl -I../.. " \
         "../../tests/userprog/open-boundary.ck tests/userprog/open-boundary " \
         "tests/userprog/open-boundary.result\npass tests/userprog/open-boundary\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/open-empty -a open-empty -- -q  -f run open-empty < /dev/null 2> " \
         "tests/userprog/open-empty.errors > tests/userprog/open-empty.output\nperl -I../.. " \
         "../../tests/userprog/open-empty.ck tests/userprog/open-empty tests/userprog/open-empty.result\npass " \
         "tests/userprog/open-empty\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/open-null -a " \
         "open-null -- -q  -f run open-null < /dev/null 2> tests/userprog/open-null.errors > " \
         "tests/userprog/open-null.output\nperl -I../.. ../../tests/userprog/open-null.ck tests/userprog/open-null " \
         "tests/userprog/open-null.result\npass tests/userprog/open-null\npintos -v -k -T 60 --qemu  --filesys-size=2 " \
         "-p tests/userprog/open-bad-ptr -a open-bad-ptr -- -q  -f run open-bad-ptr < /dev/null 2> " \
         "tests/userprog/open-bad-ptr.errors > tests/userprog/open-bad-ptr.output\nperl -I../.. " \
         "../../tests/userprog/open-bad-ptr.ck tests/userprog/open-bad-ptr tests/userprog/open-bad-ptr.result\npass " \
         "tests/userprog/open-bad-ptr\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/open-twice -a " \
         "open-twice -p ../../tests/userprog/sample.txt -a sample.txt -- -q  -f run open-twice < /dev/null 2> " \
         "tests/userprog/open-twice.errors > tests/userprog/open-twice.output\nperl -I../.. " \
         "../../tests/userprog/open-twice.ck tests/userprog/open-twice tests/userprog/open-twice.result\npass " \
         "tests/userprog/open-twice\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/close-normal -a " \
         "close-normal -p ../../tests/userprog/sample.txt -a sample.txt -- -q  -f run close-normal < /dev/null 2> " \
         "tests/userprog/close-normal.errors > tests/userprog/close-normal.output\nperl -I../.. " \
         "../../tests/userprog/close-normal.ck tests/userprog/close-normal tests/userprog/close-normal.result\npass " \
         "tests/userprog/close-normal\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/close-twice -a " \
         "close-twice -p ../../tests/userprog/sample.txt -a sample.txt -- -q  -f run close-twice < /dev/null 2> " \
         "tests/userprog/close-twice.errors > tests/userprog/close-twice.output\nperl -I../.. " \
         "../../tests/userprog/close-twice.ck tests/userprog/close-twice tests/userprog/close-twice.result\npass " \
         "tests/userprog/close-twice\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/close-stdin -a " \
         "close-stdin -- -q  -f run close-stdin < /dev/null 2> tests/userprog/close-stdin.errors > " \
         "tests/userprog/close-stdin.output\nperl -I../.. ../../tests/userprog/close-stdin.ck " \
         "tests/userprog/close-stdin tests/userprog/close-stdin.result\npass tests/userprog/close-stdin\npintos -v -k " \
         "-T 60 --qemu  --filesys-size=2 -p tests/userprog/close-stdout -a close-stdout -- -q  -f run close-stdout < " \
         "/dev/null 2> tests/userprog/close-stdout.errors > tests/userprog/close-stdout.output\nperl -I../.. " \
         "../../tests/userprog/close-stdout.ck tests/userprog/close-stdout tests/userprog/close-stdout.result\npass " \
         "tests/userprog/close-stdout\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/close-bad-fd -a " \
         "close-bad-fd -- -q  -f run close-bad-fd < /dev/null 2> tests/userprog/close-bad-fd.errors > " \
         "tests/userprog/close-bad-fd.output\nperl -I../.. ../../tests/userprog/close-bad-fd.ck " \
         "tests/userprog/close-bad-fd tests/userprog/close-bad-fd.result\npass tests/userprog/close-bad-fd\npintos -v " \
         "-k -T 60 --qemu  --filesys-size=2 -p tests/userprog/read-normal -a read-normal -p " \
         "../../tests/userprog/sample.txt -a sample.txt -- -q  -f run read-normal < /dev/null 2> " \
         "tests/userprog/read-normal.errors > tests/userprog/read-normal.output\nperl -I../.. " \
         "../../tests/userprog/read-normal.ck tests/userprog/read-normal tests/userprog/read-normal.result\npass " \
         "tests/userprog/read-normal\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/read-bad-ptr -a " \
         "read-bad-ptr -p ../../tests/userprog/sample.txt -a sample.txt -- -q  -f run read-bad-ptr < /dev/null 2> " \
         "tests/userprog/read-bad-ptr.errors > tests/userprog/read-bad-ptr.output\nperl -I../.. " \
         "../../tests/userprog/read-bad-ptr.ck tests/userprog/read-bad-ptr tests/userprog/read-bad-ptr.result\npass " \
         "tests/userprog/read-bad-ptr\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/read-boundary -a " \
         "read-boundary -p ../../tests/userprog/sample.txt -a sample.txt -- -q  -f run read-boundary < /dev/null 2> " \
         "tests/userprog/read-boundary.errors > tests/userprog/read-boundary.output\nperl -I../.. " \
         "../../tests/userprog/read-boundary.ck tests/userprog/read-boundary " \
         "tests/userprog/read-boundary.result\npass tests/userprog/read-boundary\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/read-zero -a read-zero -p ../../tests/userprog/sample.txt -a sample.txt " \
         "-- -q  -f run read-zero < /dev/null 2> tests/userprog/read-zero.errors > " \
         "tests/userprog/read-zero.output\nperl -I../.. ../../tests/userprog/read-zero.ck tests/userprog/read-zero " \
         "tests/userprog/read-zero.result\npass tests/userprog/read-zero\npintos -v -k -T 60 --qemu  --filesys-size=2 " \
         "-p tests/userprog/read-stdout -a read-stdout -- -q  -f run read-stdout < /dev/null 2> " \
         "tests/userprog/read-stdout.errors > tests/userprog/read-stdout.output\nperl -I../.. " \
         "../../tests/userprog/read-stdout.ck tests/userprog/read-stdout tests/userprog/read-stdout.result\npass " \
         "tests/userprog/read-stdout\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/read-bad-fd -a " \
         "read-bad-fd -- -q  -f run read-bad-fd < /dev/null 2> tests/userprog/read-bad-fd.errors > " \
         "tests/userprog/read-bad-fd.output\nperl -I../.. ../../tests/userprog/read-bad-fd.ck " \
         "tests/userprog/read-bad-fd tests/userprog/read-bad-fd.result\npass tests/userprog/read-bad-fd\npintos -v -k " \
         "-T 60 --qemu  --filesys-size=2 -p tests/userprog/write-normal -a write-normal -p " \
         "../../tests/userprog/sample.txt -a sample.txt -- -q  -f run write-normal < /dev/null 2> " \
         "tests/userprog/write-normal.errors > tests/userprog/write-normal.output\nperl -I../.. " \
         "../../tests/userprog/write-normal.ck tests/userprog/write-normal tests/userprog/write-normal.result\npass " \
         "tests/userprog/write-normal\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/write-bad-ptr -a " \
         "write-bad-ptr -p ../../tests/userprog/sample.txt -a sample.txt -- -q  -f run write-bad-ptr < /dev/null 2> " \
         "tests/userprog/write-bad-ptr.errors > tests/userprog/write-bad-ptr.output\nperl -I../.. " \
         "../../tests/userprog/write-bad-ptr.ck tests/userprog/write-bad-ptr " \
         "tests/userprog/write-bad-ptr.result\npass tests/userprog/write-bad-ptr\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/write-boundary -a write-boundary -p ../../tests/userprog/sample.txt -a " \
         "sample.txt -- -q  -f run write-boundary < /dev/null 2> tests/userprog/write-boundary.errors > " \
         "tests/userprog/write-boundary.output\nperl -I../.. ../../tests/userprog/write-boundary.ck " \
         "tests/userprog/write-boundary tests/userprog/write-boundary.result\npass " \
         "tests/userprog/write-boundary\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/write-zero -a " \
         "write-zero -p ../../tests/userprog/sample.txt -a sample.txt -- -q  -f run write-zero < /dev/null 2> " \
         "tests/userprog/write-zero.errors > tests/userprog/write-zero.output\nperl -I../.. " \
         "../../tests/userprog/write-zero.ck tests/userprog/write-zero tests/userprog/write-zero.result\npass " \
         "tests/userprog/write-zero\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/write-stdin -a " \
         "write-stdin -- -q  -f run write-stdin < /dev/null 2> tests/userprog/write-stdin.errors > " \
         "tests/userprog/write-stdin.output\nperl -I../.. ../../tests/userprog/write-stdin.ck " \
         "tests/userprog/write-stdin tests/userprog/write-stdin.result\npass tests/userprog/write-stdin\npintos -v -k " \
         "-T 60 --qemu  --filesys-size=2 -p tests/userprog/write-bad-fd -a write-bad-fd -- -q  -f run write-bad-fd < " \
         "/dev/null 2> tests/userprog/write-bad-fd.errors > tests/userprog/write-bad-fd.output\nperl -I../.. " \
         "../../tests/userprog/write-bad-fd.ck tests/userprog/write-bad-fd tests/userprog/write-bad-fd.result\npass " \
         "tests/userprog/write-bad-fd\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/exec-once -a " \
         "exec-once -p tests/userprog/child-simple -a child-simple -- -q  -f run exec-once < /dev/null 2> " \
         "tests/userprog/exec-once.errors > tests/userprog/exec-once.output\nperl -I../.. " \
         "../../tests/userprog/exec-once.ck tests/userprog/exec-once tests/userprog/exec-once.result\npass " \
         "tests/userprog/exec-once\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/exec-arg -a " \
         "exec-arg -p tests/userprog/child-args -a child-args -- -q  -f run exec-arg < /dev/null 2> " \
         "tests/userprog/exec-arg.errors > tests/userprog/exec-arg.output\nperl -I../.. " \
         "../../tests/userprog/exec-arg.ck tests/userprog/exec-arg tests/userprog/exec-arg.result\npass " \
         "tests/userprog/exec-arg\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/exec-bound -a " \
         "exec-bound -p tests/userprog/child-args -a child-args -- -q  -f run exec-bound < /dev/null 2> " \
         "tests/userprog/exec-bound.errors > tests/userprog/exec-bound.output\nperl -I../.. " \
         "../../tests/userprog/exec-bound.ck tests/userprog/exec-bound tests/userprog/exec-bound.result\npass " \
         "tests/userprog/exec-bound\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/exec-bound-2 -a " \
         "exec-bound-2 -- -q  -f run exec-bound-2 < /dev/null 2> tests/userprog/exec-bound-2.errors > " \
         "tests/userprog/exec-bound-2.output\nperl -I../.. ../../tests/userprog/exec-bound-2.ck " \
         "tests/userprog/exec-bound-2 tests/userprog/exec-bound-2.result\npass tests/userprog/exec-bound-2\npintos -v " \
         "-k -T 60 --qemu  --filesys-size=2 -p tests/userprog/exec-bound-3 -a exec-bound-3 -- -q  -f run exec-bound-3 " \
         "< /dev/null 2> tests/userprog/exec-bound-3.errors > tests/userprog/exec-bound-3.output\nperl -I../.. " \
         "../../tests/userprog/exec-bound-3.ck tests/userprog/exec-bound-3 tests/userprog/exec-bound-3.result\npass " \
         "tests/userprog/exec-bound-3\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/exec-multiple -a " \
         "exec-multiple -p tests/userprog/child-simple -a child-simple -- -q  -f run exec-multiple < /dev/null 2> " \
         "tests/userprog/exec-multiple.errors > tests/userprog/exec-multiple.output\nperl -I../.. " \
         "../../tests/userprog/exec-multiple.ck tests/userprog/exec-multiple " \
         "tests/userprog/exec-multiple.result\npass tests/userprog/exec-multiple\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/exec-missing -a exec-missing -- -q  -f run exec-missing < /dev/null 2> " \
         "tests/userprog/exec-missing.errors > tests/userprog/exec-missing.output\nperl -I../.. " \
         "../../tests/userprog/exec-missing.ck tests/userprog/exec-missing tests/userprog/exec-missing.result\npass " \
         "tests/userprog/exec-missing\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/exec-bad-ptr -a " \
         "exec-bad-ptr -- -q  -f run exec-bad-ptr < /dev/null 2> tests/userprog/exec-bad-ptr.errors > " \
         "tests/userprog/exec-bad-ptr.output\nperl -I../.. ../../tests/userprog/exec-bad-ptr.ck " \
         "tests/userprog/exec-bad-ptr tests/userprog/exec-bad-ptr.result\npass tests/userprog/exec-bad-ptr\npintos -v " \
         "-k -T 60 --qemu  --filesys-size=2 -p tests/userprog/wait-simple -a wait-simple -p " \
         "tests/userprog/child-simple -a child-simple -- -q  -f run wait-simple < /dev/null 2> " \
         "tests/userprog/wait-simple.errors > tests/userprog/wait-simple.output\nperl -I../.. " \
         "../../tests/userprog/wait-simple.ck tests/userprog/wait-simple tests/userprog/wait-simple.result\npass " \
         "tests/userprog/wait-simple\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/wait-twice -a " \
         "wait-twice -p tests/userprog/child-simple -a child-simple -- -q  -f run wait-twice < /dev/null 2> " \
         "tests/userprog/wait-twice.errors > tests/userprog/wait-twice.output\nperl -I../.. " \
         "../../tests/userprog/wait-twice.ck tests/userprog/wait-twice tests/userprog/wait-twice.result\npass " \
         "tests/userprog/wait-twice\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/wait-killed -a " \
         "wait-killed -p tests/userprog/child-bad -a child-bad -- -q  -f run wait-killed < /dev/null 2> " \
         "tests/userprog/wait-killed.errors > tests/userprog/wait-killed.output\nperl -I../.. " \
         "../../tests/userprog/wait-killed.ck tests/userprog/wait-killed tests/userprog/wait-killed.result\npass " \
         "tests/userprog/wait-killed\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/wait-bad-pid -a " \
         "wait-bad-pid -- -q  -f run wait-bad-pid < /dev/null 2> tests/userprog/wait-bad-pid.errors > " \
         "tests/userprog/wait-bad-pid.output\nperl -I../.. ../../tests/userprog/wait-bad-pid.ck " \
         "tests/userprog/wait-bad-pid tests/userprog/wait-bad-pid.result\npass tests/userprog/wait-bad-pid\npintos -v " \
         "-k -T 60 --qemu  --filesys-size=2 -p tests/userprog/multi-recurse -a multi-recurse -- -q  -f run " \
         "'multi-recurse 15' < /dev/null 2> tests/userprog/multi-recurse.errors > " \
         "tests/userprog/multi-recurse.output\nperl -I../.. ../../tests/userprog/multi-recurse.ck " \
         "tests/userprog/multi-recurse tests/userprog/multi-recurse.result\npass tests/userprog/multi-recurse\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/multi-child-fd -a multi-child-fd -p " \
         "../../tests/userprog/sample.txt -a sample.txt -p tests/userprog/child-close -a child-close -- -q  -f run " \
         "multi-child-fd < /dev/null 2> tests/userprog/multi-child-fd.errors > " \
         "tests/userprog/multi-child-fd.output\nperl -I../.. ../../tests/userprog/multi-child-fd.ck " \
         "tests/userprog/multi-child-fd tests/userprog/multi-child-fd.result\npass " \
         "tests/userprog/multi-child-fd\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/rox-simple -a " \
         "rox-simple -- -q  -f run rox-simple < /dev/null 2> tests/userprog/rox-simple.errors > " \
         "tests/userprog/rox-simple.output\nperl -I../.. ../../tests/userprog/rox-simple.ck tests/userprog/rox-simple " \
         "tests/userprog/rox-simple.result\npass tests/userprog/rox-simple\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/rox-child -a rox-child -p tests/userprog/child-rox -a child-rox -- -q  " \
         "-f run rox-child < /dev/null 2> tests/userprog/rox-child.errors > tests/userprog/rox-child.output\nperl " \
         "-I../.. ../../tests/userprog/rox-child.ck tests/userprog/rox-child tests/userprog/rox-child.result\npass " \
         "tests/userprog/rox-child\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/rox-multichild -a " \
         "rox-multichild -p tests/userprog/child-rox -a child-rox -- -q  -f run rox-multichild < /dev/null 2> " \
         "tests/userprog/rox-multichild.errors > tests/userprog/rox-multichild.output\nperl -I../.. " \
         "../../tests/userprog/rox-multichild.ck tests/userprog/rox-multichild " \
         "tests/userprog/rox-multichild.result\npass tests/userprog/rox-multichild\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/bad-read -a bad-read -- -q  -f run bad-read < /dev/null 2> " \
         "tests/userprog/bad-read.errors > tests/userprog/bad-read.output\nperl -I../.. " \
         "../../tests/userprog/bad-read.ck tests/userprog/bad-read tests/userprog/bad-read.result\npass " \
         "tests/userprog/bad-read\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/bad-write -a " \
         "bad-write -- -q  -f run bad-write < /dev/null 2> tests/userprog/bad-write.errors > " \
         "tests/userprog/bad-write.output\nperl -I../.. ../../tests/userprog/bad-write.ck tests/userprog/bad-write " \
         "tests/userprog/bad-write.result\npass tests/userprog/bad-write\npintos -v -k -T 60 --qemu  --filesys-size=2 " \
         "-p tests/userprog/bad-read2 -a bad-read2 -- -q  -f run bad-read2 < /dev/null 2> " \
         "tests/userprog/bad-read2.errors > tests/userprog/bad-read2.output\nperl -I../.. " \
         "../../tests/userprog/bad-read2.ck tests/userprog/bad-read2 tests/userprog/bad-read2.result\npass " \
         "tests/userprog/bad-read2\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/bad-write2 -a " \
         "bad-write2 -- -q  -f run bad-write2 < /dev/null 2> tests/userprog/bad-write2.errors > " \
         "tests/userprog/bad-write2.output\nperl -I../.. ../../tests/userprog/bad-write2.ck tests/userprog/bad-write2 " \
         "tests/userprog/bad-write2.result\npass tests/userprog/bad-write2\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/userprog/bad-jump -a bad-jump -- -q  -f run bad-jump < /dev/null 2> " \
         "tests/userprog/bad-jump.errors > tests/userprog/bad-jump.output\nperl -I../.. " \
         "../../tests/userprog/bad-jump.ck tests/userprog/bad-jump tests/userprog/bad-jump.result\npass " \
         "tests/userprog/bad-jump\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/bad-jump2 -a " \
         "bad-jump2 -- -q  -f run bad-jump2 < /dev/null 2> tests/userprog/bad-jump2.errors > " \
         "tests/userprog/bad-jump2.output\nperl -I../.. ../../tests/userprog/bad-jump2.ck tests/userprog/bad-jump2 " \
         "tests/userprog/bad-jump2.result\npass tests/userprog/bad-jump2\npintos -v -k -T 60 --qemu  --filesys-size=2 " \
         "-p tests/userprog/iloveos -a iloveos -- -q  -f run iloveos < /dev/null 2> tests/userprog/iloveos.errors > " \
         "tests/userprog/iloveos.output\nperl -I../.. ../../tests/userprog/iloveos.ck tests/userprog/iloveos " \
         "tests/userprog/iloveos.result\npass tests/userprog/iloveos\npintos -v -k -T 60 --qemu  --filesys-size=2 -p " \
         "tests/userprog/practice -a practice -- -q  -f run practice < /dev/null 2> tests/userprog/practice.errors > " \
         "tests/userprog/practice.output\nperl -I../.. ../../tests/userprog/practice.ck tests/userprog/practice " \
         "tests/userprog/practice.result\npass tests/userprog/practice\npintos -v -k -T 60 --qemu  --filesys-size=2 " \
         "-p tests/userprog/do-nothing -a do-nothing -- -q  -f run do-nothing < /dev/null 2> " \
         "tests/userprog/do-nothing.errors > tests/userprog/do-nothing.output\nperl -I../.. " \
         "../../tests/userprog/do-nothing.ck tests/userprog/do-nothing tests/userprog/do-nothing.result\npass " \
         "tests/userprog/do-nothing\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/stack-align-1 -a " \
         "stack-align-1 -- -q  -f run stack-align-1 < /dev/null 2> tests/userprog/stack-align-1.errors > " \
         "tests/userprog/stack-align-1.output\nperl -I../.. ../../tests/userprog/stack-align-1.ck " \
         "tests/userprog/stack-align-1 tests/userprog/stack-align-1.result\npass tests/userprog/stack-align-1\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/stack-align-2 -a stack-align-2 -- -q  -f run " \
         "'stack-align-2 a' < /dev/null 2> tests/userprog/stack-align-2.errors > " \
         "tests/userprog/stack-align-2.output\nperl -I../.. ../../tests/userprog/stack-align-2.ck " \
         "tests/userprog/stack-align-2 tests/userprog/stack-align-2.result\npass tests/userprog/stack-align-2\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/stack-align-3 -a stack-align-3 -- -q  -f run " \
         "'stack-align-3 ab' < /dev/null 2> tests/userprog/stack-align-3.errors > " \
         "tests/userprog/stack-align-3.output\nperl -I../.. ../../tests/userprog/stack-align-3.ck " \
         "tests/userprog/stack-align-3 tests/userprog/stack-align-3.result\npass tests/userprog/stack-align-3\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/stack-align-4 -a stack-align-4 -- -q  -f run " \
         "'stack-align-4 ab cd' < /dev/null 2> tests/userprog/stack-align-4.errors > " \
         "tests/userprog/stack-align-4.output\nperl -I../.. ../../tests/userprog/stack-align-4.ck " \
         "tests/userprog/stack-align-4 tests/userprog/stack-align-4.result\npass tests/userprog/stack-align-4\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/userprog/wait-halt -a wait-halt -p tests/userprog/halt -a " \
         "halt -- -q  -f run wait-halt < /dev/null 2> tests/userprog/wait-halt.errors > " \
         "tests/userprog/wait-halt.output\nperl -I../.. ../../tests/userprog/wait-halt.ck tests/userprog/wait-halt " \
         "tests/userprog/wait-halt.result\npass tests/userprog/wait-halt\npintos -v -k -T 60 --qemu  --filesys-size=2 " \
         "-p tests/userprog/wait-four -a wait-four -p tests/userprog/child-simple -a child-simple -- -q  -f run " \
         "wait-four < /dev/null 2> tests/userprog/wait-four.errors > tests/userprog/wait-four.output\nperl -I../.. " \
         "../../tests/userprog/wait-four.ck tests/userprog/wait-four tests/userprog/wait-four.result\npass " \
         "tests/userprog/wait-four\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/filesys/base/lg-create -a " \
         "lg-create -- -q  -f run lg-create < /dev/null 2> tests/filesys/base/lg-create.errors > " \
         "tests/filesys/base/lg-create.output\nperl -I../.. ../../tests/filesys/base/lg-create.ck " \
         "tests/filesys/base/lg-create tests/filesys/base/lg-create.result\npass tests/filesys/base/lg-create\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/filesys/base/lg-full -a lg-full -- -q  -f run lg-full < " \
         "/dev/null 2> tests/filesys/base/lg-full.errors > tests/filesys/base/lg-full.output\nperl -I../.. " \
         "../../tests/filesys/base/lg-full.ck tests/filesys/base/lg-full tests/filesys/base/lg-full.result\npass " \
         "tests/filesys/base/lg-full\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/filesys/base/lg-random -a " \
         "lg-random -- -q  -f run lg-random < /dev/null 2> tests/filesys/base/lg-random.errors > " \
         "tests/filesys/base/lg-random.output\nperl -I../.. ../../tests/filesys/base/lg-random.ck " \
         "tests/filesys/base/lg-random tests/filesys/base/lg-random.result\npass tests/filesys/base/lg-random\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/filesys/base/lg-seq-block -a lg-seq-block -- -q  -f run " \
         "lg-seq-block < /dev/null 2> tests/filesys/base/lg-seq-block.errors > " \
         "tests/filesys/base/lg-seq-block.output\nperl -I../.. ../../tests/filesys/base/lg-seq-block.ck " \
         "tests/filesys/base/lg-seq-block tests/filesys/base/lg-seq-block.result\npass " \
         "tests/filesys/base/lg-seq-block\npintos -v -k -T 60 --qemu  --filesys-size=2 -p " \
         "tests/filesys/base/lg-seq-random -a lg-seq-random -- -q  -f run lg-seq-random < /dev/null 2> " \
         "tests/filesys/base/lg-seq-random.errors > tests/filesys/base/lg-seq-random.output\nperl -I../.. " \
         "../../tests/filesys/base/lg-seq-random.ck tests/filesys/base/lg-seq-random " \
         "tests/filesys/base/lg-seq-random.result\npass tests/filesys/base/lg-seq-random\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/filesys/base/sm-create -a sm-create -- -q  -f run sm-create < /dev/null 2> " \
         "tests/filesys/base/sm-create.errors > tests/filesys/base/sm-create.output\nperl -I../.. " \
         "../../tests/filesys/base/sm-create.ck tests/filesys/base/sm-create " \
         "tests/filesys/base/sm-create.result\npass tests/filesys/base/sm-create\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/filesys/base/sm-full -a sm-full -- -q  -f run sm-full < /dev/null 2> " \
         "tests/filesys/base/sm-full.errors > tests/filesys/base/sm-full.output\nperl -I../.. " \
         "../../tests/filesys/base/sm-full.ck tests/filesys/base/sm-full tests/filesys/base/sm-full.result\npass " \
         "tests/filesys/base/sm-full\npintos -v -k -T 60 --qemu  --filesys-size=2 -p tests/filesys/base/sm-random -a " \
         "sm-random -- -q  -f run sm-random < /dev/null 2> tests/filesys/base/sm-random.errors > " \
         "tests/filesys/base/sm-random.output\nperl -I../.. ../../tests/filesys/base/sm-random.ck " \
         "tests/filesys/base/sm-random tests/filesys/base/sm-random.result\npass tests/filesys/base/sm-random\npintos " \
         "-v -k -T 60 --qemu  --filesys-size=2 -p tests/filesys/base/sm-seq-block -a sm-seq-block -- -q  -f run " \
         "sm-seq-block < /dev/null 2> tests/filesys/base/sm-seq-block.errors > " \
         "tests/filesys/base/sm-seq-block.output\nperl -I../.. ../../tests/filesys/base/sm-seq-block.ck " \
         "tests/filesys/base/sm-seq-block tests/filesys/base/sm-seq-block.result\npass " \
         "tests/filesys/base/sm-seq-block\npintos -v -k -T 60 --qemu  --filesys-size=2 -p " \
         "tests/filesys/base/sm-seq-random -a sm-seq-random -- -q  -f run sm-seq-random < /dev/null 2> " \
         "tests/filesys/base/sm-seq-random.errors > tests/filesys/base/sm-seq-random.output\nperl -I../.. " \
         "../../tests/filesys/base/sm-seq-random.ck tests/filesys/base/sm-seq-random " \
         "tests/filesys/base/sm-seq-random.result\npass tests/filesys/base/sm-seq-random\npintos -v -k -T 300 --qemu  " \
         "--filesys-size=2 -p tests/filesys/base/syn-read -a syn-read -p tests/filesys/base/child-syn-read -a " \
         "child-syn-read -- -q  -f run syn-read < /dev/null 2> tests/filesys/base/syn-read.errors > " \
         "tests/filesys/base/syn-read.output\nperl -I../.. ../../tests/filesys/base/syn-read.ck " \
         "tests/filesys/base/syn-read tests/filesys/base/syn-read.result\npass tests/filesys/base/syn-read\npintos -v " \
         "-k -T 60 --qemu  --filesys-size=2 -p tests/filesys/base/syn-remove -a syn-remove -- -q  -f run syn-remove < " \
         "/dev/null 2> tests/filesys/base/syn-remove.errors > tests/filesys/base/syn-remove.output\nperl -I../.. " \
         "../../tests/filesys/base/syn-remove.ck tests/filesys/base/syn-remove " \
         "tests/filesys/base/syn-remove.result\npass tests/filesys/base/syn-remove\npintos -v -k -T 60 --qemu  " \
         "--filesys-size=2 -p tests/filesys/base/syn-write -a syn-write -p tests/filesys/base/child-syn-wrt -a " \
         "child-syn-wrt -- -q  -f run syn-write < /dev/null 2> tests/filesys/base/syn-write.errors > " \
         "tests/filesys/base/syn-write.output\nperl -I../.. ../../tests/filesys/base/syn-write.ck " \
         "tests/filesys/base/syn-write tests/filesys/base/syn-write.result\nFAIL tests/filesys/base/syn-write\nKernel " \
         "panic in run: PANIC at ../../devices/block.c:112 in check_sector(): Access past end of device hda2 (" \
         "sector=611093284, size=4096)\nCall stack: 0xc0028cef\nTranslation of call stack:\n0xc0028cef: debug_panic (" \
         ".../../lib/kernel/debug.c:38)\nrm -f tmp.dsk\npintos-mkdisk tmp.dsk --filesys-size=2\npintos -v -k -T 60 " \
         "--qemu  --disk=tmp.dsk -p tests/filesys/extended/dir-empty-name -a dir-empty-name -p " \
         "tests/filesys/extended/tar -a tar -- -q  -f run dir-empty-name < /dev/null 2> " \
         "tests/filesys/extended/dir-empty-name.errors > tests/filesys/extended/dir-empty-name.output\npintos -v -k " \
         "-T 60  --qemu --disk=tmp.dsk -g fs.tar -a tests/filesys/extended/dir-empty-name.tar -- -q  run 'tar fs.tar " \
         "/' < /dev/null 2> tests/filesys/extended/dir-empty-name-persistence.errors > " \
         "tests/filesys/extended/dir-empty-name-persistence.output\nrm -f tmp.dsk\nperl -I../.. " \
         "../../tests/filesys/extended/dir-empty-name.ck tests/filesys/extended/dir-empty-name " \
         "tests/filesys/extended/dir-empty-name.result\nFAIL tests/filesys/extended/dir-empty-name\nrun: mkdir "" (" \
         "must return false): FAILED\nrm -f tmp.dsk\npintos-mkdisk tmp.dsk --filesys-size=2\npintos -v -k -T 60 " \
         "--qemu  --disk=tmp.dsk -p tests/filesys/extended/dir-mk-tree -a dir-mk-tree -p tests/filesys/extended/tar " \
         "-a tar -- -q  -f run dir-mk-tree < /dev/null 2> tests/filesys/extended/dir-mk-tree.errors > " \
         "tests/filesys/extended/dir-mk-tree.output\npintos -v -k -T 60  --qemu --disk=tmp.dsk -g fs.tar -a " \
         "tests/filesys/extended/dir-mk-tree.tar -- -q  run 'tar fs.tar /' < /dev/null 2> " \
         "tests/filesys/extended/dir-mk-tree-persistence.errors > " \
         "tests/filesys/extended/dir-mk-tree-persistence.output\n../../tests/filesys/extended/Make.tests:49: recipe " \
         "for target 'tests/filesys/extended/dir-mk-tree.output' failed\nmake: *** [" \
         "tests/filesys/extended/dir-mk-tree.output] Error 1 "

print(string.count("\npass "), string.count("\nFAIL "))
