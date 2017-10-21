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

(when true
  (println "Success!")
  "abra cadabra")
; => Success! ( printed in the REPL)
; => "abra cadabra" ( returned as the value of the entire when expression)

(nil? 1)
; => false

(nil? nil)
; => true

(if "bears eat beets"
  "bears beets Battlestar Galactica")
; => "bears beets Battlestar Galactica"

(if nil
  "This won't be the result because nil is falsey"
  "nil is falsey")
; => "nil is falsey"

(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
; => :large_I_mean_venti

(or (= 0 1) (= "yes" "no"))
; => false

(or nil)
; => nil

(and :free_wifi :hot_coffee)
; => :hot_coffee (returns the last truthy value)

(and :feelin_super_cool nil false)
; => nil (returns the first falsey value)

(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

failed-protagonist-names
; => ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(error-message :mild)
; => "OH GOD! IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"

; MAPS
(hash-map :a 1 :b 2)
; => {:a 1 :b 2}

(get {:a 0 :b 1} :b)
; => 1

(get {:a 0 :b {:c "ho hum"}} :b)
; => {:c "ho hum"}

(get {:a 0 :b 1} :c)
; => nil

(get {:a 0 :b 1} :c "unicorns?")
; => "unicorns?" ("unicorns?" here is the default value)

; the get-in function lets you look up values in nested maps:
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
; => "ho hum"

; Another way to look up a value in a map is to treat
; the map like a function with the key as its argument:
({:name "The Human Coffeepot"} :name)
; => "The Human Coffeepot"

(:a {:a 1 :b 2 :c 3})
; => 1

(get {:a 1 :b 2 :c 3} :a)
; => 1

(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows")
; => "No gnome knows homes like Noah knows" (this is the default value here)


; VECTORS
(get [3 2 1] 0)
; => 3

(get ["a" {:name "Pugsley Winterbottom"} "c"] 1)
; => {:name "Pugsley Winterbottom"}

(vector "creepy" "full" "moon")
; => ["creepy" "full" "moon"]

(conj [1 2 3] 4)
; => [1 2 3 4]

; LISTS

; creating lists:
'(1 2 3 4)
; => (1 2 3 4)

(list 1 "two" {3 4})
; => (1 "two" {3 4})

; retrieving elements from lists:
(nth '(:a :b :c) 0)
; => :a

(nth '(:a :b :c) 2)
; => :c

; elements are added to beginning of list:
(conj '(1 2 3) 4)
; => (4 1 2 3)


; SETS (collections of unique values)

; literal notation for a hash set:
#{"kurt vonnegut" 20 :icicle}

;using hash-test to create a set:
(hash-set 1 1 2 2)
; => #{1 2}

(conj #{:a :b} :b)
; => #{:a :b}

; turn vectors into hash-set:
(set [3 3 3 4 4])

(contains? #{:a :b} :a)
; => true

(contains? #{:a :b} 3)
; => false

(contains? #{nil} nil)
; => true

(:a #{:a :b})
; => :a

(get #{:a :b} :a)
; => :a

(get #{:a nil} nil)
; => nil

(get #{:a :b} "kurt vonnegut")
; => nil
; => #{3 4}


; CALLING FUNCTIONS
; (or + -) returns the first truthy value, i.e. +
((or + -) 1 2 3)
; => 6

; and returns the last truthy value:
((and (= 1 1) +) 1 2 3)
; => 6

((first [+ 0]) 1 2 3)
; => 6

(map inc [0 1 2 3])
; => (1 2 3 4) (btw map doesn't return a vector)

(+ (inc 199) (/ 100 (- 7 2)))
(+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
(+ 200 (/ 100 5)) ; evaluated (- 7 2)
(+ 200 20) ; evaluated (/ 100 5)
220 ; final evaluation

; FUNCTION DEFINITIONS & ARITY
(defn no-params
  []
  "I take no parameters!")
(defn one-param
  [x]
  (str "I take one parameter: " x))
(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
  "together to spite you! " x y))

(two-params "1" " & 2")
; "Two parameters! That's nothing! Pah! I will smoosh them together to spite you! 1 & 2"


;multi-arity
(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
     (str "I " chop-type " chop " name "! Take that!"))
  ([name]
     (x-chop name "karate")))

(x-chop "Kanye West" )
; => "I karate chop Kanye West! Take that!"

(x-chop "Kanye West" "slap")
; => "I slap chop Kanye West! Take that!"


; variable-arity functions contain a rest parameter (i.e. &),
; as in “put the rest of these arguments in a list with the following name”:
(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "Billy" "Anne-Marie" "The Incredible Bulk")
; => ("Get off my lawn, Billy!!!"
;      "Get off my lawn, Anne-Marie!!!"
;    "Get off my lawn, The Incredible Bulk!!!")

; mixing rest parameters with normal parameters:
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")
; => "Hi, Doreen, here are my favorite things: gum, shoes, kara-te"


;; DESTRUCTURING

;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])
; => Your first choice is: Marmalade
; => Your second choice is: Handsome Jack
; => We're ignoring the rest of your choices. Here they are in case \
;   you need to cry over them: Pigpen, Aquaman

;destructuring maps:

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 28.22
; => Treasure lng: 81.33

(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 28.22
; => Treasure lng: 81.33

; FUNCTION BODY

;Clojure automatically returns the last form evaluated:
(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")

(illustrative-function)
; => "joe"

(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! What a big number!"
    "That number's OK, I guess"))

(number-comment 5)
; => "That number's OK, I guess"

(number-comment 7)
; => "Oh my gosh! What a big number!"
