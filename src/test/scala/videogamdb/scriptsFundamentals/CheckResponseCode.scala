package videogamdb.scriptsFundamentals

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt


class CheckResponseCode extends Simulation{
  // http configuration
  val httpProtocol = http.baseUrl("https://videogamedb.uk/api")
    .acceptHeader("application/json")

  //scenario definition
  val scn = scenario("Video Game - 3 calls")
    .exec(http("Get all video games - 1st Call")
      .get("/videogame")
      .check(status.is(200)))
    .pause(5)

    .exec(http("Get specific game")
      .get("/videogame/1"))
//       .check(status.in(200 <= to <= 210 )))
    .pause(1, 10)

    .exec(http("Get all video games - 2nd call")
      .get("/videogame")
      .check(status.not(404), status.not( 500)))

    .pause(3000.milliseconds)

  //load scenario
  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)
}
