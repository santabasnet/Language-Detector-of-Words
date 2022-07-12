package com.iict.preprocessing

import java.io.File

/**
  * This class is a part of the package com.iict.preprocessing and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-13.
  */
object Documents {

    /**
      * Relative folder.
      */
    val rootFolder = "resources/"

    /**
      * English Language Folder Source.
      */
    val englishPath: String = rootFolder + "english/"

    /**
      * Nepali Language Folder Source.
      */
    val nepaliPath: String = rootFolder + "nepali/"

    /**
      * Nepali Language
      */
    val NEPALI_LANGUAGE = "NEPALI"

    /**
      * English Language
      */
    val ENGLISH_LANGUAGE = "ENGLISH"

    /**
      * File extension.
      */
    val extension: String = ".txt"

    /**
      * Formats the file name for the given language word.
      *
      * @param word
      * @param language
      * @return fileName to save the word document.
      */
    def fileNameOf(word: String, language: String): String = language.toUpperCase match {
        case NEPALI_LANGUAGE => nepaliFileNameOf(word)
        case ENGLISH_LANGUAGE => englishFileNameOf(word)
        case _ => nepaliFileNameOf(word)
    }

    /**
      * English Folder.
      */
    def englishFolder: File = new File(englishPath)

    /**
      * Nepali Folder.
      */
    def nepaliFolder: File = new File(nepaliPath)

    /**
      * Nepali word file name.
      */
    private def nepaliFileNameOf(word: String): String = List(nepaliPath, formatName(word), extension).mkString

    /**
      * English word file name.
      */
    private def englishFileNameOf(word: String): String = List(englishPath, formatName(word), extension).mkString

    /**
      * Format file name.
      */
    private def formatName(word: String): String = word.hashCode.toString.replaceFirst("-", "_")

}
