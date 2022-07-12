package com.iict.preprocessing

import scala.util.Random

/**
  * This class is a part of the package com.iict.preprocessing and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-14.
  */
object WordListProcessorTest extends App {

    /**
      * Prints word list.
      */
    val dicFileName = "C:\\Work & Publish\\IICT\\Language Detector of Words\\data\\English\\nep_eng_combined.txt"
    //val dicFileName2 = "C:\\Work & Publish\\IICT\\Language Detector of Words\\data\\English\\corncob_lowercase.txt"
    val wordList = WordlistProcessor.buildWith(dicFileName).processLines

    System.out.println("Words : \n")
    val filteredWords = wordList.filter(w => w.length > 2 && w.length < 20).distinct.sorted
    /**
      * Generates more words from the given word List.
      */
    val bracketedWords = Random.shuffle(filteredWords)
        .take((filteredWords.size.toFloat * 0.4).toInt)
        .map(List("(", _, ")").mkString)

    (filteredWords ++ bracketedWords).filter(w => w.length > 3 && w.length < 22).sorted.foreach(println)

}
