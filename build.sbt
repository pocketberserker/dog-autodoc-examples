scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.0")

scalacOptions ++= Seq("-feature", "-language:higherKinds")

fullResolvers ~= {_.filterNot(_.name == "jcenter")}

autodocSettings
autodocVersion := "0.3.0"
autodocEnable := true
autodocHtml := true
autodocToc := true

libraryDependencies ++= Seq(
  "com.github.xuwei-k" %% "httpz-scalaj" % "0.5.0",
  "com.github.nscala-time" %% "nscala-time" % "2.14.0"
)
