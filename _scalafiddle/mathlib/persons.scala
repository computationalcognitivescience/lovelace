case class Person(name: String) {
  override def toString: String = name
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
}