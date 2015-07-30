(ns com.palletops.aws-instance-types.core-test
  (:require [com.palletops.aws-instance-types.core :refer :all]
            [clojure.test :refer :all]))

(deftest data-is-valid
  (is (validate-data (load-data))))
