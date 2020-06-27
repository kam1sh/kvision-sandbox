#!/bin/sh
set -eo pipefail
npx gulp build
cp dist/semantic.min.css dist/semantic.min.js ../src/main/resources/

