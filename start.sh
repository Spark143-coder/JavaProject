#!/bin/bash

if [ "$1" = "java" ]; then
    shift
    java -cp /app MyInfArith "$@"
else
    python3 ./my_exe "$@"
fi
