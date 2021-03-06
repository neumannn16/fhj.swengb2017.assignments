package at.fhj.swengb.apps.battleship

import at.fhj.swengb.apps.battleship.model._
import org.scalacheck.Prop
import org.scalatest.WordSpecLike
import org.scalatest.prop.Checkers


class BattleShipProtocolSpec extends WordSpecLike {

  import at.fhj.swengb.apps.battleship.model.BattleshipGameGen._

  "BattleShipProtocol" should {
    "be deserializable" in {
      val battlefield: BattleField = BattleField(10, 10, Fleet(FleetConfig.Standard))
      val expected: BattleShipGame = BattleShipGame(battlefield, x => x.toDouble, x => x.toDouble, x => (), x=>())
      expected.hit(BattlePos(1,2))
      val actual: BattleShipGame = BattleShipProtocol.convert(BattleShipProtocol.convert(expected))
      assert(actual.battleField == expected.battleField)
      assert(actual.alreadyClicked == expected.alreadyClicked)
    }
  }
}
