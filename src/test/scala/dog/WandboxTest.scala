package dog
package autodoc
package examples

import Wandbox._
import Show._

object WandboxTest extends DogAutodoc {

  val interpreter = httpz.scalajhttp.ScalajInterpreter.sequential.empty

  val url = "http://melpon.org/wandbox/api/"

  val `GET /list.json` = {
    val req = Command.List.requestWithURL(url)
    val description = "List compiler informations."
    val action = Autodoc.json[Compilers](req, description)
    Autodoc[Compilers](interpreter, action.nel){ res =>
      Assert.equal(200, res.status)
    }
      .skip("huge output")
  }

  val `POST /compile.json` = {
    val compile = Compile(
      compiler = "gcc-head",
      code = "#include <iostream>\nint main() { int x = 0; std::cout << \"hoge\" << std::endl; }",
      options = Some("warning,gnu++1y"),
      compilerOptionRaw = Some("-Dx=hogefuga\n-O3"),
      save = Some(true)
    )
    val req = Command.Compile(compile).requestWithURL(url)
    val description = """Compile posted code.

This API accepts "application/json" in a "Content-Type" header."""
    val action = Autodoc.json[CompileResult](req, description)
    Autodoc[CompileResult](interpreter, action.nel){ res =>
      Assert.equal(200, res.status)
    }
  }

  val `GET /permlink/:link` = for {
    link <- `POST /compile.json`
      .flatMap[String](_.response.body.permanentLink match {
        case Some(p) => TestCase.ok(p)
        case None => Assert.fail[String]("permanent link did not be returned.")
      })
    marker <- TestCase.delay {
      val req = Command.Link(link).requestWithURL(url)
      val description = "Get a result specified a permanent link :link"
      val action = Autodoc.json[PermanentLink](req, description)
      Autodoc[PermanentLink](interpreter, action.nel){ res =>
        Assert.equal(200, res.status)
      }
    }
  } yield marker
}
