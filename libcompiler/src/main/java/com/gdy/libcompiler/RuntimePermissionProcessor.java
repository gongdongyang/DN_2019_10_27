package com.gdy.libcompiler;

import com.gdy.libannotation.PermissionDenied;
import com.gdy.libannotation.PermissionGrant;
import com.gdy.libannotation.PermissionRational;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;


/**
 * Created by gongdongyang
 * on 2019/12/14
 */
@AutoService(Process.class)
public class RuntimePermissionProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Messager messager;
    private  Filer filer;
    private HashMap<String, MethodInfo> methodMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        messager = processingEnv.getMessager();
         filer = processingEnv.getFiler();
    }



    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        methodMap.clear();
        messager.printMessage(Diagnostic.Kind.NOTE,"process star...");
        if(!handlerAnnotationInfo(roundEnv,PermissionGrant.class)){
            return  false;
        }

        if(!handlerAnnotationInfo(roundEnv,PermissionDenied.class)){
            return  false;
        }

        if(! handlerAnnotationInfo(roundEnv,PermissionRational.class)){
            return  false;
        }

        for (String name : methodMap.keySet()) {
            MethodInfo methodInfo = methodMap.get(name);
            try {
                //error(methodInfo.getTypeElement(), methodInfo.getProxyClassFullName());
                JavaFileObject sourceFile = filer.createSourceFile(methodInfo.packageName+"."+ methodInfo.fileName);
                Writer writer = sourceFile.openWriter();
                writer.write(methodInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {

            }
        }

        return false;
    }

    private boolean handlerAnnotationInfo(RoundEnvironment roundEnv, Class<? extends Annotation> annotation) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
        for(Element element:elements){
            if(checkMethodValidator(element,annotation)){
                return  false;
            }

            ExecutableElement methodElement = (ExecutableElement)element;

            TypeElement enclosingElements = (TypeElement) methodElement.getEnclosingElement();

            String className = enclosingElements.getQualifiedName().toString();

            MethodInfo methodInfo = methodMap.get(className);
            if(methodInfo==null){
                methodInfo = new MethodInfo(elementUtils,enclosingElements);
                methodMap.put(className,methodInfo);
            }

            Annotation annotationClass = methodElement.getAnnotation(annotation);

            String menthodName = methodElement.getSimpleName().toString();

            List<? extends VariableElement> parameters = methodElement.getParameters();

            if(parameters==null || parameters.size()<1){
                String message = "the method %s marked by annaotation %s must have an unique parmater [String []] parameters";
                throw new IllegalArgumentException(String.format(message, menthodName,annotationClass.getClass().getSimpleName()));
            }

            if(annotationClass instanceof  PermissionGrant){

                int requestCode = ((PermissionGrant) annotationClass).value();
                methodInfo.grantMethodMap.put(requestCode,menthodName);
            }

            if(annotationClass instanceof  PermissionDenied){
                int requestCode = ((PermissionDenied) annotationClass).value();
                methodInfo.deniedMethodMap.put(requestCode,menthodName);
            }

            if(annotationClass instanceof  PermissionRational){
                int requestCode = ((PermissionRational) annotationClass).value();
                methodInfo.rationalMethodMap.put(requestCode,menthodName);
            }
        }
        return true;
    }

    private boolean checkMethodValidator(Element element, Class<? extends Annotation> annotation) {
        if(element.getKind()!= ElementKind.METHOD){
            return false;
        }

        if(ClassValidator.isPrivate(element)|| ClassValidator.isAbstract(element)){
            return false;
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportList = new HashSet<String>();
        supportList.add(PermissionDenied.class.getCanonicalName());
        supportList.add(PermissionGrant.class.getCanonicalName());
        supportList.add(PermissionRational.class.getCanonicalName());
        return supportList;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
