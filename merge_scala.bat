del _scalafiddle\mathlib.scala

type _scalafiddle\settheory.scala > _scalafiddle\mathlib.scala
echo: >> _scalafiddle\mathlib.scala

for /f "delims=" %%a in ('dir /b /s /a-d _scalafiddle\mathlib\*.scala') do type "%%a" >> _scalafiddle\mathlib.scala & echo: >> _scalafiddle\mathlib.scala

type _scalafiddle\main.scala >> _scalafiddle\mathlib.scala

