package Sandbox.Examples.ExtAPIS

object FactorySecret {

  def apply(source:String) = {
    source match  {
      case "mysql" => new SecretMySQL
      case "fs" => new SecretFS
      case "gcs" => new SecretGCS

    }

  }


}
