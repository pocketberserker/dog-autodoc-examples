package dog
package autodoc
package examples

import argonaut._, Argonaut._
import httpz.JsonToString
import com.github.nscala_time.time.Imports._

object Wandbox {

  implicit def DateTimeDecodeJson: DecodeJson[DateTime] =
    DecodeJson.optionDecoder(_.string.flatMap(_.toDateTimeOption), "org.joda.time.DateTime")

  implicit def DateTimeEncodeJson: EncodeJson[DateTime] =
    EncodeJson(a => jString(a.toString))

  final case class SwitchOption(name: String, displayFlags: String, displayName: String)

  sealed abstract class Switch
  final case class SingleSwitch(default: Boolean, option: SwitchOption, typ: String) extends Switch
  final case class SelectSwitch(default: String, options: List[SwitchOption], typ: String) extends Switch

  implicit val SwitchOptionCodecJson: CodecJson[SwitchOption] =
    CodecJson((o: SwitchOption) =>
      ("name" := o.name) ->:
      ("display-flags" := o.displayFlags) ->:
      ("display-name" := o.displayName) ->:
      jEmptyObject,
      o => for {
        name <- (o --\ "name").as[String]
        displayFlags <- (o --\ "display-flags").as[String]
        displayName <- (o --\ "display-name").as[String]
      } yield SwitchOption(name, displayFlags, displayName))

  val SingleSwitchCodecJson: CodecJson[SingleSwitch] =
    CodecJson((s: SingleSwitch) =>
      ("default" := s.default) ->:
      ("name" := s.option.name) ->:
      ("display-flags" := s.option.displayFlags) ->:
      ("display-name" := s.option.displayName) ->:
      ("type" := s.typ) ->:
      jEmptyObject,
      s => for {
        default <- (s --\ "default").as[Boolean]
        name <- (s --\ "name").as[String]
        displayFlags <- (s --\ "display-flags").as[String]
        displayName <- (s --\ "display-name").as[String]
        t <- (s --\ "type").as[String]
      } yield SingleSwitch(default, SwitchOption(name, displayFlags, displayName), t))

  val SelectSwitchCodecJson: CodecJson[SelectSwitch] =
    casecodec3(SelectSwitch.apply _, SelectSwitch.unapply _)("default", "options", "type")

  implicit val SwitchEncodeJson: EncodeJson[Switch] =
    EncodeJson(_ match {
      case s@SingleSwitch(_, _, _) => SingleSwitchCodecJson.encode(s)
      case m@SelectSwitch(_, _, _) => SelectSwitchCodecJson.encode(m)
    })

  implicit val SwitchDecodeJson: DecodeJson[Switch] =
    SingleSwitchCodecJson.map(_.asInstanceOf[Switch]) ||| (SelectSwitchCodecJson.map(_.asInstanceOf[Switch]))

  final case class Compiler(
    name: String,
    version: String,
    language: String,
    displayName: String,
    compilerOptionRaw: Boolean,
    runtimeOptionRaw: Boolean,
    displayCompileCommand: String,
    switches: List[Switch])

  implicit val CompilerCodecJson: CodecJson[Compiler] =
    CodecJson((c: Compiler) =>
      ("name" := c.name) ->:
      ("version" := c.version) ->:
      ("language" := c.language) ->:
      ("display-name" := c.displayName) ->:
      ("compiler-option-raw" := c.compilerOptionRaw) ->:
      ("runtime-option-raw" := c.runtimeOptionRaw) ->:
      ("display-compile-command" := c.displayCompileCommand) ->:
      ("switches" := c.switches) ->:
      jEmptyObject,
      c => for {
        name <- (c --\ "name").as[String]
        version <- (c --\ "version").as[String]
        language <- (c --\ "language").as[String]
        displayName <- (c --\ "display-name").as[String]
        compilerOptionRaw <- (c --\ "compiler-option-raw").as[Boolean]
        runtimeOptionRaw <- (c --\ "runtime-option-raw").as[Boolean]
        displayCompilerCommand <- (c --\ "display-compile-command").as[String]
        switches <- (c --\ "switches").as[List[Switch]]
      } yield Compiler(name, version, language, displayName, compilerOptionRaw, runtimeOptionRaw, displayCompilerCommand, switches))

  final case class Compilers(value: List[Compiler]) extends JsonToString[Compilers]

  implicit def CompilersCodecJson: CodecJson[Compilers] =
    CodecJson.derived[List[Compiler]].xmap(Compilers(_))(_.value)

  final case class Compile(
    compiler: String,
    code: String,
    codes: Option[List[String]] = None,
    options: Option[String] = None,
    stdin: Option[String] = None,
    compilerOptionRaw: Option[String] = None,
    runtimeOptionRaw: Option[String] = None,
    createdAt: Option[DateTime] = None,
    save: Option[Boolean] = None,
    compilerInfo: Option[Compiler] = None) extends JsonToString[Compile]

  implicit val CompileCodecJson: CodecJson[Compile] =
    CodecJson((c: Compile) =>
      ("compiler" := c.compiler) ->:
      ("code" := c.code) ->:
      (c.codes.map("codes" := _)) ->?:
      (c.options.map("options" := _)) ->?:
      (c.stdin.map("stdin" := _)) ->?:
      (c.compilerOptionRaw.map("compiler-option-raw" := _)) ->?:
      (c.runtimeOptionRaw.map("runtime-option-raw" := _)) ->?:
      (c.createdAt.map("created-at" := _)) ->?:
      (c.save.map("save" := _)) ->?:
      (c.compilerInfo.map("compiler-info" := _)) ->?:
      jEmptyObject,
      c => for {
        compiler <- (c --\ "compiler").as[String]
        code <- (c --\ "code").as[String]
        codes <- (c --\ "codes").as[Option[List[String]]]
        options <- (c --\ "options").as[Option[String]]
        stdin <- (c --\ "stdin").as[Option[String]]
        compilerOptionRaw <- (c --\ "compiler-option-raw").as[Option[String]]
        runtimeOptionRaw <- (c --\ "runtime-option-raw").as[Option[String]]
        createdAt <- (c --\ "created-at").as[Option[DateTime]]
        save <- (c --\ "save").as[Option[Boolean]]
        compilerInfo <- (c --\ "compiler-info").as[Option[Compiler]]
      } yield Compile(compiler, code, codes, options, stdin, compilerOptionRaw, runtimeOptionRaw, createdAt, save, compilerInfo))

  final case class CompileResult(
    status: String,
    signal: Option[String],
    compilerOutput: Option[String],
    compilerError: Option[String],
    compilerMessage: Option[String],
    programOutput: Option[String],
    programError: Option[String],
    programMessage: Option[String],
    permanentLink: Option[String],
    url: Option[String]) extends JsonToString[CompileResult]

  implicit val CompileResultCodecJson: CodecJson[CompileResult] =
    casecodec10(CompileResult.apply, CompileResult.unapply)(
      "status",
      "signal",
      "compiler_output",
      "compiler_error",
      "compiler_message",
      "program_output",
      "program_error",
      "program_message",
      "permlink",
      "url")

  final case class PermanentLink(compile: Compile, result: CompileResult) extends JsonToString[PermanentLink]

  implicit val PermanentLinkCodecJson: CodecJson[PermanentLink] =
    casecodec2(PermanentLink.apply, PermanentLink.unapply)("parameter", "result")
}
