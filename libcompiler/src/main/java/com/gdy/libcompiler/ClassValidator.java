package com.gdy.libcompiler;


import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * Created by gongdongyang
 * on 2019/12/14
 */
public class ClassValidator {

    public static boolean isPrivate(Element element){
        return  element.getModifiers().contains(Modifier.PRIVATE);
    }

    public static boolean isAbstract(Element element){
        return  element.getModifiers().contains(Modifier.ABSTRACT);
    }

    public static String getClassName(TypeElement typeElement, String packageName) {
        int packageLen = packageName.length() + 1;
        return typeElement.getQualifiedName().toString().substring(packageLen)
                .replace('.', '$');

    }
}
