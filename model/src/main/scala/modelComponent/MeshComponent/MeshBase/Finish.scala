package modelComponent.meshComponent.meshBase

final case class Finish(Player: Int) extends FieldFactory{
    override val Arr = ((("-") * 4 + "  ") * Player).toArray
}