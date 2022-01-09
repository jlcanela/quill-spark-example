import org.apache.spark.sql.SparkSession

import io.getquill.QuillSparkContext._

case class Person(firstName: String, age: Int)
case class Collaborator(firstName: String, age: Int, company: String)

object App {
  def main(args: Array[String]) = {
    println("hello world !")
    val sparkSession =
      SparkSession
        .builder()
        .config(
          "spark.sql.shuffle.partitions",
          2
        ) // Default shuffle partitions is 200, too much for tests
        .config("spark.ui.enabled", "false")
        .master("local[1]")
        .appName("spark test")
        .getOrCreate()

    sparkSession.sparkContext.setLogLevel("WARN")

    implicit val sqlContext = sparkSession.sqlContext

    import io.getquill.QuillSparkContext
    val context = QuillSparkContext

    import sparkSession.implicits._

    val persons = liftQuery(sparkSession.createDataset(List(Person("John", 18), Person("Philip", 30), Person("John", 45))))
    
    val q = quote {
        persons.groupBy(_.firstName).map { case (id, cl) => (id, cl.size)}
    }

    val q2 = quote {
        persons.map( p => Collaborator(p.firstName, p.age + 1, "comp"))
    }
    
    val r = QuillSparkContext.run(q2)
    r.explain(true)
   // println(r.)

  }
}
