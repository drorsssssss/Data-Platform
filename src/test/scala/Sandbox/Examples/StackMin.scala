package Sandbox.Examples

class StackMin[A] extends Stack[A]{
  var topMin:Node[A] = Node()

  def push(element:A) (implicit ord: Ordering[A])={
    super.push(element)
    import ord.mkOrderingOps
    if (topMin == None || (element < topMin.data.getOrElse(Integer.MAX_VALUE).asInstanceOf[A])) {
      var n = Node(Some(element))
      n.next=topMin
      topMin=n


    }

  }


}

object t{
  def main(args: Array[String]): Unit = {
    var st = new StackMin[Int]
    st.push(5)
    st.push(10)
    st.push(1)
    print(1)

  }
}
