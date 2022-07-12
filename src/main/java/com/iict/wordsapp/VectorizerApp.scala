package com.iict.wordsapp

import com.iict.language_training.GenerateStats

/**
  * This class is a part of the package com.iict.wordsapp and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-08-16.
  */
object VectorizerApp {

    /**
      * Program starts here.
      */
    def main(args: Array[String]): Unit = {

        /**
          * Generates language statistics.
          * It generates statistics and writes the model output.
          */
        GenerateStats(TrainParameters.trainingInput).train

    }

}
