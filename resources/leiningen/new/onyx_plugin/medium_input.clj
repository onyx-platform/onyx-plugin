(ns onyx.plugin.{{medium}}-input
  (:require [onyx.plugin.protocols :as p]))

;; Often you will need some data in your event map for use by the plugin
;; or other lifecycle functions. Try to place these in your builder function (pipeline)
;; first if possible.
(defn inject-into-eventmap
  [event lifecycle]
  {:{{medium}}/example-datasource (atom (list))})

(def reader-calls
  {:lifecycle/before-task-start inject-into-eventmap})

(defrecord ExampleInput [event example-datasource completed? checkpoint]
  p/Plugin
  (start [this event]
    ;; Initialize the plugin, generally by assoc'ing any initial state.
    this)

  (stop [this event]
    ;; Nothing is required here. However, most plugins have resources
    ;; (e.g. a connection) to clean up.
    ;; Mind that such cleanup is also achievable with lifecycles.
    this)

  p/Checkpointed
  (checkpoint [this]
    ;; Returns the checkpoint state that allows the plugin state to be recovered.
    ;; The value returned by this function will be passed to recover!.
    ;; Nothing is required here, but if your data source provides a checkpoint
    ;; mechanism, the checkpoint state should be returned here.
    @checkpoint)

  (recover! [this replica-version checkpoint]
    (reset! completed? false))

  ;; Nothing is required here. This is useful for plugins that need to do
  ;; something after a checkpoint is committed, e.g., acknowledging messages.
  (checkpointed! [this epoch])

  p/BarrierSynchronization
  (synced? [this epoch]
    ;; Nothing is required here. This is mostly unused for input plugins, but is
    ;; useful when there is work that needs to be done before Onyx moves to the
    ;; next barrier epoch.
    true)

  (completed? [this]
    @completed?)

  p/Input
  (poll! [this event timeout-ms]
    ;; Read a segment from your data-source
    ;; For data sources which enable read timeouts, please make sure to pass
    ;; timeout-ms into the read call.
    (if-let [segment (first @example-datasource)]
      ;; Check if all the messages have been consumed. If so, set the completed?
      ;; set the corresponding atom, which is then returned by completed?.
      (do
        (swap! example-datasource rest)
        segment)
      (do (reset! completed? true)
        nil))))

  ;; p/WatermarkedInput
  ;; (watermark [this]))
  ;;   Nothing is required here. This is useful when the input messages include
  ;;   a timestamp.

;; Builder function for your plugin. Instantiates a record.
;; It is highly recommended that you inject and pre-calculate frequently used data
;; from your task-map here, in order to improve the performance of your plugin
;; Extending the function below is likely good for most use cases.
(defn input [event]
  (map->ExampleInput {:event event
                      :example-datasource (:{{medium}}/example-datasource event)
                      :completed? (atom false)
                      :checkpoint (atom nil)}))
