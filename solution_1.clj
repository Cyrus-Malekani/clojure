(ns stefan-1.core
  (:gen-class))

(defn balanced?
  "Parenthesis balance checker."
  {:test #(do
            (assert (true? (balanced? "(h(e)j(h(o)p)p)")))
            (assert (false? (balanced? "(he)j)h(opp"))))}
  ([expr] (balanced? (clojure.string/split expr #"") 0))
  ([[x & xs] count]
    (cond (neg? count) false
          (nil? x) (zero? count)
          (= x "(") (recur xs (inc count))
          (= x ")") (recur xs (dec count))
          :else (recur xs count))))


(defn -main
  "Main function"
  [& args]
  (test #'balanced?)
  (balanced? "(((())))()"))
