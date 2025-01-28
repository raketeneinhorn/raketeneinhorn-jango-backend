#!/usr/bin/env bash

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

cd ~/Developer/repos/git/raketeneinhorn/raketeneinhorn-infrastack || exit
docker compose --env-file ${SCRIPT_DIR}/local.env config --environment | grep INFRASTACK
docker compose --env-file ${SCRIPT_DIR}/local.env up