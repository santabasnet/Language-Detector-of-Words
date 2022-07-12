package com.iict.language_training

import java.util.UUID

import com.iict.language_training.GramDocument.delimiter

import scala.collection.mutable

/**
  * This class is a part of the package com.iict.language_training and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-15.
  */
case class GramDocument(documentText: String) {
    /**
      * Document ID.
      */
    lazy val docId: String = UUID.randomUUID().toString

    /**
      * Gram-Frequency Count.
      */
    private val gramFrequencies: Map[String, Int] = {
        val gramFrequency = mutable.Map[String, Int]()
        unigrams.foreach(gram => gramFrequency.put(gram, gramFrequency.getOrElse(gram, 0) + 1))
        gramFrequency.toMap
    }

    private def unigrams = documentText.split(delimiter).map(_.trim).filter(_.nonEmpty).toList

    /**
      * Returns gram frequency stats.
      *
      * @return mapOfGramFrequencies
      */
    def gramStats: Map[String, Int] = gramFrequencies

    /**
      * Inverse Gram Map.
      */
    def documentOfGrams: Map[String, String] =
        gramFrequencies.keySet.map(gram => gram -> docId).toMap

}

/**
  * Companion object to use helper functions.
  */
object GramDocument {
    /**
      * Delimiter.
      */
    val delimiter: String = " |\n"

    /**
      * Tokenize unigram.
      *
      * @param documentText
      * @return listOfTokenGrams
      */
    def tokenizeUnigram(documentText: String): List[String] = documentText.split(delimiter).map(_.trim).filter(_.nonEmpty).toList
}
