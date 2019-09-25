package Sandbox.FPScala.Examples



class TreeNode(data:Option[Int],left: Option[TreeNode], right:Option[TreeNode]){

  var _data:Option[Int]=data
  var _left:Option[TreeNode]=left
  var _right:Option[TreeNode]=right


  def this()=this(None,None,None)
  def this(data:Option[Int]) = this(data,None,None)
}

class BST{
  var head:TreeNode=new TreeNode()


  def insertNode(root:TreeNode=head,data:Int):Unit={

    if (root._data == None) {
        head=new TreeNode(Some(data))

      }
    else {

      if (data <= root._data.get) {
        if (root._left != None) (insertNode(root._left.get, data))
        else root._left = Some(new TreeNode(Some(data)))

      }
      else {
        if (root._right != None) {
          insertNode(root._right.get, data)
        }
        else root._right = Some(new TreeNode(Some(data)))

      }
    }

  }



}



object BST {

  def main(args: Array[String]): Unit = {

//    val n1 = new TreeNode(5)
    ////    val n2 = new TreeNode(4)
    ////    val n3 = new TreeNode(4,n1,n2)
    ////    println(n3._data)

    val tr = new BST()
    tr.insertNode(data=8)
    tr.insertNode(data=3)
    tr.insertNode(data=15)
    println("test")
  }

}

