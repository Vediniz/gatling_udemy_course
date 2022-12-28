package videogamdb

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class myFirstTest extends Simulation{
  // http configuration
  val httpProtocol = http.baseUrl("https://videogamedb.uk/api")
    .acceptHeader("application/json")

  //scenario definition
  val scn = scenario("My First test")
      .exec(http("Get all video games")
      .get("/videogame"))

  //load scenario
  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)
}
