import scala.annotation.tailrec
import scala.util.Random

/**
 * Implementation of basic set theory as implicits
 */
object SetTheory {
  trait NumberSetOps[T] {
    def sumElements(set: Set[T]): T
    def mulElements(set: Set[T]): T
  }

  implicit object IntNumberOps extends NumberSetOps[Int] {
    override def sumElements(set: Set[Int]): Int = set.sum
    override def mulElements(set: Set[Int]): Int = set.product
  }

  implicit object DoubleNumberOps extends NumberSetOps[Double] {
    override def sumElements(set: Set[Double]): Double = set.sum
    override def mulElements(set: Set[Double]): Double = set.product
  }

  implicit object FloatNumberOps extends NumberSetOps[Float] {
    override def sumElements(set: Set[Float]): Float = set.sum
    override def mulElements(set: Set[Float]): Float = set.product
  }

  def powerset[A](set: Set[A]): Set[Set[A]] = set.subsets.toSet
  def P[A](set: Set[A]): Set[Set[A]] = powerset(set)
  def powerset[A](set: Set[A], len: Int): Set[Set[A]] = set.subsets(len).toSet
  def P[A](set: Set[A], len: Int): Set[Set[A]] = powerset(set, len)
  def powersetUp[A](set: Set[A], upperbound: Int): Set[Set[A]] =
    (for(len <- 0 to upperbound) yield powerset(set, len)).toSet.flatten
  def powersetLow[A](set: Set[A], lowerbound: Int): Set[Set[A]] =
    (for(len <- lowerbound to set.size) yield powerset(set, len)).toSet.flatten


  def argMax[A, T](set: Set[A], f: A => T)(implicit ord: Ordering[T]): Set[A] = {
    val max = set.map(f).max  // find max value
    set.filter(f(_) == max)           // return all elems with max value
  }

  def sum[T](set: Set[T])(implicit nso: NumberSetOps[T]): T = nso.sumElements(set)
  def sum[A, T](set: Set[A], f: A => T)(implicit nso: NumberSetOps[T]): T = nso.sumElements(set.map(f))
  def sum[A, T](set: Set[(A, A)], f: (A, A) => T)(implicit nso: NumberSetOps[T]): T = nso.sumElements(set.map(pair => f(pair._1, pair._2)))

  def product[T](set: Set[T])(implicit nso: NumberSetOps[T]): T = nso.mulElements(set)
  def product[A, T](set: Set[A], f: A => T)(implicit nso: NumberSetOps[T]): T = nso.mulElements(set.map(f))
  def product[A, T](set: Set[(A, A)], f: (A, A) => T)(implicit nso: NumberSetOps[T]): T = nso.mulElements(set.map(pair => f(pair._1, pair._2)))

  def random[A](set: Set[A]): Option[A] = if (set.isEmpty) None
  else Some(set.toList(Random.nextInt(set.size)))

  implicit class ImplAny[A](elem: A) {
    def in(set: Set[A]): Boolean = set.contains(elem)
  }

