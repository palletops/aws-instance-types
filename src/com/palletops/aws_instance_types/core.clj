(ns com.palletops.aws-instance-types.core
  (:require
   [cheshire.core :as json]
   [clj-yaml.core :as yaml]
   [clojure.java.io :as io]
   [schema.core :as schema]))

(def Disk
  "A schema for disks"
  {(schema/optional-key :count) schema/Int
   :size schema/Num
   (schema/optional-key :type) (schema/enum :ssd :hdd)})

(def Io
  "A schema for IO rating"
  (schema/enum :low :moderate :high :very-high :10-gigabit))

(def Type
  "A schema for an instance type"
  {:cpus schema/Num
   :ram schema/Num
   :disks [Disk]
   :io (schema/either Io
                      [(schema/one Io "lower bound")
                       (schema/one Io "Upper bound")])
   (schema/optional-key :processor) #{schema/Keyword}
   (schema/optional-key :clock)
   (schema/either
    schema/Num
    [(schema/one (schema/eq :_) "Undefined lower bound")
     (schema/one schema/Num "Upper bound")])
   :features #{(schema/enum :avx :avx2 :intel-turbo :ebs-opt :enh-network)}
   (schema/optional-key :32-bit) schema/Bool
   })

(def Data
  "A schema for the data map."
  {schema/Keyword Type})

(defn load-data
  "Load the data file."
  []
  (-> "com/palletops/aws-instance-types.edn"
      io/resource
      io/file
      .getCanonicalPath
      load-file))

(defn validate-data
  "Validate the EDN data."
  [data]
  (schema/validate Data data))

(defn validate
  "Validate the EDN data from the resource file."
  []
  (validate-data (load-data)))

(defn to-json
  "Return a JSON string for the given data."
  [data]
  (json/generate-string data {:pretty true}))

;; (to-json (load-data))

(defn to-yaml
  "Return a YAML string for the given data."
  [data]
  (yaml/generate-string data))

;; (to-yaml (load-data))

(defn generate-json
  "Generate JSON file from the EDN file."
  []
  (spit "target/aws-instance-types.json" (to-json (load-data))))

(defn generate-yaml
  "Generate YAML file from the EDN file."
  []
  (spit "target/aws-instance-types.yaml" (to-yaml (load-data))))

(defn generate-npm
  "Generate NPM file from the EDN file."
  []
  (spit "target/aws-instance-types.js"
        (format
         "exports.instanceTypes = function() {
  return %s;
}"
         (to-json (load-data)))))
