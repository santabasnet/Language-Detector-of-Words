package com.iict.language_training

import scala.collection.mutable
import scala.io.Source

/**
  * This class is a part of the package com.iict.language_training and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-16.
  */
case class TransientStats(inputFile: String) {

    /**
      * Gram Documents.
      */
    private val gramDocuments = mutable.Map[String, mutable.ListBuffer[String]]()

    /**
      * Gram Documents count.
      */
    private val gramDocumentsCount = mutable.Map[String, Int]()

    /**
      * Grams ordered list.
      */
    private var gramsList = List[String]()

    /**
      * Gram TF-IDF.
      */
    private val gramsScore = mutable.Map[Int, Double]()

    /**
      * Grams index.
      */
    private val gramsIndex = mutable.Map[String, Int]()

    /**
      * Gram Frequencies.
      */
    val gramFrequencies: mutable.Map[String, Int] = mutable.Map[String, Int]()

    /**
      *
      * @param document
      */
    private def addToFrequencyTypes(document: GramDocument): Unit =
        document.gramStats.foreach(tuple => {
            gramFrequencies.put(tuple._1, gramFrequencies.getOrElse(tuple._1, 0) + tuple._2)
            val result = gramDocuments.getOrElse(tuple._1, mutable.ListBuffer[String]())
            result += document.docId
            gramDocuments.put(tuple._1, result)
        })

    /**
      * Load Documents from the input file.
      */
    private val allDocuments: Iterator[GramDocument] = {
        val allDocs = Source.fromFile(inputFile).getLines.map(GramDocument(_))
        allDocs.foreach(addToFrequencyTypes)
        gramDocuments.foreach(entry => gramDocumentsCount.put(entry._1, entry._2.size))
        gramsList = gramDocuments.keySet.toList.sorted
        gramsList.zipWithIndex.foreach(gram_index => gramsIndex.put(gram_index._1, gram_index._2))

        // For other computation, now considered frequency as score.
        gramsList.foreach(gram => {
            val index: Int = gramsIndex.getOrElse(gram, -1)
            val score: Double = gramFrequencies.getOrElse(gram, 0).toDouble
            gramsScore.put(index, score)
        })
        allDocs
    }

    def allGramDocuments: List[GramDocument] = allDocuments.toList

    def getGramFrequencies: Map[String, Int] = gramFrequencies.toMap

    def getGramDocuments: Map[String, List[String]] = gramDocuments.map(entry => entry._1-> entry._2.toList).toMap

    def getGramIndices: Map[String, Int] = gramsIndex.toMap

    def getGramsScores: Map[Int, Double] = gramsScore.toMap

    def getVectorMagnitude: Double = VectorUtils.magnitudeOf(getGramsScores)

    def getGramStats: GramStats = GramStats(getGramIndices, getGramsScores, getVectorMagnitude)

}
