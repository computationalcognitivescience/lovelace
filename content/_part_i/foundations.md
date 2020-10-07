---
layout: page
title: Conceptual foundations
chapter: 2
nav_exclude: true
---
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tristique urna quis diam ultrices aliquet. Curabitur ac faucibus ante, non faucibus tortor. Maecenas in urna nec sapien porta facilisis. Sed maximus diam magna, vel ullamcorper diam ultrices ac. In nunc neque, porttitor id interdum at, dictum vitae quam. Vestibulum euismod non mauris nec gravida. Donec metus dui, tempus vel mauris at, convallis faucibus lectus. Fusce nec iaculis velit, ac mattis turpis. Nullam vehicula imperdiet ex in bibendum.

Duis rhoncus lobortis convallis. Nulla dapibus, ipsum ac maximus malesuada, enim ex varius sem, eget consectetur purus est non libero. In scelerisque turpis nec lacus hendrerit, et hendrerit nunc tempor. Vestibulum varius sem in odio blandit, eget fermentum ipsum volutpat. Vestibulum eget metus quis ligula ullamcorper viverra. Nulla faucibus nibh in mi tempus, non tempor tortor euismod. Ut vitae mi quam. Nullam dapibus tincidunt odio, eu vehicula nisl lacinia dignissim. Nunc luctus dolor sed urna vehicula placerat. Suspendisse posuere luctus ante vitae mollis. Nulla feugiat tortor et posuere tempus. Nulla molestie purus sit amet eros porta condimentum. Vestibulum condimentum luctus turpis eget gravida. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Praesent ultrices porta lacus, eu ornare nisl tristique ac. Duis id lorem a nisi imperdiet consectetur.

{% scastie {isWorksheetMode:false} %}
import com.markblokpoel.probability4scala.Distribution

val d = Set("heads", "tails")
val v = Map(
    "heads" -> 0.5,
    "tails" -> 0.5
)

val dist = Distribution(d, v)

val samples = for(_ <- 0 until 10) yield dist.sample
 
samples.foreach(println)
{% endscastie %}

Nulla lectus nisl, tristique a nisi id, tristique sagittis ante. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed feugiat arcu eget enim tristique dictum. Nunc non quam sed velit pulvinar venenatis vitae ac dui. Morbi viverra eros vitae posuere scelerisque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Ut nec mauris at justo molestie suscipit at vitae turpis. Donec consectetur tellus ut nibh molestie vestibulum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean pulvinar nunc justo, vel tristique sapien eleifend ullamcorper. Mauris neque arcu, vulputate sagittis purus id, tincidunt molestie sem. Maecenas ullamcorper quis lacus et aliquam. Etiam ut vehicula est. Nam sed molestie nisi. Maecenas suscipit mi ut luctus laoreet. Sed pulvinar quis turpis ac viverra.