(ns leiningen.new.onyx-plugin
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "onyx-plugin"))

(defn onyx-plugin
  "A Leiningen template for Onyx plugins."
  [name medium]
  (let [data {:name name
              :medium medium
              :clojure-version "1.7.0"
              :sanitized (name-to-path name)}
        sanitized-medium (name-to-path medium)]
    (main/info "Generating fresh 'lein new' onyx-plugin project.")
    (->files data
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render ".gitignore" data)]
             [(str "src/onyx/plugin/" sanitized-medium "_input.clj") (render "medium_input.clj" data)]
             [(str "test/onyx/plugin/" sanitized-medium "_input_test.clj") (render "medium_input_test.clj" data)]
             [(str "src/onyx/plugin/" sanitized-medium "_output.clj") (render "medium_output.clj" data)]
             [(str "test/onyx/plugin/" sanitized-medium "_output_test.clj") (render "medium_output_test.clj" data)])))
