package com.iict.analyzer

import java.nio.file.Path

import com.iict.preprocessing.{Documents, WordlistProcessor}

/**
  * This class is a part of the package com.iict.analyzer and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-14.
  */
object DocumentUtils {
    /**
      * Generate from word list in the file.
      */
    def generateEnglishDocuments(wordsFile: String): Unit = WordlistProcessor.buildWith(wordsFile)
        .processLines.foreach(generateAndWrite(_, Documents.ENGLISH_LANGUAGE))

    /**
      * Generate Nepali documents from the word list in the file.
      */
    def generateNepaliDocuments(wordsFile: String): Unit = WordlistProcessor.buildWith(wordsFile)
        .processLines.foreach(generateAndWrite(_, Documents.NEPALI_LANGUAGE))

    /**
      * Utilize the document generation and writes it to the generated
      * file name.
      */
    private def generateAndWrite(word: String, language: String): Path = WordlistProcessor
        .buildWith(Documents.fileNameOf(word, language))
        .write(NGramDocument.buildWith(word).generate)

}
