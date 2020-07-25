# Quick Start

## A -> B -> C
```shell script
# compile
$ cd jboss-demo/modules-export
$ mvn clean install

# unzip
$ cd jboss-demo/modules-export/modules-export-assembly/target
$ unzip modules-export-assembly-dist.zip

# run
$ java -jar jboss-modules-1.5.2.Final.jar -mp modules A
```

## Verification

If modify modules/system/layers/base/B/main/module.xml, change export to false

```xml
<module name="C" export="false" />
```

Then invoke Module C in Module A:

```markdown
new Cast().cast();
```
Error will occur in Module A:
Exception in thread "main" java.lang.NoClassDefFoundError: com/example/modules/export/c/Cast
        at com.examples.modules.export.a.Afirm.afirm(Afirm.java:16)
        at com.examples.modules.export.a.Afirm.main(Afirm.java:23)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
        at java.lang.reflect.Method.invoke(Unknown Source)
        at org.jboss.modules.Module.run(Module.java:330)
        at org.jboss.modules.Main.main(Main.java:505)
Caused by: java.lang.ClassNotFoundException: com.example.modules.export.c.Cast from [Module "A:main" from local module loader @3c679bde (finder: local module finder @16b4a017 (roots: \home\Code\Java\jboss-demo\modules-export\modules-export-assembly\target\modules-export\modules))]
        at org.jboss.modules.ModuleClassLoader.findClass(ModuleClassLoader.java:198)
        at org.jboss.modules.ConcurrentClassLoader.performLoadClassUnchecked(ConcurrentClassLoader.java:363)
        at org.jboss.modules.ConcurrentClassLoader.performLoadClass(ConcurrentClassLoader.java:351)
        at org.jboss.modules.ConcurrentClassLoader.loadClass(ConcurrentClassLoader.java:93)
        ... 8 more

* Where ever export true or false, that doesn't affect using C in B.

## D -> A -> B -> C

1.use jboss-service.xml define JMX 
```xml
<mbean code="com.example.modules.export.d.Direct" name="jboss-demo.modules-export:service=Direct">
</mbean>
```
2.use jboss-deployment-structure.xml define module
```xml
<deployment>
    <dependencies>
        <module name="A"/>
    </dependencies>
</deployment>
```
