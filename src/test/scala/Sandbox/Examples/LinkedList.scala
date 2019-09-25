package Sandbox.Examples


class LinkedList {

  case class Node(var data:String, var next:Node=null)
  var head:Node=null

  def insert(data:String) = {
    val n = Node(data)
    n.next=head
    head=n
  }

  def printLinkedList() = {
    var start = head
    print("Head ")
    while (start !=null) {
      print(s" -> ${start.data}")
      start=start.next
    }
    print("-> Null")
  }

  def removeMiddle(n:Node): Boolean ={
    if (n == null || n.next == null) return false
    val nn = n.next
    n.data=nn.data
    n.next = nn.next
    return true

  }

  def searchNode(data:String):Node = {
    if (head.data == data) return head
    var start=head
    while (start != null) {
      if (start.data == data) return start
      start=start.next

    }
    return null

  }

}

object LinkedList{

  def main(args: Array[String]): Unit = {
    val ll = new LinkedList()
    ll.insert("a")
    ll.insert("b")
    ll.insert("c")
    ll.insert("d")
    ll.printLinkedList()
    println()
    ll.removeMiddle(ll.searchNode("b"))
    ll.printLinkedList()
  }

}