  implicit class ImplSet[A](set: Set[A]) {
    // for set membership, use set.contains(element)

    def isSubsetOf(set2: Set[A]): Boolean = set != set2 && set.subsetOf(set2)
    def <(set2: Set[A]): Boolean = isSubsetOf(set2)

    def isSubsetEqTo(set2: Set[A]): Boolean = set.subsetOf(set2)
    def <=(set2: Set[A]): Boolean = isSubsetEqTo(set2)

    def isSupersetOf(set2: Set[A]): Boolean = set2 isSubsetOf set
    def >(set2: Set[A]): Boolean = isSupersetOf(set2)

    def isSupersetEqTo(set2: Set[A]): Boolean = set2 isSubsetEqTo set
    def >=(set2: Set[A]): Boolean = isSupersetEqTo(set2)

    // for intersection use set.intersect(set2)
    def /\(set2: Set[A]): Set[A] = set.intersect(set2)

    // for union use set.union(set2)
    def \/(set2: Set[A]): Set[A] = set.union(set2)

    def build(f: A => Boolean): Set[A] = set.filter(f(_))

    def |(f: A => Boolean): Set[A] = set build f

    def \(set2: Set[A]): Set[A] = set.diff(set2)

    def cardinalProduct[B](set2: Set[B]): Set[(A, B)] =
      for (x <- set; y <- set2) yield (x, y)
    def x[B](set2: Set[B]): Set[(A, B)] = cardinalProduct(set2)

    def pairs: Set[(A, A)] = for (x <- set; y <- set) yield (x, y)

    def uniquePairs: Set[(A, A)] = for (x <- set; y <- set if x != y) yield (x, y)

	def unorderedPairs: Set[Set[A]] = for (x <- set; y <- set) yield Set(x, y)
	
	def unorderedUniquePairs: Set[Set[A]] = for (x <- set; y <- set if x != y) yield Set(x, y)

    def powerset: Set[Set[A]] = SetTheory.powerset(set)
    def P: Set[Set[A]] = SetTheory.powerset(set)

    def allPartitions: Set[Set[Set[A]]] = {
      if (set.isEmpty) Set.empty
      else {
        val hd = set.head
        val solutions = set.tail.allPartitions
        val part1 = if (solutions.isEmpty) Set(Set(Set(hd)))
        else solutions.map(partitioning => {
          partitioning + Set(hd)
        })
        val part2 = if (solutions.isEmpty) Set(Set(Set(hd)))
        else solutions.flatMap(partitioning => partitioning.map(part => {
          val a = part + hd
          val b = partitioning - part
          b + a
        }))
        part1.union(part2)
      }
    }

    def argMax[T](f: A => T)(implicit ord: Ordering[T]): Set[A] = SetTheory.argMax(set, f)

    def allBijections[B](target: Set[B]): Set[Map[A, B]] = {
      val perm = target.toList.permutations.toSet
      val bijections = perm
        .map(set zip _)
        .map(_.toMap)
      bijections
    }

    def allMappings[B](coDomain: Set[B]): Set[Map[A, B]] = {
      @tailrec
      def allMappingsRec(domain: Set[A], coDomain: Set[B], acc: Set[Map[A,B]] = Set(Map[A,B]())): Set[Map[A, B]] = {
        if(domain.isEmpty) acc
        else if(coDomain.isEmpty) acc
        else {
          val newMappings: Set[(A, B)] = coDomain.map(domain.head -> _)
          val newAcc = acc.flatMap(oldMapping => newMappings.map(oldMapping + _))
          allMappingsRec(domain.tail, coDomain, newAcc)
        }
      }

      allMappingsRec(set, coDomain)
    }
    
    def random: Option[A] = SetTheory.random(set)
  }
  implicit class Impl2Set[A, B](sets: (Set[A], Set[B])) {
    // Example (set, set2) build((a: Int, b: Int) => a/2==0 && b%2==0)
    def build(f: (A, B) => Boolean): Set[(A, B)] =
      (sets._1 cardinalProduct sets._2) build Function.tupled(f)
    def |(f: (A, B) => Boolean): Set[(A, B)] = sets build f
  }

  implicit class ImplSetSet[A](setOfSets: Set[Set[A]]) {
    def union: Set[A] =
      if (setOfSets.nonEmpty) setOfSets.reduce(_ union _) else Set.empty

    def intersection: Set[A] =
      if (setOfSets.nonEmpty) setOfSets.reduce(_ intersect _) else Set.empty
  }

  def requirement(b: Boolean, msg: String): Unit =
    if (!b) {
      println(s"Requirement not met: $msg")
      assert(false)
    }
}

import SetTheory._ 
import scalatags.JsDom.all._

