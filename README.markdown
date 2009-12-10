# cljex #

This project is all about providing examples for the existing API
documentation that can be found via your REPL `(doc name)`, or via
[clojure's API documentation](http://clojure.org/api "clojure.org's API documentation").

## Read 'Em! ##

If you're interested in reading the examples locally, simply: 

> `git clone git://github.com/defn/cljex.git`

> `cd cljex/`

and from the command line run:

> `java -jar clojure-examples.jar`

This will spawn a webserver at [localhost:5885](http://localhost:5885).  Steer your
browser there, and you're off to the races.

If you'd rather not go to the trouble, the current progress of the
project can always be viewed at [getclojure.org](http://getclojure.org/)

## Contributions Very Welcome ##

If you're planning on contributing to the code being used for the
project itself, currently the only requirements for building are:

* [Clojure](http://clojure.org/)
* [Compojure](http://github.com/weavejester/compojure/)
* [Leiningen](http://github.com/technomancy/leiningen/)
* [Pygments](http://pygments.org/)
   * If you have python 2.6:
      * `easy_install pygments`
      * `easy_install ElementTree`
      * `easy_install Markdown`

If you'd like to contribute documentation, it's very simple.  Under
the `src/` directory you'll find a `docs/` directory which contains
markdown files.

From there you'll find a directory structure with the examples in the
following format:

    cljex/
       \-> src/
            \-> docs/
                 \-> a/
                 |    \-> accessor.markdown
                 |    |-> ...
                 |    |-> ...
                 |
                 \-> b/
                 |    \-> bases.markdown
                 |    |-> ...
                 |    |-> ...
                 |
                 \-> ...
                 \-> ...

To contribute an example, simply fork this project on `github`
(where else?), observe the proper **formatting** for examples, and get
down to business.

## Formatting ##

The formatting of these markdown documents is very
straight-forward.  An example file looks like this:

    ### take ###

    > *clojure.core/take*
    > `([n coll])`    
    > Returns a lazy sequence of the first n items in coll, or all items if
    > there are fewer than n. 

        #!clojure
        (take 5 (range 0 10))
        ;=> (0 1 2 3 4)

For many of Clojure's forms there are multiple examples one could
give to flesh out the possibilities and possible use-cases for a
particular form.  The above example documentation is rather sparse,
feel free to embellish where necessary. Other than that, feel free to
experiment, make suggestions, etc. 

Finally, to see a current online version of the examples submitted
to-date you can check out [getclojure.org](http://getclojure.org/)

## Building ##

Standard [leiningen](http://github.com/technomancy/leiningen/) stuff:
>    lein deps

>    lein compile

>    lein uberjar
