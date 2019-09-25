package Sandbox.Examples.OOP.DesignPatterns.Strategy

class Bird extends Animal{
   val PerformDrinking: Drinkable = new DrinkNose()
   val PerformCrawling: Crawable = new NoCrawl()

   PerformCrawling.crawl()
   PerformDrinking.drink()





}
