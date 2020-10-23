---
title: Home
layout: page
permalink: /home
---

Welcome to this online, open and interactive, textbook on theoretical modeling for cognitive science and psychology.

## Table of contents

{%- for collection in site.collections -%}
{%- for page in site.pages  -%}
{% if page.category==collection.label %}
<p><a href="{{ site.baseurl }}{{ page.url }}">Part {{ page.title }}</a></p>
<ul>
{%- for chapter in collection.docs -%}
<li><a href="{{ site.baseurl }}{{ chapter.url }}">Chapter {{ chapter.chapter }} - {{ chapter.title }}</a></li>
{%- endfor -%}
</ul>
{% endif %}
{%- endfor -%}
{%- endfor -%}

## Cover art
The cover art is by Danielle Navarro (used with permission & released under [CC-BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/)).

Danielle Navarro's award-winning open textbook [Learning Statistics with R](https://learningstatisticswithr.com) and open teaching materials [R for Psychological Science](http://psyr.djnavarro.net) inspired us to write this open and online textbook. Choosing her art for the book's cover represents our gratitude for this inspiration as much as our love for her [artwork](https://art.djnavarro.net).

## License
This open textbook is released under a [CC-BY 4.0 license](https://creativecommons.org/licenses/by/4.0/) and the Scala software used herein is released under an [UGN GPL v3 license]() or any later version. This book is written in [Jekyll](https://jekyllrb.com) using the [tufte-jekyll](https://github.com/clayh53/tufte-jekyll) theme released under an [MIT license](https://github.com/fongandrew/hydeout/blob/master/LICENSE.md). The source code is available on Github [here](https://github.com/computationalcognitivescience/lovelace).

## How to cite this book
Blokpoel, M. & van Rooij, I. (2020). Theoretical modeling for cognitive science and psychology. Version 0.1. Retrieved year-month-day from [URL].

## In-progress
At present this book is a living document, some sections have been written while others are still under construction. If the parts you are interested in aren't finished yet, please check back at a later time. Feedback and suggestions are welcome.

## About the authors
[Mark Blokpoel](http://www.markblokpoel.com) is a computer scientist and computational cognitive scientist. [Iris van Rooij](irisvanrooijcogsci.com) is a psychologist and cognitive scientist. Both authors are based at Donders Institute for Brain, Cognition, and Behaviour, Radboud University. Van Rooij is currently writing parts of this book while at the Netherlands Institute for Advanced Study in the Humanities and Social Sciences ([NIAS](https://nias.knaw.nl)). Van Rooij and Blokpoel previously co-authored [Cognition and Intractability](https://cognitionandintractability.com) with Johan Kwisthout and Todd Wareham.

## Reuse of materials

Parts of this book are based on materials that previously appeared in:

van Rooij, I., & Baggio, G. (2020/in press). Theory before the test: How to build high-verisimilitude explanatory theories in psychological science. *Perspectives on Psychological Science*. [preprint](https://psyarxiv.com/7qbpr/)

van Rooij, I., & Blokpoel, M. (2020/in press). Formalizing verbal theories: A tutorial by dialogue. *Social Psychology*. [preprint](https://psyarxiv.com/r2zqy)

Specifically, parts of these papers were reused in the 'Conceptual Foundations' and the 'Math Concepts and Notation' sections.


## Acknowledgements
We have learned a lot from participants in courses and workshops that we taught on the topic over the last 10 years. We would like to thank them all. This includes hundreds of BSc Artificial Intelligence students and dozens of Psychology, Cognitive Neuroscience, and Computer Science BSc, MSc and PhD students who have taken the [Computational & Formal Modeling](https://www.ru.nl/courseguides/socsci/courses-osiris/ai/sow-bki211-computational-and-formal-modeling/) course at Radboud University. It also includes PhD students at the [Behavioural Science Institute](https://www.ru.nl/bsi/), staff at the Dept. of Social Psychology at Radboud University, and members of the [Experimental Psychpathology and Affective Neuroscience (EPAN)](https://www.epanlab.nl) research group at the Donders Institute who participated in workshops that we organized on the topic of this book.

This work has been made possible by a [Distinguished Lorentz Fellowship (DLF) & Prize](http://nias-lorentz.nl/dlf-fellowships/) awarded by [NIAS](https://nias.knaw.nl) and the [Lorentz Center](https://www.lorentzcenter.nl) to Van Rooij and a [Language in Interaction](https://www.languageininteraction.nl) grant (nr. 024.001.006) supporting Blokpoel.
