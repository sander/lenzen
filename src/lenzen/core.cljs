(ns lenzen.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {:sets [{:id 2
                              :days [{:y 2014 :m 8 :d 5}
                                     {:y 2014 :m 8 :d 6}]
                              :active true}
                             {:id 1
                              :days [{:y 2014 :m 5 :d 16}]
                              :active false}]
                      :selected-id [2]}))

(defn selected?
  [set state]
  (= ((:selected-id state) 0) (:id set)))

(defn day-view
  [{:keys [day]} _]
  (om/component
    (dom/li nil (str (:y day) "-" (:m day) "-" (:d day)))))

(defn set-view
  [{[sel] :selected-id {:keys [id active]} :set :as data} _]
  (om/component
    (dom/li #js {:className (str "set"
                                 (if (= sel id) " selected")
                                 (if active " active"))
                 :onClick #(om/update! (:selected-id data) [0] id)}
           (str "set"))))

(defn set-display
  [{{days :days} :set :as data} _]
  (om/component
    (dom/div nil
             (apply dom/ol nil
                    (conj (mapv #(om/build day-view {:day %}) days)
                          (dom/li nil
                                  (dom/button nil "Add today")))))))

(defn set-list
  [{:keys [sets selected-id]} _]
  (om/component
    (dom/div #js {:className "sets"}
             (apply dom/ol nil
                    (mapv #(om/build set-view {:set %
                                               :selected-id selected-id}) sets))
             (dom/button #js {:onClick
                              (fn [_] (om/transact! sets #( conj % {:id 42 :days [] :active true})))
                              } "New set"))))

; use (map-indexed (fn [idx itm] [idx itm]) "foobar") and leave out ids

(defn app
  [{:keys [sets selected-id] :as data} _]
  (om/component
    (let [set (first (filter #(= (:id %) (selected-id 0)) sets))]
      (dom/div nil
               (om/build set-list data)
               (om/build set-display {:set set})))))

(om/root app app-state {:target js/app})
