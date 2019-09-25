package Sandbox.FPScala.Laziness

object Laziness {

  def main(args: Array[String]): Unit = {
    val lst = List(1,2,3)

    val res = lst.foldLeft(0)(_+_)
    println(res)

    val lst2 = List(10,11,12)

    println(lst.zip(lst2))
    var x=1
    if2(1==1,x=5,println("a"))
    println(x)
    println("begin")
    lazy val k = {
      Thread.sleep(5000)
      println("finish")
      5
    }

    println(k)
    println(k)






  }
  def if2[A](cond: Boolean, onTrue: =>A,onFalse: =>A): Unit = {
    if(cond) onTrue else onFalse

  }


}
