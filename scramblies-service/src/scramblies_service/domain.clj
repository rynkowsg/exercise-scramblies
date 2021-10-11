(ns scramblies-service.domain
  (:require
   [multiset.core :as multiset :refer [multiset]]))

(defn scramble? [text word]
  (let [text-items (apply multiset text)
        word-items (apply multiset word)]
    (multiset/subset? word-items text-items)))

(comment
 (scramble? "abcd" "abc")
 (scramble? "ab" "abc")
 (scramble? "ab" "aaa")
 .)
