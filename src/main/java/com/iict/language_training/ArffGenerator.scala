package com.iict.language_training

import java.io.PrintWriter
import java.util.regex.{Matcher, Pattern}

import com.iict.analyzer.NGramDocument
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
object ArffGenerator {

    /**
      * Template Dataset heading.
      */
    private val heading: List[String] = List(
        "% 1. Title: English/Nepali Language Word Detection Database",
        "% ",
        "% 2. Sources",
        "%     a. Many technical and non-technical English dictionaries available in Nepal.",
        "%     b. corncob dictionary words.",
        "%     c.Nepali Brihat Shabdakosh from Nepal Academy.",
        "%     d. b. Various translated books from English to Nepali Language.",
        "\n",
        "% Prepared by: Santa [at] IICT",
        "% On 2020-07-14.",
        "\n"
    )

    /**
      * Template relation.
      */
    private val relation = List(
        "@relation 'Language Detection of a Word between English and Nepali.'",
        "\n"
    )

    /**
      * Template labels.
      */
    private val labels: List[String] = List(
        "@attribute label {english,nepali}",
        "@attribute text string",
        "\n"
    )

    /**
      * Template data.
      */
    private val data = List("@data", "\n")

    /**
      * English label.
      */
    private val englishLabel = "english,\t"

    /**
      * Nepali label.
      */
    private val nepaliLabel = "nepali,\t"

    /**
      * Input/Output Files.
      */
    val nepali_file_words: String = "resources/input/n_final_list.txt"
    val english_file_words: String = "resources/input/e_final_list.txt"
    val output_file: String = "resources/training.arff"

    /**
      * Write the generated
      */
    def generateArffFile: Unit = {
        val fileData = List(heading, relation, labels, data, languageDocuments).flatten
        Some(new PrintWriter(output_file))
            .foreach { filePointer => fileData.foreach(item => filePointer.write(item + "\n")); filePointer.close }
    }

    /**
      * Generate training data.
      *
      * @return trainingDataList
      */
    private def languageDocuments: List[String] = {
        val nepaliWordList = WordlistProcessor.buildWith(nepali_file_words).processLines
        val englishWordList = WordlistProcessor.buildWith(english_file_words).processLines

        val nepaliData = nepaliWordList.map(NGramDocument.buildWith(_).generateNGrams)
            .map(list => list.map(treatSpecialReplacement))
            .map(_.mkString(" "))
            .map(List("'", _, "'").mkString)
            .map(nepaliLabel + _)

        val englishData = englishWordList.map(NGramDocument.buildWith(_).generateNGrams)
            .map(list => list.map(treatSpecialReplacement))
            .map(_.mkString(" "))
            .map(List("'", _, "'").mkString)
            .map(englishLabel + _)

        englishData ++ nepaliData
    }

    /**
      * Treat special character escaping for Nepali.
      * \\\\'   => \\\\\\'
      * \\'     => \\\\'
      */
    def treatSpecialReplacement(word: String): String = {
        word.replaceAll("[" + Pattern.quote("\\'\\") + "]", Matcher.quoteReplacement("\\")+"$0")
    }

}
