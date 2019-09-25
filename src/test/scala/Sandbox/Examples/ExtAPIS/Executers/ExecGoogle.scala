package Sandbox.Examples.ExtAPIS.Executers
import Sandbox.Examples.ExtAPIS._


object ExecGoogle {
  def main(args: Array[String]): Unit = {
    val googlePaymentsData:String = AdapterGoogleAPI.getPaymentsDataByDay("localFS","2019-07-17")
    println(googlePaymentsData)
  }

}
