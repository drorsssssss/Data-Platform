package Sandbox.Examples

import scala.collection.mutable.ListBuffer

class Examples {
  def URLify(input:String):String = {
    val inputs = input.split(",")
    val inputt = inputs(0)
    val limit = inputs(1).toInt

    var i=0
    var ls = new ListBuffer[String]()

    while (i<limit){
      if (inputt(i)==' '){ ls.append("%20")}
      else {ls.append(inputt(i).toString)}
      i+=1
    }

    return ls.mkString("")

  }



}

object Examples{
  def main(args: Array[String]): Unit = {
    val z = new Examples()

    println(z.URLify("mr john smith   ,13"))
  }



}
