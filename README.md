# aws-instance-types

Static data about [AWS Instance Types][aws-instance-types].

The data is maintained in an EDN file.  JSON and YAML are generated
from this file.

If you want dynamic pricing data, there are several libraries
available, such as [amazon-pricing][amazon-pricing].

## Usage

To use the EDN data, add a dependency on
`[com.palletops/aws-instance-types "0.1.0"]`, which will use the
[published jar][data-edn] which is on [clojars][clojars].  The data is available
as a resource at `com/palletops/aws=instance-types.edn`, and can be loaded with:

```clj
(-> "com/palletops/aws-instance-types.edn"
    io/resource
    io/reader
    load-reader)
```

To use the JSON data, install the [aws-instance-types][data-npm]
package with [npm][npm], e.g. `npm install aws-instance-types`.  The
data is returned by the package's `instanceTypes` function.

```js
var awsInstanceTypes = require('aws-instance-types');

var output = awsInstanceTypes.instanceTypes();
console.log(output);
```

## License

Copyright © 2015 Hugo Duncan

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.


[aws-instance-types]: http://aws.amazon.com/ec2/instance-types/#instance-type-matrix "AWS Instance types"
[amazon-pricing]: https://github.com/CloudHealth/amazon-pricing "Amazon pricing GEM from CloudHealth"
[clojars]: https://clojars.org/ "Clojars"
[data-edn]: https://clojars.org/com.palletops/aws-instance-types "aws-instance-types on clojars"
[data-npm]: https://www.npmjs.com/package/aws-instance-types "aws-instance-types on npm" "aws-instance-types on npm"
[npm]: https://www.npmjs.com/ "npm"
