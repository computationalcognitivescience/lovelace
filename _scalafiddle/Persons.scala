import scala.util.Random
import scalatags.JsDom.all._


case object VegaRenderer {
  def render(vegaSpec: String): Unit = {
    Fiddle.print(
      div(id:="vis", "test"),
      script("""
        var script = document.createElement('script');
        script.onload = function () {
          requirejs.config({
            baseUrl: 'https://cdn.jsdelivr.net/npm/',
            paths: {
              "vega-embed":  "vega-embed@3?noext",
              "vega-lib": "vega-lib?noext",
              "vega-lite": "vega-lite@2?noext",
              "vega": "vega@3?noext"
            }
          });

        require(["vega-embed"], function(vegaEmbed) {
          const spec = {
            """ + vegaSpec + """
          };
          vegaEmbed('#vis', spec, {defaultStyle: true}).catch(console.warn);
        });
      };

      script.src = "https://requirejs.org/docs/release/2.3.6/minified/require.js";
      document.head.appendChild(script);
      """)
    )
  }
}

object Math {
  // def build[U](set: Set[U], f: U => Boolean): Set[U] = set.filter(f(_))
  //
  // def sdiff[A](s1: Set[A], s2: Set[A]): Set[A] = (s1 diff s2) union (s2 diff s1)
  //
  // def bigUnion[A](setOfSets: Set[Set[A]]): Set[A] = if(setOfSets.nonEmpty) setOfSets.reduce(_ union _) else Set.empty
  //
  // def bigIntersection[A](setOfSets: Set[Set[A]]): Set[A] = if(setOfSets.nonEmpty) setOfSets.reduce(_ intersect _) else Set.empty
  //
  // def cross[A](set1: Set[A], set2: Set[A]): Set[(A,A)] = for(x <- set1; y <- set2) yield (x,y)
  //
  // def cross[A](set1: Set[A], set2: Set[A], cond: (A, A) => Boolean): Set[(A,A)] = for(x <- set1; y <- set2 if cond(x,y)) yield (x,y)
  //
  // def forallUniquePairs[A](set: Set[A], cond: (A, A) => Boolean): Boolean = cross(set, set, (x: A, y: A)=>x!=y).forall((pair: (A,A)) => cond(pair._1, pair._2))
  //
  // def sum[A](set: Set[A], fun: A => Int): Int = (set map fun).sum
  //
  // def prod[A](set: Set[A], fun: A => Int): Int = (set map fun).product
  //
  // def fapply[A,B](set: Set[A], fun: A => B): Set[B] = set map fun
  //
  // def partitions[A](set: Set[A]): Set[Set[Set[A]]] = {
  // def partitions(list: Seq[A]): Set[Set[Set[A]]] = {
  //   list match {
  //     case head::Nil => Set(Set(Set(head)))
  //     case head::tail =>
  //       val possiblePartitions = partitions(tail)
  //       val case1 = for(partition <- possiblePartitions) yield partition + Set(head)
  //       val case2 = for(partition <- possiblePartitions; part <- partition) yield (partition - part) + (part + head)
  //       case1 union case2
  //   }
  // }
  // partitions(set.toList)
  // }
  //
  // def powerset[A](t: Set[A]): Set[Set[A]] = {
  // @annotation.tailrec
  // def pwr(t: Set[A], ps: Set[Set[A]]): Set[Set[A]] =
  //   if (t.isEmpty) ps
  //   else pwr(t.tail, ps ++ (ps map (_ + t.head)))
  // pwr(t, Set(Set.empty[A]))
  // }
  //
  // def argmax[A](set: Set[A], f: A => Int): Option[A] = {
  // val seq = set.toSeq // convert to sequence to preserve ordering in zip function
  // val valSeq = seq map f
  // val maxValue = valSeq.max
  // val maxValSet = seq zip valSeq filter (_._2 == maxValue)
  // if(maxValSet.nonEmpty) Some(maxValSet(new Random().nextInt(maxValSet.length))._1) // if one or more maxima exist return random
  // else None
  // }
  implicit class ImplSet[A](set: Set[A]) {
    def pairs: Set[(A,A)] = for(x <- set; y <- set) yield (x,y)

    def uniquepairs: Set[(A,A)] = for(x <- set; y <- set if x!=y) yield (x,y)
  }
}

