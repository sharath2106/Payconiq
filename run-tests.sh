#!/bin/sh
set -x

export USER="admin"
export PASSWORD="password123"

mvn spotless:apply && mvn clean test