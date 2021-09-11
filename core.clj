(ns stefan-1.core
  (:gen-class))

(defn balanced? 
  "Parenthesis balance checker."
  {:test #(do
            (assert (true? (balanced? "()")))
            (assert (true? (balanced? "()()")))
            (assert (true? (balanced? "(()(()))")))
            (assert (true? (balanced? "(h(e)j(h(o)p)p)")))
            (assert (false? (balanced? "(he)j)h(opp")))
            (assert (false? (balanced? "())("))))}
    [s]
    (->> s
        ;; remove non-paranthesis characters
        (filter #{\( \)})

        ;; reduce down to an empty or non-empty vector
        (reduce
            (fn [stack item]
                (cond
                    ;; checks if item is left paranthesis, if so put in stack.
                    (#{ \( } item) (conj stack item)
                    ;; if the last char in stack is \( and if the item is \) the map will return \( and check if \( is last on stack - if so it will pop - reducing a pair
                    (and (#{ \( } (last stack))
                         (= ({ \) \( } item) (last stack)))
                    (pop stack)
                    ;; if above conditions return false, then we add it to stack since it might be the beginning of a new pair.
                    :else (conj stack item)))
        [])

        ;; return whether we have any unbalanced brackets
        empty?))

(defn -main
  "Main function"
  [& args]
  (if (empty? args) (test #'balanced?))
  (balanced? args))
