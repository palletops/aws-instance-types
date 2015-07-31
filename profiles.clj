{:dev {:source-paths ["src"],
       :plugins [[lein-pallet-release "RELEASE"]]}
 :test {:source-paths ["src"]
        :dependencies [[org.clojure/clojure "1.6.0"]
                       [prismatic/schema "0.4.3"]
                       [cheshire "5.5.0"]
                       [circleci/clj-yaml "0.5.3"]]}}
