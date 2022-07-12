package com.iict.preprocessing

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths, StandardOpenOption}

import scala.io.Source

/**
  * This class is a part of the package com.iict.preprocessing and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-13.
  */
case class WordlistProcessor(fileSource: String) {

    /**
      * Returns a list of word from the file source.
      */
    def extract: List[String] =
        Source.fromFile(fileSource).getLines.map(_.trim).filterNot(_.isEmpty).toList

    /**
      * Writes the file content to the given file name.
      * @param fileName
      * @param fileContent
      * @return filePath
      */
    def write(fileContent:String): Path =
        Files.write(Paths.get(fileSource), fileContent.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE)

    /**
      * Processes words line by line
      * @return listOfWords.
      */
    def processLines: List[String] = extract
        .flatMap(line => line.split(" ").toList).map(_.trim).filter(_.nonEmpty).sorted

}

object WordlistProcessor {

    /**
      * Factory to build the word processor.
      *
      * @param fileSource
      * @return instanceOfWorldListProcessor.
      */
    def buildWith(fileSource: String) = new WordlistProcessor(fileSource)

    /**
      * Trim Nepali numbers.
      */
    def removeNepaliNumbers(word: String): String = word.filterNot(ch => ch >= '०' && ch <= '९')

}
