### accessor ###

>  *clojure.core/accessor*
>  `([s key])`
>  Returns a fn that, given an instance of a structmap with the basis,
>  returns the value at the key.  The key must be in the basis. The
>  returned function should be (slightly) more efficient than using
>  get, but such use of accessors should be limited to known
>  performance-critical areas.

    #!clojure
    (defstruct machine :id :description)
    ;=> #'user/machine
    (def m (struct machine "rhickey" "the inventor of Clojure"))
    ;=> #'user/m
    (:id m)
    ;=> rhickey
    (def machine-id (accessor machine :id)) ; bind accessor
    ;=> #'user/machine-id
    (machine-id m)
    ;=> "rhickey"
