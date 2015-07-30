SRC = resources/com/palletops/aws-instance-types.edn
TARGETS = target/aws-instance-types.yaml target/aws-instance-types.json target/aws-instance-types.js
.phony: validate, all

all: ${TARGETS}

validate:
	lein test

target/aws-instance-types.yaml: validate ${SRC}
	lein with-profile +test run -m com.palletops.aws-instance-types.core/generate-yaml

target/aws-instance-types.json: validate ${SRC}
	lein with-profile +test run -m com.palletops.aws-instance-types.core/generate-json

target/aws-instance-types.js: validate ${SRC}
	lein with-profile +test run -m com.palletops.aws-instance-types.core/generate-npm
