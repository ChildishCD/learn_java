package com.java.intro.annotation;

import java.lang.annotation.RetentionPolicy;

public class Demo {
    public static void main(String[] args) {
        //-->all element in clas file can add annotation above:package,class(type),method,field,parameter...
        //-->annotation can do
        //define interceptor,AOP... in frame
        //-->different annotation
        //1. for ide: @override...
        //2. for tools to process .class file: lombook(retention source file)
        //3. for programme (usually)
        //4. meta annotation in java.lang.annotation: @Target,@Retention,@Documented,@Inherited
        //  @Target: when custom an annotation, define what element can be annotation
        //  @Retention: when custom an annotation, define when use it(source file,class file,run time)

    }
}
