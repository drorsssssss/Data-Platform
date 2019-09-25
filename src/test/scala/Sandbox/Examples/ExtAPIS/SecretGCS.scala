package Sandbox.Examples.ExtAPIS

class SecretGCS extends Securable {
  override def getCreds(): String = {
    println("Google Cloud Storage Creds")
    return "Google Cloud Storage Creds"

  }

}
