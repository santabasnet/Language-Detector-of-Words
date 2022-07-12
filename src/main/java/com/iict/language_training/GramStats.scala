package com.iict.language_training

import scala.util.Random

/**
  * This class is a part of the package com.iict.language_training and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-16.
  */
case class GramStats(gramIndices: Map[String, Int], languageVector: Map[Int, Double], vectorMagnitude: Double) {
    /**
      * Get Similarity with Query Document.
      */
    def cosineSimilarity(textDocument: String): Double = if (isEmpty) 0.0 else calculateSimilarity(textDocument)

    /**
      * Emptyness of model.
      *
      * @return true if the model has any one of the empty parameter learned.
      */
    private def isEmpty: Boolean = gramIndices.isEmpty || languageVector.isEmpty || vectorMagnitude == 0.0

    /**
      * Perform calculation.
      */
    private def calculateSimilarity(textDocument: String): Double = {
        val queryVector = generateQueryVector(textDocument)
        val indexedVector = queryVector.keySet.map(index => index -> languageVector.getOrElse(index, 0.0)).toMap
        VectorUtils.cosineOf(queryVector, indexedVector, vectorMagnitude)
    }

    /**
      * Generate query vector from the query document.
      *
      * @return indexedVector.
      */
    private def generateQueryVector(documentText: String): Map[Int, Double] = {
        val random = new Random()
        GramDocument.tokenizeUnigram(documentText).distinct.map(gram => {
            val index = gramIndices.getOrElse(gram, random.nextInt())
            index -> languageVector.getOrElse(index, 0.0)
        }).toMap
    }

}

/**
  * Companion object for utility.
  */
object GramStats {
    /**
      * Empty model, to use as a pure function.
      *
      * @return emptyModel
      */
    def empty = new GramStats(Map[String, Int](), Map[Int, Double](), 0.0)
}
