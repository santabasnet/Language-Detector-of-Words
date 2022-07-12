package com.iict.detector

import com.iict.analyzer.NGramDocument
import com.iict.language_training.ProbabilityModel

/**
  * This class is a part of the package com.iict.detector and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-08-17.
  */
case class WordClassifier(probabilityModel: ProbabilityModel) {

    /**
      * The classification part.
      *
      * @param word, given language word.
      * @return classificationScores
      */
    private def performClassification(word: String): Scores = probabilityModel.classify(wordDocumentOf(word))

    /**
      * Classify word.
      *
      * @param word, given word to detect its language, whether it is English or in Nepali.
      * @return classificationResult
      */
    def classify(word: String): String = performClassification(word).toJSON

    /**
      * Get language of Word.
      */
    def languageOf(word: String): String = performClassification(word).language

    /**
      * Identifies if the given word is English or not.
      *
      * @param word, given language word.
      * @return true if the given word is of TTF English.
      */
    def isEnglishWord(word: String): Boolean = if (word.trim.length < 4) false else ProbabilityModel.englishLanguage.equals(languageOf(word))

    /**
      * Returns a word document generated from the ngram strategy.
      *
      * @param word, text of a word.
      * @return wordDocument in text format.
      */
    private def wordDocumentOf(word: String): String = NGramDocument.buildWith(word).generate


}

object WordClassifier {
    /**
      * Similarity model.
      */
    private val probabilityModel = ProbabilityModel.loadModel

    /**
      * Perform initialization model classifier.
      *
      * @return wordDetector instance.
      */
    def init = WordClassifier(probabilityModel)
}
