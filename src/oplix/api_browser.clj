;; %! Copyright (C) 2011 Kei Suzuki  All rights reserved. !%
;; 
;; This file is part of Openar, a Clojure environment ("This Software").
;; 
;; The use and distribution terms for this software are covered by the Eclipse
;; Public License version 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the COPYING at the root of this distribution.
;; By using this software in any fashion, you are agreeing to be bound by the
;; terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns ^{:oplix true}
  oplix.api-browser
  (:use [openar event log oplix]
        [oplix.api-browser java ui]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn opening
  [event]
  (when-not (:keyword (oplix-args))
    (create-event-response
     :openar.event/response-donot-open
     :missing-api-symbol)))

(defn frame-created
  [event]
  (add-ui))

(defn opened
  [event]
  (let [api-sym (:keyword (oplix-args))]
    (invoke-later #(if (browse-java-api? (str api-sym))
                     (close-oplix (oplix-name))
                     (set-oplix-visible)))))

(defn saving
  [event]
  (event-response-donot-save))