case object Viz {

  var vizCounter = 0

  def renderAlt(dot: String): Unit = {
    Fiddle.print(
	  div(id:=s"plot$vizCounter"),
	  script(s"""
  	    var script = document.createElement('script');
	    script.onload = function () {
		  requirejs.config({
		    baseUrl: 'https://unpkg.com/vis-network/standalone/umd/',
		    paths: {
			  "vis": "vis-network.min"
		    }
		  });

	    require(["vis"], function(vis) {
  		  const dotString = '${dot}';
		  var parsedData = vis.parseDOTNetwork(dotString);
		  var data = {
			  nodes: parsedData.nodes,
			  edges: parsedData.edges
			}
		  var options = parsedData.options;
  		  var container = document.getElementById('plot$vizCounter');
		  var network = new vis.Network(container, data, options);
	    });
	  };

	  script.src = "https://requirejs.org/docs/release/2.3.6/minified/require.js";
	  document.head.appendChild(script);
	  """)
    )
    vizCounter = vizCounter + 1
  }

  def render(dot: String): Unit = {
    Fiddle.print(
	  div(id:=s"plot$vizCounter"),
	  script(s"""
  	    var script = document.createElement('script');
	    script.onload = function () {
		  requirejs.config({
		    baseUrl: 'http://webgraphviz.com/',
		    paths: {
			  "viz": "viz"
		    }
		  });

	    require(["viz"], function(viz) {
  		  const dotString = '${dot}';
  		  var svg = Viz(dotString, "svg");
  		  document.getElementById('plot$vizCounter').innerHTML = svg;
	    });
	  };

	  script.src = "https://requirejs.org/docs/release/2.3.6/minified/require.js";
	  document.head.appendChild(script);
	  """)
    )
    vizCounter = vizCounter + 1
  }
}


 
import scala.util.Random

case class Person(name: String) {
  override def toString: String = name
  def likes(other: Person): Likes = Likes(this, other, true)
  def dislikes(other: Person): Likes = Likes(this, other, false)
}

case class Likes(a: Person, b: Person, likes: Boolean) {
  def isAbout(pair: Set[Person]): Boolean = {
	require(pair.size == 2, "pair in Likes.isAbout does not contain exactly 2 persons")
	a == pair.head && b == pair.tail.head ||
	a == pair.tail.head && b == pair.head
  }
  override def toString: String = if(likes) s"$a likes $b" else s"$a dislikes $b"
}


