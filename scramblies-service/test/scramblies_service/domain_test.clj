(ns scramblies-service.domain-test
  (:require
   [scramblies-service.domain :as SUT]
   [clojure.test :refer [deftest is testing]]))

(deftest test-example
  (is (= true (SUT/scramble? "rekqodlw" "world")))
  (is (= true (SUT/scramble? "cedewaraaossoqqyt" "codewars")))
  (is (= false (SUT/scramble? "katas" "steak"))))

(deftest test-edge-cases
  (testing "scramble of empty str can be rearranged to an empty str"
    (is (= true (SUT/scramble? "" ""))))
  (testing "scramble of non-empty str can be rearranged to an empty str"
    (is (= true (SUT/scramble? "world" ""))))
  (testing "test when word uses same letters"
    (is (= false (SUT/scramble? "a" "aaa")))
    (is (= false (SUT/scramble? "abc" "aaa")))))
