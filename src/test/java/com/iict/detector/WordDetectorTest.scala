package com.iict.detector

/**
  * This class is a part of the package com.iict.detector and the package
  * is a part of the project wordclassifier.
  *
  * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
  * https://www.integratedict.com.np
  *
  * Created by Santa on 2020-07-15.
  */
object WordDetectorTest extends App {

    //val detector = WordDetector.init
    val detector = WordClassifier.init
    val words = "g]kfnLx¿ (country) df cr]n wdfwd crDdsf syfx¿ e}/x]sf 5g\\."
    //val words = "cfjZostf"
    val lists = words.split(" ").toList
    val output = lists.map(w => w -> detector.classify(w)).toMap
    println(output)

}
