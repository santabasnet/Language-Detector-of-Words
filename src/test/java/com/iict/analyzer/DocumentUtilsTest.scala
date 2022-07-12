package com.iict.analyzer

import com.iict.language_training.ArffGenerator

/**
  * This class is a part of the package com.iict.analyzer and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-14.
  */
object DocumentUtilsTest {
    /**
      * Input Files.
      */
    val nepali_file_words: String = "resources/input/n_final_list.txt"
    val english_file_words: String = "resources/input/e_final_list.txt"

    def main(args: Array[String]): Unit = {
        /**
          * Prepare documents from the word list of Nepali Language.
          */
        //DocumentUtils.generateEnglishDocuments(english_file_words)

        val w = "testing"
        //println(NGramDocument.buildWith(w).generateNGrams)
        //WordlistProcessor.buildWith("resources/a.txt").write(NGramDocument.buildWith(w).generate)*/

        ArffGenerator.generateArffFile

        /**
          * Generating Nepali Documents.
          */
        //DocumentUtils.generateNepaliDocuments(nepali_file_words)

    }


}
