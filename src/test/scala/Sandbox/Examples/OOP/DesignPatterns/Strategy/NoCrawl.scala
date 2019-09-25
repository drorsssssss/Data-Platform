package Sandbox.Examples.OOP.DesignPatterns.Strategy

class NoCrawl extends Crawable {
  override def crawl(): Unit = println("I can't crawl!")

}
