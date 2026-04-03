;; # Chapter 2: Time Series Graphics (Napkinsketch version)
;; Translating fpp3 Chapter 2 examples from R to Clojure
;; using tablecloth, tablecloth.time, and **napkinsketch**.
;;
;; Reference: https://otexts.com/fpp3/graphics.html
;; Compare with: chapter_02_time_series_graphics.clj (tableplot version)

(ns chapter-02-napkinsketch
  (:require [tablecloth.api :as tc]
            [tablecloth.column.api :as tcc]
            [tech.v3.datatype :as dtype]
            [tech.v3.datatype.functional :as dfn]
            [scicloj.napkinsketch.api :as sk]
            [scicloj.kindly.v4.kind :as kind]
            [tablecloth.time.api :as tct])
  (:import [java.time LocalDate]))

;; ## Data Loading

(defn load-fpp3
  "Load one of the fpp3 datasets from CSV."
  [name]
  (tc/dataset (str "data/fpp3/" name ".csv")))

;; ## 2.2 — Time Plots (Figure 2.2)
;;
;; The a10 dataset: monthly antidiabetic drug sales in Australia.
;; R: autoplot(a10, Cost)

(def a10 (load-fpp3 "a10"))

;; Simple time plot — line chart with Month on x, Cost on y:

(-> a10
    (sk/lay-line :Month :Cost)
    (sk/options {:title "Australian antidiabetic drug sales"
                 :width 600
                 :height 400}))

;; TODO: Add more figures here...