case class Person(name: String) {
  def likes(other: Person): Relation = Relation(this, other, true)
  def dislikes(other: Person): Relation = Relation(this, other, false)

  override def toString: String = name
}

case object Person {
    val names = List("Nettie Baldwin","Lester Peters","Brian Estrada","Cody Brock","Erik Mccarthy","William Bowers","Molly Fernandez","Joey Chapman","Thelma Casey","Edgar Miller","Emanuel Bass","Sergio Warner","Herman Rodriquez","Kelley Taylor","Wilfred Matthews","Guadalupe Morrison","Paula Snyder","Sheila Mendez","Javier Curtis","Kelly Harrington","Jason Hughes","Gilbert Dixon","Harriet Price","Meghan Santiago","Kenneth Clayton","Holly Sanchez","Rose Frazier","Lela Kennedy","Brenda Lamb","Constance Perkins","Vera George","Ramiro Sutton","Diana Holland","Charlene Wallace","Betty Mcgee","Michelle Hamilton","Frederick Mathis","Elmer Mckenzie","Byron Christensen","Randal Ward","Roderick Rhodes","Clark Marsh","Mathew Mendoza","Sammy Tran","Colleen Harvey","Marian Bailey","Tyrone Klein","Keith Stanley","Tonya Byrd","John Parsons","Kayla Aguilar","Johanna Stevenson","Dwayne Long","Antonia Curry","Kerry Perry","Fannie Hammond","Nichole Strickland","Jeanne Salazar","Roberto Barber","Vicky Oliver","Jesus Brady","Angela Shaw","Fredrick Munoz","Fernando Payne","Vivian Garcia","Natalie Guerrero","Johnnie Bennett","Monica Thompson","Angelica Armstrong","Anna Holt","Carlos Kelley","Marion Welch","Henry Fields","Lawrence Craig","Alexis Beck","Garry Mills","Bernard Parks","Jana Kim","Ernestine Rios","Deborah Phillips","Willard Cox","Eileen Knight","Erica Anderson","Elvira Steele","Myron Robertson","Elena Mckinney","Ervin Owens","Jeannette Pierce","Veronica Gross","Abraham Harrison","Lamar Thornton","Wanda Richardson","Lorraine Tate","Doris Foster","Leigh Henry","Devin Keller","Lindsay Moss","Isabel Stone","Marlene Hudson","Betsy Day")
    def random: Person = Person(names(Random.nextInt(names.length)))
    def randomSet(size: Int): Set[Person] = List.tabulate(size)(_ => Person.random).toSet
}

case class Relation(a: Person, b: Person, liking: Boolean) {
    def canEqual(a: Any) = a.isInstanceOf[Relation]

    override def equals(that: Any): Boolean = that match {
        case that: Relation => {
            this.liking == that.liking && (this.a == that.a && this.b == that.b || this.a == that.b && this.b == that.a)
        }
        case _ => false
    }
}

object Helpers {
  import Math._

  implicit class ImplRelation(personA: String) {
    def likes(personB: String): Relation = Relation(Person(personA), Person(personB), true)

    def dislikes(personB: String): Relation = Relation(Person(personA), Person(personB), false)
  }

  implicit class ImplRelFun(relations: Set[Relation]) {
    def deriveFun: ((Person, Person) => Boolean) = {
      (a: Person, b: Person) => {
        val rel = relations.find(p => p.a == a && p.b == b || p.a == b && p.b == a)
        if(rel.isDefined) rel.get.liking
        else false
      }
    }

  //   def toString(persons: Set[Person]): String =
  //     cross(persons, persons, (a: Person, b: Person) => a!=b)
  //       .map(p => {
  //         if(relations.deriveFun(p._1, p._2)) s"${p._1} like ${p._2}"
  //         else s"${p._1} dislike ${p._2}"
  //       })
  //       .mkString("\n")
  }
}

import Math._
import Helpers._

////
