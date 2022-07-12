package com.iict.language_training

import com.iict.wordsapp.TrainParameters

/**
  * This class is a part of the package com.iict.language_training and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-15.
  */
object ClassifierTool extends App {

    /**
      * Perform training here.
      */
    //Train.NaiveBayesClassifier()


    //val doc = "-af afo fon onf nf_ f_n _n] -afo afon fonf onf_ nf_n f_n] -afon afonf fonf_ onf_n nf_n]"
    //println(GramDocument(doc).gramStats)
    //println(GramDocument(doc).gramDocument)
    val word = "(country)"
    /*val nGrams = NGramDocument(word).generateNGrams
    val englishStats = GenerateStats(englishInputFile)
    val nepaliStats = GenerateStats(nepaliInputFile)
    //stats.getGramFrequencies.foreach(println)
    //stats.getGramDocuments.foreach(println)
    //stats.getGramIndices.foreach(println)
    println("\nEnglish Scores : ")
    nGrams
        .map(gram => gram -> englishStats.getGramsScores.getOrElse(gram, 0.0) ).toMap
        .foreach(println)
    println("\nNepali Scores : ")
    nGrams
        .map(gram => gram -> nepaliStats.getGramsScores.getOrElse(gram, 0.0) ).toMap
        .foreach(println)*/

    /**
      * Generates language statistics.
      */
    GenerateStats(TrainParameters.trainingInput).train

    //val wordList = List("wd", "df", "fw", "wd", "wdf", "dfw", "fwd")
    //val freq = wordList.groupBy(_.toString).mapValues(_.size)
    //println(freq)=

    /*val detector = WordDetector.init
    //val words = "g]kfnLx¿ (country) df cr]n wdfwd crDdsf syfx¿ e}/x]sf 5g\\."
    val words = "cfjZostf"
    val lists = words.split(" ").toList
    val output = lists.map(w => w -> detector.classify(w)).toMap
    println(output)*/

}
