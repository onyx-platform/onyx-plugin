(ns leiningen.new.onyx-plugin
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "onyx-plugin"))

(defn onyx-plugin
  "A Leiningen template for Onyx plugins."
  [name medium]
  (let [data {:name name
              :medium medium
              :clojure-version "1.6.0"
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' onyx-plugin project.")
    (->files data
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["src/onyx/plugin/{{medium}}.clj" (render "medium.clj" data)]
             ["test/onyx/plugin/{{medium}}_test.clj" (render "medium_test.clj" data)])))
