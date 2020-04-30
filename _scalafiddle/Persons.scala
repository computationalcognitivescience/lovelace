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

case class Person(name: String) {
  def likes(other: Person): Relation = Relation(this, other, true)
  def dislikes(other: Person): Relation = Relation(this, other, false)
}

case object Person {
    val names = List("Nettie Baldwin","Lester Peters","Brian Estrada","Cody Brock","Erik Mccarthy","William Bowers","Molly Fernandez","Joey Chapman","Thelma Casey","Edgar Miller","Emanuel Bass","Sergio Warner","Herman Rodriquez","Kelley Taylor","Wilfred Matthews","Guadalupe Morrison","Paula Snyder","Sheila Mendez","Javier Curtis","Kelly Harrington","Jason Hughes","Gilbert Dixon","Harriet Price","Meghan Santiago","Kenneth Clayton","Holly Sanchez","Rose Frazier","Lela Kennedy","Brenda Lamb","Constance Perkins","Vera George","Ramiro Sutton","Diana Holland","Charlene Wallace","Betty Mcgee","Michelle Hamilton","Frederick Mathis","Elmer Mckenzie","Byron Christensen","Randal Ward","Roderick Rhodes","Clark Marsh","Mathew Mendoza","Sammy Tran","Colleen Harvey","Marian Bailey","Tyrone Klein","Keith Stanley","Tonya Byrd","John Parsons","Kayla Aguilar","Johanna Stevenson","Dwayne Long","Antonia Curry","Kerry Perry","Fannie Hammond","Nichole Strickland","Jeanne Salazar","Roberto Barber","Vicky Oliver","Jesus Brady","Angela Shaw","Fredrick Munoz","Fernando Payne","Vivian Garcia","Natalie Guerrero","Johnnie Bennett","Monica Thompson","Angelica Armstrong","Anna Holt","Carlos Kelley","Marion Welch","Henry Fields","Lawrence Craig","Alexis Beck","Garry Mills","Bernard Parks","Jana Kim","Ernestine Rios","Deborah Phillips","Willard Cox","Eileen Knight","Erica Anderson","Elvira Steele","Myron Robertson","Elena Mckinney","Ervin Owens","Jeannette Pierce","Veronica Gross","Abraham Harrison","Lamar Thornton","Wanda Richardson","Lorraine Tate","Doris Foster","Leigh Henry","Devin Keller","Lindsay Moss","Isabel Stone","Marlene Hudson","Betsy Day")
    def random: Person = Person(names(Random.nextInt(names.length)))
    def randomSet(size: Int): Set[Person] = List.tabulate(size)(_ => Person.random).toSet
}

case class Relation(a: Person, b: Person, likes: Boolean) {
    def canEqual(a: Any) = a.isInstanceOf[Relation]

    override def equals(that: Any): Boolean = that match {
        case that: Relation => {
            this.likes == that.likes && (this.a == that.a && this.b == that.b || this.a == that.b && this.b == that.a)
        }
        case _ => false
    }
}

object Helpers {
  implicit class ImplRelation(personA: String) {
    def likes(personB: String): Relation = Relation(Person(personA), Person(personB), true)

    def dislikes(personB: String): Relation = Relation(Person(personA), Person(personB), false)
  }
}


////
