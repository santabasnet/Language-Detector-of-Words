package com.iict.preprocessing

/**
  * This class is a part of the package com.iict.preprocessing and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-14.
  */
object NepaliWordProcess extends App {

    /**
      * File name.
      */
    val fileName = "C:\\Work & Publish\\IICT\\Language Detector of Words\\data\\Nepali\\n_final_list.txt"
    val wordList = WordlistProcessor.buildWith(fileName).processLines

    System.out.println("Words : \n")
    val filteredWords = wordList.filter(w => w.length > 3 && w.length < 20)
        .distinct.sorted
    filteredWords.foreach(println)

}
