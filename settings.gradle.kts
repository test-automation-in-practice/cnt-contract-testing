rootProject.name = "cnt-contract-testing"

include("pact:http:consumer-one")
include("pact:http:consumer-two")
include("pact:http:provider")

include("pact:messaging:consumer")
include("pact:messaging:provider")

include("spring-cloud-contract:http:consumer-one")
include("spring-cloud-contract:http:consumer-two")
include("spring-cloud-contract:http:provider")

include("spring-cloud-contract:messaging:consumer")
include("spring-cloud-contract:messaging:provider")
