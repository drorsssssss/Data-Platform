package Sandbox.Examples.ExtAPIS

class SecretMySQL extends Securable {

  override def getCreds(): String ={
    println("Mysql Creds")
    return "Mysql Creds"
    }


}
