import mill._, scalalib._

import mill.modules.Assembly
import coursier.maven.MavenRepository

object Deps  {
    val ZIO_V1 = "1.0.13"
}

object sparkapp extends ScalaModule {

  import Deps._ 

  val a= 1
  def scalaVersion = "2.12.15"

  /*def repositoriesTask = T.task { super.repositoriesTask() ++ Seq(
    MavenRepository("https://oss.sonatype.org/content/repositories/snapshots")) 
  }*/
//https://oss.sonatype.org/content/repositories/snapshots/dev/zio/zio-prelude/1.0.0-RC8+50-9f687f11-SNAPSHOT/zio-zio-prelude/1.0.0-RC8+50-9f687f11-SNAPSHOT.pom
//https://oss.sonatype.org/content/repositories/snapshots/dev/zio/zio-prelude_3/1.0.0-RC8+50-9f687f11-SNAPSHOT/zio-prelude_3-1.0.0-RC8+50-9f687f11-SNAPSHOT.pom
  def ivyDeps = Agg(
    ivy"dev.zio::zio::${ZIO_V1}",
    ivy"io.getquill::quill-spark:3.12.0",
    ivy"org.apache.spark::spark-sql:3.2.0",
  //  ivy"dev.zio::zio-json::0.3.0-RC1-1",
 //   ivy"dev.zio::zio-cache:0.2.0-RC1",
 //   ivy"dev.zio::zio-schema:0.1.5",
  // Required for automatic generic derivation of schemas
  //  ivy"dev.zio::zio-schema-derivation:0.1.5"
//    ivy"org.scala-lang:scala-reflect:${scalaVersion.value},
    
    //ivy"dev.zio::zio-prelude:1.0.0-RC6",
   // ivy"dev:zio::zio-prelude::1.0.0-RC8+50-9f687f11-SNAPSHOT"
//    https://oss.sonatype.org/content/repositories/snapshots/dev/zio/zio-prelude_3/1.0.0-RC8+50-9f687f11-SNAPSHOT/
  //  https://oss.sonatype.org/content/repositories/snapshots/dev/zio/zio-prelude_3/1.0.0-RC8+50-9f687f11-SNAPSHOT/
  )

//    def unmanagedClasspath = T {
//     if (!os.exists(millSourcePath / "lib")) Agg()
//     else Agg.from(os.list(millSourcePath / "lib").map(PathRef(_)))
//   }
  override def mainClass = T { Some("App") }


}
