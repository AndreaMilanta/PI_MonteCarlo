/**
 * author: Andrea
 * Created on: 9/30/2017
 */

package montecarlo

import PI._

import scala.util.Random

object Main extends App{

  //To avoid memory overflow the computation is split in multiple steps
  val NUM_TRIES = 1000000000     //number of random throws
  val MAX_ARRAY_SIZE = 1000000
  var circleArea: Int = 0

  for(i <- 1 to NUM_TRIES by MAX_ARRAY_SIZE) {
    var randArray =
      if ((i + MAX_ARRAY_SIZE - 1) > NUM_TRIES)
        //List.fill(NUM_TRIES - i)(null).map(_ => new Point(Random.nextDouble(), Random.nextDouble()))
        List.fill(NUM_TRIES - i)(new Point(Random.nextDouble(), Random.nextDouble()))
      else
        //List.fill(MAX_ARRAY_SIZE)(null).map(_ => new Point(Random.nextDouble(), Random.nextDouble()))
        List.fill(MAX_ARRAY_SIZE)(new Point(Random.nextDouble(), Random.nextDouble()))

    circleArea += randArray.count(_.inCircle())
  }

  val pi:Double =  4 * circleArea.toFloat / NUM_TRIES

  println(f"pi = 4 * $circleArea / $NUM_TRIES = $pi%.8f ")

}
