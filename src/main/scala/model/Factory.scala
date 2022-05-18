package model

trait Factory() {
    
    val Arr = ("").toArray
    val eol = sys.props("line.separator")

    override def toString(): String =  {
        Arr.mkString("") + eol
    }
}
