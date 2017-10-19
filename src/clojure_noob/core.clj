(ns clojure-noob.core
  (:gen-class))

(if true
  "By Zeus's hammer!"
  "By Aquaman's trident!")
; => "By Zeus's hammer!"

(if false
  "By Zeus's hammer!"
  "By Aquaman's trident!")
; => "By Aquaman's trident!"

(if false
  "By Odin's Elbow!")
; => nil

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))
; => Success! ( printed in the REPL)
; => "By Zeus's hammer!" ( returned as the value of the entire if expression)
