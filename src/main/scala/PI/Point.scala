/**
  * author: Andrea
  * Created on: 9/30/2017
  */

package PI

class Point(val x: Double, val y: Double) {

  def squaredDistance(p: Point): Double = {
    Math.pow(p.x - x,2) + Math.pow(p.y - y,2)
  }

  def inCircle() : Boolean = {
    if (squaredDistance(PICircle.center) <= Math.pow(PICircle.r, 2))
      return true
    false
  }

}
