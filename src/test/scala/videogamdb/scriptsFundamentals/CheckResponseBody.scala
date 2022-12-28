package videogamdb.scriptsFundamentals

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CheckResponseBody extends Simulation{

  val httpProtocol = http.baseUrl("https://videogamedb.uk/api")
    .acceptHeader("application/json")

  val scn = scenario("Check with JSON path")
    .exec(http("Get specific game")
    .get("/videogame/1")
    .check(jsonPath("$.name").is("Resident Evil 4")))

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)


}
