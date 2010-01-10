# cljex #

This project is all about providing examples for the existing API
documentation that can be found via your REPL `(doc name)`, or via
[clojure's API documentation](http://clojure.org/api "clojure.org's API documentation").

## Read 'Em! ##

If you're interested in reading the examples locally, simply: 

> `git clone git://github.com/defn/cljex.git`

> `cd cljex/`

> `./build.sh`

and from the command line run:

> `java -jar cljex.jar`

This will create up-to-date documentation files for you.  Then all you have left to do is run `java -jar cljex.jar --server` and point your browser at [localhost:8080](http://localhost:8080).  You're off to the races.

If you'd rather not go to the trouble, the current progress of the
project can always be viewed at [getclojure.org](http://getclojure.org/)

## Contributions Very Welcome ##

If you're planning on contributing to the code being used for the
project itself, currently the only requirements for building are:

* [Leiningen](http://github.com/technomancy/leiningen/)
* [Pygments](http://pygments.org/)
   * If you have python < 2.5.x
      * `easy_install Pygments==1.1.1`
      * `easy_install ElementTree`
      * `easy_install Markdown`

If you'd like to contribute documentation, it's very simple.  Under
the `src/` directory you'll find a `docs/examples/` directory which contains
markdown files.  Note that you must name your own example files with a leading `_`.  For instance: `_->>`.  In this case creating the file would mean doing something like this (to escape special characters): `touch _\-\>\>`

From there you'll find a directory structure with the examples in the
following format:

    cljex/
       \-> src/
            \-> docs/
                 \-> examples
                       \-> _->>
                       |-> _accessor
                       |-> _and
                       |-> ...

To contribute an example, simply fork this project on `github`
(where else?), observe the proper **formatting** for examples, and get
down to business.

## Formatting ##

The formatting of these markdown documents is very
straight-forward.  An example file looks like this:

Note that you can use `#!clojure` to show fancy line numbers.  Or you can use
`:::clojure` for no line numbers.

    #### Example A ####
        #!clojure
        (take 5 (range 0 10))
        ; => (0 1 2 3 4)
    
    #### Example B ####
        :::clojure
        (take 5 (range 0 10))
        ; => (0 1 2 3 4)

See the `_accessor` example for a good template.

For many of Clojure's forms there are multiple examples one could
give to flesh out the possibilities and possible use-cases for a
particular form.  The above example documentation is rather sparse,
feel free to embellish where necessary. Other than that, feel free to
experiment, make suggestions, etc. 

Finally, to see a current online version of the examples submitted
to-date you can check out [getclojure.org](http://getclojure.org/)

## Building ##

`./build.sh`

## Running ##
To build the latest docs:
>    java -jar cljex.jar

To start up the server:
>    java -jar cljex.jar --server

To do both:

`./start-server.sh`
