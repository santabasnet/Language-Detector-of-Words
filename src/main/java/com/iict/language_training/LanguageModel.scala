package com.iict.language_training

/**
  * This class is a part of the package com.iict.language_training and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-08-17.
  */
case class LanguageModel(languageProbability: Double, ngramsProbability: Map[String, Double]) {

    /**
      * Calculate the bayesian similarity of the query document.
      *
      * @param textDocument, given text document build from NGrams.
      * @return similarityScore
      */
    def bayesianSimilarity(textDocument: String): Double = if (isEmpty) 0.0 else calculateSimilarity(textDocument)

    /**
      * Perform calculation.
      */
    private def calculateSimilarity(textDocument: String): Double = {
        val queryProbabilities = ngramProbabilitiesOf(textDocument)
        queryProbabilities.values.sum * languageProbability
    }

    /**
      * Emptyness of model.
      *
      * @return true if the model has any one of the empty parameter learned.
      */
    private def isEmpty: Boolean = languageProbability == 0 || ngramsProbability.isEmpty

    /**
      * Generate query vector from the query document.
      *
      * @return indexedVector.
      */
    private def ngramProbabilitiesOf(documentText: String): Map[String, Double] = {
        GramDocument.tokenizeUnigram(documentText).distinct.map(gram => {
            gram -> ngramsProbability.getOrElse(gram, 0.0)
        }).toMap
    }

}

/**
  * Companion object for utility.
  */
object LanguageModel {
    /**
      * Empty model, to use as a pure function.
      *
      * @return emptyModel
      */
    def empty = new LanguageModel(0.0, Map[String, Double]())
}

