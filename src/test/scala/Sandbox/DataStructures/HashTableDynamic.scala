package Sandbox.DataStructures

import Array._


class HashTableDynamic {
  case class kv(var key: String, var value: String)

  private val DEFAULT_SIZE=4
  private var arr = fill[kv](DEFAULT_SIZE)(kv(null,null))
  private var numInputElements=0




  def hashingFunc(arr:Array[kv],key:String):Int = { return calcAsciiWord(key) % arr.size}

  def insert(key:String,value:String) = {

    if (calcLoadFactor()>=0.75) {resize()}

    var index:Int = hashingFunc(arr,key)
    var i:Int=0
    while(arr(index).key != null && arr(index).key != key && i<= arr.size){
      index=(index+i) % arr.size
      i+=1
    }
    arr(index).key=key
    arr(index).value=value


    arr.foreach(println)
    numInputElements+=1

  }
  def get(key:String) = {
    var index:Int = hashingFunc(arr,key)
    var i:Int = 0
    while(arr(index).key != null && arr(index).key != key && i<= arr.size){
      index=(index+i) % arr.size
      i+=1
    }
    arr(index).key match {
      case key => arr(index).value
      case _ => null

    }

  }

  def resize(): Unit ={
    var new_arr = fill[kv](2*arr.size)(kv(null,null))
    for (i<-0 until arr.size) {
        if (arr(i).key != null) {
          var new_index = hashingFunc(new_arr, arr(i).key)
          var j: Int = 0
          while (new_arr(new_index).key != arr(i) && new_arr(new_index).key != null && j <= new_arr.size) {
            new_index = (new_index + j) % new_arr.size
            j += 1

          }
          new_arr(new_index).key = arr(i).key
          new_arr(new_index).value = arr(i).value
        }

    }

    arr=new_arr

  }

  def calcAsciiWord(str:String):Int = {return str.map(_.toInt).reduce(_+_)}
  def calcLoadFactor():Double = {return 1.0*numInputElements/arr.size}


}

object HashTableDynamic{
  def main(args: Array[String]): Unit = {
    var x = new HashTableDynamic()
    x.insert("1","dror")
    println("")
    x.insert("a","sivan")
    println("")
    x.insert("t","shalom")
    println("")
    x.insert("10","mango")
    println("")
    x.insert("0","22")
    println("")
    println(s"Got the value: ${x.get("1")} ")
    println(s"Got the value: ${x.get("0")} ")
  }

}
