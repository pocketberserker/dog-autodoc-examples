package dog
package autodoc
package examples

import argonaut.DecodeJson
import httpz._
import scalaz.Free.FreeC
import scalaz.{Free, Inject, NonEmptyList}

sealed abstract class Command[A](val f: String => Request)(implicit val decoder: DecodeJson[A]){

  final def requestWithURL(baseURL: String): httpz.Request =
    f(baseURL)

  final def actionWithURL(baseURL: String): httpz.Action[A] =
    Core.json[A](requestWithURL(baseURL))(decoder)

  final def lift[F[_]](implicit I: Inject[Command, F]): FreeC[F, A] =
    Free.liftFC(I.inj(this))
}

object Command {

  import Wandbox._

  private[examples] def get(url: String, opt: Config = emptyConfig): String => Request =
    baseUrl => opt(Request(url = baseUrl + url))

  private[examples] def post(url: String, opt: Config = emptyConfig): String => Request =
    baseUrl => opt(Request(url = baseUrl + url, method = "POST"))

  case object List extends Command[Compilers](
    get("list.json")
  )

  case class Compile(input: Wandbox.Compile) extends Command[CompileResult](
    post("compile.json",
      Request.bodyString(input.toString)
        .compose(Request.header("Content-type", "application/json")))
  )

  case class Link(link: String) extends Command[PermanentLink](
    get("permlink/" + link)
  )
}
