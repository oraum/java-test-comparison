#!/usr/bin/env bash

# simple script to duplicate the test classes in each module
# usage duplicateTest.sh n where n is the number of tests

file=GreetingServiceTest.java

function copyFile() {
  cp $file "GreetingServiceTest$1.java"
  sed -i "s/GreetingServiceTest/GreetingServiceTest$1/g" "GreetingServiceTest$1.java"
}

dirs=(junit-mockito/src/test/java/com/github/oraum/service/ junit-weld-dbunit/src/test/java/com/github/oraum/service/ arquillian-liberty/src/test/java/com/github/oraum/service/)

if [ $# = 0 ]; then
  print no copy count supplied
  exit 1
fi
cwd=$(pwd)
for dir in "${dirs[@]}"; do
  cd "$dir" || exit
  i=1
  while [ $i -lt "$1" ]; do
    copyFile $i
    ((i = i + 1))
  done
  cd "$cwd" || exit

done
