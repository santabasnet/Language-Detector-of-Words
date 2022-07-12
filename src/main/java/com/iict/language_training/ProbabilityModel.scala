package com.iict.language_training

import java.io.{File, FileInputStream, ObjectInputStream}

import com.iict.detector.Scores

/**
  * This class is a part of the package com.iict.language_training and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-08-17.
  */
class ProbabilityModel(languageModels: Map[String, LanguageModel]) extends Serializable {

    /**
      * Perform classification here.
      *
      * @param textDocument
      * @return classificationScores
      */
    def classify(textDocument: String): Scores = {
        val allScores = calculateScores(textDocument)
        val maxScore = allScores.maxBy { case (key, value) => value }
        Scores(if (maxScore._2 == 0.0) SimilarityModel.defaultLanguage else maxScore._1, maxScore._2, allScores)
    }

    /**
      * Calculates similarity of language model against the given text document.
      *
      * @param language    , label of language class.
      * @param textDocument, document of ngrams text.
      * @return similarityScore with language.
      */
    private def withLanguage(language: String, textDocument: String): Double = {
        languageModels.getOrElse(language, LanguageModel.empty).bayesianSimilarity(textDocument)
    }


    /**
      * Accumulates the calculated scores.
      *
      * @param textDocument, given text document.
      * @return calculatedScores.
      */
    private def calculateScores(textDocument: String): Map[String, Double] =
        ProbabilityModel.availableLanguages.map(key => key -> withLanguage(key, textDocument)).toMap


}

object ProbabilityModel {
    /**
      * Similarity model file.
      */
    val MODEL_FILE: String = "resources/model/similarity_model.dat"

    /**
      * Default language.
      */
    val defaultLanguage: String = "NEPALI"

    /**
      * English language label.
      */
    val englishLanguage: String = "ENGLISH"

    /**
      * Nepali language label.
      */
    val nepaliLanguage: String = defaultLanguage

    /**
      * Default language stats.
      */
    val availableLanguages: Set[String] = List(nepaliLanguage, englishLanguage).toSet

    /**
      * Empty Model.
      */
    def empty = new ProbabilityModel(Map[String, LanguageModel]())

    /**
      * Load from serialized data.
      * Check if the model file available or not for safe computation.
      */
    def loadModel: ProbabilityModel = {
        val modelFile = new File(MODEL_FILE)
        if (!modelFile.exists())
            ProbabilityModel.empty
        else {
            val modelReader = new ObjectInputStream(new FileInputStream(SimilarityModel.MODEL_FILE))
            val probabilityModel = modelReader.readObject.asInstanceOf[ProbabilityModel]
            modelReader.close()
            probabilityModel
        }
    }
}
