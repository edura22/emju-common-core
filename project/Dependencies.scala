import sbt._
import Keys._
import _root_.play.sbt.Play.autoImport._

object Dependencies {

  object cassandra {
    val all  		= "org.apache.cassandra" % "cassandra-all" % "2.2.1"
    val core  		= "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.7.1"
    val mapping    	= "com.datastax.cassandra" % "cassandra-driver-mapping" % "2.1.7.1"
    val test    	= "org.cassandraunit" % "cassandra-unit-shaded" % "2.1.9.2" % "test"
    val yaml		= "org.yaml" % "snakeyaml" % "1.15" % "test"
    val hector		= "org.hectorclient" % "hector-core" % "2.0-0" % "test"
  }
  
  object aspect {
    val aspectjrt	= "org.aspectj" % "aspectjrt" % "1.7.3"
    val aspectjweaver	= "org.aspectj" % "aspectjweaver" % "1.7.3"
  }

  val json		= "com.fasterxml.jackson.core" % "jackson-core" % "2.6.1"
  val slf4j		= "org.slf4j" % "slf4j-api" % "1.7.5"	
  val mockito		= "org.mockito" % "mockito-core" % "1.10.19" % "test"
  var hamcrest 		= "org.hamcrest" % "hamcrest-all" % "1.3" % "test"		
  val test		= "com.lordofthejars" % "nosqlunit-cassandra" % "0.8.1" % "test"
  val gson 		= "com.google.code.gson" % "gson" % "2.3.1"
  val rxjava = "io.reactivex" % "rxjava" % "1.1.0"

  val playDependencies: Seq[ModuleID] = Seq(
    cassandra.test,
    cassandra.all,
    cassandra.core,
    cassandra.mapping,
    cassandra.hector,
    slf4j,
    mockito,
    hamcrest,
    test,
    filters,
    gson,
    rxjava
  )

  val serviceDependencies: Seq[ModuleID] = playDependencies ++ Seq(
    javaWs,
    json,
    slf4j
  )

  val webDependencies: Seq[ModuleID] = playDependencies ++ serviceDependencies

}
