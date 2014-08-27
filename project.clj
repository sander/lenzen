(defproject lenzen "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [om "0.7.1"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [lein-garden "0.2.0"]]

  :source-paths ["src" "src-clj"]

  :cljsbuild {
    :builds [{:id "lenzen"
              :source-paths ["src"]
              :compiler {
                :output-to "lenzen.js"
                :output-dir "out"
                :optimizations :none
                :source-map true}}]}
  
  :garden {:builds [{:stylesheet lenzen.style/main
                     :compiler {:output-to "style.css"
                                :pretty-print? true}}]})
