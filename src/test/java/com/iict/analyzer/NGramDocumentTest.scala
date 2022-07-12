package com.iict.analyzer

/**
  * This class is a part of the package com.iict.preprocessing and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-13.
  */
object NGramDocumentTest extends App {

    /**
      * Given Word.
      */
    private val word: String = "test"
    println("\nGenerated NGrams : " + NGramDocument.buildWith(word).generateNGrams)

    /**
      * Given Another Word.
      */
    private val word1 = "university"
    println("\nGenerated NGrams : " + NGramDocument.buildWith(word1).generate)

    /**
      * Given a Short Word.
      */
    private val word2 = "at"
    println("\nGenerated NGrams : " + NGramDocument.buildWith(word2).generateNGrams)


}
