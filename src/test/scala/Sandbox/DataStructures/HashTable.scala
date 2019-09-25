package Sandbox.DataStructures


class HashTable {
  case class kv(var key: String, var value: String)

  private val DEFAULT_SIZE=4
  private var arr = new Array[kv](DEFAULT_SIZE)

  Range(0,arr.size).foreach(m=>arr(m)=kv(null,null))

  def hashingFunc(key:String):Int = { return calcAsciiWord(key) % arr.size}

  def insert(key:String,value:String) = {
    var index:Int = hashingFunc(key)
    var i:Int=0
    while(arr(index).key != null && arr(index).key != key && i<= arr.size){
      index=(index+i) % arr.size
      i+=1
    }
    arr(index).key=key
    arr(index).value=value

    arr.foreach(println)

  }
  def get(key:String) = {
    var index:Int = hashingFunc(key)
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

  def calcAsciiWord(str:String):Int = {return str.map(_.toInt).reduce(_+_)}




}

object HashTable{
  def main(args: Array[String]): Unit = {
    var x = new HashTable()
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
  }

}
