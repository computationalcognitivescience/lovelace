#!/bin/bash

rm _scalafiddle/mathlib.scala

cat _scalafiddle/SetTheory.scala > _scalafiddle/mathlib.scala
echo >> _scalafiddle/mathlib.scala ;

for f in $(ls _scalafiddle/mathlib);
do
  echo Appending _scalafiddle/mathlib/$f ;
  cat _scalafiddle/mathlib/$f >> _scalafiddle/mathlib.scala ;
  echo >> _scalafiddle/mathlib.scala ;
done;

cat _scalafiddle/main.scala >> _scalafiddle/mathlib.scala
