package com.iict.detector

import org.json4s.DefaultFormats
import org.json4s.jackson.Json

/**
  * This class is a part of the package com.iict.detector and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-16.
  */
case class Scores(predictedLanguage: String, predictedScore: Double, calculatedScores: Map[String, Double]) {

    implicit val formats: DefaultFormats.type = DefaultFormats

    def language: String = predictedLanguage

    def heighestScore: Double = predictedScore

    def toJSON: String = Json(formats) writePretty this
}
