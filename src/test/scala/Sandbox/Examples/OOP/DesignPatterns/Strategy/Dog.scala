package Sandbox.Examples.OOP.DesignPatterns.Strategy

class Dog extends Animal {
  val PerformCrawling: Crawable = new CrawlBand()
  val PerformDrinking: Drinkable = new DrinkTongue()

  PerformCrawling.crawl()
  PerformDrinking.drink()






}
