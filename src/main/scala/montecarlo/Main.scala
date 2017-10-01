/**
 * author: Andrea
 * Created on: 9/30/2017
 */

package montecarlo

import PI._

import scala.util.Random

object Main extends App{

  val NUM_TRIES = 1000000     //number of random throws
  def randArray = (1 to NUM_TRIES).map(_ => new Point(Random.nextDouble(), Random.nextDouble()))
  val randResults = randArray.map(_.inCircle())
  val circleArea = randResults.count(_ == true)
  def pi:Double =  4 * circleArea.toFloat / NUM_TRIES

  println(f"pi = 4 * $circleArea / $NUM_TRIES = $pi%.5f ")

}
