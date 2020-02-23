package com.gdy.libcompiler;

import java.util.HashMap;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by gongdongyang
 * on 2019/12/14
 */
public class MethodInfo {

    public String packageName;

    public String className;

    public String fileName;

    public static final String PROXY_NAME = "PermissionProxy";

    public HashMap<Integer,String> grantMethodMap = new HashMap<>();

    public HashMap<Integer,String> deniedMethodMap = new HashMap<>();

    public HashMap<Integer,String> rationalMethodMap = new HashMap<>();

    public MethodInfo(Elements elementUtils, TypeElement typeElement){
        PackageElement packageElement = elementUtils.getPackageOf(typeElement);
        packageName = packageElement.getQualifiedName().toString();
        className = ClassValidator.getClassName(typeElement,packageName);

        fileName = className +"$$" + PROXY_NAME;
    }

    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();

        builder.append("// generate code. do not modify \n");

        builder.append("package ").append(packageName).append(";\n\n");

        builder.append("import com.gdy.libpermissionhepler.*;\n");
        builder.append('\n');

        builder.append("public class ").append(fileName).append(" implements " + PROXY_NAME + "<" + className + ">");
        builder.append('\n');

        generateMethods(builder);

        builder.append('\n');

        builder.append("}\n");
        return builder.toString();
        
    }

    private void generateMethods(StringBuilder builder) {

        generateGrantMethod(builder);
        generateDeniedMethod(builder);
        generateRationaleMethod(builder);

    }

    private void generateRationaleMethod(StringBuilder builder) {
        builder.append("@Override\n ");
        builder.append("public boolean rationale(" + className + " source , int requestCode,String[] permissions, PermissionRationalCallback rationalCallback) {\n");
        builder.append("switch(requestCode) {");
        for (int code : rationalMethodMap.keySet()) {
            builder.append("case " + code + ":");
            builder.append("source." + rationalMethodMap.get(code) + "(permissions,rationalCallback);");
            builder.append("return true;");
        }
        builder.append("}");
        builder.append("return false;");
        builder.append("  }\n");

        ///

        builder.append("@Override\n ");
        builder.append("public boolean needShowRationale(int requestCode,String[] permission) {\n");
        builder.append("switch(requestCode) {");
        for (int code : rationalMethodMap.keySet()) {
            builder.append("case " + code + ":");
            builder.append("return true;");
        }
        builder.append("}\n");
        builder.append("return false;");
        builder.append("  }\n");
    }

    private void generateDeniedMethod(StringBuilder builder) {
        builder.append("@Override\n ");
        builder.append("public void denied(" + className + " source , int requestCode,String[] permissions) {\n");
        builder.append("switch(requestCode) {");
        for (int code : deniedMethodMap.keySet()) {
            builder.append("case " + code + ":");
            builder.append("source." + deniedMethodMap.get(code) + "(permissions);");
            builder.append("break;");
        }

        builder.append("}");
        builder.append("  }\n");
    }

    private void generateGrantMethod(StringBuilder builder) {
        builder.append("@Override\n ");
        builder.append("public void grant(" + className + " source , int requestCode,String[] permissions) {\n");
        builder.append("switch(requestCode) {");
        for (int code : grantMethodMap.keySet()) {
            builder.append("case " + code + ":");
            builder.append("source." + grantMethodMap.get(code) + "(permissions);");
            builder.append("break;");
        }

        builder.append("}");
        builder.append("  }\n");
    }

}
