(ns aws.cognito-test
  (:require [aws.cognito :as cognito]
            [cljs.test :refer-macros [deftest is testing]]))

(deftest simple
  (testing "is ok"
    (is (= 1 1))))
