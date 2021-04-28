import scala.util.Random

case class Person(name: String) {
  override def toString: String = name
  def likes(other: Person): Likes = Likes(this, other, true)
  def dislikes(other: Person): Likes = Likes(this, other, false)
}

case class Likes(a: Person, b: Person, likes: Boolean) {
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
			val completeLike: Map[(Person, Person), Boolean] = (persons x persons).map(pair => {
					val likeOption: Option[Likes] = partialLikes.find(like => like.a == pair._1 && like.b == pair._2)
					if(likeOption.isDefined) pair -> likeOption.get.likes
					else pair -> false
			}).toMap

			def like(a: Person, b: Person): Boolean = {
				if(completeLike.contains((a,b))) completeLike((a,b))
				else false
			}

			like
		}
		
		def randomLikeFunction(probability: Double = 0.5): (Person, Person) => Boolean = {
			require(probability >=0 && probability <= 1, "Probability must range from 0 and 1.")
			
			val completeLike: Map[(Person, Person), Boolean] = (persons x persons).map(_ -> (Random.nextDouble <= probability)).toMap
			
			def like(a: Person, b: Person): Boolean = {
				if(completeLike.contains((a,b))) completeLike((a,b))
				else false
			}
			
			like 
		}
	}
}

case object SelectingInvitees {
	def si4(persons: Set[Person],
        personsLiked: Set[Person],
        personsDisliked: Set[Person],
        like: (Person, Person) => Boolean,
        k: Int): Set[Person] = {

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
}

import Person._