# Implementation of Indoor Positioning Systems
* N-Lateration
* Static Fingerpint
* Hidden Markov Model

## N-Lateration
- The implemented N-lateration algorithm computes the estimated position by minimizing the
sum of distances to 4-spheres inside the perimeter of the 4 spheric volumes
agregated ;
  - The factory design pattern was used (for the given data set)
  
 ## Static Fingerprint 
- The fingerprint algorithm (offline training) stores the location dependent characteristics of a signal collected at known locations ahead of the system's use for localization in a database
- Then determine by matching the k best fingerprint closest to the catched environment and determine by computing weights the positioning of the object/human.


## Hidden Markov Model
Hidden Markov Model (HMM) is a statistical Markov model in which the system being modeled is assumed to be a Markov process – call it *X* – with unobservable ("hidden") states. HMM assumes that there is another process *Y* whose behavior "depends" on *X*. The goal is to learn about *X* by observing *Y*.
<br>

***In the currecnt implementation it's possible to determine the next state based on the current (X -> Y) and the previous state based on the current (Y -> X)***
