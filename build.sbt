scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.5", "2.11.7")

scalacOptions ++= Seq("-feature", "-language:higherKinds")

fullResolvers ~= {_.filterNot(_.name == "jcenter")}

autodocSettings
autodocVersion := "0.2.0"
autodocEnable := true
autodocHtml := true
autodocToc := true

libraryDependencies ++= Seq(
  "com.github.xuwei-k" %% "httpz-scalaj" % "0.3.0",
  "com.github.nscala-time" %% "nscala-time" % "2.2.0"
)
