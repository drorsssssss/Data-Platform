package Sandbox.Examples.ExtAPIS

object AdapterGoogleAPI extends AdapterPayments {

  val googleapi:GoogleAPI = new GoogleAPI()

  override def getPaymentsDataByDay(path: String, day: String): String = {
    return googleapi.getGoogleData(path,day)

  }



}
