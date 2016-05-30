# JAXB Utils

Sometimes when you use JAXB it may fall if XML contains some bad XML 1.0 characters like `\u0019`.

So you can use the following code to make it work:

```java
//Marshal example
try (BadXmlCharacterFilterWriter writer = new BadXmlCharacterFilterWriter(file, encoding)) {
    JAXB.marshal(jaxbObject, writer);
}

//Unmarshal example
try (BadXmlCharactersFilterReader reader = new BadXmlCharactersFilterReader(file, encoding)) {
    return JAXB.unmarshal(reader, jaxbObjectClazz);
}
```