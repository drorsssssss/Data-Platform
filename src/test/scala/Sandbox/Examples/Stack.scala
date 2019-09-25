package Sandbox.Examples

class Stack[A] {
  case class Node[A](var data:Option[A]=None,var next:Node[A]=null)
  var top:Node[A]=null

  def pop():Node[A] = {
    val poped:Node[A] = top
    top=top.next

    return poped
  }

  def push(element:A) = {
    var n:Node[A] = Node[A](Some(element))
    n.next=top
    top=n
  }
  def peek():Node[A] = {
    return top
  }
  def isEmpty():Boolean = {
    return top==null
  }
}

object test{
  def main(args: Array[String]): Unit = {
    var st = new Stack[Int]
    st.push(5)
    st.push(6)
    st.push(7)
    println(st)



  }



}
