package Sandbox.Examples.OOP.DesignPatterns.Strategy

object Context {


  def main(args: Array[String]): Unit = {
    var context:Animal = new Bird()

    context.PerformCrawling
    context.PerformDrinking



  }

}
