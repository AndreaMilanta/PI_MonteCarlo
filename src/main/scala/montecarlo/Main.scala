/**
 * author: Andrea
 * Created on: 9/30/2017
 */

package montecarlo

import scala.util.Random
import PI._

object Main extends App{

  val R = org.ddahl.rscala.RClient()

  //To avoid memory overflow the computation is split in multiple steps
  val NUM_TRIES = 1000000000     //number of random throws
  val MAX_ARRAY_SIZE = 1000000
  var circleArea: Int = 0

  for(i <- 1 to NUM_TRIES by MAX_ARRAY_SIZE) {
    var randArray =
      if ((i + MAX_ARRAY_SIZE - 1) > NUM_TRIES) {
        //List.fill(NUM_TRIES - i)(null).map(_ => new Point(Random.nextDouble(), Random.nextDouble()))
        //List.fill(NUM_TRIES - i)(new Point(Random.nextDouble(), Random.nextDouble()))
        //List.fill(NUM_TRIES - i)(new Point(normRand(), normRand()))
        val r1 = R.evalD1(s"rnorm(${NUM_TRIES - i},0.5,0.2)")
        val r2 = R.evalD1(s"rnorm(${NUM_TRIES - i},0.5,0.2)")
        List.tabulate(NUM_TRIES - i)(n => new Point(r1(n), r2(n)))
      }
      else {
        //List.fill(MAX_ARRAY_SIZE)(null).map(_ => new Point(Random.nextDouble(), Random.nextDouble()))
        //List.fill(MAX_ARRAY_SIZE)(new Point(Random.nextDouble(), Random.nextDouble()))
        val r1 = R.evalD1(s"rnorm($MAX_ARRAY_SIZE,0.5,0.2)")
        val r2 = R.evalD1(s"rnorm($MAX_ARRAY_SIZE,0.5,0.2)")
        List.tabulate(MAX_ARRAY_SIZE)(n => new Point(r1(n), r2(n)))
      }
    circleArea += randArray.count(_.inCircle())
  }

  val pi:Double =  4 * circleArea.toFloat / NUM_TRIES
  println(f"pi = 4 * $circleArea / $NUM_TRIES = $pi%.8f ")

  /*
  def normRand(): Double = {
    var c:Double = - 1
    while(c < 0 || c > 1 )
      c = R.evalD0("rnorm(1,0.5,0.2)")
    c
  }
  */
}

