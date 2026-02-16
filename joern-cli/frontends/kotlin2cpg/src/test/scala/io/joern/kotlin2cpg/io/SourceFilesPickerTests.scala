package io.joern.kotlin2cpg.io

import io.joern.kotlin2cpg.files.SourceFilesPicker
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.BeforeAndAfterAll

class SourceFilesPickerTests extends AnyFreeSpec with Matchers with BeforeAndAfterAll {

  "SourceFilesPicker" - {
    "should not filter out fileNames ending in `build.gradle`" in {
      val inFileName = "/path/does/not/exist/build.gradle"
      SourceFilesPicker.shouldFilter(inFileName) shouldBe false
    }

    "should filter generated Kotlin sources in `build` paths" in {
      val inFileName = "artifact/build/generated/ksp/main/kotlin/com/example/HelloWorld.kt"
      SourceFilesPicker.shouldFilter(inFileName) shouldBe true
    }

    "should filter generated Kotlin sources in `bin` paths" in {
      val inFileName = "artifact/bin/main/com/example/HelloWorld.kt"
      SourceFilesPicker.shouldFilter(inFileName) shouldBe true
    }

    "should not filter regular `src/main` Kotlin sources" in {
      val inFileName = "artifact/src/main/kotlin/com/example/HelloWorld.kt"
      SourceFilesPicker.shouldFilter(inFileName) shouldBe false
    }
  }

}
