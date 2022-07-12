package com.iict.wordsapp

/**
  * This class is a part of the package com.iict.wordsapp and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-08-16.
  */
object TrainParameters {

    /**
      * Input file.
      */
    val englishInputFile = "resources/input/english_train.txt"
    val nepaliInputFile = "resources/input/nepali_train.txt"
    val englishLabel = "ENGLISH"
    val nepaliLabel = "NEPALI"

    /**
      * Training Input.
      */
    val trainingInput = Map[String, String](
        englishLabel -> englishInputFile,
        nepaliLabel -> nepaliInputFile
    )

}
