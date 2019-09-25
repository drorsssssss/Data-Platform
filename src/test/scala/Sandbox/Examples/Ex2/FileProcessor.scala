package Sandbox.Examples.Ex2

import scala.collection.mutable
import scala.io.Source._

class FileProcessor(path:String) {
  case class Row(id:Int,name:String,country:String)

  def readIntoList():List[String] = {
    val lines = fromFile(path).getLines().toList
    return lines
  }

  def readIntoTuples() = {
      fromFile(path).getLines().map(row=>{
        val lst = row.split(",")
        val mp = mutable.HashMap[String,Tuple2[String,String]](lst(0),("a","b"))


      })



  }



}


object main{
  def main(args: Array[String]): Unit = {
    val fp = new FileProcessor("/Users/dsivan/GitRepos/Data-Platform/src/test/scala/Sandbox/Examples/Ex2/sample.csv")
//    val file_list = fp.readIntoList()
//    println(file_list)
    fp.readIntoTuples()
  }

}
