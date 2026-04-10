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
  (tc/dataset (str "data/fpp3/" name ".csv") {:key-fn keyword}))

;; ## 2.2 — Time Plots (Figure 2.2)
;;
;; The a10 dataset: monthly antidiabetic drug sales in Australia.
;; R: autoplot(a10, Cost)

;; Load PBS and derive a10 (antidiabetic drugs, code A10)
(def PBS (load-fpp3 "PBS"))

PBS

(def a10
  (-> PBS
      (tc/select-rows #(= "A10" (% :ATC2)))
      (tc/select-columns [:Month :Concession :Type :Cost])
      (tc/group-by [:Month])
      (tc/sum [:Cost])
      #_(tc/add-column "Cost" #(dfn// (% "TotalC") 1e6))))

a10

(-> a10
    :Month)


(-> a10
    (tc/add-column :Month
                    #(-> % :Month tech.v3.datatype.packing/unpack))
    (sk/lay-line :Month :summary)
    (sk/options {:title "Australian antidiabetic drug sales"
                 :width 600
                 :height 400})
    #_(sk/plan))

;; TODO: Add more figures here...
