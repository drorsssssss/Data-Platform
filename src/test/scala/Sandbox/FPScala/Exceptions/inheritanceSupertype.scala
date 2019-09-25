package Sandbox.FPScala.Exceptions

abstract class Animal{
  def name:String
}

abstract class Pet extends Animal{
}

class Cat extends Pet{
  override def name: String = "cat"

}

class Dog extends Animal{
  override def name: String = "Dog"

}

class petContainer[P <: Pet](p: P){
  def pet:P = p

}

object test {

  def seekEmployee(name:String,EmplyeeList:Seq[String]):Option[Boolean]={
    Some(EmplyeeList.contains(name))
  }

  def mean(seq:Seq[Double]):Option[Double]= Some(seq.sum / seq.length)

  def variance(xs:Seq[Double]):Option[Double]={
    mean(xs).flatMap(m=>mean(xs.map((x)=>math.pow(x-m,2))))

  }
  def test[A,B](a:Option[A],b:Option[B]):Option[B]={
    for{
      aa<-a
      bb<-b
    }yield bb

  }


  def main(args: Array[String]): Unit = {
    val cat = new petContainer[Cat](new Cat)
    //val dog = new petContainer[Dog](new Dog)

    val sq = Seq("dror","sivan","nir")
    //println(seekEmployee("nir",sq))
    val sq2 = Seq(1.0,2.0,3.0)

    println(variance(sq2))

    println(test[String,String](Some("5"),Some("6")))

  }
}


