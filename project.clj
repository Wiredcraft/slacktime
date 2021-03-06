(defproject time-plan "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0-alpha-11"]
                 [org.clojure/clojurescript "1.9.225"]
                 [org.clojure/core.async "0.2.374"]
                 [devcards "0.2.1-5"]
                 [sablono "0.5.3"]
                 [org.omcljs/om "1.0.0-alpha41"]
                 [mount "0.1.8"]
                 [com.cognitect/transit-cljs "0.8.237"]
                 [com.cognitect/transit-clj "0.8.285"]
                 [datascript "0.15.2"]
                 [org.clojure/core.logic "0.8.10"]
                 [figwheel-sidecar "0.5.0-4"]
                 [com.andrewmcveigh/cljs-time "0.4.0"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [bidi "1.25.0"]
                 [com.cemerick/friend "0.2.1" :exclusions [org.apache.httpcomponents/httpclient]]
                 [friend-oauth2 "0.1.3" :exclusions [org.apache.httpcomponents/httpclient]]
                 [environ "1.0.1"]
                 [org.clojure/core.cache "0.6.3"]
                 [org.clojure/core.memoize "0.5.8" :exclusions [org.clojure/core.cache]]
                 [compojure "1.4.0"]
                 [postgresql "9.3-1102.jdbc41"]
                 [datomic-schema "1.3.0"]
                 [clj-http "2.0.1"]
                 #_[com.datomic/datomic-pro "0.9.5344"]
                 ]
  :main clj.time-plan.core
  :min-lein-version "2.0.0"
  :plugins [[lein-cljsbuild "1.1.2" :exclusions [org.clojure/clojure]]
            [lein-environ "1.0.1"]]
  :uberjar
  {:source-paths ^:replace ["src/clj/time_plan"]
   :hooks        [leiningen.cljsbuild]
   :omit-source  true
   :aot          :all
   :cljsbuild    {:builds
                  {:app
                   {:source-paths ^:replace ["src/cljs/time_plan"]
                    :compiler
                                  {:optimizations :advanced
                                   :pretty-print  false}}}}}

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]

  :source-paths ["src", "script"]

  :cljsbuild
  {:builds [{:id           "dev"
             :source-paths ["src"]
             :figwheel     true
             :compiler     {:main                 "time-plan.cljs.core"
                            :asset-path           "js/compiled/out"
                            :output-to            "resources/public/js/compiled/time_plan.js"
                            :output-dir           "resources/public/js/compiled/out"
                            :source-map-timestamp true}}
            {:id           "devcards"
             :source-paths ["src"]
             :figwheel     {:devcards true}                 ;; <- note this
             :compiler     {:main                 "time-plan.devcards.core"
                            :asset-path           "js/compiled/devcards_out"
                            :output-to            "resources/public/js/compiled/time_plan_devcards.js"
                            :output-dir           "resources/public/js/compiled/devcards_out"
                            :source-map-timestamp true}}
            {:id           "prod"
             :source-paths ["src"]
             :compiler     {:main          "time-plan.cljs.core"
                            :asset-path    "js/compiled/out"
                            :output-to     "resources/public/js/compiled/time_plan.js"
                            :optimizations :advanced}}]}


  :figwheel {:css-dirs ["resources/public/css"] :open-file-command "open-in-intellij"})
