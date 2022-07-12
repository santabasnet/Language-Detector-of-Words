package com.iict.wordsapp

import java.io.PrintWriter

import com.iict.analyzer.NGramDocument
import com.iict.language_training.ArffGenerator
import com.iict.preprocessing.WordlistProcessor

/**
  * This class is a part of the package com.iict.language_training and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-14.
  */
object PlainTextGenerator {

    /**
      * English file name.
      */
    val englishFile = "resources/english_train.txt"

    /**
      * Nepali file name.
      */
    val nepaliFile = "resources/nepali_train.txt"

    def main(args: Array[String]): Unit = {
        prepareNepali
        prepareEnglish
    }

    private def prepareNepali: Unit = {
        val nepaliWordList = WordlistProcessor.buildWith(ArffGenerator.nepali_file_words).processLines
        val nepaliData = nepaliWordList
            .map(NGramDocument.buildWith(_).generateNGrams)
            .map(_.mkString(" "))

        Some(new PrintWriter(nepaliFile))
            .foreach { filePointer => nepaliData.foreach(item => filePointer.write(item + "\n")); filePointer.close }

    }

    private def prepareEnglish: Unit = {
        val englishWordList = WordlistProcessor.buildWith(ArffGenerator.english_file_words).processLines
        val englishData = englishWordList
            .map(NGramDocument.buildWith(_).generateNGrams)
            .map(_.mkString(" "))

        Some(new PrintWriter(englishFile))
            .foreach { filePointer => englishData.foreach(item => filePointer.write(item + "\n")); filePointer.close }

    }


}
