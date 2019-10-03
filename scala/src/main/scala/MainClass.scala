import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
//import io.circe._
//import io.circe.parser._
// import io.circe._
// import io.circe.parser._
//import cats.syntax.either._
//import spark.implicits._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.log4j._
import org.apache.spark.sql.SparkSession

object MainClass{
  def main(args: Array[String]){
    val conf = new SparkConf().setAppName("Sample App")
    conf.setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val mySpark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    import mySpark.implicits._

    val inputPath = "/Users/V/Desktop/scala/src/main/scala/AA_wiki_00"

    val readExample = mySpark.read.option("multiLine", true)
      .option("mode", "PERMISSIVE")
      .json(inputPath)

    readExample.printSchema()
    readExample.createOrReplaceTempView("Files")
    val data=mySpark.sql("select * from Files")
    data.show()

    /*val df = sqlContext.read.json(inputPath)
    df.printSchema()
    df.registerTempTable("JSONdata")
    val data=sqlContext.sql("select title from JSONdata")
    c
    sc.stop*/

    //val inputPath = args(0)
    //val rawJson: String = """{"foo": "bar","baz": 123,"list of stuff": [ 4, 5, 6 ]}"""
    //val parseResult = parse(rawJson).getOrElse(Json.Null)
    //println(parseResult)
  }
}
