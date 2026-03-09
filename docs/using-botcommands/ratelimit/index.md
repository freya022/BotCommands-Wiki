# Rate limiting

Rate limits lets you reject interactions when they are used too often in a time span.

This uses [Bucket4J](https://github.com/bucket4j/bucket4j), here's a quick breakdown:

- A "bucket" contains how many requests are remaining
- A bucket is composed of one or more "limits", if one is exhausted, the request is rejected
- A limit describes how many requests are allowed (tokens), and define how fast, and in which way, the tokens are regenerated

!!! abstract "For more details, check out the [docs of Bucket4J](https://bucket4j.com/8.14.0/toc.html)"

Next, look at how to [declare rate limiters](declaring.md), or, declare them [with annotations](usage-in-commands.md#annotated-commands).
