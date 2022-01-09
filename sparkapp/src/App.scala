import org.apache.spark.sql.SparkSession

import io.getquill.QuillSparkContext
import io.getquill.QuillSparkContext._

case class PersonCount(firstName: String, count: Long)

case class Person(firstName: String, age: Int)

object App {

  def execute(spark: SparkSession) = {
    import spark.implicits._

    val persons = liftQuery(
      spark.createDataset(
        List(Person("John", 18), Person("Philip", 30), Person("John", 45))
      )
    )

    val q = quote {
      persons.groupBy(_.firstName).map { case (id, cl) =>
        PersonCount(id, cl.size)
      }
    }

    implicit val sqlContext = spark.sqlContext
    val r = QuillSparkContext.run(q)
    r.show

  }

  def main(args: Array[String]) = {

    val spark =
      SparkSession
        .builder()
        .config(
          "spark.sql.shuffle.partitions",
          2
        ) // Default shuffle partitions is 200, too much for tests
        .config("spark.ui.enabled", "false")
        .appName("spark example")
        .getOrCreate()

    execute(spark)

  }
}
