package com.iict.language_training

/**
  * This class is a part of the package com.iict.language_training and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-16.
  */
object VectorUtils {

    /**
      * Calculate the cosine angle between query and language vector.
      *
      * @param v1, first vector.
      * @param v2, second vector.
      * @return cosineAngle between two vectors v1 and v2.
      */
    def cosineOf(v1: Map[Int, Double], v2: Map[Int, Double], vectorMagnitude: Double): Double = {
        val productOfMagnitude = magnitudeOf(v1) * vectorMagnitude
        if (productOfMagnitude == 0.0) 0.0 else dotProduct(v1, v2) / productOfMagnitude
    }

    /**
      * Calculates the dot product of two indexed vectors.
      *
      * @param v1, first vector.
      * @param v2, second vector.
      * @return dotProductOfVectors
      */
    def dotProduct(v1: Map[Int, Double], v2: Map[Int, Double]): Double =
        (v1.keySet ++ v2.keySet).map(index => v1.getOrElse(index, 0.0) * v2.getOrElse(index, 0.0)).sum

    /**
      * Calculates the magnitude of the indexed vector.
      *
      * @param gramsScore, a form of indexed vector.
      * @return vectorMagnitude
      */
    def magnitudeOf(gramsScore: Map[Int, Double]): Double = math.sqrt(gramsScore.values.map(score => score * score).sum)

    /**
      * Total Ngrams Count.
      *
      * @param gramsScore, a form of indexed vector.
      * @return totalNgrams of a language.
      */
    def totalNgramsOf(gramsScore: Map[Int, Double]): Double = gramsScore.values.sum

    /**
      * Vector Probability.
      *
      * @param gramsScore
      * @param totalGrams
      * @return languageVectorProbability
      */
    def languageProbability(gramsScore: Map[Int, Double], totalGrams: Double): Double = totalNgramsOf(gramsScore) / totalGrams

    /**
      * NGrams Probability.
      *
      * @param gramsScore
      * @param totalGrams
      * @return probability calculation.
      */
    def calculateProbabilityOf(gramsScore: Map[Int, Double], totalGrams: Double): Double =
        gramsScore.values.map(_ / totalGrams).sum

    /**
      * Grams Probability scores.
      *
      * @param gramsScore
      * @param totalGrams
      * @return mapOfNgrams probabilities.
      */
    def ngramsProbability(gramIndices: Map[String, Int], gramsScore: Map[Int, Double], totalGrams: Double): Map[String, Double] = gramIndices
        .map(gramIndex => gramIndex._1 -> gramsScore.getOrElse(gramIndex._2, 0.0))
        .map(gramEntry => gramEntry._1 -> gramEntry._2 / totalGrams)

}
