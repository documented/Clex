#!/bin/bash

find src/docs -name '*.markdown' | while read FILENAME ; do 
    name=$(basename -s '.markdown' $FILENAME)
    outputname="src/public/docs/${name}.html"
    markdown $FILENAME -f $outputname -x codehilite
    echo "Processing ${name}..."
done

echo "Running 'lein deps'"
lein deps

echo "Running 'lein compile'"
lein compile

echo "Running 'lein uberjar'"
lein uberjar

# Remove cljex.jar and rename to standalone since it includes
# our clojure libraries and will be best suited for this prj
rm cljex.jar
mv cljex-standalone.jar cljex.jar

#End 