package com.iict.language_training

import java.io.{FileOutputStream, ObjectOutputStream}

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

/**
  * Takes language file input and generates all the required statistics.
  * Format: language -> fileName
  *
  * @param languageFiles, given training files of language.
  */
case class GenerateStats(languageFiles: Map[String, String]) {
    /**
      * Generated Statistics.
      */
    private val languageStatistics = mutable.Map[String, TransientStats]()

    /**
      * Perform Training here.
      */
    def train: Unit = {

        println("\n Training started ...")
        languageFiles.foreach(entry => languageStatistics.put(entry._1, TransientStats(entry._2)))

        println("\n Saving model ...")
        writeModel(new ProbabilityModel(trainedStats))

        println("\n Training completed.\n")
    }

    /**
      * Formats the trained model to be saved.
      *
      * @return trainedStats
      */
    private def trainedStats: Map[String, LanguageModel] = {
        val gramStats = languageStatistics.map(entry => entry._1 -> entry._2.getGramStats).toMap
        val gramsTotal = gramStats.map(entry => entry._1 -> VectorUtils.totalNgramsOf(entry._2.languageVector))
        val totalNGrams = gramsTotal.values.sum

        /**
          * Calculate each language probabilities.
          */
        val languageProbabilities = gramStats
            .map(entry => entry._1 -> VectorUtils.languageProbability(entry._2.languageVector, totalNGrams))

        /**
          * Calculate individual ngrams probabilities.
          */
        val ngramProbabilities = gramStats
            .map(entry => entry._1 -> VectorUtils.ngramsProbability(entry._2.gramIndices, entry._2.languageVector, gramsTotal(entry._1)))

        languageStatistics.keys
            .map(language => language -> LanguageModel(languageProbabilities(language), ngramProbabilities(language)))
            .toMap
    }

    /**
      * Writes the object serialized model.
      *
      * @param model, a statistically generated model.
      */
    private def writeModel(model: SimilarityModel): Unit = {
        val outputWriter = new ObjectOutputStream(new FileOutputStream(SimilarityModel.MODEL_FILE))
        outputWriter.writeObject(model)
        outputWriter.close()
    }

    /**
      * Writes the object serialized model.
      *
      * @param model, a statistically generated model.
      */
    private def writeModel(model: ProbabilityModel): Unit = {
        val outputWriter = new ObjectOutputStream(new FileOutputStream(SimilarityModel.MODEL_FILE))
        outputWriter.writeObject(model)
        outputWriter.close()
    }

}
