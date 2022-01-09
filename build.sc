import mill._, scalalib._

import mill.modules.Assembly
import coursier.maven.MavenRepository

object Deps  {
    val ZIO_V1 = "1.0.13"
}

object sparkapp extends ScalaModule {

  import Deps._ 

  def scalaVersion = "2.12.15"

  def ivyDeps = Agg(
    ivy"dev.zio::zio::${ZIO_V1}",
    ivy"io.getquill::quill-spark:3.12.0",
    ivy"org.apache.spark::spark-sql:3.2.0",
  )

  override def mainClass = T { Some("App") }

  def forkArgs = Seq("-Dspark.master=local[*]")

}
