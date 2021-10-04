---
title: Home
layout: page
permalink: /home
---

Welcome to this open and interactive textbook on theoretical modeling for cognitive science and psychology.

## Table of contents

{%- for collection in site.collections -%}
{%- for page in site.pages  -%}
{% if page.category==collection.label %}
<p style="text-transform: uppercase; margin-top: 2.5rem;"><a href="{{ site.baseurl }}{{ page.url }}">Part {{ page.title }}</a></p>
<ul>
{%- for chapter in collection.docs -%}
{% assign path = chapter.relative_path | split: "/" %}
{% if path.size == 2 %}
<li style="text-align: left;"><a href="{{ site.baseurl }}{{ chapter.url }}">Chapter {{ chapter.chapter }} - {{ chapter.title }}</a></li>
{% endif %}
{%- endfor -%}
</ul>
{% endif %}
{%- endfor -%}
{%- endfor -%}

## Cover art
The [cover art](https://computationalcognitivescience.github.io/lovelace/) is by Danielle Navarro (used with permission & released under [CC-BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/)).

Danielle Navarro's award-winning open textbook [Learning Statistics with R](https://learningstatisticswithr.com) and open teaching materials [R for Psychological Science](http://psyr.djnavarro.net) inspired us to write this open and online textbook. Choosing her art for the book's cover represents our gratitude for this inspiration as much as our love for her [artwork](https://art.djnavarro.net).

## License
This open textbook is released under a [CC-BY 4.0 license](https://creativecommons.org/licenses/by/4.0/) and the Scala software used herein is released under an [UGN GPL v3 license]() or any later version. This book is written in [Jekyll](https://jekyllrb.com) using the [tufte-jekyll](https://github.com/clayh53/tufte-jekyll) theme released under an [MIT license](https://github.com/fongandrew/hydeout/blob/master/LICENSE.md). The source code is available on Github [here](https://github.com/computationalcognitivescience/lovelace).

## How to cite this book
Blokpoel, M. & van Rooij, I. (2021). Theoretical modeling for cognitive science and psychology. Retrieved [date] from [URL].

## In-progress
At present this book is a living document, some sections have been written while others are still under construction. If the parts you are interested in aren't finished yet, please check back at a later time. Feedback and suggestions are welcome.

## About the authors
[Mark Blokpoel](http://www.markblokpoel.com) is a computer scientist and computational cognitive scientist. [Iris van Rooij](irisvanrooijcogsci.com) is a psychologist and cognitive scientist. Both authors are based at Donders Institute for Brain, Cognition, and Behaviour, Radboud University. Parts of this book were written while Van Rooij was at the Netherlands Institute for Advanced Study in the Humanities and Social Sciences ([NIAS](https://nias.knaw.nl)). Van Rooij and Blokpoel previously co-authored [Cognition and Intractability](https://cognitionandintractability.com) with Johan Kwisthout and Todd Wareham.

## Reuse of materials

Parts of this book are based on materials that previously appeared in:

van Rooij, I., & Baggio, G. (2021). [Theory before the test](https://journals.sagepub.com/doi/full/10.1177/1745691620970604): How to build high-verisimilitude explanatory theories in psychological science. *Perspectives on Psychological Science*.

van Rooij, I., & Blokpoel, M. (2020). [Formalizing verbal theories: A tutorial by dialogue](https://doi.org/10.1027/1864-9335/a000428). *Social Psychology, 51*(5), 285-298

Specifically, parts of these papers were reused in the 'Conceptual Foundations', the 'Math Concepts and Notation', and 'Subset Choice' chapters.


## Acknowledgements
We have learned a lot from participants in courses and workshops that we taught on the topic over the last 10 years. We would like to thank them all. This includes hundreds of BSc Artificial Intelligence students and dozens of Psychology, Cognitive Neuroscience, and Computer Science BSc, MSc and PhD students who have taken the Computational & Formal Modeling course at Radboud University. It also includes PhD students at the [Behavioural Science Institute](https://www.ru.nl/bsi/), staff at the Dept. of Social Psychology at Radboud University, and members of the [Experimental Psychopathology and Affective Neuroscience (EPAN)](https://www.epanlab.nl) research group at the Donders Institute who participated in workshops that we organized on the topic of this book.

This work has been made possible by a [Distinguished Lorentz Fellowship (DLF) & Prize](http://nias-lorentz.nl/dlf-fellowships/) awarded by [NIAS](https://nias.knaw.nl) and the [Lorentz Center](https://www.lorentzcenter.nl) to Van Rooij and a [Language in Interaction](https://www.languageininteraction.nl) grant (nr. 024.001.006) supporting Blokpoel.
