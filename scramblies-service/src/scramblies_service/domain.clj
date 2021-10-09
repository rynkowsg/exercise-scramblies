(ns scramblies-service.domain
  (:require
   [clojure.set :as set]))

(defn scramble? [text word]
  (let [text-items (set text)
        word-items (set word)
        common     (set/intersection text-items word-items)]
    (= common word-items)))

(comment
 (scramble? "abcd" "abc")
 (scramble? "ab" "abc")
 .)