case object Person {
    private val names: Set[String] = Set("Nettie","Lester","Brian","Cody","Erik","William","Molly","Joey","Thelma","Edgar","Emanuel","Sergio","Herman","Kelley","Wilfred","Guadalupe","Paula","Sheila","Javier","Kelly","Jason","Gilbert","Harriet","Meghan","Kenneth","Holly","Rose","Lela","Brenda","Constance","Vera","Ramiro","Diana","Charlene","Betty","Michelle","Frederick","Elmer","Byron","Randal","Roderick","Clark","Mathew","Sammy","Colleen","Marian","Tyrone","Keith","Tonya","John","Kayla","Johanna","Dwayne","Antonia","Kerry","Fannie","Nichole","Jeanne","Roberto","Vicky","Jesus","Angela","Fredrick","Fernando","Vivian","Natalie","Johnnie","Monica","Angelica","Anna","Carlos","Marion","Henry","Lawrence","Alexis","Garry","Bernard","Jana","Ernestine","Deborah","Willard","Eileen","Erica","Elvira","Myron","Elena","Ervin","Jeannette","Veronica","Abraham","Lamar","Wanda","Lorraine","Doris","Leigh","Devin","Lindsay","Isabel","Marlene","Betsy")

    def random: Person = Person(names.random.getOrElse("Easter Bunny"))
    
    // Returns a set of k random persons.
    def randomGroup(size: Int): Set[Person] = {
        def rg(size: Int, namesLeft: Set[String]): Set[Person] = {
            if(size == 0) Set.empty
            else {
                val newPerson = namesLeft.random
                if(newPerson.isEmpty) Set.empty
                else rg(size - 1, namesLeft - newPerson.get) + Person(newPerson.get)
            }
        }
        
        rg(size, names)
    }
	
	implicit class ImplPersons(persons: Set[Person]) {
		def deriveLikeFunction(partialLikes: Set[Likes]): (Person, Person) => Boolean = {
			//require(persons.uniquePairs.forall(pair => partialLikes.find(like => like.a == pair._1 && like.b == pair._2) == partialLikes.find(like => like.a == pair._2 && like.b == pair._1)), s"partialLikes contains asymmetric like relations")
		
			val completeLike: Map[Set[Person], Boolean] = persons.unorderedUniquePairs
				.map(pair => {
					val likeOption: Option[Likes] = partialLikes.find(_.isAbout(pair))
					
					if(likeOption.isDefined)
						pair -> likeOption.get.likes
					else
						pair -> false
				}).toMap

			def like(a: Person, b: Person): Boolean = {
				if(completeLike.contains(Set(a,b))) completeLike(Set(a,b))
				else false
			}

			like
		}
		
		def randomLikeFunction(probability: Double = 0.5): (Person, Person) => Boolean = {
			require(probability >=0 && probability <= 1, "Probability must range from 0 and 1.")
			
			val completeLike: Map[Set[Person], Boolean] = persons.unorderedUniquePairs
				.map(_ -> (Random.nextDouble <= probability)).toMap
			
			def like(a: Person, b: Person): Boolean = {
				if(completeLike.contains(Set(a,b))) completeLike(Set(a,b))
				else false
			}
			
			like 
		}
		
		def toDotString(like: (Person, Person) => Boolean): String = {
			"graph people {\\n" +
			"size=\"7,7\";\\n" +
			"ratio=compress;\\n" +
			"node [shape = circle];\\n" +
			persons.unorderedUniquePairs.map(pair => {
			  if(like(pair.head, pair.tail.head)) s"${pair.head} -- ${pair.tail.head} [style=dashed];"
			  else s"${pair.head} -- ${pair.tail.head} [style=solid];"
			}).mkString("\\n")+
			"}"
		}
		
		def toDotString(personsLiked: Set[Person], personsDisliked: Set[Person], like: (Person, Person) => Boolean): String = {
			"graph people {\\n" +
			"size=\"7,7\";\\n" +
			"ratio=compress;\\n" +
			"node [shape=circle,style=filled,fillcolor=darkolivegreen1];\\n" +
			personsLiked.mkString("",",",";\\n") +
			"node [shape=circle,style=filled,fillcolor=lightcoral];\\n" +
			personsDisliked.mkString("",",",";\\n") +
			persons.unorderedUniquePairs.map(pair => {
			  if(like(pair.head, pair.tail.head))
				s"${pair.head} -- ${pair.tail.head} [style=dashed];"
			  else 
				s"${pair.head} -- ${pair.tail.head} [style=solid];"
			}).mkString("\\n")+
			"}"
		}
	}
}

