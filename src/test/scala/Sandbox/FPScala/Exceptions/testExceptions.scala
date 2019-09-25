package Sandbox.FPScala.Exceptions





object testExceptions {

  def test (x : Int)(y:String) : (Int,String) = {
    (x,y)
  }

  def mean(seq:Seq[Int]): Option[Int] = {
    seq.isEmpty match {
      case true => None
      case _ => Some (seq.sum / seq.length)
    }

  }


  def main(args: Array[String]): Unit = {
   val num = 5
   val name = "dror"
   val seq:Seq[Int] = Seq()
   //println(test(num)(name))

    println(mean(seq))


  }


}
