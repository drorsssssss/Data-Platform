package Sandbox.Examples.ExtAPIS

class SecretFS extends Securable {
  override def getCreds(): String = {
    println("File System Creds")
    return "File System Creds"

  }

}
