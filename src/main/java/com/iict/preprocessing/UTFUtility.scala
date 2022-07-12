package com.iict.preprocessing

import java.io.{File, PrintWriter}

import com.iict.conversionapi.FontConversionAPI

import scala.collection.mutable

/**
  * This class is a part of the package com.iict.preprocessing and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-08-16.
  */
object UTFUtility {

    val file = "resources/spell_data/iict_combined_dict.dat"

    val output = "resources/input/n_final_list.txt"

    val ttfPreeti = "Preeti"
    val ttfHimal = "himalb"
    val ttfNavjeevan = "navjeevan"

    private def containsUTFCharacter(word: String): Boolean = word.forall(c => c < 256)

    /**
      * Write the generated output here.
      */
    private def writeWords(wordList: List[String]): Unit = {
        val wordWriter = new PrintWriter(new File(output))
        wordList.foreach(content => {
            wordWriter.write(content)
            wordWriter.write(System.lineSeparator())
        })
        wordWriter.close()
    }


    /**
      * Main File.
      *
      * @param args
      */
    def main(args: Array[String]): Unit = {
        val wordList = WordlistProcessor.buildWith(file).extract.filter(_.length > 2)
        // wordList.foreach(println)

        val preetiWords = wordList.map(sourceWord => FontConversionAPI.produceTTF(ttfPreeti, sourceWord))
        val himalbWords = wordList.map(sourceWord => FontConversionAPI.produceTTF(ttfHimal, sourceWord))
        val navjeevanWords = wordList.map(sourceWord => FontConversionAPI.produceTTF(ttfNavjeevan, sourceWord))

        val allWords = (((mutable.ListBuffer[String]() ++ preetiWords) ++ himalbWords) ++ navjeevanWords)
            .toList.distinct.filter(containsUTFCharacter).filter(_.length > 2).sorted

        /**
          * Writes the final nepali word list.
          */
        writeWords(allWords)

    }

}
