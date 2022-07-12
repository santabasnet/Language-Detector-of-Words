# Language-Detector-of-Words
A word classifier training, testing and implementation done from scratch in Scala using Naive Bayes Classifier. 

### Usage:
-----
```Scala
    val detector = WordClassifier.init
    val words = "g]kfnLx¿ (country) df cr]n wdfwd crDdsf syfx¿ e}/x]sf 5g\\."
    val lists = words.split(" ").toList
    val output = lists.map(w => w -> detector.classify(w)).toMap
    println(output)
```
