package Sandbox.Examples.ExtAPIS

class GoogleAPI {

  //TODO Pass factorysecret source from config
  val creds:String = FactorySecret("mysql").getCreds()

  def getGoogleToken(creds:String):String = {
    println("Get TOKEN using creds")
    return creds+"000"
  }
  def getGoogleData(path:String,day:String):String = {
    val res1 = getGoogleToken(creds)
    println(s"Perform request after got token ${res1}")
    return res1+"SUCCESS"+"-"+path+"-"+day
  }


}
