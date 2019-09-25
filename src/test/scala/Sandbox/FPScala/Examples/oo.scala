package Sandbox.FPScala.Examples

trait behaviour {

  def mood(): String
  def predictMood():String

}

class Person(name:String,age:Int) {
  val _name=name
  val _age=age

  def hiFive(): Unit ={
    println("Hello")
  }

}

class FireMan(name:String,age:Int) extends Person(name,age) with behaviour {
  override def hiFive() = {println("Stam Fire guy")}

  override def mood(): String = {if (this._age>20) return "old" else return "young"}

  override def predictMood(): String = {if (this._name == "dror") return "shalom leha" else return "mi ata"}

}


object app{
  def main(args: Array[String]): Unit = {
    val fm = new FireMan("dror",30)
    fm.hiFive()
    println(fm.mood())
    println(fm.predictMood())
  }



}