package Sandbox.Examples

abstract class Person(name:String) {

  protected[this] def getAge():Int = {return 20}

}

class FireMan(name:String,level:Int) extends Person(name){
  def getLevel():Int = {return level}

  def increaseAge():Int = {
    return super.getAge() + 10

  }

}

object tt{
  def main(args: Array[String]): Unit = {
    var x = new FireMan("dror",10)
    println(x.getLevel())
    println(x.increaseAge())

  }


}
