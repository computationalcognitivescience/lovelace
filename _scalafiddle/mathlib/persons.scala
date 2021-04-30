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