case object SelectingInvitees {
	def si4(persons: Set[Person],
        personsLiked: Set[Person],
        personsDisliked: Set[Person],
        like: (Person, Person) => Boolean,
        k: Int): Set[Person] = {

    // Input must satisfy these constraints, or program halts.
    require(personsLiked <= persons, "personsLiked must be a subset of persons")
    require(personsDisliked <= persons, "personsDisliked must be a subset of persons")
    require(personsLiked /\ personsDisliked == Set.empty, "intersection between personsLiked and personsDisliked must be emtpy")
    require(personsLiked \/ personsDisliked == persons, "union of personsLiked and personsLiked must equal persons")

    // Specify that invitees is valid if |G /\ D| <= k.
    def atMostKDislikes(invitees: Set[Person]): Boolean = 
        (invitees /\ personsDisliked).size <= k
    
    // Specify the optimality condition.
    def xg(invitees: Set[Person]): Int = {
        val x = invitees.uniquePairs // From all pairs of invitees,
                .build(like.tupled)  // select all pairs that like each other,
                .size                // and count them.
        val g = invitees.size        // Count the number of total invitees.
        x + g
    }
    
    val invitees = powerset(persons)  // From all possible subsets of persons,
        .build(atMostKDislikes)       // select subsets that contain at most k disliked persons,
        .argMax(xg)                   // and select the subsets that maximize the optimality condition.
    
    // If more than one solution exists, return one at random. Always 1 solution must exist,
    // because the empty set is a valid solution. Hence, we can assume random does not
    // return None and 'get' the value.
    invitees.random.get 
}
	
	def si5(persons: Set[Person],
        personsLiked: Set[Person],
        personsDisliked: Set[Person],
        like: (Person, Person) => Boolean): Set[Person] = {
		
    // Input must satisfy these constraints, or program halts.
    require(personsLiked <= persons, "personsLiked must be a subset of persons")
    require(personsDisliked <= persons, "personsDisliked must be a subset of persons")
    require(personsLiked /\ personsDisliked == Set.empty, "intersection between personsLiked and personsDisliked must be emtpy")
    require(personsLiked \/ personsDisliked == persons, "union of personsLiked and personsLiked")
	
    // Specify the optimality condition.
    def gl_x_g(invitees: Set[Person]): Int = {
        val gl = (invitees /\ personsLiked)
    	         .size                // Count the invitees the host likes.
        val x  = invitees.uniquePairs // From all pairs of invitees,
                 .build(like.tupled)  // select all pairs that like each other,
                 .size                // and count them.
        val g  = invitees.size        // Count the number of total invitees.
        gl + x + g
    }

    val invitees = powerset(persons)  // From all possible subsets of persons,
        .argMax(gl_x_g)               // select those that maximize |G/\L| + |X| + |G|
    
    // If more than one solution exists, return one at random. Always 1 solution must exist,
    // because the empty set is a valid solution. Hence, we can assume random does not
    // return None and 'get' the value.
    invitees.random.get 
}
	
	def si6(persons: Set[Person],
        personsLiked: Set[Person],
        personsDisliked: Set[Person],
        like: (Person, Person) => Boolean,
        k: Int): Set[Person] = {
    
    // Input must satisfy these constraints, or program halts.
    require(personsLiked <= persons, "personsLiked must be a subset of persons")
    require(personsDisliked <= persons, "personsDisliked must be a subset of persons")
    require(personsLiked /\ personsDisliked == Set.empty, "intersection between personsLiked and personsDisliked must be emtpy")
    require(personsLiked \/ personsDisliked == persons, "union of personsLiked and personsLiked")

	// Specify that invitees is valid if |Y| <= k.
    def atMostKPairDislikes(invitees: Set[Person]): Boolean = 
      { invitees.uniquePairs | like.tupled }.size <= k
		
    // Specify the optimality condition.
    def gl_g(invitees: Set[Person]): Int = {
        val gl = (invitees /\ personsLiked)
    	         .size                // Count the invitees the host likes.
        val g  = invitees.size        // Count the number of total invitees.
        gl + g
    }

    val invitees = { powerset(persons) | atMostKPairDislikes _ }
                   .argMax(gl_g)
    
    // If more than one solution exists, return one at random. Always 1 solution must exist,
    // because the empty set is a valid solution. Hence, we can assume random does not
    // return None and 'get' the value.
    invitees.random.get 
}
}

import Person._ 
import scalatags.JsDom.all._

case object Plotly {

  var plotCounter = 0

  def render(plotJson: String): Unit = {
    Fiddle.print(
	  div(id:=s"plot$plotCounter"),
	  script(s"""
  	    var script = document.createElement('script');
	    script.onload = function () {
		  requirejs.config({
		    baseUrl: 'https://cdn.jsdelivr.net/npm/',
		    paths: {
			  "plotly": "plotly.js@1.58.4/dist/plotly.min.js?noext"
		    }
		  });

	    require(["plotly"], function(plotly) {
		  const figure = JSON.parse('${plotJson.filter(_ >= ' ')}');
		  plotly.newPlot('plot$plotCounter', figure.data, figure.layout).catch(console.warn);
	    });
	  };

	  script.src = "https://requirejs.org/docs/release/2.3.6/minified/require.js";
	  document.head.appendChild(script);
	  """)
    )
    plotCounter = plotCounter + 1
  }
} 
val result = {
////
}
if(!result.isInstanceOf[Unit]) println(result)