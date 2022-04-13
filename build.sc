import mill._, scalalib._

import mill.modules.Assembly
import coursier.maven.MavenRepository


object sparkapp extends ScalaModule {

  def scalaVersion = "2.12.15"

  def ivyDeps = Agg(
    ivy"dev.zio::zio::2.0.0-RC2",
    ivy"io.getquill::quill-spark:3.17.0-RC2",
    ivy"org.apache.spark::spark-sql:3.2.0",
  )

  override def mainClass = T { Some("App") }

  def forkArgs = Seq("-Dspark.master=local[*]")

}
